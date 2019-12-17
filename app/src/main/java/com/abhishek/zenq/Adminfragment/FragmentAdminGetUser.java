package com.abhishek.zenq.Adminfragment;

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

import com.abhishek.zenq.Bean.GetUserKYcBean;
import com.abhishek.zenq.R;
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

public class FragmentAdminGetUser extends Fragment {
    List<GetUserKYcBean> getuserkyclist;
    GetUserKYcBean getUserKYcBean;
    AdapterAdminGetUser adapterAdminGetUser;
    View rootView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView userrecyclelist;
    AVLoadingIndicatorView bar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_admin_getuser, container, false);
        // fragmentManager = getActivity().getSupportFragmentManager();

        //init();

        find();
        getUserKyc();
        return rootView;
    }

    public void find() {
        bar = rootView.findViewById(R.id.bar);
        userrecyclelist = rootView.findViewById(R.id.userrecyclelist);
        userrecyclelist.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        userrecyclelist.setLayoutManager(layoutManager);
        userrecyclelist.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        userrecyclelist.setAdapter(adapterAdminGetUser);

    }


    private void getUserKyc() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{}, new
                String[]{});
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            Call<GetUSERKYCResponse> call = apiInterface.GetUserKYC(builder.build());
            call.enqueue(new Callback<GetUSERKYCResponse>() {
                @Override
                public void onResponse(Call<GetUSERKYCResponse> call, Response<GetUSERKYCResponse> response) {
                    bar.setVisibility(View.GONE);

                    if (response.isSuccessful() && response.body().getStatus().equals("1") && response.isSuccessful() && response.body().getMsg().equals("Successfully")) {
                        getuserkyclist = new ArrayList<>();

                        for (int i = 0; i < response.body().getInfo().size(); i++) {
                            getUserKYcBean = new GetUserKYcBean();

                            getUserKYcBean.setId(response.body().getInfo().get(i).getId());

                            getUserKYcBean.setName(response.body().getInfo().get(i).getName());
                            getUserKYcBean.setEmail(response.body().getInfo().get(i).getEmail());
                            getUserKYcBean.setPassword(response.body().getInfo().get(i).getPassword());
                            getUserKYcBean.setUser_type(response.body().getInfo().get(i).getUser_type());
                            getUserKYcBean.setRole(response.body().getInfo().get(i).getRole());
                            getUserKYcBean.setIs_kyc(response.body().getInfo().get(i).getIs_kyc());
                            getUserKYcBean.setMobile(response.body().getInfo().get(i).getMobile());
                            getUserKYcBean.setIs_mail(response.body().getInfo().get(i).getIs_mail());
                            getUserKYcBean.setBusiness_name(response.body().getInfo().get(i).getBusiness_name());
                            getUserKYcBean.setLast_name(response.body().getInfo().get(i).getLast_name());
                            getUserKYcBean.setAddress(response.body().getInfo().get(i).getAddress());
                            getUserKYcBean.setLocatlity(response.body().getInfo().get(i).getLocatlity());
                            getUserKYcBean.setState(response.body().getInfo().get(i).getState());
                            getUserKYcBean.setCity(response.body().getInfo().get(i).getCity());
                            getUserKYcBean.setPancard(response.body().getInfo().get(i).getPancard());

                            getUserKYcBean.setPassword(response.body().getInfo().get(i).getPincode());
                            getUserKYcBean.setCreate_date(response.body().getInfo().get(i).getCreate_date());
                            getUserKYcBean.setImage(AppUrls.usIMage_aURL+response.body().getInfo().get(i).getImage());
                            Log.e("AppIMAGE", "" + AppUrls.usIMage_aURL+response.body().getInfo().get(i).getImage());


                            getuserkyclist.add(getUserKYcBean);
                        }
                        Log.e("userOrderHistoryBean", "" + getuserkyclist.size());
                        adapterAdminGetUser = new AdapterAdminGetUser(getActivity(), getuserkyclist);
                        userrecyclelist.setAdapter(adapterAdminGetUser);
                    } else {
                        Log.e("userOrderHistory1ize", "" + "else");
                    }
                }

                @Override
                public void onFailure(Call<GetUSERKYCResponse> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });
        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }
}
