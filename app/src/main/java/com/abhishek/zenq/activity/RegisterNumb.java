package com.abhishek.zenq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abhishek.zenq.R;

public class RegisterNumb extends AppCompatActivity implements View.OnClickListener {
Button btn_sign;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_upp_number);

        find();

    }
    public void find(){
        btn_sign=findViewById(R.id.btn_sign);
        btn_sign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==btn_sign){
            startActivity(new Intent(RegisterNumb.this,RegisterNum_OTP.class));

        }
    }
}
