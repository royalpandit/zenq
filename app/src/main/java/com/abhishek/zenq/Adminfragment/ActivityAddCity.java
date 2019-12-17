package com.abhishek.zenq.Adminfragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abhishek.zenq.Location.FragmentGetCity;
import com.abhishek.zenq.R;
import com.abhishek.zenq.Response.AddCityResponse;
import com.abhishek.zenq.Response.AddStateResponse;
import com.abhishek.zenq.Utility.CommonUtils;
import com.abhishek.zenq.rest.ApiClient;
import com.abhishek.zenq.rest.ApiInterface;
import com.wang.avi.AVLoadingIndicatorView;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAddCity extends AppCompatActivity implements View.OnClickListener {
EditText editcityname;
Button btnadd_city;
AVLoadingIndicatorView avi;
ImageView back;
String StrEditCity,str_Stateid,str_StateCode,str_StateName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        Intent intent = getIntent();


        str_Stateid = intent.getStringExtra("state_id");
        str_StateCode = intent.getStringExtra("state_code");
        str_StateName = intent.getStringExtra("state_name");

        find();
    }


    public void find(){
        avi=findViewById(R.id.avi);
        back=findViewById(R.id.back);
        editcityname=findViewById(R.id.editcityname);
        btnadd_city=findViewById(R.id.btnadd_city);
        btnadd_city.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnadd_city) {
            StrEditCity = editcityname.getText().toString().trim();
             if (StrEditCity.equals("") || StrEditCity.isEmpty()) {
                Toast.makeText(this, "Please Enter State", Toast.LENGTH_SHORT).show();
            } else {
               AddCity();
//                startActivity(new Intent(getApplicationContext(), FragmentGetState.class));

            }
        }if (v==back){
            onBackPressed();
        }
    }



    private void AddCity() {

        avi.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"state_name", "state_code","state_id","city_name"},
                new String[]{str_StateName, str_StateCode,str_Stateid,StrEditCity});
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {

            Call<AddCityResponse> call = apiInterface.FleetAddCity(builder.build());
            call.enqueue(new Callback<AddCityResponse>() {
                @Override
                public void onResponse(Call<AddCityResponse> call, Response<AddCityResponse> response) {
                    avi.setVisibility(View.GONE);
                    Log.d("11111", "11111");
                    if (response.isSuccessful() && response.body().getSuccess().equals("1") ) {
                         Toast.makeText(ActivityAddCity.this, "City Added successfully.", Toast.LENGTH_SHORT).show();
                        Log.d("2322", ""+ response.body().getMessage().equals("City Added successfully."));

                                startActivity(new Intent(ActivityAddCity.this, FragmentGetCity.class));

                     //   onBackPressed();
                    }
                }

                @Override
                public void onFailure(Call<AddCityResponse> call, Throwable t) {
                    avi.setVisibility(View.GONE);
                }
            });

        }else {
            avi.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }




    }

}
