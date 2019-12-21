package com.abhishek.zenq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abhishek.zenq.R;
import com.abhishek.zenq.Response.OtpResponse;
import com.abhishek.zenq.Utility.CommonUtils;
import com.abhishek.zenq.rest.ApiClient;
import com.abhishek.zenq.rest.ApiInterface;
import com.wang.avi.AVLoadingIndicatorView;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterNumb extends AppCompatActivity implements View.OnClickListener {
    Button btn_sign;
    EditText editemail;
    String StrEditNum;
    AVLoadingIndicatorView bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_upp_number);

        find();

    }

    public void find() {
        bar = findViewById(R.id.bar);
        editemail = findViewById(R.id.editemail);
        btn_sign = findViewById(R.id.btn_sign);
        btn_sign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_sign) {
            StrEditNum = editemail.getText().toString().trim();
            if (TextUtils.isEmpty(StrEditNum)) {
                editemail.setError("Please enter your mobile number");
                editemail.requestFocus();
                return;
            } else if (StrEditNum.length() != 10) {
                editemail.setError("Invalid mobile number");
                editemail.requestFocus();
                return;
            }
            getOTP();

        }
    }


    private void getOTP() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"phone"}, new
                String[]{StrEditNum});
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {
            Call<OtpResponse> call = apiInterface.sendsms(builder.build());
            call.enqueue(new Callback<OtpResponse>() {
                @Override
                public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                    bar.setVisibility(View.GONE);

                    if (response.isSuccessful() && response.body().getStatus().equals("1") && response.body().getMsg().equals("Successfully")) {
                        String Str_OTp = String.valueOf(response.body().getOtp());

                        Intent intent = new Intent(RegisterNumb.this, RegisterNum_OTP.class);
                        intent.putExtra("number", Str_OTp);
                        intent.putExtra("phoneNUmber",StrEditNum);
                        startActivity(intent);
                        finish();

                    } else {

                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<OtpResponse> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });
        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }
}
