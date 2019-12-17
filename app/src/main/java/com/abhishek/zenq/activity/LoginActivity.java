package com.abhishek.zenq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.abhishek.zenq.Bean.LoginBean;
import com.abhishek.zenq.MainActivity;
import com.abhishek.zenq.Prefrence.AppPreferences;
import com.abhishek.zenq.R;
import com.abhishek.zenq.Response.LoginResponse;
import com.abhishek.zenq.rest.ApiClient;
import com.abhishek.zenq.rest.ApiInterface;
import com.wang.avi.AVLoadingIndicatorView;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button login;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    EditText editemail, editpassword;
    AVLoadingIndicatorView avi;
    String StrUserType;
    TextView signup;
    String StrEmail, StrPassword, Status;
    private Spinner spinner;
    private String[] categories = {"Admin", "Truck", "Agent", "Commision Agent"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
/*        https://github.com/wajahatkarim3/EasyFlipView
* https://www.uisources.com/app/design-code
* */


        find();
    }

    public void find() {
     //   signup=findViewById(R.id.signup);
        avi = findViewById(R.id.bar);
        editemail = findViewById(R.id.editemail);
        editpassword = findViewById(R.id.editpassword);
        login = findViewById(R.id.login);
        signup=findViewById(R.id.signup);
   //     spinner = findViewById(R.id.spinner);
 //       spinner.setOnItemSelectedListener(this);
//        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
 //       aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 //       spinner.setAdapter(aa);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == login) {
            StrEmail = editemail.getText().toString().trim();
            StrPassword = editpassword.getText().toString().trim();
            if (StrEmail.equals("") || StrEmail.isEmpty()) {
                Toast.makeText(this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
            } else if (!StrEmail.matches(emailPattern)) {

                Toast.makeText(this, "Invalid Email.", Toast.LENGTH_SHORT).show();
            } else if (StrPassword.equals("") || StrPassword.isEmpty()) {
                Toast.makeText(this, "Please enter password.", Toast.LENGTH_SHORT).show();
            } else if (StrPassword.length() < 5 || StrPassword.length() > 15) {
                Toast.makeText(this, "Password should be 6 to 15 characters.", Toast.LENGTH_SHORT).show();
            } else {
                Login();
              /*  startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();*/
            }
        }if (v==signup){
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            finish();

        }
    }

/*
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //select_Categories = categories[position];
        //Toast.makeText(this, "position :"+select_Categories, Toast.LENGTH_SHORT).show();
     //   StrUserType = String.valueOf(spinner.getSelectedItemPosition());
        //  Toast.makeText(this, "position1 :"+select_Categories, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
*/


    private void Login() {

        avi.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"email", "password"}, new String[]{StrEmail, StrPassword});
        Call<LoginResponse> call = apiInterface.Login(builder.build());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                avi.setVisibility(View.GONE);
                Log.d("11111", "11111");
                if (response.isSuccessful()) {
                    Log.d("2222", "2222");
                    try {
                        if (response.isSuccessful() && response.body().getStatus().equals("1") && response.body().getMsg().equalsIgnoreCase("Successfully")) {
                            if (response.body().getInfo().getUser_type().equals("0") && response.body().getInfo().getIs_kyc().equals("1")) {
                                LoginBean loginBean = response.body().getInfo();
                                AppPreferences.SaveUserdetail(LoginActivity.this, loginBean);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));

                            } else {
                                Toast.makeText(LoginActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                onApiFailure(call, t);
            }
        });

    }

    public void onApiFailure(Call<LoginResponse> call, Throwable t) {
        //Log.e("error", t.toString());
        avi.setVisibility(View.GONE);

        // stopLoadingDialog();

    }


}
