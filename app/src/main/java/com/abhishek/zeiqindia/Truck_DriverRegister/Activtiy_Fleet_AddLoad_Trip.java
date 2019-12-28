package com.abhishek.zeiqindia.Truck_DriverRegister;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.abhishek.zeiqindia.AdapterFleet_Owner.AdapterGetParty;
import com.abhishek.zeiqindia.Bean.CityBean;
import com.abhishek.zeiqindia.Bean.DriverBean;
import com.abhishek.zeiqindia.Bean.PartyBean;
import com.abhishek.zeiqindia.Bean.StateBean;
import com.abhishek.zeiqindia.Bean.TruckBean;
import com.abhishek.zeiqindia.Prefrence.AppPreferences;
import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Response.AddTripResponse;
import com.abhishek.zeiqindia.Response.FleetOwnerDriverResponse;
import com.abhishek.zeiqindia.Response.FleetOwnerTruckResponse;
import com.abhishek.zeiqindia.Response.FleetownerAddTripMOreResponse;
import com.abhishek.zeiqindia.Response.GepPartyResponse;
import com.abhishek.zeiqindia.Response.GetCityResponse;
import com.abhishek.zeiqindia.Response.GetStateResponse;
import com.abhishek.zeiqindia.Utility.AppBaseActivity;
import com.abhishek.zeiqindia.Utility.CommonUtils;
import com.abhishek.zeiqindia.rest.ApiClient;
import com.abhishek.zeiqindia.rest.ApiInterface;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activtiy_Fleet_AddLoad_Trip extends AppBaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    ArrayAdapter Str_spinner_fromstate, Str_spinner_fromcity,Str_Adapterspinner_party ;
    List<StateBean> stateBeanList;
    List<PartyBean> partyBeanList;
    PartyBean partyBean;
String Owner_id;
    LinearLayout editcategory_tripdriver,editcategory_triptruck,editcategory_triptype;
    StateBean stateBean;
    CityBean cityBean;
    List<CityBean >cityBeanList;
         String dates="0",Str_trip_Type="0",Str_truck_type="0",Str_spinn_truck_id="0",Str_spinn_driver_id="0" ;

    String Str_User_Id,Str_edit_weight="0", Str_edit_price_per_ton="0",  Str_edit_metarial_type="0",
            Str_edit_advance="0", Str_edit_shortage="0",
            Str_edit_deduction="0", Str_edit_munsiyana="0", Str_edit_commision="0", Str_edit_other="0", Str_edit_party,Str_spinn_fromstate,Str_spinn_fromcity,Str_spinn_tostate,Str_spinn_tocity;
    String   STr_selectedfromStateName,STr_selectedtoStateName,Str_selectedfrom_city,Str_selectedto_city,Str_Str_spinner_party;
     TextView edit_todate;
    String selectedState_id,Trip_id;
    Button btn_add_trip;
    AVLoadingIndicatorView bar;
    EditText edit_weight, edit_price_per_ton,  edit_metarial_type, edit_advance, edit_shortage,
            edit_deduction, edit_munsiyana, edit_commision, edit_other, edit_party;
    Spinner spinner_fromstate, spinner_fromcity, spinner_tostate, spinner_tocity, spinner_triptype,spinner_party,spinner_truck;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet_add_trip);
        Owner_id = AppPreferences.getSavedUser(mContext).getId();

        Trip_id = getIntent().getStringExtra("Trip_id");
        Log.d("tripssd",""+Trip_id);

        find();

        Str_User_Id = AppPreferences.getSavedUser(mContext).getId();

        stateBeanList=new ArrayList<>();
        cityBeanList=new ArrayList<>();

        getStateApi();
        getMyParty();
        spinner_triptype.setOnItemSelectedListener(this);
        spinner_fromstate.setOnItemSelectedListener(this);
        spinner_fromcity.setOnItemSelectedListener(this);
        spinner_tostate.setOnItemSelectedListener(this);
        spinner_tocity.setOnItemSelectedListener(this);



        // getCityApi();

    }

    public void find() {
        bar = findViewById(R.id.bar);
        back = findViewById(R.id.back);
        editcategory_tripdriver=findViewById(R.id.editcategory_tripdriver);
        editcategory_triptruck=findViewById(R.id.editcategory_triptruck);
        editcategory_triptype=findViewById(R.id.editcategory_triptype);
        editcategory_triptype.setVisibility(View.GONE);
        editcategory_triptruck.setVisibility(View.GONE);
        editcategory_tripdriver.setVisibility(View.GONE);

        edit_todate = findViewById(R.id.edit_todate);
        edit_todate.setVisibility(View.GONE);
        spinner_party=findViewById(R.id.spinner_party);
        edit_weight = findViewById(R.id.edit_weight);
        edit_price_per_ton = findViewById(R.id.edit_price_per_ton);
        /*  edit_truck_type = findViewById(R.id.edit_truck_type);*/
        edit_metarial_type = findViewById(R.id.edit_metarial_type);
        edit_advance = findViewById(R.id.edit_advance);
        edit_shortage = findViewById(R.id.edit_shortage);
        edit_deduction = findViewById(R.id.edit_deduction);
        edit_munsiyana = findViewById(R.id.edit_munsiyana);
        edit_commision = findViewById(R.id.edit_commision);
        edit_other = findViewById(R.id.edit_other);
         spinner_fromstate = findViewById(R.id.spinner_fromstate);

        spinner_fromcity = findViewById(R.id.spinner_fromcity);
        spinner_tostate = findViewById(R.id.spinner_tostate);
        spinner_tocity = findViewById(R.id.spinner_tocity);
        spinner_triptype = findViewById(R.id.spinner_triptype);

      //  spinner_truck.setVisibility(View.GONE);
       // spinner_driver.setVisibility(View.GONE);
        spinner_triptype.setVisibility(View.GONE);
        btn_add_trip = findViewById(R.id.btn_add_trip);
        spinner_party.setOnItemSelectedListener(this);

        spinner_fromstate.setOnItemSelectedListener(this);
        spinner_fromcity.setOnItemSelectedListener(this);
        spinner_tostate.setOnItemSelectedListener(this);
        spinner_tocity.setOnItemSelectedListener(this);





        btn_add_trip.setOnClickListener(this);
         back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_add_trip) {
            Str_edit_weight = edit_weight.getText().toString().trim();
            Str_edit_price_per_ton = edit_price_per_ton.getText().toString().trim();
             Str_edit_metarial_type = edit_metarial_type.getText().toString().trim();
            Str_edit_advance = edit_advance.getText().toString().trim();
            Str_edit_shortage = edit_shortage.getText().toString().trim();
            Str_edit_deduction = edit_deduction.getText().toString().trim();
            Str_edit_munsiyana = edit_munsiyana.getText().toString().trim();
            Str_edit_commision = edit_commision.getText().toString().trim();
            Str_edit_other = edit_other.getText().toString().trim();
             if (Str_edit_weight.equals("") || Str_edit_weight.isEmpty()) {
                CommonUtils.showToast(mContext, "Please enter Weight");
            }else if (Str_edit_price_per_ton.equals("") || Str_edit_price_per_ton.isEmpty()) {
                CommonUtils.showToast(mContext, "Please enter Price Per Ton");
            }else if (Str_edit_metarial_type.equals("") || Str_edit_metarial_type.isEmpty()) {
                CommonUtils.showToast(mContext, "Please Enter Material Type");
            }else if (Str_Str_spinner_party.equals("")||Str_Str_spinner_party.isEmpty()) {
                CommonUtils.showToast(mContext, "Please Select Party");
            }else {
                AddLoadMoreTrip();

            }
        }

        if (v==back){
            onBackPressed();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Log.d("125z","12346547");
          if (parent==spinner_fromstate){
            Str_spinn_fromstate = String.valueOf(spinner_fromstate.getSelectedItemPosition());
            STr_selectedfromStateName=""+stateBeanList.get(position).getState_name();
            selectedState_id=""+stateBeanList.get(position).getId();
            Log.d("selectedStateName",STr_selectedfromStateName);

            Log.d("125zfromstate",""+selectedState_id);
            getCityApi(selectedState_id);

        }  else if (parent == spinner_tostate) {
            Str_spinn_tostate = String.valueOf(spinner_tostate.getSelectedItemPosition());
            selectedState_id=""+stateBeanList.get(position).getId();
            STr_selectedtoStateName=""+stateBeanList.get(position).getState_name();
            Log.d("Str_spinn_tostate",Str_spinn_tostate);

            Log.d("125ztostate",""+selectedState_id);
            getCityApi(selectedState_id);


        } else if (parent==spinner_fromcity){
            Str_spinn_fromcity = String.valueOf(spinner_fromcity.getSelectedItemPosition());
            Str_selectedfrom_city= ""+cityBeanList.get(position).getCity_name();
            Log.d("Str_spinn_fromcity",Str_spinn_fromcity);


        } else if (parent==spinner_tocity){
            Str_spinn_tocity = String.valueOf(spinner_tocity.getSelectedItemPosition());
            Log.d("Str_spinn_tocity",Str_spinn_tocity);
            Str_selectedto_city= ""+cityBeanList.get(position).getCity_name();


          } else if (parent == spinner_party) {
              Str_Str_spinner_party=""+partyBeanList.get(position).getCompany_name();

          }






    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void getStateApi() {
        //avi.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]
                        {},
                new String[]{});
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {
            Call<GetStateResponse> call = apiInterface.FleetGetState(builder.build());
            call.enqueue(new Callback<GetStateResponse>() {
                @Override
                public void onResponse(Call<GetStateResponse> call, Response<GetStateResponse> response) {
                    // avi.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        stateBeanList.clear();
                        try {
                            ArrayList<String> stateNameList = new ArrayList<>();


                            if (response.isSuccessful() && response.body() != null) {

                                Log.e("statelistsizesxx", "1545454");

                                for (int i = 0; i < response.body().getInfo().size(); i++) {
                                    stateBean = new StateBean();
                                    stateBean.setState_name(response.body().getInfo().get(i).getState_name());
                                    stateBean.setId(response.body().getInfo().get(i).getId());
                                    //stateId=response.body().getInfo().get(i).getId();

                                    stateNameList.add(response.body().getInfo().get(i).getState_name());

                                    stateBeanList.add(stateBean);

                                    Log.e("statelistsize", String.valueOf(stateNameList.size()));
                                }
                                Str_spinner_fromstate = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, stateNameList);
                                Str_spinner_fromstate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner_fromstate.setAdapter(Str_spinner_fromstate);
                                spinner_tostate.setAdapter(Str_spinner_fromstate);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetStateResponse> call, Throwable t) {
                    //onApiFailure(call, t);
                    //avi.setVisibility(View.GONE);
                    //Toast.makeText(SignUpActivity.this, "Get states,Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }

    }



    private void getCityApi(String selectedState_id) {
        //avi.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]
                        {"state_id"},
                new String[]{selectedState_id});
        if(CommonUtils.isNetworkAvailable(getApplicationContext())) {
            Call<GetCityResponse> call = apiInterface.FleetGetCity(builder.build());
            call.enqueue(new Callback<GetCityResponse>() {
                @Override
                public void onResponse(Call<GetCityResponse> call, Response<GetCityResponse> response){
                    // avi.setVisibility(View.GONE);
                    if (response.isSuccessful())
                    {
                        try {
                            ArrayList<String> cityNameList = new ArrayList<>();
                            if(response.isSuccessful() && response.body()!=null){

                                for(int i = 0; i<response.body().getInfo().size();i++){
                                    CityBean cityBean = new CityBean();
                                    cityBean.setCity_name(response.body().getInfo().get(i).getCity_name());
                                    cityBean.setId(response.body().getInfo().get(i).getId());

                                    cityBeanList.add(cityBean);
                                    cityNameList.add(response.body().getInfo().get(i).getCity_name());
                                }
                                Str_spinner_fromcity = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cityNameList);
                                Str_spinner_fromcity.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                                spinner_fromcity.setAdapter(Str_spinner_fromcity);
                                spinner_tocity.setAdapter(Str_spinner_fromcity);

                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void onFailure(Call<GetCityResponse> call, Throwable t) {
                    //onApiFailure(call, t);
                    //avi.setVisibility(View.GONE);
                    // Toast.makeText(SignUpActivity.this, "Get cities,Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }

    }
    private void getMyParty() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"owner_id"}, new
                String[]{Owner_id});
        if (CommonUtils.isNetworkAvailable(mContext)) {
            Call<GepPartyResponse> call = apiInterface.Fleet_GetParty(builder.build());
            call.enqueue(new Callback<GepPartyResponse>() {
                @Override
                public void onResponse(Call<GepPartyResponse> call, Response<GepPartyResponse> response) {
                    bar.setVisibility(View.GONE);
                    ArrayList<String> partyNameList = new ArrayList<>();

                    if (response.isSuccessful() && response.body().getStatus().equals("1") && response.body().getMsg().equals("Successfully")) {
                        partyBeanList = new ArrayList<>();
                        for (int i = 0; i < response.body().getInfo().size(); i++) {
                            partyBean = new PartyBean();
                            partyBean.setId(response.body().getInfo().get(i).getId());
                            partyBean.setCompany_name(response.body().getInfo().get(i).getCompany_name());

                            partyNameList.add(response.body().getInfo().get(i).getCompany_name());


                            partyBeanList.add(partyBean);
                        }
                        Log.e("allBidBeanList", "" + partyBeanList.size());
                        Str_Adapterspinner_party = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, partyNameList);
                        Str_Adapterspinner_party.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_party.setAdapter(Str_Adapterspinner_party);
                    } else {
                        Log.e("userOrderHistory1ize", "" + "else");
                    }
                }

                @Override
                public void onFailure(Call<GepPartyResponse> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });
        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(mContext, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }




    private void AddLoadMoreTrip() {

        bar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"owner_id", "to_date",
                        "weight", "price_per_ton", "truck_type", "material_type", "advance", "shortage","deduction",
                        "munsiyana", "commision", "trip_type", "other", "from_state", "from_city","to_state",
                        "to_city", "party", "truck_id", "driver_id","trip_id" },


                new String[]{Str_User_Id, dates, Str_edit_weight, Str_edit_price_per_ton, Str_truck_type,
                        Str_edit_metarial_type, Str_edit_advance, Str_edit_shortage, Str_edit_deduction,
                        Str_edit_munsiyana,
                        Str_edit_commision, Str_trip_Type, Str_edit_other,STr_selectedfromStateName,
                        Str_selectedfrom_city, STr_selectedtoStateName,
                        Str_selectedto_city,Str_Str_spinner_party,Str_spinn_truck_id,Str_spinn_driver_id,Trip_id});
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {

            Call<FleetownerAddTripMOreResponse> call = apiInterface.Fleet_GetMoreLoad(builder.build());
            call.enqueue(new Callback<FleetownerAddTripMOreResponse>() {
                @Override
                public void onResponse(Call<FleetownerAddTripMOreResponse> call, Response<FleetownerAddTripMOreResponse> response) {
                    bar.setVisibility(View.GONE);

                    try {
                        if (response.isSuccessful() && response.body().getSuccess().equals("1")) {
                            onBackPressed();

                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }

                @Override
                public void onFailure(Call<FleetownerAddTripMOreResponse> call, Throwable t) {
                    // onApiFailure(call, t);
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }


    }

}