package com.abhishek.zeiqindia.AdapterFleet_Owner;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.zeiqindia.Bean.MyTripsBean;
import com.abhishek.zeiqindia.FleetOwnerDashBoard.FragmentFleetOwnerMyTrip;
import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Response.AddPartyResponse;
import com.abhishek.zeiqindia.Truck_DriverRegister.ActivityFleetAddTrip;
import com.abhishek.zeiqindia.Truck_DriverRegister.MyTripsView;
import com.abhishek.zeiqindia.Utility.CommonUtils;
import com.abhishek.zeiqindia.Utility.Constants;
import com.abhishek.zeiqindia.rest.ApiClient;
import com.abhishek.zeiqindia.rest.ApiInterface;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterMyTrips extends RecyclerView.Adapter<AdapterMyTrips.ViewHolder> {
    Context context;
    List<MyTripsBean> myTripsBeanList;
    String Str_Statustype, Str_id, StrTrip_Type, TotalBidAmount, Str_Driver_advance;
    ProgressBar progressBar;
    AVLoadingIndicatorView bar;
    FragmentManager fragmentManager;
    Dialog dialog ;


    public AdapterMyTrips(Context context, List<MyTripsBean> myTripsBeanList) {
        this.context = context;
        this.myTripsBeanList = myTripsBeanList;
    }

    @Override
    public AdapterMyTrips.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_fleetowner_mytrips, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterMyTrips.ViewHolder holder, final int position) {
        int TotalWeight = Integer.parseInt(myTripsBeanList.get(position).getWeight());
        int TotalAmount = Integer.parseInt(myTripsBeanList.get(position).getPrice_per_ton());
        int StrAmount = TotalAmount * TotalWeight;
        TotalBidAmount = String.valueOf(StrAmount);
        Str_id = myTripsBeanList.get(position).getId();
        StrTrip_Type = myTripsBeanList.get(position).getTrip_type();
        Str_Statustype = myTripsBeanList.get(position).getIs_status();
        Str_Driver_advance = myTripsBeanList.get(position).getDriver_advance();


        if (myTripsBeanList.get(position).getTrip_type().equalsIgnoreCase("single")) {
            if (myTripsBeanList.get(position).getIs_status().equalsIgnoreCase("0")) {
                holder.btn_start_trip.setVisibility(View.VISIBLE);
                holder.btn_start_trip.setText("Start");
            } else if (myTripsBeanList.get(position).getIs_status().equalsIgnoreCase("1")) {
                holder.btn_start_trip.setVisibility(View.VISIBLE);
                holder.btn_start_trip.setText("Stop");
            }else {
                holder.btn_start_trip.setVisibility(View.VISIBLE);
                holder.btn_start_trip.setText("Completed");
            }
            holder.Imgarrow.setBackgroundResource(R.drawable.ic_arrorcircle);

        } else {
            if (myTripsBeanList.get(position).getIs_status().equalsIgnoreCase("0")) {
                holder.btn_start_trip.setVisibility(View.VISIBLE);

                holder.btn_start_trip.setText("Start");
            } else if (myTripsBeanList.get(position).getIs_status().equalsIgnoreCase("1")) {
                holder.btn_start_trip.setVisibility(View.VISIBLE);

                holder.btn_start_trip.setText("Stop");
            } else if (myTripsBeanList.get(position).getIs_status().equalsIgnoreCase("2")) {
                holder.btn_start_trip.setVisibility(View.VISIBLE);

                holder.btn_start_trip.setText("Start");
            } else if (myTripsBeanList.get(position).getIs_status().equalsIgnoreCase("3")) {
                holder.btn_start_trip.setVisibility(View.VISIBLE);

                holder.btn_start_trip.setText("Stop");
            }else {
                holder.btn_start_trip.setVisibility(View.VISIBLE);
                holder.btn_start_trip.setText("Completed");
            }

            holder.Imgarrow.setBackgroundResource(R.drawable.round_trip_icon);
        }
        holder.tv_to.setText(myTripsBeanList.get(position).getTo_city());
        holder.tv_to_add.setText(myTripsBeanList.get(position).getTo_state());
        holder.tv_from.setText(myTripsBeanList.get(position).getFrom_city());
        holder.tv_from_add.setText(myTripsBeanList.get(position).getFrom_state());
        holder.tv_goods.setText(myTripsBeanList.get(position).getMaterial_type());
        holder.tv_weight.setText(myTripsBeanList.get(position).getWeight() + " Ton");
        holder.tv_expire.setText(myTripsBeanList.get(position).getTo_date());
        holder.tv_weight_body.setText(myTripsBeanList.get(position).getTruck_type());
        holder.tv_bidd_total.setText("\u20B9 " + TotalBidAmount);
        holder.tv_bidd_amount.setText("\u20B9 " + myTripsBeanList.get(position).getPrice_per_ton() + "/" + "T");

        holder.btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyTripsView.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("KeyArray", myTripsBeanList.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.btn_start_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.e("positioon", "" + myTripsBeanList.get(position).getId());
                Str_id = myTripsBeanList.get(position).getId();
                Log.e("positioonQ", "" + myTripsBeanList.get(position));
                Str_Driver_advance= myTripsBeanList.get(position).getDriver_advance();
                Log.e("1212", Str_Driver_advance);

                if (myTripsBeanList.get(position).getTrip_type().equalsIgnoreCase("single")) {
                    if (myTripsBeanList.get(position).getIs_status().equalsIgnoreCase("0")) {

                        if (Str_Driver_advance.equals("0")) {


                            AdvanceClick(Str_id);
                            //btn_exp.setVisibility(View.GONE);
                        } else {
                            Str_Statustype = "1";
                            getStartTrip(Str_id, Str_Statustype);
                        }


                    } else if (myTripsBeanList.get(position).getIs_status().equalsIgnoreCase("1")) {
                        Str_Statustype = "5";
                        getStartTrip(Str_id, Str_Statustype);
                    }


                } else {
                    if (myTripsBeanList.get(position).getIs_status().equalsIgnoreCase("0")) {
                        if (Str_Driver_advance.equals("0")) {
                            AdvanceClick(Str_id);
                            //btn_exp.setVisibility(View.GONE);
                        } else {
                            Str_Statustype = "1";
                            getStartTrip(Str_id, Str_Statustype);
                        }


                    } else if (myTripsBeanList.get(position).getIs_status().equalsIgnoreCase("1")) {
                        Str_Statustype = "2";
                        getStartTrip(Str_id, Str_Statustype);

                    } else if (myTripsBeanList.get(position).getIs_status().equalsIgnoreCase("2")) {
                        Str_Statustype = "3";
                        getStartTrip(Str_id, Str_Statustype);

                    } else if (myTripsBeanList.get(position).getIs_status().equalsIgnoreCase("3")) {
                        Str_Statustype = "5";
                        getStartTrip(Str_id, Str_Statustype);


                    }

                }




            }
        });
    }

    @Override
    public int getItemCount() {
        //  Log.e("size", "");
        return myTripsBeanList.size();


    }
    public void OnRefreshTotal1() {

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (fragment instanceof FragmentFleetOwnerMyTrip) // avoid crash if cast fail
        {

            ((FragmentFleetOwnerMyTrip) fragment).RefreshApi();
        }
    }

    private void getAdvanceTrip(String Tripid, String Str_amount) {
        bar.setVisibility(View.VISIBLE);
        //   progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"trip_id", "amount"}, new
                String[]{Tripid, Str_amount});
        if (CommonUtils.isNetworkAvailable(context)) {
            Call<AddPartyResponse> call = apiInterface.Fleet_AddAdvanceTrip(builder.build());
            call.enqueue(new Callback<AddPartyResponse>() {
                @Override
                public void onResponse(Call<AddPartyResponse> call, Response<AddPartyResponse> response) {

                    if (response.isSuccessful() && response.body().getStatus().equals("1")) {
                        // notifyDataSetChanged();
                       // OnRefreshTotal1();
        /*                Intent intent = new Intent(context, MyTripsView.class);
                        context.startActivity(intent);*/
                    // dialog.dismiss();


                    }

                    Log.e("allmyTripsBeanList", "" + myTripsBeanList.size());
                }


                @Override
                public void onFailure(Call<AddPartyResponse> call, Throwable t) {
                }
            });
        } else {
            Toast.makeText(context, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }


    }


    private void AdvanceClick(final String Tripid) {
      dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dilaog_my_trips_popup);
        bar = dialog.findViewById(R.id.bar);
        final EditText adv_amount = (EditText) dialog.findViewById(R.id.adv_amount);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        Button ok = (Button) dialog.findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adv_amountStr = adv_amount.getText().toString();

                if (adv_amountStr.isEmpty()) {
                    adv_amount.setError("Please Enter Amount");

                    //hitForgotAPI(emailStr, dialog);
                } else {
                    getAdvanceTrip(Tripid, adv_amountStr);

                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void getStartTrip(String Str_id, String Str_Statustype) {
        //bar.setVisibility(View.VISIBLE);
        //   progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"trip_id", "is_status"}, new
                String[]{Str_id, Str_Statustype});
        if (CommonUtils.isNetworkAvailable(context)) {
            Call<AddPartyResponse> call = apiInterface.Fleet_ChangeTrip(builder.build());
            call.enqueue(new Callback<AddPartyResponse>() {
                @Override
                public void onResponse(Call<AddPartyResponse> call, Response<AddPartyResponse> response) {
                    //     bar.setVisibility(View.GONE);
                    //            progressBar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body().getStatus().equals("1")) {
                        // notifyDataSetChanged();
           //             OnRefreshTotal1();


                    }

                    Log.e("allmyTripsBeanList", "" + myTripsBeanList.size());
                }


                @Override
                public void onFailure(Call<AddPartyResponse> call, Throwable t) {
                }
            });
        } else {
            Toast.makeText(context, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }


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
            tv_bidd_amount = itemView.findViewById(R.id.tv_bidd_amount);
            btn_view = itemView.findViewById(R.id.btn_view);
            btn_start_trip = itemView.findViewById(R.id.btn_start_trip);
            Imgarrow = itemView.findViewById(R.id.arrow);

            btn_com_trip = itemView.findViewById(R.id.btn_com_trip);


        }
    }

}
