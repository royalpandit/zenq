package com.abhishek.zeiqindia.FleetOwnerDashBoard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.zeiqindia.AdapterFleet_Owner.AdapterAllTruck;
import com.abhishek.zeiqindia.Bean.TruckBean;
import com.abhishek.zeiqindia.Prefrence.AppPreferences;
import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Response.FleetOwnerTruckResponse;
import com.abhishek.zeiqindia.Truck_DriverRegister.ActivityDriverPersonal_Details;
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

public class FleetOwnerMyDriverList extends Fragment implements View.OnClickListener {
    String User_Id;
    List<TruckBean> truckBeanList;
    Toolbar toolbar;
    TruckBean truckBean;
    AdapterAllTruck adapterAllTruck;
    FloatingActionButton fab;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recycleview_nytruck;
    AVLoadingIndicatorView bar;
    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_fleet_owner_my_truck_list, container, false);
        User_Id = AppPreferences.getSavedUser(getActivity()).getId();

        find();
        return rootView;
    }

    public void find() {
        getActivity().setTitle(R.string.my_driver);

        fab = rootView.findViewById(R.id.fab);
        bar = rootView.findViewById(R.id.bar);
        recycleview_nytruck = rootView.findViewById(R.id.MyTruckdriverrecycle);
        recycleview_nytruck.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleview_nytruck.setLayoutManager(layoutManager);
        fab.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        getAllTruckk();

    }

    private void getAllTruckk() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"owner_id"}, new
                String[]{User_Id});
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            Call<FleetOwnerTruckResponse> call = apiInterface.FleetGetAllTruck(builder.build());
            call.enqueue(new Callback<FleetOwnerTruckResponse>() {
                @Override
                public void onResponse(Call<FleetOwnerTruckResponse> call, Response<FleetOwnerTruckResponse> response) {
                    bar.setVisibility(View.GONE);

                    if (response.isSuccessful() && response.body().getStatus().equals("1") && response.body().getMsg().equals("Successfully")) {
                        truckBeanList = new ArrayList<>();
                        for (int i = 0; i < response.body().getInfo().size(); i++) {
                            truckBean = new TruckBean();
                            truckBean.setId(response.body().getInfo().get(i).getId());
                            truckBean.setReg_no(response.body().getInfo().get(i).getReg_no());
                            truckBean.setOwner_id(response.body().getInfo().get(i).getOwner_id());
                            truckBean.setReg_date(response.body().getInfo().get(i).getReg_date());
                            truckBean.setReg_vaild(response.body().getInfo().get(i).getReg_vaild());
                            truckBean.setChasis_no(response.body().getInfo().get(i).getChasis_no());
                            truckBean.setEngin_no(response.body().getInfo().get(i).getEngin_no());
                            truckBean.setMake(response.body().getInfo().get(i).getMake());
                            truckBean.setModel(response.body().getInfo().get(i).getModel());
                            truckBean.setLoad_capacity(response.body().getInfo().get(i).getLoad_capacity());
                            truckBean.setRoad_tax(response.body().getInfo().get(i).getRoad_tax());
                            truckBean.setPuv_vaild(response.body().getInfo().get(i).getPuv_vaild());
                            truckBean.setInsurance_vaild(response.body().getInfo().get(i).getInsurance_vaild());
                            truckBean.setPermit_valid(response.body().getInfo().get(i).getPermit_valid());
                            truckBean.setFitness_valid(response.body().getInfo().get(i).getFitness_valid());
                            truckBean.setIs_kyc(response.body().getInfo().get(i).getIs_kyc());
                            truckBean.setRc_image_front(response.body().getInfo().get(i).getRc_image_front());
                            truckBean.setRc_image_back(response.body().getInfo().get(i).getRc_image_back());
                            truckBean.setCreate_date(response.body().getInfo().get(i).getCreate_date());


                            truckBeanList.add(truckBean);
                        }
                        Log.e("allBidBeanList", "" + truckBeanList.size());
                        adapterAllTruck = new AdapterAllTruck(getActivity(), truckBeanList);
                        recycleview_nytruck.setAdapter(adapterAllTruck);

                    } else {
                        Log.e("userOrderHistory1ize", "" + "else");
                    }
                }

                @Override
                public void onFailure(Call<FleetOwnerTruckResponse> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });
        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == fab) {
          //  CommonUtils.intentToActivity(getActivity(), ActivityDriverPersonal_Details.class);

        }
    }
}

