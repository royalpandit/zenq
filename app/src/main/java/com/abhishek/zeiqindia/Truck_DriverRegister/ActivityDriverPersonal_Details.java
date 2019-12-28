package com.abhishek.zeiqindia.Truck_DriverRegister;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Utility.AppBaseActivity;

import java.util.Calendar;

public class ActivityDriverPersonal_Details extends AppBaseActivity implements View.OnClickListener {
    private Calendar calendar;
    int dayOfMonth, month, year;

    private String dates;
    Button next_driver_fill;
TextView editdriver_lic_valid,editdriver_dob,editdriver_doj;
    EditText editdriver_name,editdriver_lic_name,editdriver_exp,editdriver_address,
            editdriver_contact,editdriver_ins,editdriver_ins_details,editdriver_eme_con_det,editdriver_blood,
            editdriver_yrs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_register);
        initViews();
    }



    public void initViews(){
        editdriver_name=findViewById(R.id.editdriver_name);
        editdriver_lic_name=findViewById(R.id.editdriver_lic_name);
        editdriver_lic_valid=findViewById(R.id.editdriver_lic_valid);
        editdriver_exp=findViewById(R.id.editdriver_exp);
        editdriver_dob=findViewById(R.id.editdriver_dob);
        editdriver_address=findViewById(R.id.editdriver_address);
        editdriver_contact=findViewById(R.id.editdriver_contact);
        editdriver_ins=findViewById(R.id.editdriver_ins);
        editdriver_ins_details=findViewById(R.id.editdriver_ins_details);
        editdriver_eme_con_det=findViewById(R.id.editdriver_eme_con_det);
        editdriver_blood=findViewById(R.id.editdriver_blood);
        editdriver_doj=findViewById(R.id.editdriver_doj);
        editdriver_yrs=findViewById(R.id.editdriver_yrs);
        next_driver_fill=findViewById(R.id.next_driver_fill);

        calendar = Calendar.getInstance();
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        editdriver_lic_valid.setOnClickListener(this);
        editdriver_dob.setOnClickListener(this);
        editdriver_doj.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==editdriver_lic_valid){
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //  month = month + 1;
                    dates = year + "-" + month + "-" + dayOfMonth;

                    Log.d("dates", dates);
                    editdriver_lic_valid.setText(dates);
                    Log.d("_calender", editdriver_lic_valid.toString());
                }
            }, year, month, dayOfMonth);
            // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();

        }if (v==editdriver_dob){
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //  month = month + 1;
                    dates = year + "-" + month + "-" + dayOfMonth;

                    Log.d("dates", dates);
                    editdriver_dob.setText(dates);
                    Log.d("editdriver_dob", editdriver_dob.toString());
                }
            }, year, month, dayOfMonth);
            // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();

        }if (v==editdriver_doj){
            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //  month = month + 1;
                    dates = year + "-" + month + "-" + dayOfMonth;

                    Log.d("editdriver_doj", dates);
                    editdriver_doj.setText(dates);
                    Log.d("editdriver_doj", editdriver_doj.toString());
                }
            }, year, month, dayOfMonth);
            // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();

        }
    }
}
