package com.abhishek.zeiqindia.Adminfragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Response.KYCSubmitResponse;
import com.abhishek.zeiqindia.Utility.CommonUtils;
import com.abhishek.zeiqindia.rest.ApiClient;
import com.abhishek.zeiqindia.rest.ApiInterface;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityGet_Profile_Show_KYC extends Activity implements View.OnClickListener {
    AVLoadingIndicatorView avi;
    ImageView back, imguser;
    Button kycupdate;
    TextView tvusernam, tv_buisness_name, tv_name, tv_mobile, tv_email, tv_full_address, tv_locality, tv_city,
            tv_state, tv_countary, tv_pincode, tv_landline, tv_pancard, tv_password, tv_con_password;
    String Str_user_id, Strname, buisness_name, Strmobile, Stremail, Strfull_address, Strlocality, Strcity,
            Strstate, Strcountary, Strpincode, Strlandline, Strpancard, StrUsertype,
            Strpassword, Strcon_password, StrCre_date, StrImage, StrIsMail, StrIsKyc, StrLastname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getprofilekyc);
        Intent intent = getIntent();


        Str_user_id = intent.getStringExtra("user_id");

        Strname = intent.getStringExtra("name");
        Stremail = intent.getStringExtra("email");
        Strpassword = intent.getStringExtra("password");
        StrUsertype = intent.getStringExtra("user_type");
        StrIsKyc = intent.getStringExtra("Is_KYC");
        Strmobile = intent.getStringExtra("mobile");
        StrIsMail = intent.getStringExtra("is_mail");
        buisness_name = intent.getStringExtra("buisnes_name");
        StrLastname = intent.getStringExtra("last_name");
        Strfull_address = intent.getStringExtra("address");
        Strlocality = intent.getStringExtra("locality");
        Strcountary = intent.getStringExtra("countary");
        Strstate = intent.getStringExtra("state");
        Strcity = intent.getStringExtra("city");
        StrImage = intent.getStringExtra("image");
        Strpincode = intent.getStringExtra("pincode");
        Strpancard = intent.getStringExtra("pancard");

        StrCre_date = intent.getStringExtra("createdate");


        find();


    }

    public void find() {
        avi = findViewById(R.id.avi);
        imguser = findViewById(R.id.imguser);
        kycupdate = findViewById(R.id.kycupdate);
        back = findViewById(R.id.back);
        tvusernam = findViewById(R.id.tvusernam);
        tv_buisness_name = findViewById(R.id.tv_buisness_name);
        tv_name = findViewById(R.id.tv_name);
        tv_mobile = findViewById(R.id.tv_mobile);
        tv_email = findViewById(R.id.tv_email);
        tv_full_address = findViewById(R.id.tv_full_address);
        tv_locality = findViewById(R.id.tv_locality);
        tv_city = findViewById(R.id.tv_city);
        tv_state = findViewById(R.id.tv_state);
        tv_countary = findViewById(R.id.tv_countary);
        tv_pincode = findViewById(R.id.tv_pincode);
        tv_landline = findViewById(R.id.tv_landline);
        tv_pancard = findViewById(R.id.tv_pancard);
        tv_password = findViewById(R.id.tv_password);
        tv_con_password = findViewById(R.id.tv_con_password);
        back.setOnClickListener(this);
        kycupdate.setOnClickListener(this);
        tv_name.setText(Strname + StrLastname);
        tvusernam.setText("@" + Strname);
        tv_buisness_name.setText(buisness_name);
        tv_mobile.setText(Strmobile);
        tv_email.setText(Stremail);
        tv_full_address.setText(Strfull_address);
        tv_locality.setText(Strlocality);
        tv_city.setText(Strcity);
        tv_state.setText(Strstate);
        tv_countary.setText(Strcountary);
        tv_pincode.setText(Strpincode);
        tv_password.setText(Strpassword);
        tv_con_password.setText(Strpassword);
        tv_pancard.setText(Strpancard);
        Picasso.with(getApplicationContext()).load(StrImage).into(imguser);

    }

    @Override
    public void onClick(View view) {
        if (view == back) {
            onBackPressed();
        }
        if (view == kycupdate) {
            if (StrIsKyc.equals("1")){
                kycupdate.setFocusable(true);
                kycupdate.setClickable(false);

            }else {
                AddState();

            }

        }


    }


    private void AddState() {

        avi.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id"},
                new String[]{Str_user_id});
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {

            Call<KYCSubmitResponse> call = apiInterface.FleetUserKYC(builder.build());
            call.enqueue(new Callback<KYCSubmitResponse>() {
                @Override
                public void onResponse(Call<KYCSubmitResponse> call, Response<KYCSubmitResponse> response) {
                    avi.setVisibility(View.GONE);
                    Log.d("11111", "11111");
                    if (response.isSuccessful()&&response.isSuccessful() && response.body().getStatus().equals("1") && response.body().getMsg().equalsIgnoreCase("KYC updated successfully.")) {
                        Log.d("2222", "2222");

startActivity(new Intent(ActivityGet_Profile_Show_KYC.this,FragmentAdminGetUser.class));
finish();

                        Toast.makeText(getApplicationContext(), "KYC updated successfully.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<KYCSubmitResponse> call, Throwable t) {
                    avi.setVisibility(View.GONE);
                }
            });

        } else {
            avi.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }


    }


}
