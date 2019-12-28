package com.abhishek.zeiqindia.Location;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.zeiqindia.Adminfragment.ActivityAddState;
import com.abhishek.zeiqindia.Bean.StateBean;
import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Response.GetStateResponse;
import com.abhishek.zeiqindia.Utility.CommonUtils;
import com.abhishek.zeiqindia.adapter.AdapterAdminState;
import com.abhishek.zeiqindia.rest.ApiClient;
import com.abhishek.zeiqindia.rest.ApiInterface;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentGetState extends Fragment implements View.OnClickListener {
    List<StateBean>stateBeanList;
    AdapterAdminState adapterAdminState;
    StateBean stateBean;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView staterecycle;
    AVLoadingIndicatorView bar;
    Button addstate;
    View rootView;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_admin_getstate, container, false);
        // fragmentManager = getActivity().getSupportFragmentManager();


        find();
        getState();
        return rootView;
    }

    public void find() {
        addstate=rootView.findViewById(R.id.addstate);
        bar = rootView.findViewById(R.id.bar);
        staterecycle = rootView.findViewById(R.id.staterecycle);
        staterecycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        staterecycle.setLayoutManager(layoutManager);
        staterecycle.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
       // staterecycle.setAdapter(adapterAdminGetUser);
        addstate.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
if (v==addstate){
    startActivity(new Intent(getActivity(), ActivityAddState.class));
}
    }

    @Override
    public void onResume() {
        super.onResume();
        getState();
    }

    private void getState() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{}, new
                String[]{});
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            Call<GetStateResponse> call = apiInterface.FleetGetState(builder.build());
            call.enqueue(new Callback<GetStateResponse>() {
                @Override
                public void onResponse(Call<GetStateResponse> call, Response<GetStateResponse> response) {
                    bar.setVisibility(View.GONE);

                    if (response.isSuccessful() && response.body().getStatus().equals("1")  && response.body().getMsg().equals("Successfully")) {
                        stateBeanList = new ArrayList<>();

                        for (int i = 0; i < response.body().getInfo().size(); i++) {
                            stateBean = new StateBean();


                            stateBean.setId(response.body().getInfo().get(i).getId());
                            stateBean.setState_code(response.body().getInfo().get(i).getState_code());
                            stateBean.setState_name(response.body().getInfo().get(i).getState_name());
                            stateBeanList.add(stateBean);
                        }
                        Log.e("userOrderHistoryBean", "" + stateBeanList.size());
                        adapterAdminState = new AdapterAdminState(getActivity(), stateBeanList);
                        staterecycle.setAdapter(adapterAdminState);
                    } else {
                        Log.e("userOrderHistory1ize", "" + "else");
                    }
                }

                @Override
                public void onFailure(Call<GetStateResponse> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });
        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }


}
