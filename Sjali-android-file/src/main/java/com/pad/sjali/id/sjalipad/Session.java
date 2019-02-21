package com.pad.sjali.id.sjalipad;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setLogStatus(String newstatus) {
        prefs.edit().putString("logStatus", newstatus).commit();
    }

    public String getLogStatus() {
        String logStatus = prefs.getString("logStatus","");
        return logStatus;
    }
    public void setEmail(String newEmail) {
        prefs.edit().putString("email", newEmail).commit();
    }

    public String getEmail() {
        String logStatus = prefs.getString("email","");
        return logStatus;
    }

    public void setUserID(String newUserID) {
        prefs.edit().putString("user_id", newUserID).commit();
    }

    public String getUserID() {
        String newUserID = prefs.getString("user_id","");
        return newUserID;
    }



}
