package com.abhishek.zeiqindia.Truck_DriverRegister;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.abhishek.zeiqindia.Prefrence.AppPreferences;
import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Response.AddPartyResponse;
import com.abhishek.zeiqindia.Utility.AppBaseActivity;
import com.abhishek.zeiqindia.Utility.CommonUtils;
import com.abhishek.zeiqindia.Utility.Constants;
import com.abhishek.zeiqindia.rest.ApiClient;
import com.abhishek.zeiqindia.rest.ApiInterface;
import com.wang.avi.AVLoadingIndicatorView;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityFleet_Add_MyParty extends AppBaseActivity implements View.OnClickListener {
    String User_Id;

    Button btn_add_party;
    AVLoadingIndicatorView bar;
    String Str_edit_company_name, Str_edit_company_email, Str_edit_company_person, Str_edit_company_number, Str_edit_company_gst, Str_edit_company_pan, Str_edit_company_address;
    EditText edit_company_name, edit_company_email, edit_company_person, edit_company_number, edit_company_gst, edit_company_pan, edit_company_address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet_add_myparty);
        User_Id = AppPreferences.getSavedUser(mContext).getId();

        find();

    }


    public void find() {
        bar = findViewById(R.id.bar);
        edit_company_name = findViewById(R.id.edit_company_name);
        edit_company_email = findViewById(R.id.edit_company_email);
        edit_company_person = findViewById(R.id.edit_company_person);
        edit_company_address = findViewById(R.id.edit_company_address);
        edit_company_number = findViewById(R.id.edit_company_number);
        edit_company_gst = findViewById(R.id.edit_company_gst);
        edit_company_pan = findViewById(R.id.edit_company_pan);
        btn_add_party = findViewById(R.id.btn_add_party);
        btn_add_party.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_add_party) {
            Str_edit_company_name = edit_company_name.getText().toString().trim();
            Str_edit_company_email = edit_company_email.getText().toString().trim();
            Str_edit_company_person = edit_company_person.getText().toString().trim();
            Str_edit_company_number = edit_company_number.getText().toString().trim();
            Str_edit_company_gst = edit_company_gst.getText().toString().trim();
            Str_edit_company_pan = edit_company_pan.getText().toString().trim();
            Str_edit_company_address = edit_company_address.getText().toString().trim();

            if (Str_edit_company_name.equals("") || Str_edit_company_name.isEmpty()) {
                CommonUtils.showToast(mContext, getString(R.string.error_company_name));
            } else if (Str_edit_company_email.equals("") || Str_edit_company_email.isEmpty()) {
                CommonUtils.showToast(mContext, getString(R.string.blank_email));
            } else if (!Str_edit_company_email.matches(Constants.EMAIL_PATTERN)) {
                CommonUtils.showToast(mContext, getString(R.string.invalid_email));
            } else if (Str_edit_company_person.equals("") || Str_edit_company_person.isEmpty()) {
                CommonUtils.showToast(mContext, getString(R.string.error_person));
            } else if (Str_edit_company_number.equals("") || Str_edit_company_number.isEmpty()) {
                CommonUtils.showToast(mContext, getString(R.string.error_mobile));
            } else if (Str_edit_company_gst.equals("") || Str_edit_company_gst.isEmpty()) {
                CommonUtils.showToast(mContext, getString(R.string.error_gst));
            } else if (Str_edit_company_pan.equals("") || Str_edit_company_pan.isEmpty()) {
                CommonUtils.showToast(mContext, getString(R.string.error_pan));
            } else if (Str_edit_company_address.equals("") || Str_edit_company_address.isEmpty()) {
                CommonUtils.showToast(mContext, getString(R.string.error_person));
            } else {
                AddParty();
            }
        }
    }


    private void AddParty() {

        bar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"owner_id", "company_name",
                        "company_address", "email", "contact_person", "contact_number", "gst", "pan"},
                new String[]{User_Id, Str_edit_company_name, Str_edit_company_address, Str_edit_company_email, Str_edit_company_person,
                        Str_edit_company_number, Str_edit_company_gst, Str_edit_company_pan});
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {

            Call<AddPartyResponse> call = apiInterface.Fleet_AddParty(builder.build());
            call.enqueue(new Callback<AddPartyResponse>() {
                @Override
                public void onResponse(Call<AddPartyResponse> call, Response<AddPartyResponse> response) {
                    bar.setVisibility(View.GONE);

                    try {
                        if (response.isSuccessful() && response.body().getStatus().equals("1")) {
                            onBackPressed();

                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }

                @Override
                public void onFailure(Call<AddPartyResponse> call, Throwable t) {
                    // onApiFailure(call, t);
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }


    }
}