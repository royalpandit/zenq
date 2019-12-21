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
    public static final String KEY_IS_KYC="is_kyc";
    public static final String KEY_ROLE="role";
    public static final String KEY_IS_ACTIVE="is_active";
    public static final String KEY_NAME="name";
    public static final String KEY_MOBILE="mobile";
    public static final String KEY_IS_MAIL="is_mail";
    public static final String KEY_BUISNESS_NAME="business_name";
    public static final String KEY_LAST_NAME="last_name";
    public static final String KEY_ADDRESS="address";
    public static final String KEY_LOCALITY="locatlity";
    public static final String KEY_LANDLINE="landline";
    public static final String KEY_COUNTARY="country";
    public static final String KEY_STATE="state";
    public static final String KEY_CITY="city";
    public static final String KEY_PINCODE="pincode";
    public static final String KEY_CREATE_DATE="create_date";
    public static final String KEY_IMAGE="image";
    public static final String KEY_IMAGE_PROOF="img_proof";
    public static final String KEY_PAN_CARD="pancard";






    public static SharedPreferences.Editor editor;


    public static void SaveUserdetail(Context ctx, LoginBean login_model) {

        SharedPreferences prefs = ctx.getSharedPreferences(Prefsname,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_ID, login_model.getId());
        editor.putString(KEY_EMAIL_ID, login_model.getEmail());
        editor.putString(KEY_PASSWORD, login_model.getPassword());
        editor.putString(KEY_USER_TYPE, login_model.getUser_type());
        editor.putString(KEY_IS_KYC, login_model.getIs_kyc());
        editor.putString(KEY_ROLE, login_model.getRole());
        editor.putString(KEY_IS_ACTIVE, login_model.getIs_active());
        editor.putString(KEY_NAME, login_model.getName());
        editor.putString(KEY_MOBILE, login_model.getMobile());
        editor.putString(KEY_IS_MAIL, login_model.getIs_mail());
        editor.putString(KEY_BUISNESS_NAME, login_model.getBusiness_name());
        editor.putString(KEY_LAST_NAME, login_model.getLast_name());
        editor.putString(KEY_ADDRESS, login_model.getAddress());
        editor.putString(KEY_LOCALITY, login_model.getLocatlity());
        editor.putString(KEY_LANDLINE, login_model.getLandline());
        editor.putString(KEY_COUNTARY, login_model.getCountry());
        editor.putString(KEY_STATE, login_model.getState());
        editor.putString(KEY_CITY, login_model.getCity());
        editor.putString(KEY_PINCODE, login_model.getPincode());
        editor.putString(KEY_CREATE_DATE, login_model.getCreate_date());
        editor.putString(KEY_IMAGE, login_model.getImage());
        editor.putString(KEY_IMAGE_PROOF, login_model.getImg_proof());
        editor.putString(KEY_PAN_CARD, login_model.getPancard());




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
        modal.setIs_kyc(prefs.getString(KEY_IS_KYC, "-1"));
        modal.setRole(prefs.getString(KEY_ROLE, "-1"));
        modal.setIs_active(prefs.getString(KEY_IS_ACTIVE, "-1"));
        modal.setName(prefs.getString(KEY_NAME, "-1"));
        modal.setMobile(prefs.getString(KEY_MOBILE, "-1"));
        modal.setIs_mail(prefs.getString(KEY_IS_MAIL, "-1"));
        modal.setBusiness_name(prefs.getString(KEY_BUISNESS_NAME, "-1"));
        modal.setLast_name(prefs.getString(KEY_LAST_NAME, "-1"));
        modal.setAddress(prefs.getString(KEY_ADDRESS, "-1"));
        modal.setLocatlity(prefs.getString(KEY_LOCALITY, "-1"));
        modal.setLandline(prefs.getString(KEY_LANDLINE, "-1"));
        modal.setCountry(prefs.getString(KEY_COUNTARY, "-1"));
        modal.setState(prefs.getString(KEY_STATE, "-1"));
        modal.setCity(prefs.getString(KEY_CITY, "-1"));
        modal.setPincode(prefs.getString(KEY_PINCODE, "-1"));
        modal.setCreate_date(prefs.getString(KEY_CREATE_DATE, "-1"));
        modal.setImage(prefs.getString(KEY_IMAGE, "-1"));
        modal.setImg_proof(prefs.getString(KEY_IMAGE_PROOF, "-1"));
        modal.setPancard(prefs.getString(KEY_PAN_CARD, "-1"));

        return modal;

    }

    public static void clearPrefsdata(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(Prefsname,
                Context.MODE_PRIVATE);
        editor = prefs.edit();
        editor.clear().commit();

    }

}
