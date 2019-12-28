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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class ActivityFleetAddTrip extends AppBaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    ArrayAdapter Str_spinner_fromstate, Str_spinner_fromcity, Str_spinner_driver, Str_spinner_truck, Str_Adapterspinner_party;
    List<StateBean> stateBeanList;
    List<TruckBean> truckBeanList;
    List<DriverBean> driverBeanList;
    DriverBean driverBean;
    TruckBean truckBean;
    StateBean stateBean;
    CityBean cityBean;
    List<CityBean> cityBeanList;

    List<PartyBean> partyBeanList;
    PartyBean partyBean;
    String stateId;
    int dayOfMonth, month, year;
    String Str_User_Id, Str_edit_weight, Str_edit_price_per_ton, Str_edit_truck_type, Str_edit_metarial_type,
            Str_edit_advance, Str_edit_shortage,
            Str_edit_deduction, Str_edit_munsiyana, Str_edit_commision, Str_edit_other, Str_edit_party, Str_spinn_fromstate, Str_spinn_fromcity, Str_spinn_tostate, Str_spinn_tocity, Str_spinner_party, Str_spinn_driver_id, Str_spinn_truck, Str_spinn_truck_id;
    String STr_selectedfromStateName, STr_selectedtoStateName, Str_selectedfrom_city, Str_selectedto_city, Str_selectedto_truck, Str_selectedto_driver;
    TextView edit_todate;
    String selectedState_id;
    Button btn_add_trip;
    AVLoadingIndicatorView bar;
    EditText edit_weight, edit_price_per_ton, edit_truck_type, edit_metarial_type, edit_advance, edit_shortage,
            edit_deduction, edit_munsiyana, edit_commision, edit_other, edit_party;
    Spinner spinner_fromstate, spinner_fromcity, spinner_tostate, spinner_tocity, spinner_triptype, spinner_driver, spinner_truck, spinner_party;
    ImageView back;
    private Calendar calendar;
    private String dates, Str_spin_Trip_Type;
    private String[] TripType = {"Single", "Double Trip"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet_add_trip);

        find();
        Str_User_Id = AppPreferences.getSavedUser(mContext).getId();

        stateBeanList = new ArrayList<>();
        cityBeanList = new ArrayList<>();

        getStateApi();
        getAddTruck();
        getAddDriver();
        getMyParty();
        spinner_triptype.setOnItemSelectedListener(this);
        spinner_fromstate.setOnItemSelectedListener(this);
        spinner_fromcity.setOnItemSelectedListener(this);
        spinner_tostate.setOnItemSelectedListener(this);
        spinner_driver.setOnItemSelectedListener(this);
        spinner_truck.setOnItemSelectedListener(this);
    }

    public void find() {
        bar = findViewById(R.id.bar);
        back = findViewById(R.id.back);

        edit_todate = findViewById(R.id.edit_todate);
        edit_weight = findViewById(R.id.edit_weight);
        edit_price_per_ton = findViewById(R.id.edit_price_per_ton);
        edit_metarial_type = findViewById(R.id.edit_metarial_type);
        edit_advance = findViewById(R.id.edit_advance);
        edit_shortage = findViewById(R.id.edit_shortage);
        edit_deduction = findViewById(R.id.edit_deduction);
        edit_munsiyana = findViewById(R.id.edit_munsiyana);
        edit_commision = findViewById(R.id.edit_commision);
        edit_other = findViewById(R.id.edit_other);
        spinner_fromstate = findViewById(R.id.spinner_fromstate);
        spinner_driver = findViewById(R.id.spinner_driver);
        spinner_truck = findViewById(R.id.spinner_truck);
        spinner_party = findViewById(R.id.spinner_party);
        spinner_fromcity = findViewById(R.id.spinner_fromcity);
        spinner_tostate = findViewById(R.id.spinner_tostate);
        spinner_tocity = findViewById(R.id.spinner_tocity);
        spinner_triptype = findViewById(R.id.spinner_triptype);
        btn_add_trip = findViewById(R.id.btn_add_trip);
        spinner_triptype.setOnItemSelectedListener(this);
        spinner_fromstate.setOnItemSelectedListener(this);
        spinner_party.setOnItemSelectedListener(this);
        spinner_fromcity.setOnItemSelectedListener(this);
        spinner_tostate.setOnItemSelectedListener(this);
        spinner_tocity.setOnItemSelectedListener(this);
        spinner_driver.setOnItemSelectedListener(this);
        spinner_truck.setOnItemSelectedListener(this);
        getStateApi();
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, TripType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_triptype.setAdapter(aa);


        calendar = Calendar.getInstance();
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        btn_add_trip.setOnClickListener(this);
        edit_todate.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_add_trip) {
            Str_edit_weight = edit_weight.getText().toString().trim();
            Str_edit_price_per_ton = edit_price_per_ton.getText().toString().trim();
            /*  Str_edit_truck_type = edit_truck_type.getText().toString().trim();*/
            Str_edit_metarial_type = edit_metarial_type.getText().toString().trim();
            Str_edit_advance = edit_advance.getText().toString().trim();
            Str_edit_shortage = edit_shortage.getText().toString().trim();
            Str_edit_deduction = edit_deduction.getText().toString().trim();
            Str_edit_munsiyana = edit_munsiyana.getText().toString().trim();
            Str_edit_commision = edit_commision.getText().toString().trim();
            Str_edit_other = edit_other.getText().toString().trim();
            //  Str_edit_party = edit_party.getText().toString().trim();
            if (Str_edit_weight.equals("") || Str_edit_weight.isEmpty()) {
                CommonUtils.showToast(mContext, "Please Select Date");
            }else if (Str_edit_price_per_ton.equalsIgnoreCase("")||Str_edit_price_per_ton.isEmpty()){
                CommonUtils.showToast(mContext, "Please Enter Price ");
            }else if (Str_edit_metarial_type.equalsIgnoreCase("")||Str_edit_metarial_type.isEmpty()){
                CommonUtils.showToast(mContext, "Please Enter Material ");
            }else if (Str_edit_advance.equalsIgnoreCase("")||Str_edit_advance.isEmpty()){
                CommonUtils.showToast(mContext, "Please Enter Advance ");
            }else if (Str_edit_shortage.equalsIgnoreCase("")||Str_edit_shortage.isEmpty()){
                CommonUtils.showToast(mContext, "Please Enter Shortage Amount ");
            }else if (Str_edit_deduction.equalsIgnoreCase("")||Str_edit_deduction.isEmpty()){
                CommonUtils.showToast(mContext, "Please Enter Deduction ");
            }else if (Str_edit_munsiyana.equalsIgnoreCase("")||Str_edit_munsiyana.isEmpty()){
                CommonUtils.showToast(mContext, "Please Enter Musiyana ");
            }else if (Str_edit_commision.equalsIgnoreCase("")||Str_edit_commision.isEmpty()){
                CommonUtils.showToast(mContext, "Please Enter Commision ");
            }else if (Str_edit_other.equalsIgnoreCase("")||Str_edit_other.isEmpty()){
                CommonUtils.showToast(mContext, "Please Enter Other ");
            }else if (Str_spin_Trip_Type.equalsIgnoreCase("")||Str_spin_Trip_Type.isEmpty()){
                CommonUtils.showToast(mContext, "Please Select Trip ");
            }else if (STr_selectedfromStateName.equalsIgnoreCase("")||STr_selectedfromStateName.isEmpty()){
                CommonUtils.showToast(mContext, "Please Select State ");
            }else {
                AddTrip();

            }
        }
        if (v == edit_todate) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    dates = dayOfMonth + "/" + month + "/" + year;

                    Log.d("edit_todate", dates);
                    edit_todate.setText(dates);
                    Log.d("edit_todate", edit_todate.toString());
                }
            }, year, month, dayOfMonth);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();
        }
        if (v == back) {
            onBackPressed();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent == spinner_triptype) {
            Str_spin_Trip_Type = String.valueOf(spinner_triptype.getSelectedItemPosition());


        } else if (parent == spinner_fromstate) {
            Str_spinn_fromstate = String.valueOf(spinner_fromstate.getSelectedItemPosition());
            STr_selectedfromStateName = "" + stateBeanList.get(position).getState_name();
            selectedState_id = "" + stateBeanList.get(position).getId();
            Log.d("selectedStateName", STr_selectedfromStateName);

            Log.d("125zfromstate", "" + selectedState_id);
            getCityApi(selectedState_id);

        } else if (parent == spinner_tostate) {
            Str_spinn_tostate = String.valueOf(spinner_tostate.getSelectedItemPosition());
            selectedState_id = "" + stateBeanList.get(position).getId();
            STr_selectedtoStateName = "" + stateBeanList.get(position).getState_name();
            Log.d("Str_spinn_tostate", Str_spinn_tostate);

            Log.d("125ztostate", "" + selectedState_id);
            getCityApi(selectedState_id);


        } else if (parent == spinner_fromcity) {
            Str_spinn_fromcity = String.valueOf(spinner_fromcity.getSelectedItemPosition());
            Str_selectedfrom_city = "" + cityBeanList.get(position).getCity_name();
            Log.d("Str_spinn_fromcity", Str_spinn_fromcity);


        } else if (parent == spinner_tocity) {
            Str_spinn_tocity = String.valueOf(spinner_tocity.getSelectedItemPosition());
            Log.d("Str_spinn_tocity", Str_spinn_tocity);
            Str_selectedto_city = "" + cityBeanList.get(position).getCity_name();


        } else if (parent == spinner_driver) {
            //Str_spinn_driver = String.valueOf(spinner_driver.getSelectedItemPosition());
            Str_spinn_driver_id = String.valueOf(spinner_driver.getSelectedItemPosition());
            Str_selectedto_driver = "" + driverBeanList.get(position).getName();

            Log.d("Str_spinn_driver", Str_selectedto_driver);


        } else if (parent == spinner_truck) {
            Str_spinn_truck = String.valueOf(spinner_truck.getSelectedItemPosition());
            Str_spinn_truck_id = String.valueOf(spinner_truck.getSelectedItemPosition());
            Str_selectedto_truck = "" + truckBeanList.get(position).getReg_no();

            Log.d("Str_spinn_truck", Str_spinn_truck);


        } else if (parent == spinner_party) {
            Str_spinner_party = "" + partyBeanList.get(position).getCompany_name();


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
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {
            Call<GetCityResponse> call = apiInterface.FleetGetCity(builder.build());
            call.enqueue(new Callback<GetCityResponse>() {
                @Override
                public void onResponse(Call<GetCityResponse> call, Response<GetCityResponse> response) {
                    // avi.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        try {
                            ArrayList<String> cityNameList = new ArrayList<>();
                            if (response.isSuccessful() && response.body() != null) {

                                for (int i = 0; i < response.body().getInfo().size(); i++) {
                                    CityBean cityBean = new CityBean();
                                    cityBean.setCity_name(response.body().getInfo().get(i).getCity_name());
                                    cityBean.setId(response.body().getInfo().get(i).getId());

                                    cityBeanList.add(cityBean);
                                    cityNameList.add(response.body().getInfo().get(i).getCity_name());
                                }
                                Str_spinner_fromcity = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cityNameList);
                                Str_spinner_fromcity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner_fromcity.setAdapter(Str_spinner_fromcity);
                                spinner_tocity.setAdapter(Str_spinner_fromcity);

                            }
                        } catch (Exception e) {
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
        } else {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }

    }


    private void getAddDriver() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"owner_id"}, new
                String[]{Str_User_Id});
        if (CommonUtils.isNetworkAvailable(mContext)) {
            Call<FleetOwnerDriverResponse> call = apiInterface.FleetGetAllDriver(builder.build());
            call.enqueue(new Callback<FleetOwnerDriverResponse>() {
                @Override
                public void onResponse(Call<FleetOwnerDriverResponse> call, Response<FleetOwnerDriverResponse> response) {
                    bar.setVisibility(View.GONE);

                    if (response.isSuccessful() && response.body().getStatus().equals("1") && response.body().getMsg().equals("Successfully")) {
                        ArrayList<String> driverNameList = new ArrayList<>();

                        driverBeanList = new ArrayList<>();
                        for (int i = 0; i < response.body().getInfo().size(); i++) {
                            driverBean = new DriverBean();
                            driverBean.setId(response.body().getInfo().get(i).getId());
                            driverBean.setName(response.body().getInfo().get(i).getName());
                            driverBean.setOwner_id(response.body().getInfo().get(i).getOwner_id());

                            driverNameList.add(response.body().getInfo().get(i).getName());
                            driverNameList.add(response.body().getInfo().get(i).getId());


                            driverBeanList.add(driverBean);
                        }
                        Str_spinner_driver = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, driverNameList);
                        Str_spinner_driver.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_driver.setAdapter(Str_spinner_driver);
                    } else {
                        Log.e("userOrderHistory1ize", "" + "else");
                    }
                }

                @Override
                public void onFailure(Call<FleetOwnerDriverResponse> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });
        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(mContext, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getAddTruck() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"owner_id"}, new
                String[]{Str_User_Id});
        if (CommonUtils.isNetworkAvailable(mContext)) {
            Call<FleetOwnerTruckResponse> call = apiInterface.FleetGetAllTruck(builder.build());
            call.enqueue(new Callback<FleetOwnerTruckResponse>() {
                @Override
                public void onResponse(Call<FleetOwnerTruckResponse> call, Response<FleetOwnerTruckResponse> response) {
                    bar.setVisibility(View.GONE);

                    if (response.isSuccessful() && response.body().getStatus().equals("1") && response.body().getMsg().equals("Successfully")) {
                        ArrayList<String> truckNameList = new ArrayList<>();

                        truckBeanList = new ArrayList<>();
                        for (int i = 0; i < response.body().getInfo().size(); i++) {
                            truckBean = new TruckBean();
                            truckBean.setId(response.body().getInfo().get(i).getId());
                            truckBean.setReg_no(response.body().getInfo().get(i).getReg_no());
                            truckBean.setOwner_id(response.body().getInfo().get(i).getOwner_id());

                            truckNameList.add(response.body().getInfo().get(i).getReg_no());
                            truckNameList.add(response.body().getInfo().get(i).getId());


                            truckBeanList.add(truckBean);
                        }
                        Str_spinner_truck = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, truckNameList);
                        Str_spinner_truck.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_truck.setAdapter(Str_spinner_truck);
                    } else {
                        Log.e("userOrderHistory1ize", "" + "else");
                    }
                }

                @Override
                public void onFailure(Call<FleetOwnerTruckResponse> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });
        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(mContext, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }


    private void AddTrip() {

        bar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"owner_id", "to_date",
                        "weight", "price_per_ton", "truck_type", "material_type", "advance", "shortage", "deduction",
                        "munsiyana", "commision", "trip_type", "other", "from_state", "from_city", "to_state",
                        "to_city", "party", "truck_id", "driver_id"},


                new String[]{Str_User_Id, dates, Str_edit_weight, Str_edit_price_per_ton, Str_spinn_truck_id,
                        Str_edit_metarial_type, Str_edit_advance, Str_edit_shortage, Str_edit_deduction,
                        Str_edit_munsiyana,
                        Str_edit_commision, Str_spin_Trip_Type, Str_edit_other, STr_selectedfromStateName,
                        Str_selectedfrom_city, STr_selectedtoStateName,
                        Str_selectedto_city, Str_spinner_party, Str_selectedto_truck, Str_spinn_driver_id});
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {

            Call<AddTripResponse> call = apiInterface.Fleet_AddTrip(builder.build());
            call.enqueue(new Callback<AddTripResponse>() {
                @Override
                public void onResponse(Call<AddTripResponse> call, Response<AddTripResponse> response) {
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
                public void onFailure(Call<AddTripResponse> call, Throwable t) {
                    // onApiFailure(call, t);
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }


    }

    private void getMyParty() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"owner_id"}, new
                String[]{Str_User_Id});
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

}
