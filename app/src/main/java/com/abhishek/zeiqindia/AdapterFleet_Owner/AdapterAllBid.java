package com.abhishek.zeiqindia.AdapterFleet_Owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.zeiqindia.Bean.AllBidBean;
import com.abhishek.zeiqindia.R;

import java.util.List;

public class AdapterAllBid extends RecyclerView.Adapter<AdapterAllBid.ViewHolder> {
    Context context;
    String StrRole,StrImagerole;
    String StrUser;
    String StrEmail;
    String StrKyc;
    List<AllBidBean> allBidBeanList;


    public AdapterAllBid(Context context, List<AllBidBean> allBidBeanList) {
        this.context = context;
        this.allBidBeanList = allBidBeanList;
    }


     @Override
    public AdapterAllBid.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ownerall_bid_adapter, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(  AdapterAllBid.ViewHolder holder, final int position) {
        holder.tv_to.setText(allBidBeanList.get(position).getTo_city());
        holder.tv_to_add.setText(allBidBeanList.get(position).getTo_state());
        holder.tv_from.setText(allBidBeanList.get(position).getFrom_city());
        holder.tv_from_add.setText(allBidBeanList.get(position).getFrom_state());
        holder.tv_goods.setText(allBidBeanList.get(position).getMaterial_type());
        holder.tv_weight.setText(allBidBeanList.get(position).getWeight()+" Ton");
        holder.tv_expire.setText("Expires on \n "+allBidBeanList.get(position).getTo_date());
        holder.tv_weight_body.setText(allBidBeanList.get(position).getTruck_type());
        holder.tv_bidd_amount.setText(allBidBeanList.get(position).getPrice_per_ton()+"/"+"T");
      }


    @Override
    public int getItemCount() {
        //  Log.e("size", "");
        return allBidBeanList.size();


    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_to, tv_to_add, tv_from,tv_from_add,tv_goods,tv_weight,tv_expire,tv_weight_body,tv_bidd_amount;
         Button btn_bid,btn_book;


        public ViewHolder(final View itemView) {
            super(itemView);
            tv_to = itemView.findViewById(R.id.tv_to);
            tv_to_add = itemView.findViewById(R.id.tv_to_add);
            tv_from = itemView.findViewById(R.id.tv_from);
            tv_from_add = itemView.findViewById(R.id.tv_from_add);
            tv_goods = itemView.findViewById(R.id.tv_goods);
            tv_weight = itemView.findViewById(R.id.tv_weight);
            tv_expire = itemView.findViewById(R.id.tv_expire);
            tv_weight_body = itemView.findViewById(R.id.tv_weight_body);
            tv_bidd_amount = itemView.findViewById(R.id.tv_bidd_amount);
            btn_bid = itemView.findViewById(R.id.btn_bid);
            btn_book = itemView.findViewById(R.id.btn_book);




        }
    }
}