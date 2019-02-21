package com.pad.sjali.id.sjalipad;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pad.sjali.id.sjalipad.Adapter.TimelineAdapter;
import com.pad.sjali.id.sjalipad.Model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class TimelineFragment extends Fragment {

    private Session session;
    private Context mContext;
    private ApiURL apiURL;
    private String strUrl;

    private RecyclerView recyclerView;
    private TimelineAdapter adapter;
    private ArrayList<Book> bookArrayList;

    private SwipeRefreshLayout mySwipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_timeline, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //super.onActivityCreated(savedInstanceState);
        //setContentView(R.layout.activity_timeline);

        super.onActivityCreated(savedInstanceState);

        recyclerView = getView().findViewById(R.id.recycler_view);
        mySwipeRefreshLayout =  getView().findViewById(R.id.swiperefresh);


        bookArrayList = new ArrayList<>();
        mContext = getContext();

        session = new Session(getContext());
        apiURL = new ApiURL();
        strUrl = apiURL.getUrl();

        getData();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        Intent intent = new Intent(getContext(), ReadBookActivity.class);
                        Book book = bookArrayList.get(position);
                        String book_id = book.getBook_id();
                        intent.putExtra("book_id", book_id);
                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        adapter = new TimelineAdapter(bookArrayList);

        // set a GridLayoutManager with default vertical orientation and 3 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setAdapter(adapter);

        //coding untuk pulldown refresh


        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        getData();
                    }
                }
        );
    }

    void addData(JSONArray newBookArr){

        //menghapus data arraylist sebelumnya
        bookArrayList.clear();

        String id,title, name, updated_at;
        int likes;

        //looping data baru ke arraylist barang
        for(int i = 0; i < newBookArr.length();i++){

            try {
                JSONObject rowObj = newBookArr.getJSONObject(i);

                id = rowObj.getString("id");
                title = rowObj.getString("title");
                name = rowObj.getString("name");
                updated_at = rowObj.getString("updated_at");
                likes = rowObj.getInt("likes");

                bookArrayList.add(new Book(
                        id, "",
                        title,"",
                        "",updated_at,
                        name,likes));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        //agar data baru muncul pada recyclerview
        adapter.notifyDataSetChanged();
        mySwipeRefreshLayout.setRefreshing(false);

    }

    void getData(){

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = strUrl+"timeline.php";
        Log.d("linke",url);
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Berhasil", response);

                        try {

                            JSONArray responseArr = new JSONArray(response);
                            addData(responseArr);

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
                        makeToast("Connection Error");
                    }
                }
        );
        queue.add(postRequest);
    }

    void makeToast(String message){
        Context context = getContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
