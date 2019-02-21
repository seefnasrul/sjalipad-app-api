package com.pad.sjali.id.sjalipad.Utils;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.config.AppCommonsConfiguration;
import com.bentech.android.appcommons.validator.EditTextValidator;


/**
 * Created by Daniel on 7/29/2015.
 */
public final class EditTextUtils {


    public boolean isEmpty(EditText editText){
        if (editText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    public boolean isValidEmail(EditText editText) {

        String email = editText.getText().toString();

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPassword(EditText editText) {

        String pass = editText.getText().toString();

        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

}

