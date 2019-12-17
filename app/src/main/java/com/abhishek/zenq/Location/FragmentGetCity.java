package com.abhishek.zenq.Location;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.zenq.Adminfragment.ActivityAddCity;
import com.abhishek.zenq.Bean.CityBean;
import com.abhishek.zenq.R;
import com.abhishek.zenq.Response.GetCityResponse;
import com.abhishek.zenq.Utility.CommonUtils;
import com.abhishek.zenq.adapter.AdapterAdminCity;
import com.abhishek.zenq.rest.ApiClient;
import com.abhishek.zenq.rest.ApiInterface;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentGetCity extends Activity implements View.OnClickListener {

    List<CityBean> cityBeanList;
    CityBean cityBean;
    AdapterAdminCity adapterAdminCity;
    RecyclerView cityrecycle;
    RecyclerView.LayoutManager layoutManager;
    String str_Stateid, str_StateName, str_StateCode;
    AVLoadingIndicatorView avi;
    ImageView back;
    Button addcity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_admin_getcity);
        Intent intent = getIntent();


        str_Stateid = intent.getStringExtra("state_id");
        str_StateName = intent.getStringExtra("state_name");
        str_StateCode = intent.getStringExtra("state_code");

        find();
        getCity();
    }


    public void find() {
        avi = findViewById(R.id.avi);
        back = findViewById(R.id.back);
        addcity = findViewById(R.id.addcity);
        cityrecycle = findViewById(R.id.cityrecycle);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        cityrecycle.setLayoutManager(layoutManager);
        cityrecycle.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        cityrecycle.setAdapter(adapterAdminCity);
        back.setOnClickListener(this);
        addcity.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == back) {
            onBackPressed();
        }
        if (v == addcity) {
            Intent i = new Intent(FragmentGetCity.this, ActivityAddCity.class);
            i.putExtra("state_name", str_StateName);
            i.putExtra("state_code", str_StateCode);
            i.putExtra("state_id", str_Stateid);
            startActivity(i);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCity();
    }

    private void getCity() {
        avi.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"state_id"}, new
                String[]{str_Stateid});
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {
            Call<GetCityResponse> call = apiInterface.FleetGetCity(builder.build());
            call.enqueue(new Callback<GetCityResponse>() {
                @Override
                public void onResponse(Call<GetCityResponse> call, Response<GetCityResponse> response) {
                    avi.setVisibility(View.GONE);

                    if (response.isSuccessful() && response.body().getStatus().equals("1") && response.body().getMsg().equals("Successfully")) {
                        cityBeanList = new ArrayList<>();

                        for (int i = 0; i < response.body().getInfo().size(); i++) {
                            cityBean = new CityBean();


                            cityBean.setId(response.body().getInfo().get(i).getId());
                            cityBean.setState_name(response.body().getInfo().get(i).getState_name());
                            cityBean.setState_id(response.body().getInfo().get(i).getState_id());
                            cityBean.setDistrict_id(response.body().getInfo().get(i).getDistrict_id());
                            cityBean.setDistrict_name(response.body().getInfo().get(i).getDistrict_name());
                            cityBean.setCity_name(response.body().getInfo().get(i).getCity_name());
                            cityBean.setState_code(response.body().getInfo().get(i).getState_code());
                            cityBeanList.add(cityBean);
                        }
                        Log.e("userOrderHistoryBean", "" + cityBeanList.size());
                        adapterAdminCity = new AdapterAdminCity(getApplicationContext(), cityBeanList);
                        cityrecycle.setAdapter(adapterAdminCity);
                    } else {
                        Log.e("userOrderHistory1ize", "" + "else");
                    }
                }

                @Override
                public void onFailure(Call<GetCityResponse> call, Throwable t) {
                    avi.setVisibility(View.GONE);
                }
            });
        } else {
            avi.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }


}
