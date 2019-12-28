package com.abhishek.zeiqindia.AdapterFleet_Owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.zeiqindia.Bean.GetLoadBean;
import com.abhishek.zeiqindia.R;

import java.util.List;

public class AdapterAddTripExpAdapter extends RecyclerView.Adapter<AdapterAddTripExpAdapter.ViewHolder> {
    Context context;
    List<GetLoadBean> getLoadBeanList;
    String Str_ExpensesType, Str_id, StrTrip_Type, TotalBidAmount, Str_Driver_advance;

    public AdapterAddTripExpAdapter(Context context, List<GetLoadBean> getLoadBeanList) {
        this.context = context;
        this.getLoadBeanList = getLoadBeanList;
    }

    @Override
    public AdapterAddTripExpAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapterfleet_owner_myexp, viewGroup, false);
        return new AdapterAddTripExpAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterAddTripExpAdapter.ViewHolder holder, final int position) {

           holder.dates.setText(getLoadBeanList.get(position).getCreate_date());
        Str_ExpensesType=getLoadBeanList.get(position).getExpenses_type();
        if (Str_ExpensesType.equalsIgnoreCase("Disel")){

            holder.linmake.setVisibility(View.VISIBLE);
            holder.tv_toexp_type_name.setText(getLoadBeanList.get(position).getExpenses_type()+" / "+getLoadBeanList.get(position).getExpenses_amount());
            holder.tv_d_quantity.setText(getLoadBeanList.get(position).getD_qty()+"ltr");
            holder.tv_d_Rate.setText(getLoadBeanList.get(position).getD_rate()+"/ltr");
            holder.tv_d_amount.setText(getLoadBeanList.get(position).getD_amount());
            holder.tv_d_KM.setText(getLoadBeanList.get(position).getD_km());
            holder.tv_tyreMake.setVisibility(View.GONE);
            holder.tv_totyre_make.setVisibility(View.GONE);

           // holder.tv_tyreMake.setText("Null");
            holder.tv_tod_qty.setText("Diesel Quantity");
            holder.tv_tod_rate.setText("Diesel Price");
            holder.tv_tod_amount.setText("Total Amount");
            holder.tv_tod_km.setText("KiloMeter");
           // holder.tv_totyre_make.setText("Other");




        }else if (Str_ExpensesType.equalsIgnoreCase("Tyre")){
            holder.linmake.setVisibility(View.VISIBLE);

            holder.tv_toexp_type_name.setText(getLoadBeanList.get(position).getExpenses_type()+" / "+getLoadBeanList.get(position).getExpenses_amount());
            holder.tv_d_quantity.setText(getLoadBeanList.get(position).getT_qty());
            holder.tv_d_Rate.setText(getLoadBeanList.get(position).getT_make());
            holder.tv_d_amount.setText(getLoadBeanList.get(position).getT_amount());
            holder.tv_d_KM.setText(getLoadBeanList.get(position).getT_model());
            holder.tv_tyreMake.setText(getLoadBeanList.get(position).getT_serial_no());

            holder.tv_tod_qty.setText("Tyre Quantity");
            holder.tv_tod_rate.setText("Tyre Make");
            holder.tv_tod_amount.setText("Tyre Amount");
            holder.tv_tod_km.setText("Tyre Modal");
            holder.tv_totyre_make.setText("Tyre Serial No.");
        }else if  (Str_ExpensesType.equalsIgnoreCase("Other")){
            holder.linother.setVisibility(View.VISIBLE);

            holder.tv_toexp_type_name.setText(getLoadBeanList.get(position).getExpenses_type()+" / "+getLoadBeanList.get(position).getExpenses_amount());
            holder.tv_toexp_type_other.setText(getLoadBeanList.get(position).getComment());



        }else {
            holder.tv_toexp_type_name.setText(getLoadBeanList.get(position).getExpenses_type()+" / "+getLoadBeanList.get(position).getExpenses_amount());



        }




    }

    @Override
    public int getItemCount() {
        //  Log.e("size", "");
        return getLoadBeanList.size();


    }



    public class ViewHolder extends RecyclerView.ViewHolder {
TextView tv_tod_qty,tv_tod_rate,tv_tod_amount,tv_tod_km,tv_totyre_make;
        TextView tv_toamount, dates, tv_toexp_type_other, tv_toexp_type_name, tv_d_quantity, tv_d_Rate, tv_d_amount, tv_d_KM, tv_tyreMake ;
        LinearLayout linmake,linother;


        public ViewHolder(final View itemView) {
            super(itemView);
            /*tv_toamount = itemView.findViewById(R.id.tv_toamount);
          */
            tv_toexp_type_name = itemView.findViewById(R.id.tv_toexp_type_name);
            tv_d_quantity = itemView.findViewById(R.id.tv_d_quantity);
            tv_d_Rate = itemView.findViewById(R.id.tv_d_Rate);
            tv_d_amount = itemView.findViewById(R.id.tv_d_amount);
            tv_d_KM = itemView.findViewById(R.id.tv_d_KM);
            tv_tyreMake = itemView.findViewById(R.id.tv_tyreMake);
             linmake = itemView.findViewById(R.id.linmake);
            linother = itemView.findViewById(R.id.linother);
            tv_toexp_type_other = itemView.findViewById(R.id.tv_toexp_type_other);
            dates = itemView.findViewById(R.id.dates);






            tv_tod_qty = itemView.findViewById(R.id.tv_tod_qty);
            tv_tod_rate = itemView.findViewById(R.id.tv_tod_rate);
            tv_tod_amount = itemView.findViewById(R.id.tv_tod_amount);
            tv_tod_km = itemView.findViewById(R.id.tv_tod_km);
            tv_totyre_make = itemView.findViewById(R.id.tv_totyre_make);


        }
    }
}