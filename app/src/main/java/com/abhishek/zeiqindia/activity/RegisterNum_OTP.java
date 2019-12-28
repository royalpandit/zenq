package com.abhishek.zeiqindia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abhishek.zeiqindia.R;

public class RegisterNum_OTP extends AppCompatActivity implements View.OnClickListener {
String Otp;
Button btn_otp;
EditText editotp;
    String getotp_edit;
    int randomNumber;
    String StrPhoneNUmber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_num_otp);
        Otp = getIntent().getStringExtra("number");
        StrPhoneNUmber=getIntent().getStringExtra("phoneNUmber");
        Log.e("OTCP",""+Otp);


        find();
    }
    public void find(){
        editotp=findViewById(R.id.editemail);
        btn_otp=findViewById(R.id.btn_otp);
        btn_otp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==btn_otp){
             getotp_edit =  (editotp.getEditableText().toString());
            Log.e("getotp_edit",""+getotp_edit);

            Log.e("OTP",""+Otp);
            if  (getotp_edit.equals("") || getotp_edit.isEmpty()){
                editotp.setError("Please enter your 4 Digit OTP");
                editotp.requestFocus();
                return;
            }else if (getotp_edit.equals(Otp)){
                Toast.makeText(getApplicationContext(), "OTP is Right", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterNum_OTP.this, RegisterNumAppDetail.class);
                intent.putExtra("phoneNUmber",StrPhoneNUmber);
                startActivity(intent);
                finish();



            }else {
                Toast.makeText(getApplicationContext(), "OTP is wronge", Toast.LENGTH_SHORT).show();

            }


        }
    }
}
