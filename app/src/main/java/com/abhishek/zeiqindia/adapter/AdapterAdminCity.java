package com.abhishek.zeiqindia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.zeiqindia.Bean.CityBean;
import com.abhishek.zeiqindia.R;

import java.util.List;

public class AdapterAdminCity extends RecyclerView.Adapter<AdapterAdminCity.ViewHolder> {
    Context context;
    String StrRole,StrImagerole;
    String StrUser;
    String StrEmail;
    String StrKyc;
    List<CityBean> cityBeanList;


    public AdapterAdminCity(Context context, List<CityBean> cityBeanList) {
        this.context = context;
        this.cityBeanList = cityBeanList;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_getcity, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder( ViewHolder holder, final int position) {


        holder.state.setText(cityBeanList.get(position).getState_name());

        holder.city.setText(cityBeanList.get(position).getCity_name());

    }


    @Override
    public int getItemCount() {
        //  Log.e("size", "");
        return cityBeanList.size();


    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView state, s_code,city;


        public ViewHolder(final View itemView) {
            super(itemView);
            state = itemView.findViewById(R.id.state);
/*
            s_code = itemView.findViewById(R.id.s_code);
*/
            city = itemView.findViewById(R.id.city);


        }
    }
}
