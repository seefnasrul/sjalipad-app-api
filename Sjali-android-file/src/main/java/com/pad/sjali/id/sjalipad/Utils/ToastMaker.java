package com.pad.sjali.id.sjalipad.Utils;

import android.content.Context;
import android.widget.Toast;

public class ToastMaker {
    public void makeToast(Context context, String message){
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
