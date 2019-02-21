package com.pad.sjali.id.sjalipad;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Session session;
    private String logStatus;
    public BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //mendeklaraskan navigasi bawah
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        //mendapatkan data session
        session = new Session(getApplicationContext());
        logStatus = session.getLogStatus();


        //jika log status kosong akan dilempar ke login actvity
        if(logStatus.equals("")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else if(logStatus.equals("1")){
            Fragment fragment = new TimelineFragment();
            loadFragment(fragment);
        }
    }


    //listener untuk bottom navigation item
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new TimelineFragment();
                    break;
                case R.id.navigation_create:
                    fragment = new CreateBookFragment();
                    break;
                case R.id.navigation_profile:
                    fragment = new ProfileFragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };

    //function untuk membuka fragment
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}
