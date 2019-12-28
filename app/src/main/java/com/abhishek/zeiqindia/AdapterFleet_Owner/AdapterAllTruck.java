package com.abhishek.zeiqindia.AdapterFleet_Owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.zeiqindia.Bean.TruckBean;
import com.abhishek.zeiqindia.R;

import java.util.List;

public class AdapterAllTruck extends RecyclerView.Adapter<AdapterAllTruck.ViewHolder> {
    Context context;
    String StrRole,StrImagerole;
    String StrUser;
    String StrEmail;
    String StrKyc;
    List<TruckBean> truckBeanList;


    public AdapterAllTruck(Context context, List<TruckBean> truckBeanList) {
        this.context = context;
        this.truckBeanList = truckBeanList;
    }


    @Override
    public AdapterAllTruck.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_fleet_owner_my_truck, viewGroup, false);
        return new AdapterAllTruck.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(  AdapterAllTruck.ViewHolder holder, final int position) {

    holder.tv_driver_date.setText("Date : "+truckBeanList.get(position).getReg_date());
         holder.tvf_own_truck_no.setText("Truck No. : "+truckBeanList.get(position).getReg_no());
        holder.tvf_own_truck_valid.setText("Reg. Valid. : "+truckBeanList.get(position).getReg_vaild());
        holder.tvf_own_truck_permit.setText("Permit Valid. : "+truckBeanList.get(position).getPermit_valid());
        holder.tvf_own_truck_status.setText(truckBeanList.get(position).getIs_kyc());

    }


    @Override
    public int getItemCount() {
        //  Log.e("size", "");
        return truckBeanList.size();


    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_driver_date, tvf_own_truck_no, tvf_own_truck_valid,tvf_own_truck_permit,tvf_own_truck_status;
        Button btn_view;
        ImageView img_truck;


        public ViewHolder(final View itemView) {
            super(itemView);
            tv_driver_date = itemView.findViewById(R.id.tv_driver_date);
            tvf_own_truck_no = itemView.findViewById(R.id.tvf_own_truck_no);
            tvf_own_truck_valid = itemView.findViewById(R.id.tvf_own_truck_valid);
            tvf_own_truck_permit = itemView.findViewById(R.id.tvf_own_truck_permit);
            tvf_own_truck_status = itemView.findViewById(R.id.tvf_own_truck_status);
            btn_view = itemView.findViewById(R.id.btn_view);
            img_truck = itemView.findViewById(R.id.img_truck);




        }
    }
}
