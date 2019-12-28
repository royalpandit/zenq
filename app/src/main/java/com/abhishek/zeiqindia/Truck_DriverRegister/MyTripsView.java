package com.abhishek.zeiqindia.Truck_DriverRegister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.abhishek.zeiqindia.Bean.MyTripsBean;
import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Utility.AppBaseActivity;

import java.util.ArrayList;

public class MyTripsView extends AppBaseActivity implements View.OnClickListener {
    TextView tv_to, tv_to_add, tv_from, tv_from_add, tv_goods,
            tv_bidd_total, tv_weight, tv_expire, tv_weight_body, tv_bidd_amount, edit_advance,
            edit_shortage, edit_deduction, edit_munsiyana, edit_commision, edit_other, edit_is_status, edit_party, edit_trip_type;
MyTripsBean myTripsBean;
ImageView back;
String Str_driver_advance,Str_trip_id;
Button btn_exp,btn_load;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips_view);


       find();


        myTripsBean= (MyTripsBean) getIntent().getSerializableExtra("KeyArray");
        Str_trip_id=myTripsBean.getId();
        tv_to.setText(myTripsBean.getTo_city());
        tv_to_add.setText(myTripsBean.getTo_state());
        tv_from.setText(myTripsBean.getFrom_city());
        tv_from_add.setText(myTripsBean.getFrom_state());
        tv_goods.setText(myTripsBean.getMaterial_type());
        tv_weight.setText(myTripsBean.getWeight() + " Ton");
        tv_expire.setText(myTripsBean.getTo_date());
        tv_bidd_total.setText(myTripsBean.getTo_city());
        tv_weight_body.setText(myTripsBean.getTruck_type());
        tv_bidd_amount.setText("\u20B9 " +myTripsBean.getPrice_per_ton() + "/" + "T");
        edit_advance.setText("Advance : "+myTripsBean.getAdvance());
        edit_shortage.setText("Shortage : "+myTripsBean.getShortage());
        edit_deduction.setText("Deduction : "+myTripsBean.getDeduction());
        edit_munsiyana.setText("Munsiyana : "+myTripsBean.getMunsiyana());
        edit_commision.setText("Commision : "+myTripsBean.getCommision());
        edit_other.setText("Other : "+myTripsBean.getOther());
        edit_is_status.setText("Status : "+myTripsBean.getIs_status());
        edit_party.setText("Party Name : "+myTripsBean.getParty());
        edit_trip_type.setText("Trip Type : "+myTripsBean.getTrip_type());
        Str_driver_advance=myTripsBean.getDriver_advance();
        if (Str_driver_advance.equals("0")){
            btn_exp.setVisibility(View.GONE);
        }else {
            btn_exp.setVisibility(View.VISIBLE);
        }

    }
    public void find() {
        back = findViewById(R.id.back);
        btn_exp = findViewById(R.id.btn_exp);
        btn_load = findViewById(R.id.btn_load);

        tv_to = findViewById(R.id.tv_to);
        tv_to_add = findViewById(R.id.tv_to_add);
        tv_from = findViewById(R.id.tv_from);
        tv_from_add = findViewById(R.id.tv_from_add);
        tv_goods = findViewById(R.id.tv_goods);
        tv_weight = findViewById(R.id.tv_weight);
        tv_expire = findViewById(R.id.tv_expire);
        tv_bidd_total = findViewById(R.id.tv_bidd_total);
        tv_weight_body = findViewById(R.id.tv_weight_body);
        tv_bidd_amount = findViewById(R.id.tv_bidd_amount);
        edit_advance = findViewById(R.id.edit_advance);
        edit_shortage = findViewById(R.id.edit_shortage);
        edit_deduction = findViewById(R.id.edit_deduction);
        edit_munsiyana = findViewById(R.id.edit_munsiyana);
        edit_commision = findViewById(R.id.edit_commision);
        edit_other = findViewById(R.id.edit_other);
        edit_is_status = findViewById(R.id.edit_is_status);
        edit_party = findViewById(R.id.edit_party);
        edit_trip_type = findViewById(R.id.edit_trip_type);
        btn_load.setOnClickListener(this);
        btn_exp.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==back){
            onBackPressed();
        }if (v==btn_load){
            Intent intent = new Intent(mContext, ActivityFleetAddTripLoadlist.class);
            intent.putExtra("trip_id",myTripsBean.getId());
            startActivity(intent);

        }if (v==btn_exp){
            Intent intent = new Intent(mContext, Activity_FleetTripExpList.class);
            intent.putExtra("trip_id",myTripsBean.getId());
            startActivity(intent);
        }
    }
}
