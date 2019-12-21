package com.abhishek.zenq.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.abhishek.zenq.FleetOwnerDashBoard.MainActivityOwnerDashboard;
import com.abhishek.zenq.MainActivity;
import com.abhishek.zenq.Prefrence.AppPreferences;
import com.abhishek.zenq.R;

public class ActivitySplash extends AppCompatActivity {
    String id;
    private static int SPLASH_SCREEN_TIME_OUT=2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        id = AppPreferences.getSavedUser(ActivitySplash.this).getId();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (id.equals("-1")) {
                    Intent intent = new Intent(ActivitySplash.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                    String usertpe=  AppPreferences.getSavedUser(ActivitySplash.this).getUser_type();
                    if (usertpe.equals("0")){
                        Intent intent = new Intent(ActivitySplash.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }else if (usertpe.equals("1")){
                        Intent intent = new Intent(ActivitySplash.this, MainActivityOwnerDashboard.class);
                        startActivity(intent);
                        finish();

                    }

                }
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);

    }
}
