package com.abhishek.zenq.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abhishek.zenq.R;

public class RegisterNumFinalDetail  extends AppCompatActivity implements View.OnClickListener {
Button btn_next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_add_finalphoto);
    }

    public void find(){
        btn_next=findViewById(R.id.btn_next);

        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==btn_next){

        }
    }
}
