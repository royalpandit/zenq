package com.abhishek.zenq.Prefrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.abhishek.zenq.Bean.LoginBean;

public class AppPreferences {

    // App preference Name
    public static final String Prefsname = "Zeiq";
    public static final String KEY_ID = "id";
    public static final String KEY_EMAIL_ID = "email";
    public static final String KEY_PASSWORD="password";
    public static final String KEY_USER_TYPE="user_type";
    public static final String KEY_IS_ACTIVE="is_active";
    public static final String KEY_IS_KYC="is_kyc";
    public static final String KEY_ROLE="role";


    public static SharedPreferences.Editor editor;


    public static void SaveUserdetail(Context ctx, LoginBean login_model) {

        SharedPreferences prefs = ctx.getSharedPreferences(Prefsname,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_ID, login_model.getId());
        editor.putString(KEY_EMAIL_ID, login_model.getEmail());
        editor.putString(KEY_PASSWORD, login_model.getPassword());
        editor.putString(KEY_USER_TYPE, login_model.getUser_type());
        editor.putString(KEY_IS_ACTIVE, login_model.getIs_active());
        editor.putString(KEY_IS_KYC, login_model.getIs_kyc());
        editor.putString(KEY_ROLE, login_model.getRole());



        Log.d("nnnnn", login_model.getEmail() + login_model.getEmail() + login_model.getId() + "HHH" + login_model.getId());
        editor.commit();
    }


    public static LoginBean getSavedUser(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(Prefsname,
                Context.MODE_PRIVATE);
        LoginBean modal = new LoginBean();
        modal.setId(prefs.getString(KEY_ID, "-1"));
        modal.setEmail(prefs.getString(KEY_EMAIL_ID, "-1"));

        modal.setPassword(prefs.getString(KEY_PASSWORD, "-1"));

        modal.setUser_type(prefs.getString(KEY_USER_TYPE, "-1"));
        modal.setIs_active(prefs.getString(KEY_IS_ACTIVE, "-1"));
        modal.setIs_kyc(prefs.getString(KEY_IS_KYC, "-1"));
        modal.setRole(prefs.getString(KEY_ROLE, "-1"));


        return modal;

    }

    public static void clearPrefsdata(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(Prefsname,
                Context.MODE_PRIVATE);
        editor = prefs.edit();
        editor.clear().commit();

    }

}
