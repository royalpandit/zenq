package com.abhishek.zeiqindia.Truck_DriverRegister;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Utility.AppBaseActivity;

import java.util.Calendar;


public class ActivityTruckPersonal_Details extends AppBaseActivity implements View.OnClickListener {
    private Calendar calendar;
    ImageView back;
    TextView edittruck_reg_date, edittruck_reg_valid, edittruck_make, edittruck_puc, edittruck_insurance,
            edittruck_permit, edittruck_fitness, edittruck_emi;
    EditText edittruck_reg_no, edittruck_chasis, edittruck_engine,
            edittruck_model, edittruck_capacity, edittruck_road_tax,
            edittruck_finance, edittruck_onwer_name, edittruck_owner_contact,
            edittruck_owner_email, edittruck_owner_aadhar, edittruck_owner_pan, edittruck_owner_tds,
            edittruck_owner_bank, edittruck_owner_account, edittruck_owner_ifsc;
    int dayOfMonth, month, year;

    private String dates;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_register_form);
        initViews();
    }

    public void initViews() {
        back = findViewById(R.id.back);
        edittruck_reg_no = findViewById(R.id.edittruck_reg_no);
        edittruck_reg_date = findViewById(R.id.edittruck_reg_date);
        edittruck_reg_valid = findViewById(R.id.edittruck_reg_valid);
        edittruck_chasis = findViewById(R.id.edittruck_chasis);
        edittruck_engine = findViewById(R.id.edittruck_engine);
        edittruck_make = findViewById(R.id.edittruck_make);
        edittruck_model = findViewById(R.id.edittruck_model);
        edittruck_capacity = findViewById(R.id.edittruck_capacity);
        edittruck_road_tax = findViewById(R.id.edittruck_road_tax);
        edittruck_puc = findViewById(R.id.edittruck_puc);
        edittruck_insurance = findViewById(R.id.edittruck_insurance);
        edittruck_permit = findViewById(R.id.edittruck_permit);
        edittruck_fitness = findViewById(R.id.edittruck_fitness);


        edittruck_emi = findViewById(R.id.edittruck_emi);
        edittruck_finance = findViewById(R.id.edittruck_finance);
        edittruck_onwer_name = findViewById(R.id.edittruck_onwer_name);
        edittruck_owner_contact = findViewById(R.id.edittruck_owner_contact);
        edittruck_owner_email = findViewById(R.id.edittruck_owner_email);
        edittruck_owner_aadhar = findViewById(R.id.edittruck_owner_aadhar);
        edittruck_owner_pan = findViewById(R.id.edittruck_owner_pan);
        edittruck_owner_tds = findViewById(R.id.edittruck_owner_tds);
        edittruck_owner_bank = findViewById(R.id.edittruck_owner_bank);
        edittruck_owner_account = findViewById(R.id.edittruck_owner_account);
        edittruck_owner_ifsc = findViewById(R.id.edittruck_owner_ifsc);

        back.setOnClickListener(this);
        calendar = Calendar.getInstance();

        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        edittruck_reg_valid.setOnClickListener(this);
        edittruck_reg_date.setOnClickListener(this);
        edittruck_make.setOnClickListener(this);
        edittruck_puc.setOnClickListener(this);
        edittruck_insurance.setOnClickListener(this);
        edittruck_permit.setOnClickListener(this);
        edittruck_fitness.setOnClickListener(this);
        edittruck_emi.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == back) {
            onBackPressed();
        }
        if (v == edittruck_reg_date) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //  month = month + 1;
                    dates = year + "-" + month + "-" + dayOfMonth;

                    Log.d("dates", dates);
                    edittruck_reg_date.setText(dates);
                    Log.d("_calender", edittruck_reg_date.toString());
                }
            }, year, month, dayOfMonth);
            // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();

        }
        if (v == edittruck_reg_valid) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //  month = month + 1;
                    dates = year + "-" + month + "-" + dayOfMonth;

                    Log.d("dates", dates);
                    edittruck_reg_valid.setText(dates);
                    Log.d("edittruck_reg_valid", edittruck_reg_date.toString());
                }
            }, year, month, dayOfMonth);
            // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();
        }
        if (v == edittruck_make) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //  month = month + 1;
                    dates = year + "-" + month + "-" + dayOfMonth;

                    Log.d("dates", dates);
                    edittruck_make.setText(dates);
                    Log.d("edittruck_make", edittruck_make.toString());
                }
            }, year, month, dayOfMonth);
            // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();

        }
        if (v == edittruck_puc) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //  month = month + 1;
                    dates = year + "-" + month + "-" + dayOfMonth;

                    Log.d("edittruck_puc", dates);
                    edittruck_puc.setText(dates);
                    Log.d("edittruck_puc", edittruck_puc.toString());
                }
            }, year, month, dayOfMonth);
            // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();

        }
        if (v == edittruck_insurance) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //  month = month + 1;
                    dates = year + "-" + month + "-" + dayOfMonth;

                    Log.d("edittruck_insurance", dates);
                    edittruck_insurance.setText(dates);
                    Log.d("edittruck_insurance", edittruck_insurance.toString());
                }
            }, year, month, dayOfMonth);
            // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();

        }
        if (v == edittruck_permit) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //  month = month + 1;
                    dates = year + "-" + month + "-" + dayOfMonth;

                    Log.d("edittruck_permit", dates);
                    edittruck_permit.setText(dates);
                    Log.d("edittruck_permit", edittruck_permit.toString());
                }
            }, year, month, dayOfMonth);
            // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();

        }
        if (v == edittruck_fitness) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //  month = month + 1;
                    dates = year + "-" + month + "-" + dayOfMonth;

                    Log.d("edittruck_fitness", dates);
                    edittruck_fitness.setText(dates);
                    Log.d("edittruck_fitness", edittruck_fitness.toString());
                }
            }, year, month, dayOfMonth);
            // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();

        }
        if (v == edittruck_emi) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //  month = month + 1;
                    dates = year + "-" + month + "-" + dayOfMonth;

                    Log.d("edittruck_emi", dates);
                    edittruck_emi.setText(dates);
                    Log.d("edittruck_emi", edittruck_emi.toString());
                }
            }, year, month, dayOfMonth);
            // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();

        }

    }

}

