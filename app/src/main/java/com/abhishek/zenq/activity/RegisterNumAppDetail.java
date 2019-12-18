package com.abhishek.zenq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abhishek.zenq.R;

public class RegisterNumAppDetail extends AppCompatActivity implements View.OnClickListener {
Button btn_next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_app_details);

        find();

    }


    public void find(){
        btn_next=findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==btn_next){
            startActivity(new Intent(RegisterNumAppDetail.this,RegisterNumFinalDetail.class));

        }
    }
}
