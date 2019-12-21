package com.abhishek.zenq.FleetOwnerDashBoard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.zenq.AdapterFleet_Owner.AdapterAllBid;
import com.abhishek.zenq.Bean.AllBidBean;
import com.abhishek.zenq.Bean.GetUserKYcBean;
import com.abhishek.zenq.R;
import com.abhishek.zenq.Response.FleetOwnerAllBidResponse;
import com.abhishek.zenq.Response.GetUSERKYCResponse;
import com.abhishek.zenq.Utility.CommonUtils;
import com.abhishek.zenq.adapter.AdapterAdminGetUser;
import com.abhishek.zenq.rest.ApiClient;
import com.abhishek.zenq.rest.ApiInterface;
import com.abhishek.zenq.rest.AppUrls;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentFleetOwnerAllBid extends Fragment {
    List<AllBidBean> allBidBeanList;
    AllBidBean allBidBean;
    AdapterAllBid adapterAllBid;

    RecyclerView allbidrecycle;
    RecyclerView.LayoutManager layoutManager;

    AVLoadingIndicatorView bar;
View rootView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragmentfleet_owner_all_bid, container, false);
        // fragmentManager = getActivity().getSupportFragmentManager();

        //init();

       find();
      getAllBid();
        return rootView;
    }
    public void find() {
        bar = rootView.findViewById(R.id.bar);
        allbidrecycle = rootView.findViewById(R.id.allbidrecycle);
        allbidrecycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        allbidrecycle.setLayoutManager(layoutManager);
        //allbidrecycle.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        allbidrecycle.setAdapter(adapterAllBid);

    }
    private void getAllBid() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{}, new
                String[]{});
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            Call<FleetOwnerAllBidResponse> call = apiInterface.FleetGetAllBid(builder.build());
            call.enqueue(new Callback<FleetOwnerAllBidResponse>() {
                @Override
                public void onResponse(Call<FleetOwnerAllBidResponse> call, Response<FleetOwnerAllBidResponse> response) {
                    bar.setVisibility(View.GONE);

                    if (response.isSuccessful() && response.body().getStatus().equals("1") &&   response.body().getMsg().equals("Successfully")) {
                        allBidBeanList = new ArrayList<>();

                        for (int i = 0; i < response.body().getInfo().size(); i++) {
                            allBidBean = new AllBidBean();

                            allBidBean.setId(response.body().getInfo().get(i).getId());
                            allBidBean.setFrom_address(response.body().getInfo().get(i).getFrom_address());
                            allBidBean.setTo_address(response.body().getInfo().get(i).getTo_address());
                            allBidBean.setFrom_date(response.body().getInfo().get(i).getFrom_date());
                            allBidBean.setTo_date(response.body().getInfo().get(i).getTo_date());
                            allBidBean.setWeight(response.body().getInfo().get(i).getWeight());
                            allBidBean.setPrice_per_ton(response.body().getInfo().get(i).getPrice_per_ton());
                            allBidBean.setTruck_type(response.body().getInfo().get(i).getTruck_type());
                            allBidBean.setMaterial_type(response.body().getInfo().get(i).getMaterial_type());



                            allBidBeanList.add(allBidBean);
                        }
                        Log.e("allBidBeanList", "" + allBidBeanList.size());
                        adapterAllBid = new AdapterAllBid(getActivity(), allBidBeanList);
                        allbidrecycle.setAdapter(adapterAllBid);
                    } else {
                        Log.e("userOrderHistory1ize", "" + "else");
                    }
                }

                @Override
                public void onFailure(Call<FleetOwnerAllBidResponse> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });
        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }
}
