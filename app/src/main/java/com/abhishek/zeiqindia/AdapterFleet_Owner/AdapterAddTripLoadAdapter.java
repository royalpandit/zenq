package com.abhishek.zeiqindia.AdapterFleet_Owner;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.zeiqindia.Bean.GetTripBean;
import com.abhishek.zeiqindia.R;
import com.wang.avi.AVLoadingIndicatorView;

import org.w3c.dom.Text;

import java.util.List;


public class AdapterAddTripLoadAdapter extends RecyclerView.Adapter<AdapterAddTripLoadAdapter.ViewHolder> {
    Context context;
    List<GetTripBean> getTripBeanList;
    String Str_Statustype, Str_id, StrTrip_Type, TotalBidAmount, Str_Driver_advance;
    ProgressBar progressBar;
    AVLoadingIndicatorView bar;

    public AdapterAddTripLoadAdapter(Context context, List<GetTripBean> getTripBeanList) {
        this.context = context;
        this.getTripBeanList = getTripBeanList;
    }

    @Override
    public AdapterAddTripLoadAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_fleetowner_mytrips, viewGroup, false);
        return new AdapterAddTripLoadAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterAddTripLoadAdapter.ViewHolder holder, final int position) {
        int TotalWeight = Integer.parseInt(getTripBeanList.get(position).getWeight());
        int TotalAmount = Integer.parseInt(getTripBeanList.get(position).getPrice_per_ton());
        int StrAmount = TotalAmount * TotalWeight;
        TotalBidAmount = String.valueOf(StrAmount);
        Str_id = getTripBeanList.get(position).getId();
        StrTrip_Type = getTripBeanList.get(position).getTrip_type();


        holder.tv_to.setText(getTripBeanList.get(position).getTo_city());
        holder.tv_to_add.setText(getTripBeanList.get(position).getTo_state());
        holder.tv_from.setText(getTripBeanList.get(position).getFrom_city());
        holder.tv_from_add.setText(getTripBeanList.get(position).getFrom_state());
        holder.tv_goods.setText(getTripBeanList.get(position).getMaterial_type());
        holder.tv_weight.setText(getTripBeanList.get(position).getWeight() + " Ton");
        holder.tv_expire.setText(getTripBeanList.get(position).getTo_date());
        holder.tv_weight_body.setText(getTripBeanList.get(position).getTruck_type());
        holder.tv_bidd_total.setText("\u20B9 " + TotalBidAmount);
        holder.tv_bidd_amount.setText("\u20B9 " + getTripBeanList.get(position).getPrice_per_ton() + "/" + "T");

        holder.btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvanceClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        //  Log.e("size", "");
        return getTripBeanList.size();


    }
    private void AdvanceClick(int position) {
      final Dialog  dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_fleetadddtrip_view);
        TextView edit_weight=dialog.findViewById(R.id.edit_weight);
        TextView edit_price_per_ton=dialog.findViewById(R.id.edit_price_per_ton);
        TextView edit_metarial_type=dialog.findViewById(R.id.edit_metarial_type);
        TextView edit_advance=dialog.findViewById(R.id.edit_advance);
        TextView edit_shortage=dialog.findViewById(R.id.edit_shortage);
        TextView edit_deduction=dialog.findViewById(R.id.edit_deduction);
        TextView edit_munsiyana=dialog.findViewById(R.id.edit_munsiyana);
        TextView edit_commision=dialog.findViewById(R.id.edit_commision);
        TextView tv_fromstate=dialog.findViewById(R.id.tv_fromstate);
        TextView tv_fromcity=dialog.findViewById(R.id.tv_fromcity);
        TextView tv_tostate=dialog.findViewById(R.id.tv_tostate);
        TextView tv_tocity=dialog.findViewById(R.id.tv_tocity);
        TextView edit_party=dialog.findViewById(R.id.edit_party);
        Button btn_ok=dialog.findViewById(R.id.btn_ok);
        edit_weight.setText(getTripBeanList.get(position).getWeight() + " Ton");
        edit_price_per_ton.setText(getTripBeanList.get(position).getPrice_per_ton());
        edit_metarial_type.setText(getTripBeanList.get(position).getMaterial_type());
        edit_advance.setText(getTripBeanList.get(position).getAdvance());
        edit_shortage.setText(getTripBeanList.get(position).getShortage());
        edit_deduction.setText(getTripBeanList.get(position).getDeduction());
        edit_munsiyana.setText(getTripBeanList.get(position).getMunsiyana());
        edit_commision.setText(getTripBeanList.get(position).getCommision());
        tv_fromstate.setText(getTripBeanList.get(position).getFrom_state());
        tv_fromcity.setText(getTripBeanList.get(position).getFrom_city());
        tv_tostate.setText(getTripBeanList.get(position).getTo_state());
        tv_tocity.setText(getTripBeanList.get(position).getTo_city());
        edit_party.setText(getTripBeanList.get(position).getParty());
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        //  final EditText adv_amount = (EditText) dialog.findViewById(R.id.adv_amount);
//        Button cancel = (Button) dialog.findViewById(R.id.cancel);
  //      Button ok = (Button) dialog.findViewById(R.id.ok);

        dialog.show();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_to, tv_to_add, tv_from, tv_from_add, tv_goods, tv_bidd_total, tv_weight, tv_expire, tv_weight_body, tv_bidd_amount;
        Button btn_view, btn_start_trip, btn_com_trip;
        ImageView Imgarrow;


        public ViewHolder(final View itemView) {
            super(itemView);
            tv_to = itemView.findViewById(R.id.tv_to);
            tv_to_add = itemView.findViewById(R.id.tv_to_add);
            tv_from = itemView.findViewById(R.id.tv_from);
            tv_from_add = itemView.findViewById(R.id.tv_from_add);
            tv_goods = itemView.findViewById(R.id.tv_goods);
            tv_weight = itemView.findViewById(R.id.tv_weight);
            tv_expire = itemView.findViewById(R.id.tv_expire);
            tv_bidd_total = itemView.findViewById(R.id.tv_bidd_total);
            tv_weight_body = itemView.findViewById(R.id.tv_weight_body);
            tv_weight_body.setVisibility(View.GONE);
            tv_bidd_amount = itemView.findViewById(R.id.tv_bidd_amount);
            btn_view = itemView.findViewById(R.id.btn_view);
            btn_start_trip = itemView.findViewById(R.id.btn_start_trip);
            Imgarrow = itemView.findViewById(R.id.arrow);

            btn_com_trip = itemView.findViewById(R.id.btn_com_trip);
            btn_start_trip.setVisibility(View.GONE);

        }
    }
}