package com.abhishek.zeiqindia.FleetOwnerDashBoard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

 import com.abhishek.zeiqindia.AdapterFleet_Owner.AdapterMyTrips;

import com.abhishek.zeiqindia.Bean.MyTripsBean;

import com.abhishek.zeiqindia.Prefrence.AppPreferences;
import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Response.FleetOwnerMyTripsResponse;
import com.abhishek.zeiqindia.Truck_DriverRegister.ActivityDriverPersonal_Details;
import com.abhishek.zeiqindia.Truck_DriverRegister.ActivityFleetAddTrip;
import com.abhishek.zeiqindia.Utility.CommonUtils;
import com.abhishek.zeiqindia.activity.ActivitySplash;
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

public class FragmentFleetOwnerMyTrip extends Fragment implements View.OnClickListener {
    String User_Id;

    List<MyTripsBean> myTripsBeanList;
    MyTripsBean myTripsBean;
    AdapterMyTrips adapterMyTrips;

     RecyclerView.LayoutManager layoutManager;

    FloatingActionButton fab;
     RecyclerView recycleview_nytruck;
    AVLoadingIndicatorView bar;
    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate( R.layout.activity_fleet_owner_my_truck_list, container, false);
        // fragmentManager = getActivity().getSupportFragmentManager();
        User_Id = AppPreferences.getSavedUser(getActivity()).getId();

        //init();

        find();
        getMyTrip();
        return rootView;
    }
    public void find() {
        getActivity().setTitle(R.string.my_trips);

        fab = rootView.findViewById( R.id.fab);
        bar = rootView.findViewById( R.id.bar);
        recycleview_nytruck = rootView.findViewById( R.id.MyTruckdriverrecycle);
        recycleview_nytruck.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleview_nytruck.setLayoutManager(layoutManager);
        fab.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
if (v==fab){
    CommonUtils.intentToActivity(getActivity(), ActivityFleetAddTrip.class);

}
    }

    @Override
    public void onResume() {
        super.onResume();
        getMyTrip();

    }
    public void RefreshApi() {

        getMyTrip();

    }
    private void getMyTrip() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"owner_id"}, new
                String[]{User_Id});
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            Call<FleetOwnerMyTripsResponse> call = apiInterface.FleetGetMyTrip(builder.build());
            call.enqueue(new Callback<FleetOwnerMyTripsResponse>() {
                @Override
                public void onResponse(Call<FleetOwnerMyTripsResponse> call, Response<FleetOwnerMyTripsResponse> response) {
                    bar.setVisibility(View.GONE);

                    if (response.isSuccessful() && response.body().getStatus().equals("1") && response.body().getMsg().equals("Successfully")) {
                        myTripsBeanList = new ArrayList<>();

                        for (int i = 0; i < response.body().getInfo().size(); i++) {
                            myTripsBean = new MyTripsBean();



                            myTripsBean.setId(response.body().getInfo().get(i).getId());
                            myTripsBean.setOwner_id(response.body().getInfo().get(i).getOwner_id());
                            myTripsBean.setFrom_address(response.body().getInfo().get(i).getFrom_address());
                            myTripsBean.setTo_address(response.body().getInfo().get(i).getTo_address());
                            myTripsBean.setFrom_date(response.body().getInfo().get(i).getFrom_date());
                            myTripsBean.setTo_date(response.body().getInfo().get(i).getTo_date());
                            myTripsBean.setWeight(response.body().getInfo().get(i).getWeight());
                            myTripsBean.setPrice_per_ton(response.body().getInfo().get(i).getPrice_per_ton());
                            myTripsBean.setTrip_type(response.body().getInfo().get(i).getTrip_type());
                            myTripsBean.setMaterial_type(response.body().getInfo().get(i).getMaterial_type());
                            myTripsBean.setAdvance(response.body().getInfo().get(i).getAdvance());
                            myTripsBean.setShortage(response.body().getInfo().get(i).getShortage());
                            myTripsBean.setDeduction(response.body().getInfo().get(i).getDeduction());
                            myTripsBean.setMunsiyana(response.body().getInfo().get(i).getMunsiyana());
                            myTripsBean.setCommision(response.body().getInfo().get(i).getCommision());
                            myTripsBean.setOther(response.body().getInfo().get(i).getOther());
                            myTripsBean.setIs_status(response.body().getInfo().get(i).getIs_status());
                            myTripsBean.setIs_start(response.body().getInfo().get(i).getIs_start());
                            myTripsBean.setFrom_state(response.body().getInfo().get(i).getFrom_state());
                            myTripsBean.setFrom_city(response.body().getInfo().get(i).getFrom_city());
                            myTripsBean.setTo_state(response.body().getInfo().get(i).getTo_state());
                            myTripsBean.setTo_city(response.body().getInfo().get(i).getTo_city());
                            myTripsBean.setParty(response.body().getInfo().get(i).getParty());
                            myTripsBean.setTruck_type(response.body().getInfo().get(i).getTruck_type());
                            myTripsBean.setDriver_assign(response.body().getInfo().get(i).getDriver_assign());
                            myTripsBean.setTruck_assign(response.body().getInfo().get(i).getTruck_assign());
                            myTripsBean.setCreate_date(response.body().getInfo().get(i).getCreate_date());

                            myTripsBean.setDriver_advance(response.body().getInfo().get(i).getDriver_advance());

                            myTripsBeanList.add(myTripsBean);
                        }
                        Log.e("allmyTripsBeanList", "" + myTripsBeanList.size());
                        adapterMyTrips = new AdapterMyTrips(getActivity(), myTripsBeanList);
                        adapterMyTrips.notifyDataSetChanged();
                        recycleview_nytruck.setAdapter(adapterMyTrips);

                     } else {
                        Log.e("usermyTripsBeanList1ize", "" + "else");
                    }
                }

                @Override
                public void onFailure(Call<FleetOwnerMyTripsResponse> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });
        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }

}
