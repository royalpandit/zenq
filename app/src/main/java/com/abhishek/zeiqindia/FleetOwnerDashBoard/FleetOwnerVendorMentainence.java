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
import com.abhishek.zeiqindia.AdapterFleet_Owner.AdapterMaintenenceFleetOwner;
import com.abhishek.zeiqindia.Bean.MentianenceBean;
import com.abhishek.zeiqindia.Bean.TruckBean;
import com.abhishek.zeiqindia.Prefrence.AppPreferences;
import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Response.FleetOwnerMentianenceResponse;
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

public class FleetOwnerVendorMentainence extends Fragment implements View.OnClickListener  {
    String User_Id;
    List<MentianenceBean> mentianenceBeanList;
    Toolbar toolbar;
    MentianenceBean mentianenceBean;
    AdapterMaintenenceFleetOwner adapterMaintenenceFleetOwner;
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
        getActivity().setTitle(R.string.vendor_mainet);

        fab = rootView.findViewById(R.id.fab);
        bar = rootView.findViewById(R.id.bar);
        recycleview_nytruck = rootView.findViewById(R.id.MyTruckdriverrecycle);
        recycleview_nytruck.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleview_nytruck.setLayoutManager(layoutManager);
        fab.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if (v == fab) {
            CommonUtils.intentToActivity(getActivity(), ActivityDriverPersonal_Details.class);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllMentainenec();

    }

    private void getAllMentainenec() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"owner_id"}, new
                String[]{User_Id});
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            Call<FleetOwnerMentianenceResponse> call = apiInterface.FleetServiceVendor(builder.build());
            call.enqueue(new Callback<FleetOwnerMentianenceResponse>() {
                @Override
                public void onResponse(Call<FleetOwnerMentianenceResponse> call, Response<FleetOwnerMentianenceResponse> response) {
                    bar.setVisibility(View.GONE);

                    if (response.isSuccessful() && response.body().getStatus().equals("1") && response.body().getMsg().equals("Successfully")) {
                        mentianenceBeanList = new ArrayList<>();
                        for (int i = 0; i < response.body().getInfo().size(); i++) {
                            mentianenceBean = new MentianenceBean();
                            mentianenceBean.setId(response.body().getInfo().get(i).getId());
                            mentianenceBean.setOwner_id(response.body().getInfo().get(i).getOwner_id());
                            mentianenceBean.setName(response.body().getInfo().get(i).getName());
                            mentianenceBean.setPhone(response.body().getInfo().get(i).getPhone());
                            mentianenceBean.setCompany(response.body().getInfo().get(i).getCompany());
                            mentianenceBean.setGst(response.body().getInfo().get(i).getGst());
                            mentianenceBean.setAddress1(response.body().getInfo().get(i).getAddress1());
                            mentianenceBean.setAddress2(response.body().getInfo().get(i).getAddress2());
                            mentianenceBean.setAddress3(response.body().getInfo().get(i).getAddress3());
                            mentianenceBean.setCreate_date(response.body().getInfo().get(i).getCreate_date());


                            mentianenceBeanList.add(mentianenceBean);
                        }
                        Log.e("mentianenceBeanList", "" + mentianenceBeanList.size());
                        adapterMaintenenceFleetOwner = new AdapterMaintenenceFleetOwner(getActivity(), mentianenceBeanList);
                        recycleview_nytruck.setAdapter(adapterMaintenenceFleetOwner);

                    } else {
                        Log.e("mentianenceBeanList1ize", "" + "else");
                    }
                }

                @Override
                public void onFailure(Call<FleetOwnerMentianenceResponse> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });
        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }



}
