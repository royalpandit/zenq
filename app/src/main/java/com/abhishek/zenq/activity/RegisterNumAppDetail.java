package com.abhishek.zenq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abhishek.zenq.R;
import com.wang.avi.AVLoadingIndicatorView;

public class RegisterNumAppDetail extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener {
    private String[] categories = {  "owner", "Commison agent ", "Booking agent"};
    private String[] role = {  "1", "2 ", "3"};

    /*    type  : 1/2/3
     */

    AVLoadingIndicatorView bar;
    Button btn_next;
    Spinner spinner;
    String Str_editcountary="India";
    String select_Categories;
    EditText editbuisnessname, editfirstname, editregemail, editpassword, editmobile, editlandlanine, editaddress,
            editlocality, editcountary, editState, editCity, editpin, editpaan, editrole;
    String Str_editbuisnessname, Str_editfirstname, Str_editregemail, Str_editpassword, Str_editmobile, Str_editlandlanine,
            Str_editaddress,
            Str_editlocality,  Str_editState, Str_editCity, Str_editpin, Str_editpaan, Str_editrole,StrUserType;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_app_details);
        Str_editmobile=getIntent().getStringExtra("phoneNUmber");
        Log.e("phopnenumber",Str_editmobile);

        find();


    }


    public void find() {
        spinner = findViewById(R.id.spinner);
        editbuisnessname = findViewById(R.id.editbuisnessname);
        editfirstname = findViewById(R.id.editfirstname);
        editregemail = findViewById(R.id.editregemail);
        editpassword = findViewById(R.id.editpassword);
        editmobile = findViewById(R.id.editmobile);
        editlandlanine = findViewById(R.id.editlandlanine);
        editaddress = findViewById(R.id.editaddress);
        editlocality = findViewById(R.id.editlocality);
        editcountary = findViewById(R.id.editcountary);
        editState = findViewById(R.id.editState);
        editCity = findViewById(R.id.editCity);
        editpin = findViewById(R.id.editpin);
        editpaan = findViewById(R.id.editbuisnessname);
        //editrole = findViewById(R.id.editrole);
        btn_next = findViewById(R.id.btn_next);
        editmobile.setText(Str_editmobile);
        editcountary.setText(Str_editcountary);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);



        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_next) {

            Str_editbuisnessname = (editbuisnessname.getEditableText().toString());
            Str_editfirstname = (editfirstname.getEditableText().toString());
            Log.e("editbuisnessname",editbuisnessname.getEditableText().toString());
            Str_editregemail = (editregemail.getEditableText().toString());
            Str_editpassword = (editpassword.getEditableText().toString());

            Str_editlandlanine = (editlandlanine.getEditableText().toString());
            Str_editaddress = (editaddress.getEditableText().toString());
            Str_editlocality = (editlocality.getEditableText().toString());
            Str_editState = (editState.getEditableText().toString());
            Str_editCity = (editCity.getEditableText().toString());
            Str_editpin = (editpin.getEditableText().toString());
            Str_editpaan = (editpaan.getEditableText().toString());
            //Str_editrole = (editrole.getEditableText().toString());

            if (Str_editbuisnessname.equals("") || Str_editbuisnessname.isEmpty()) {
                Toast.makeText(this, "Please Enter Buisness Name", Toast.LENGTH_SHORT).show();
            } else if (Str_editfirstname.equals("") || Str_editfirstname.isEmpty()) {
                Toast.makeText(this, "Please Enter First Name", Toast.LENGTH_SHORT).show();
            } else if (Str_editregemail.equals("") || Str_editregemail.isEmpty()) {
                Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            } else if (Str_editpassword.equals("") || Str_editpassword.isEmpty()) {
                Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            }  else if (Str_editlandlanine.equals("") || Str_editlandlanine.isEmpty()) {
                Toast.makeText(this, "Please Enter Your Landline", Toast.LENGTH_SHORT).show();
            } else if (Str_editaddress.equals("") || Str_editaddress.isEmpty()) {
                Toast.makeText(this, "Please Enter Your Address", Toast.LENGTH_SHORT).show();
            } else if (Str_editlocality.equals("") || Str_editlocality.isEmpty()) {
                Toast.makeText(this, "Please Enter Your Locale Address", Toast.LENGTH_SHORT).show();
            } else if (Str_editcountary.equals("") || Str_editcountary.isEmpty()) {
                Toast.makeText(this, "Please Enter Your Countary", Toast.LENGTH_SHORT).show();
            } else if (Str_editState.equals("") || Str_editState.isEmpty()) {
                Toast.makeText(this, "Please Enter Your State", Toast.LENGTH_SHORT).show();
            } else if (Str_editCity.equals("") || Str_editCity.isEmpty()) {
                Toast.makeText(this, "Please Enter Your City", Toast.LENGTH_SHORT).show();
            } else if (Str_editpin.equals("") || Str_editpin.isEmpty()) {
                Toast.makeText(this, "Please Enter Your Pin", Toast.LENGTH_SHORT).show();
            } else if (Str_editpaan.equals("") || Str_editpaan.isEmpty()) {
                Toast.makeText(this, "Please Enter Your Pan Card", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(RegisterNumAppDetail.this, RegisterNumFinalDetail.class);
                i.putExtra("StrUserType", StrUserType);
                i.putExtra("Str_editbuisnessname", editbuisnessname.getEditableText().toString());
                i.putExtra("Str_editfirstname", Str_editfirstname);
                i.putExtra("Str_editregemail", Str_editregemail);
                i.putExtra("Str_editpassword", Str_editpassword);
                i.putExtra("Str_editmobile", Str_editmobile);
                i.putExtra("Str_editlandlanine", Str_editlandlanine);
                i.putExtra("Str_editaddress", Str_editaddress);
                i.putExtra("Str_editlocality", Str_editlocality);
                i.putExtra("Str_editcountary", Str_editcountary);
                i.putExtra("Str_editState", Str_editState);
                i.putExtra("Str_editCity", Str_editCity);
                i.putExtra("Str_editpin", Str_editpin);
                i.putExtra("Str_editpaan", Str_editpaan);
                i.putExtra("Str_editrole", Str_editrole);
                Log.e("Str_editregemail",Str_editregemail);


                startActivity(i);
                finish();

            }




        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        StrUserType = String.valueOf(spinner.getSelectedItemPosition());
          select_Categories = categories[position];
        Str_editrole=role[position];
        Toast.makeText(this, "position :"+Str_editrole, Toast.LENGTH_SHORT).show();
         // Toast.makeText(this, "position1 :"+select_Categories, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
