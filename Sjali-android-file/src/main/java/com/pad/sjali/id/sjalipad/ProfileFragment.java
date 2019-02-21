package com.pad.sjali.id.sjalipad;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class ProfileFragment extends Fragment {

    private Session session;
    private Context mContext;
    private ApiURL apiURL;
    private String strUrl;

    private RecyclerView recycler_view_profile;
    private TimelineAdapter adapter;
    private ArrayList<Book> bookArrayList;

    private SwipeRefreshLayout mySwipeRefreshLayout;

    private Toolbar mToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.activity_profile, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        session = new Session(getContext());
        apiURL = new ApiURL();
        bookArrayList = new ArrayList<>();

        recycler_view_profile = getView().findViewById(R.id.recycler_view_profile);
        mToolbar = getView().findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setTitle(session.getEmail());


        strUrl = apiURL.getUrl();


        recycler_view_profile.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, recycler_view_profile ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Intent intent = new Intent(view.getContext(), EditBookActivity.class);
                        Book book = bookArrayList.get(position);
                        String book_id = book.getBook_id();
                        intent.putExtra("book_id", book_id);
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        adapter = new TimelineAdapter(bookArrayList);

        // set a GridLayoutManager with default vertical orientation and 3 number of columns
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_view_profile.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recycler_view_profile.setAdapter(adapter);

        //coding untuk pulldown refresh
        mySwipeRefreshLayout = getView().findViewById(R.id.swiperefresh);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        getData();
                    }
                }
        );

        getData();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.profile_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout :
                doLogout();
                Log.i("item id ", item.getItemId() + "");
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //untuk logout kembali ke acivity login
    public void doLogout(){

        //menghapus session atau di set menjadi kosong
        session.setEmail("");
        session.setLogStatus("");

        //membuka activity login
        Intent intent = new Intent(getContext(),MainActivity.class);
        startActivity(intent);

        //finish agar saat di tekan back, tidak kembali ke activity timeline lagi
        getActivity().finish();
        makeToast("Logout Berhasil");
    }

    void makeToast(String message){
        Context context = getContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    void getData(){

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = strUrl+"profile.php?user_id="+session.getUserID();

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            Log.d("datahasil",response);
                            JSONObject respObjt = new JSONObject(response);
                            JSONObject profile = new JSONObject(respObjt.getString("profile"));
                            JSONArray dataArr = new JSONArray(respObjt.getString("data"));

                            addData(dataArr);


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
        );
        queue.add(postRequest);
    }


    void addData(JSONArray newBookArr){
        bookArrayList.clear();
        String id,title, name, updated_at;
        int likes;
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

        adapter.notifyDataSetChanged();
        mySwipeRefreshLayout.setRefreshing(false);

    }





}
