package com.pad.sjali.id.sjalipad;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pad.sjali.id.sjalipad.Utils.DialogMaker;
import com.pad.sjali.id.sjalipad.Utils.EditTextUtils;
import com.pad.sjali.id.sjalipad.Utils.NoChangingBackgroundTextInputLayout;
import com.pad.sjali.id.sjalipad.Utils.ToastMaker;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import android.view.View;


public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView linkRegister;
    private EditText inputEmail, inputPass;
    private NoChangingBackgroundTextInputLayout inputLayEmail,inputLayPass;
    private Session session;
    private Context mContext;
    private Context loginActivity;

    private ApiURL apiURL;
    private String strUrl;

    private ToastMaker toastMaker;
    private DialogMaker dialogMaker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = getApplicationContext();
        loginActivity = this;

        session = new Session(mContext);

        toastMaker = new ToastMaker();
        dialogMaker = new DialogMaker();

        apiURL = new ApiURL();
        strUrl = apiURL.getUrl();

        inputEmail= findViewById(R.id.inputEmail);
        inputPass= findViewById(R.id.inputPass);
        btnLogin = findViewById(R.id.btnLogin);
        linkRegister = findViewById(R.id.linkRegister);

        inputLayEmail = findViewById(R.id.inputLayoutEmail);
        inputLayPass = findViewById(R.id.inputLayoutPass);



        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //listener untuk tombol login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(validateInput()){
                    tryLogin();
                }

            }
        });
    }

    void tryLogin(){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = strUrl+"login.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            JSONObject responseObj = new JSONObject(response);
                            String successStatus = responseObj.getString("success");
                            Log.d("datahasil",successStatus);
                            if(successStatus.equals("1")){
                                String user_id = responseObj.getString("user_id");
                                session.setLogStatus("1");
                                session.setEmail(inputEmail.getText().toString());
                                session.setUserID(user_id);
                                Intent intent = new Intent(mContext, MainActivity.class);
                                startActivity(intent);
                                finish();
                                toastMaker.makeToast(mContext,"Login Berhasil");
                            }else{
                                String message = responseObj.getString("message");
                                dialogMaker.createSimpleDialog(loginActivity,"Login Failed", message);
                                Log.d("bisaa",responseObj.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", inputEmail.getText().toString());
                params.put("password", inputPass.getText().toString());

                return params;
            }
        };
        queue.add(postRequest);
    }


    private boolean validateInput(){

        EditTextUtils editTextUtils = new EditTextUtils();
        inputLayEmail.setError(null);
        inputLayPass.setError(null);

        if(editTextUtils.isEmpty(inputEmail)){
            inputLayEmail.setError(inputEmail.getHint()+" Cannot Be Empty");
        }
        if(!editTextUtils.isValidEmail(inputEmail) && inputLayEmail.getError() == null){
            inputLayEmail.setError("Invalid Email Address");
        }

        if(editTextUtils.isEmpty(inputPass)){
            inputLayPass.setError(inputPass.getHint()+" Cannot Be Empty");
        }

        if(!editTextUtils.isValidPassword(inputPass) && inputLayPass.getError() == null){
            inputLayPass.setError("Invalid Password Format");
        }

        if(inputLayEmail.getError() != null || inputLayPass.getError() != null){
            return false;
        }
        return true;
    }



}
