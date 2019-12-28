package com.abhishek.zeiqindia.Truck_DriverRegister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.zeiqindia.AdapterFleet_Owner.AdapterAddTripLoadAdapter;
import com.abhishek.zeiqindia.Bean.GetTripBean;
import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Response.FleetownerGetTripResponse;
import com.abhishek.zeiqindia.Utility.AppBaseActivity;
import com.abhishek.zeiqindia.Utility.CommonUtils;
import com.abhishek.zeiqindia.rest.ApiClient;
import com.abhishek.zeiqindia.rest.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityFleetAddTripLoadlist extends AppBaseActivity implements View.OnClickListener {
    String User_Id;
    String Trip_id;
    String Trips_id;
    TextView text_tv;
    List<GetTripBean> getTripBeanList;
    GetTripBean getTripBean;
    AdapterAddTripLoadAdapter adapterAddTripLoadAdapter;
    RelativeLayout header;
    ImageView back;
    RecyclerView.LayoutManager layoutManager;

    FloatingActionButton fab;
    RecyclerView recycleview_nytruck;
    AVLoadingIndicatorView bar;
    View rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet_owner_my_truck_list);
        Trip_id = getIntent().getStringExtra("trip_id");
        Log.e("tripd1dd",Trip_id);

        find();

        getMyParty();
    }


    public void find() {

        header = findViewById(R.id.header);
        back = findViewById(R.id.back);
        fab = findViewById(R.id.fab);
        bar = findViewById(R.id.bar);
        recycleview_nytruck = findViewById(R.id.MyTruckdriverrecycle);
        recycleview_nytruck.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recycleview_nytruck.setLayoutManager(layoutManager);
        fab.setOnClickListener(this);
        header.setVisibility(View.VISIBLE);
        text_tv = findViewById(R.id.text_tv);

        text_tv.setText("My Trips Loads");

        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == fab) {
            Intent intent=new Intent(mContext,Activtiy_Fleet_AddLoad_Trip.class);
            intent.putExtra("Trip_id",Trip_id);
            Log.e("tripd1",Trip_id);
            startActivity(intent);
//            CommonUtils.intentToActivity(mContext, Activtiy_Fleet_AddLoad_Trip.class);


            //CommonUtils.intentToActivity(mContext, ActivityFleetAddTrip.class);

        }
        if (v == back) {
            onBackPressed();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        getMyParty();
    }

    private void getMyParty() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"trip_id"}, new
                String[]{Trip_id});
        if (CommonUtils.isNetworkAvailable(mContext)) {
            Call<FleetownerGetTripResponse> call = apiInterface.Fleet_GetTripLoad(builder.build());
            call.enqueue(new Callback<FleetownerGetTripResponse>() {
                @Override
                public void onResponse(Call<FleetownerGetTripResponse> call, Response<FleetownerGetTripResponse> response) {
                    bar.setVisibility(View.GONE);

                    if (response.isSuccessful() && response.body().getStatus().equals("1") && response.body().getMsg().equals("Successfully")) {
                        getTripBeanList = new ArrayList<>();
                        for (int i = 0; i < response.body().getInfo().size(); i++) {


                            getTripBean = new GetTripBean();
                            getTripBean.setId(response.body().getInfo().get(i).getId());
                            getTripBean.setTrip_id(response.body().getInfo().get(i).getTrip_id());
                             getTripBean.setOwner_id(response.body().getInfo().get(i).getOwner_id());
                            getTripBean.setFrom_address(response.body().getInfo().get(i).getFrom_address());
                            getTripBean.setTo_address(response.body().getInfo().get(i).getTo_address());
                            getTripBean.setFrom_date(response.body().getInfo().get(i).getFrom_date());
                            getTripBean.setTo_date(response.body().getInfo().get(i).getTo_date());
                            getTripBean.setWeight(response.body().getInfo().get(i).getWeight());
                            getTripBean.setPrice_per_ton(response.body().getInfo().get(i).getPrice_per_ton());
                            getTripBean.setTrip_type(response.body().getInfo().get(i).getTrip_type());
                            getTripBean.setMaterial_type(response.body().getInfo().get(i).getMaterial_type());
                            getTripBean.setAdvance(response.body().getInfo().get(i).getAdvance());
                            getTripBean.setShortage(response.body().getInfo().get(i).getShortage());
                            getTripBean.setDeduction(response.body().getInfo().get(i).getDeduction());
                            getTripBean.setMunsiyana(response.body().getInfo().get(i).getMunsiyana());
                            getTripBean.setCommision(response.body().getInfo().get(i).getCommision());
                            getTripBean.setOther(response.body().getInfo().get(i).getOther());
                            getTripBean.setIs_start(response.body().getInfo().get(i).getIs_start());
                            getTripBean.setFrom_state(response.body().getInfo().get(i).getFrom_state());
                            getTripBean.setFrom_city(response.body().getInfo().get(i).getFrom_city());
                            getTripBean.setTo_state(response.body().getInfo().get(i).getTo_state());
                            getTripBean.setTo_city(response.body().getInfo().get(i).getTo_city());
                            getTripBean.setParty(response.body().getInfo().get(i).getParty());
                            getTripBean.setTruck_type(response.body().getInfo().get(i).getTruck_type());
                            getTripBean.setDriver_id(response.body().getInfo().get(i).getDriver_id());
                            getTripBean.setTruck_id(response.body().getInfo().get(i).getTruck_id());
                            getTripBean.setCreate_date(response.body().getInfo().get(i).getCreate_date());


                            getTripBeanList.add(getTripBean);
                        }
                        Log.e("allBidBeanList", "" + getTripBeanList.size());
                        adapterAddTripLoadAdapter = new AdapterAddTripLoadAdapter(mContext, getTripBeanList);
                        recycleview_nytruck.setAdapter(adapterAddTripLoadAdapter);

                    } else {
                        Log.e("userOrderHistory1ize", "" + "else");
                    }
                }

                @Override
                public void onFailure(Call<FleetownerGetTripResponse> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });
        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(mContext, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }

}
