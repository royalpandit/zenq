package com.abhishek.zeiqindia.Utility;

import android.content.Context;

import com.abhishek.zeiqindia.Bean.LoginBean;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;

public class Constants {
    public static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static String TO_MAIL = "mail.abhisharma0@gmail.com";
    public static SimpleDateFormat SET_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /*
    * user role list
===============
* type  : 1/2/3

    * */
    public static final String USER_ROLE_FLEET_OWNER = "1";
    public static final String USER_ROLE_COMMISION_AGENT = "2";
    public static final String USER_ROLE_BOOKING_AGENT = "3";
    public static final String USER_ROLE_ADMIN = "25";

    /*
    -----------------------------
    SHARED PREFERENCES (TINYDB) KEYS
    -----------------------------
    */
    private static final String DB_USER_DATA = "user_data_sdlkfsjtoweit";
    public static final String DB_SELECTED_CATEGORIES = "selectedCategories_seklrewj593285";
    public static final String DB_SELECTED_RADIUS = "selected_radius_sdlkrjew95832";
    public static final String DB_SELECTED_GROUP_CHAT = "selected_group_chat_5r93wksdfsdjkfre";
    public static final String DB_SELECTED_PRIVATE_CHAT = "selected_private_chat_dslfjt2390523";
    public static final String DB_REMEMBER_ME = "remember_me_dsklrjewiro";
    public static final String USER_ID = "user_id";
    public static final String NOTICE_RELATED_CASE_ID = "id";
    public static final String APPEAL_RELATED_CASE_ID = "id";
    public static final String MOTION_RELATED_CASE_ID = "id";















    public static boolean isUserLoggedIn(Context context) {
        ZeiqDb db = new ZeiqDb(context);
       LoginBean userPOJO = new Gson().fromJson(db.getString(DB_USER_DATA), LoginBean.class);
        return (userPOJO != null && userPOJO.getId() != null && !userPOJO.getId().isEmpty());
    }


    public static void setUser(Context mContext, LoginBean userPOJO) {
        ZeiqDb db = new ZeiqDb(mContext);

        String userStr = new Gson().toJson(userPOJO);
        if (userPOJO == null) {
            db.remove(DB_USER_DATA);
            return;
        }

        db.putString(DB_USER_DATA, userStr);

        AppBaseActivity baseActivity = ((AppBaseActivity) mContext);
        if (baseActivity != null) {
            baseActivity.userPOJO = userPOJO;
        }
    }

    public static LoginBean getUser(Context mContext) {
        ZeiqDb db = new ZeiqDb(mContext);
        String userStr = db.getString(DB_USER_DATA);

        LoginBean user = new Gson().fromJson(userStr, LoginBean.class);

        return user;
    }


}
