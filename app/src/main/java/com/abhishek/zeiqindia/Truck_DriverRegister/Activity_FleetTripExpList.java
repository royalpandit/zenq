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

import com.abhishek.zeiqindia.AdapterFleet_Owner.AdapterAddTripExpAdapter;
import com.abhishek.zeiqindia.AdapterFleet_Owner.AdapterAddTripLoadAdapter;
import com.abhishek.zeiqindia.Bean.GetLoadBean;
import com.abhishek.zeiqindia.Bean.GetTripBean;
import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Response.AddTttripExpwithoutarray;
import com.abhishek.zeiqindia.Response.FleetOwnerGetTripLoadResponse;
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

public class Activity_FleetTripExpList extends AppBaseActivity implements View.OnClickListener {
    int Total_Amount, RemainAmount;
    String Str_TotalAmount, Str_TotalExpe, Str_Totalremain;
    String User_Id;
    String Trip_id;
    String Trips_id;
    TextView text_tv;
    List<GetLoadBean> getLoadBeanList;
    GetLoadBean getLoadBean;
    AdapterAddTripExpAdapter adapterAddTripExpAdapter;
    RelativeLayout header;
    ImageView back;
    RecyclerView.LayoutManager layoutManager;
    TextView tv_toamount, tv_to_exp, tv_remina;
    FloatingActionButton fab;
    RecyclerView explistrecycle;
    AVLoadingIndicatorView bar;
    View rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtrip_explist);
        Trip_id = getIntent().getStringExtra("trip_id");
        Log.e("tripd1dd", Trip_id);

        find();
        getMyexpenseeTotal();
        getMyParty();
    }


    public void find() {
        tv_to_exp = findViewById(R.id.tv_to_exp);

        tv_remina = findViewById(R.id.tv_remina);

        tv_toamount = findViewById(R.id.tv_toamount);
        back = findViewById(R.id.back);
        //  fab = findViewById(R.id.fab);
        bar = findViewById(R.id.bar);
        explistrecycle = findViewById(R.id.explistrecycle);
        explistrecycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        explistrecycle.setLayoutManager(layoutManager);
        //   fab.setOnClickListener(this);
        //  header.setVisibility(View.VISIBLE);
        // text_tv = findViewById(R.id.text_tv);

        //text_tv.setText("My Trips Expenses");

          back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == back) {
            onBackPressed();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        getMyexpenseeTotal();
        getMyParty();

    }

    private void getMyParty() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"trip_id"}, new
                String[]{Trip_id});
        if (CommonUtils.isNetworkAvailable(mContext)) {
            Call<FleetOwnerGetTripLoadResponse> call = apiInterface.Fleet_GetTripeExpense(builder.build());
            call.enqueue(new Callback<FleetOwnerGetTripLoadResponse>() {
                @Override
                public void onResponse(Call<FleetOwnerGetTripLoadResponse> call, Response<FleetOwnerGetTripLoadResponse> response) {
                    bar.setVisibility(View.GONE);


                    if (response.isSuccessful() && response.body().getStatus().equals("1")) {
                        Total_Amount = response.body().getExpenses();
                        RemainAmount = response.body().getRemain();
                        Str_TotalAmount = response.body().getAdvance();

                        Str_TotalExpe = String.valueOf(Total_Amount);
                        Str_Totalremain = String.valueOf(RemainAmount);
                        Log.e("Str_TotalExpe", "" + Str_TotalExpe);
                        Log.e("Str_TotalAmount", "" + Str_TotalAmount);

                        tv_toamount.setText(Str_TotalAmount);
                        tv_to_exp.setText(Str_TotalExpe);
                        tv_remina.setText(Str_Totalremain);
                        getLoadBeanList = new ArrayList<>();
                        for (int i = 0; i < response.body().getInfo().size(); i++) {


                            getLoadBean = new GetLoadBean();
                            getLoadBean.setId(response.body().getInfo().get(i).getId());
                            getLoadBean.setTrip_id(response.body().getInfo().get(i).getTrip_id());
                            getLoadBean.setExpenses_type(response.body().getInfo().get(i).getExpenses_type());
                            getLoadBean.setExpenses_amount(response.body().getInfo().get(i).getExpenses_amount());
                            getLoadBean.setComment(response.body().getInfo().get(i).getComment());
                            getLoadBean.setD_qty(response.body().getInfo().get(i).getD_qty());
                            getLoadBean.setD_rate(response.body().getInfo().get(i).getD_rate());
                            getLoadBean.setD_amount(response.body().getInfo().get(i).getD_amount());
                            getLoadBean.setD_km(response.body().getInfo().get(i).getD_km());
                            getLoadBean.setT_qty(response.body().getInfo().get(i).getT_qty());
                            getLoadBean.setT_make(response.body().getInfo().get(i).getT_make());
                            getLoadBean.setT_model(response.body().getInfo().get(i).getT_model());
                            getLoadBean.setT_serial_no(response.body().getInfo().get(i).getT_serial_no());
                            getLoadBean.setT_amount(response.body().getInfo().get(i).getT_amount());
                            getLoadBean.setCreate_date(response.body().getInfo().get(i).getCreate_date());


                            getLoadBeanList.add(getLoadBean);
                        }
                        Log.e("allBidBeanList", "" + getLoadBeanList.size());
                        adapterAddTripExpAdapter = new AdapterAddTripExpAdapter(mContext, getLoadBeanList);
                        explistrecycle.setAdapter(adapterAddTripExpAdapter);
                     } /*else if (response.isSuccessful() && response.body().getStatus().equals("0")){
                        Total_Amount = response.body().getExpenses();
                        RemainAmount = response.body().getRemain();
                        Str_TotalAmount = response.body().getAdvance();

                        Str_TotalExpe = String.valueOf(Total_Amount);
                        Str_Totalremain = String.valueOf(RemainAmount);
                        Log.e("Str_TotalExpeelse", "" + Str_TotalExpe);
                        Log.e("Str_TotalAmountelse", "" + Str_TotalAmount);

                        tv_toamount.setText(Str_TotalAmount);
                        tv_to_exp.setText(Str_TotalExpe);
                        tv_remina.setText(Str_Totalremain);
                    }*/
                }

                @Override
                public void onFailure(Call<FleetOwnerGetTripLoadResponse> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                    Log.e("elselse", "" + "else");

                }
            });
        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(mContext, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }
    private void getMyexpenseeTotal() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"trip_id"}, new
                String[]{Trip_id});
        if (CommonUtils.isNetworkAvailable(mContext)) {
            Call<AddTttripExpwithoutarray> call = apiInterface.Fleet_GetTripeLoadExp(builder.build());
            call.enqueue(new Callback<AddTttripExpwithoutarray>() {
                @Override
                public void onResponse(Call<AddTttripExpwithoutarray> call, Response<AddTttripExpwithoutarray> response) {
                    bar.setVisibility(View.GONE);


                    if (response.isSuccessful() && response.body().getStatus().equals("1")) {
                        Total_Amount = response.body().getExpenses();
                        RemainAmount = response.body().getRemain();
                        Str_TotalAmount = response.body().getAdvance();

                        Str_TotalExpe = String.valueOf(Total_Amount);
                        Str_Totalremain = String.valueOf(RemainAmount);
                        Log.e("Str_TotalExpe", "" + Str_TotalExpe);
                        Log.e("Str_TotalAmount", "" + Str_TotalAmount);

                        tv_toamount.setText(Str_TotalAmount);
                        tv_to_exp.setText(Str_TotalExpe);
                        tv_remina.setText(Str_Totalremain);
                      /*  getLoadBeanList = new ArrayList<>();
                        for (int i = 0; i < response.body().getInfo().size(); i++) {


                            getLoadBean = new GetLoadBean();
                            getLoadBean.setId(response.body().getInfo().get(i).getId());
                            getLoadBean.setTrip_id(response.body().getInfo().get(i).getTrip_id());
                            getLoadBean.setExpenses_type(response.body().getInfo().get(i).getExpenses_type());
                            getLoadBean.setExpenses_amount(response.body().getInfo().get(i).getExpenses_amount());
                            getLoadBean.setComment(response.body().getInfo().get(i).getComment());
                            getLoadBean.setD_qty(response.body().getInfo().get(i).getD_qty());
                            getLoadBean.setD_rate(response.body().getInfo().get(i).getD_rate());
                            getLoadBean.setD_amount(response.body().getInfo().get(i).getD_amount());
                            getLoadBean.setD_km(response.body().getInfo().get(i).getD_km());
                            getLoadBean.setT_qty(response.body().getInfo().get(i).getT_qty());
                            getLoadBean.setT_make(response.body().getInfo().get(i).getT_make());
                            getLoadBean.setT_model(response.body().getInfo().get(i).getT_model());
                            getLoadBean.setT_serial_no(response.body().getInfo().get(i).getT_serial_no());
                            getLoadBean.setT_amount(response.body().getInfo().get(i).getT_amount());
                            getLoadBean.setCreate_date(response.body().getInfo().get(i).getCreate_date());


                            getLoadBeanList.add(getLoadBean);
                        }
                        Log.e("allBidBeanList", "" + getLoadBeanList.size());
                        adapterAddTripExpAdapter = new AdapterAddTripExpAdapter(mContext, getLoadBeanList);
                        explistrecycle.setAdapter(adapterAddTripExpAdapter);*/
                    } /*else if (response.isSuccessful() && response.body().getStatus().equals("0")){
                        Total_Amount = response.body().getExpenses();
                        RemainAmount = response.body().getRemain();
                        Str_TotalAmount = response.body().getAdvance();

                        Str_TotalExpe = String.valueOf(Total_Amount);
                        Str_Totalremain = String.valueOf(RemainAmount);
                        Log.e("Str_TotalExpeelse", "" + Str_TotalExpe);
                        Log.e("Str_TotalAmountelse", "" + Str_TotalAmount);

                        tv_toamount.setText(Str_TotalAmount);
                        tv_to_exp.setText(Str_TotalExpe);
                        tv_remina.setText(Str_Totalremain);
                    }*/
                }

                @Override
                public void onFailure(Call<AddTttripExpwithoutarray> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                    Log.e("elselse", "" + "else");

                }
            });
        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(mContext, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }
}