package com.pad.sjali.id.sjalipad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import com.pad.sjali.id.sjalipad.ApiURL;

public class ReadBookActivity extends AppCompatActivity {

    private ApiURL apiUrl;
    private String strUrl;
    private TextView txtTitle, txtContent, txtUser;
    private ProgressDialog progress;

    private String book_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        apiUrl = new ApiURL();
        strUrl = apiUrl.getUrl();

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtContent = (TextView) findViewById(R.id.txtContent);
        txtUser = (TextView) findViewById(R.id.txtUser);

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

                            txtTitle.setText(data.getString("title"));
                            txtContent.setText(data.getString("content"));
                            txtUser.setText("By "+data.getString("name"));

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

    void createLoading(){
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }


}
