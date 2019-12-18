package com.abhishek.zenq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abhishek.zenq.R;

public class RegisterNum_OTP extends AppCompatActivity implements View.OnClickListener {

Button btn_otp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_num_otp);
        find();
    }
    public void find(){
        btn_otp=findViewById(R.id.btn_otp);
        btn_otp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==btn_otp){
            startActivity(new Intent(RegisterNum_OTP.this,RegisterNumAppDetail.class));

        }
    }
}
