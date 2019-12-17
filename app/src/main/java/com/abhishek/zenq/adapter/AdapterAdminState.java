package com.abhishek.zenq.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.abhishek.zenq.Bean.StateBean;
import com.abhishek.zenq.Location.FragmentGetCity;
import com.abhishek.zenq.Location.FragmentGetState;
import com.abhishek.zenq.R;

import java.util.List;

public class AdapterAdminState extends RecyclerView.Adapter<AdapterAdminState.ViewHolder> {
    Context context;
    String StrRole,StrImagerole;
    String StrUser;
    String StrEmail;
    String StrKyc;
    List<StateBean> stateBeanList;


    public AdapterAdminState(Context context, List<StateBean> stateBeanList) {
        this.context = context;
        this.stateBeanList = stateBeanList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_getstate, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        holder.textstate_code.setText(stateBeanList.get(position).getState_code());
        holder.textstate.setText(stateBeanList.get(position).getState_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, FragmentGetCity.class);
                intent.putExtra("state_id",stateBeanList.get(position).getId());
                intent.putExtra("state_code",stateBeanList.get(position).getState_code());
                intent.putExtra("state_name",stateBeanList.get(position).getState_name());
                context.startActivity(intent);
            }
        });



    }


    @Override
    public int getItemCount() {
        //  Log.e("size", "");
        return stateBeanList.size();


    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textstate, textstate_code;


        public ViewHolder(final View itemView) {
            super(itemView);
            textstate_code = itemView.findViewById(R.id.textstate_code);
            textstate = itemView.findViewById(R.id.textstate);


        }
    }
}
