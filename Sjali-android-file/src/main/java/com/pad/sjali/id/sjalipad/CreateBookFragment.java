package com.pad.sjali.id.sjalipad;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class CreateBookFragment extends Fragment {

    private EditText inputTitle, inputContent;
    private Button btnSubmit;
    private Session session;
    private ApiURL apiURL;
    private String strUrl;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_create_book, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        inputTitle = getView().findViewById(R.id.inputTitle);
        inputContent = getView().findViewById(R.id.inputContent);
        btnSubmit = getView().findViewById(R.id.btnSubmit);

        session = new Session(getContext());

        apiURL = new ApiURL();
        strUrl = apiURL.getUrl();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tryToSubmit();

            }
        });

    }

    void tryToSubmit(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = strUrl+"create.php";
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

                                Fragment fragment = new TimelineFragment();
                                loadFragment(fragment);

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
                params.put("email", session.getEmail());
                params.put("title", inputTitle.getText().toString());
                params.put("content", inputContent.getText().toString());

                return params;
            }
        };
        queue.add(postRequest);
    }

    void makeToast(String message){
        Context context = getContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}
