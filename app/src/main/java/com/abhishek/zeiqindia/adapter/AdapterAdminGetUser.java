package com.abhishek.zeiqindia.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.zeiqindia.Adminfragment.ActivityGet_Profile_Show_KYC;
import com.abhishek.zeiqindia.Bean.GetUserKYcBean;
import com.abhishek.zeiqindia.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AdapterAdminGetUser extends RecyclerView.Adapter<AdapterAdminGetUser.ViewHolder> {
    Context context;
    String StrRole,StrImagerole;
    String StrUser;
    String StrEmail;
    String StrKyc;
    List<GetUserKYcBean> userKYcBeans;


    public AdapterAdminGetUser(Context context, List<GetUserKYcBean> userKYcBeans) {
        this.context = context;
        this.userKYcBeans = userKYcBeans;
    }


    @NonNull
    @Override
    public AdapterAdminGetUser.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_getuser_adapter, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterAdminGetUser.ViewHolder holder, final int position) {
        StrImagerole = userKYcBeans.get(position).getImage();
        Log.e("AppIMAGE1", "" +userKYcBeans.get(position).getImage());

        Picasso.with(context).load(StrImagerole).into(holder.userimage);

        StrRole = userKYcBeans.get(position).getRole();
        StrUser = userKYcBeans.get(position).getName();
        StrEmail = userKYcBeans.get(position).getEmail();
        StrKyc = userKYcBeans.get(position).getIs_kyc();
        if (StrKyc.equals("0")) {
            holder.btn_kycverify.setText("KYC Pending \n Please click here");
        } else if (StrKyc.equals("1")) {
            holder.btn_kycverify.setText("KYC Complete");
            holder.btn_kycverify.setBackgroundColor(context.getResources().getColor(R.color.green));
        }
        holder.tv_role.setText("Role  :" + StrRole);
        holder.tv_usertype.setText("Name :" + StrUser);
        holder.tv_email.setText("Email  :" + StrEmail);
        holder.btn_kycverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(context, ActivityGet_Profile_Show_KYC.class);
                intent.putExtra("user_id", userKYcBeans.get(position).getId());

                intent.putExtra("name", userKYcBeans.get(position).getName());
                intent.putExtra("email", userKYcBeans.get(position).getEmail());
                intent.putExtra("password", userKYcBeans.get(position).getPassword());
                intent.putExtra("user_type", userKYcBeans.get(position).getUser_type());
                intent.putExtra("role", userKYcBeans.get(position).getRole());
                intent.putExtra("Is_KYC", userKYcBeans.get(position).getIs_kyc());
                intent.putExtra("mobile", userKYcBeans.get(position).getMobile());
                intent.putExtra("is_mail", userKYcBeans.get(position).getIs_mail());
                intent.putExtra("buisnes_name", userKYcBeans.get(position).getBusiness_name());
                intent.putExtra("last_name", userKYcBeans.get(position).getLast_name());
                intent.putExtra("address", userKYcBeans.get(position).getAddress());
                intent.putExtra("locality", userKYcBeans.get(position).getLocatlity());
                intent.putExtra("state", userKYcBeans.get(position).getState());
                intent.putExtra("city", userKYcBeans.get(position).getCity());
                intent.putExtra("pancard", userKYcBeans.get(position).getPancard());

                intent.putExtra("image", userKYcBeans.get(position).getImage());


                intent.putExtra("pincode", userKYcBeans.get(position).getPincode());
                intent.putExtra("createdate", userKYcBeans.get(position).getCreate_date());
                context.startActivity(intent);


            }
        });
    }


    @Override
    public int getItemCount() {
        //  Log.e("size", "");
        return userKYcBeans.size();


    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_usertype, tv_email, tv_role;
        ImageView userimage;
        Button btn_kycverify;


        public ViewHolder(final View itemView) {
            super(itemView);
            tv_usertype = itemView.findViewById(R.id.tv_usertype);
            tv_role = itemView.findViewById(R.id.tv_role);
            btn_kycverify = itemView.findViewById(R.id.btn_kycverify);
            tv_email = itemView.findViewById(R.id.tv_email);
            userimage = itemView.findViewById(R.id.user_pic);


        }
    }
}