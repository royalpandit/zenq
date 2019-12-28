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
import com.abhishek.zeiqindia.AdapterFleet_Owner.AdapterGetParty;
import com.abhishek.zeiqindia.Bean.PartyBean;
import com.abhishek.zeiqindia.Bean.TruckBean;
import com.abhishek.zeiqindia.Prefrence.AppPreferences;
import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Response.FleetOwnerTruckResponse;
import com.abhishek.zeiqindia.Response.GepPartyResponse;
import com.abhishek.zeiqindia.Truck_DriverRegister.ActivityDriverPersonal_Details;
import com.abhishek.zeiqindia.Truck_DriverRegister.ActivityFleet_Add_MyParty;
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

public class FleetOwnerMyParty extends Fragment implements View.OnClickListener {
    String User_Id;
    List<PartyBean> partyBeanList;
    Toolbar toolbar;
    PartyBean partyBean;
    AdapterGetParty adapterGetParty;
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
//        getMyParty();
        return rootView;
    }

    public void find() {
        getActivity().setTitle(R.string.my_party);

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
        getMyParty();
    }

    private void getMyParty() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"owner_id"}, new
                String[]{User_Id});
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            Call<GepPartyResponse> call = apiInterface.Fleet_GetParty(builder.build());
            call.enqueue(new Callback<GepPartyResponse>() {
                @Override
                public void onResponse(Call<GepPartyResponse> call, Response<GepPartyResponse> response) {
                    bar.setVisibility(View.GONE);

                    if (response.isSuccessful() && response.body().getStatus().equals("1") && response.body().getMsg().equals("Successfully")) {
                        partyBeanList = new ArrayList<>();
                        for (int i = 0; i < response.body().getInfo().size(); i++) {
                            partyBean = new PartyBean();
                            partyBean.setId(response.body().getInfo().get(i).getId());
                            partyBean.setCompany_name(response.body().getInfo().get(i).getCompany_name());
                            partyBean.setCompany_address(response.body().getInfo().get(i).getCompany_address());
                            partyBean.setEmail(response.body().getInfo().get(i).getEmail());
                            partyBean.setContact_person(response.body().getInfo().get(i).getContact_person());
                            partyBean.setContact_number(response.body().getInfo().get(i).getContact_number());
                            partyBean.setGst(response.body().getInfo().get(i).getGst());
                            partyBean.setPan(response.body().getInfo().get(i).getPan());
                            partyBean.setCreate_date(response.body().getInfo().get(i).getCreate_date());
                            partyBean.setOwner_id(response.body().getInfo().get(i).getOwner_id());


                            partyBeanList.add(partyBean);
                        }
                        Log.e("allBidBeanList", "" + partyBeanList.size());
                        adapterGetParty = new AdapterGetParty(getActivity(), partyBeanList);
                        recycleview_nytruck.setAdapter(adapterGetParty);

                    } else {
                        Log.e("userOrderHistory1ize", "" + "else");
                    }
                }

                @Override
                public void onFailure(Call<GepPartyResponse> call, Throwable t) {
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
            CommonUtils.intentToActivity(getActivity(), ActivityFleet_Add_MyParty.class);

        }
    }
}
