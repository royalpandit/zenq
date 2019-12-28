package com.abhishek.zeiqindia.AdapterFleet_Owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.zeiqindia.Bean.MentianenceBean;
import com.abhishek.zeiqindia.Bean.TruckBean;
import com.abhishek.zeiqindia.R;

import java.util.List;

public class AdapterMaintenenceFleetOwner extends RecyclerView.Adapter<AdapterMaintenenceFleetOwner.ViewHolder> {
    Context context;
    List<MentianenceBean> mentianenceBeanList;


    public AdapterMaintenenceFleetOwner(Context context, List<MentianenceBean> mentianenceBeanList) {
        this.context = context;
        this.mentianenceBeanList = mentianenceBeanList;
    }


    @Override
    public AdapterMaintenenceFleetOwner.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_fleetowner_maintenence, viewGroup, false);
        return new AdapterMaintenenceFleetOwner.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(  AdapterMaintenenceFleetOwner.ViewHolder holder, final int position) {

       holder.tv_main_date.setText(mentianenceBeanList.get(position).getCreate_date());
          holder.tvf_own_servicece_main.setText("Company : "+mentianenceBeanList.get(position).getCompany());
        holder.tvf_own_main_vendor_name.setText("Name : "+mentianenceBeanList.get(position).getName());
       holder.tvf_own_vendor_mobile.setText("Phone : "+mentianenceBeanList.get(position).getPhone());

    }


    @Override
    public int getItemCount() {
        //  Log.e("size", "");
        return mentianenceBeanList.size();


    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_main_date, tvf_own_servicece_main, tvf_own_main_vendor_name,tvf_own_vendor_mobile;
        Button btn__main_view;
        ImageView img_truck;


        public ViewHolder(final View itemView) {
            super(itemView);
            tv_main_date = itemView.findViewById(R.id.tv_main_date);
            tvf_own_servicece_main = itemView.findViewById(R.id.tvf_own_servicece_main);
            tvf_own_main_vendor_name = itemView.findViewById(R.id.tvf_own_main_vendor_name);
            tvf_own_vendor_mobile = itemView.findViewById(R.id.tvf_own_vendor_mobile);
            btn__main_view = itemView.findViewById(R.id.btn__main_view);




        }
    }
}

