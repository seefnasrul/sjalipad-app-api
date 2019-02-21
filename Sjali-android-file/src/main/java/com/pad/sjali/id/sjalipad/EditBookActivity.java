package com.pad.sjali.id.sjalipad;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditBookActivity extends AppCompatActivity {


    private EditText inputTitle, inputContent;
    private Button btnUpdate, btnDelete;
    private ApiURL apiUrl;
    private String strUrl, book_id;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        apiUrl = new ApiURL();
        strUrl = apiUrl.getUrl();

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        inputTitle = (EditText) findViewById(R.id.inputTitle);
        inputContent = (EditText) findViewById(R.id.inputContent);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryUpdate();
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryDelete();
            }
        });

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        book_id = extras.getString("book_id");

        createLoading();
        getData();
    }

    void getData(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = strUrl+"read.php?book_id="+book_id;
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {

                            JSONObject respObjt = new JSONObject(response);
                            Log.d("datae",response);
                            JSONObject data = new JSONObject(respObjt.getString("data"));

                            inputTitle.setText(data.getString("title"));
                            inputContent.setText(data.getString("content"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        progress.dismiss();

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                        progress.dismiss();
                    }
                }
        );
        queue.add(postRequest);
    }

    void tryUpdate(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = strUrl+"update.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            JSONObject responseObj = new JSONObject(response);
                            String successStatus = responseObj.getString("success");
                            String message = responseObj.getString("message");
                            Log.d("datahasil",successStatus);
                            if(successStatus.equals("1")){

                                makeToast(message);

                            }else{

                                makeToast(message);

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
                params.put("book_id", book_id);
                params.put("title", inputTitle.getText().toString());
                params.put("content", inputContent.getText().toString());

                return params;
            }
        };
        queue.add(postRequest);
    }

    void tryDelete(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = strUrl+"delete.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            JSONObject responseObj = new JSONObject(response);
                            String successStatus = responseObj.getString("success");
                            String message = responseObj.getString("message");
                            Log.d("datahasil",successStatus);
                            if(successStatus.equals("1")){

                                finish();
                                makeToast(message);

                            }else{

                                makeToast(message);

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
                params.put("book_id", book_id);

                return params;
            }
        };
        queue.add(postRequest);

    }

    void createLoading(){
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }

    void makeToast(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
