package com.abhishek.zeiqindia.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abhishek.zeiqindia.Bean.LoginBean;
import com.abhishek.zeiqindia.FleetOwnerDashBoard.MainActivityOwnerDashboard;
import com.abhishek.zeiqindia.MainActivity;
import com.abhishek.zeiqindia.Prefrence.AppPreferences;
import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Response.LoginResponse;
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

public class LoginActivity extends AppBaseActivity implements View.OnClickListener {

/*    Gmail Account ....
    zeiqindia@gmail.com
    Pass zeiq@123*/

    Button login;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    EditText editemail, editpassword;
    AVLoadingIndicatorView avi;
    String StrUserType;
    TextView signup,forgor;
    String StrEmail, StrPassword, Status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        find();
    }

    public void find() {
        avi = findViewById(R.id.bar);
        editemail = findViewById(R.id.editemail);
        editpassword = findViewById(R.id.edit_loginpassword);
        login = findViewById(R.id.login);
        signup=findViewById(R.id.login_signup);
        forgor=findViewById(R.id.forgor);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);
        forgor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == login) {
            StrEmail = editemail.getText().toString().trim();
            StrPassword = editpassword.getText().toString().trim();
            if (StrEmail.equals("") || StrEmail.isEmpty()) {
                CommonUtils.showToast(mContext, getString(R.string.blank_email));
            } else if (!StrEmail.matches(emailPattern)) {
                CommonUtils.showToast(mContext, getString(R.string.invalid_email));

             } else if (StrPassword.equals("") || StrPassword.isEmpty()) {
                CommonUtils.showToast(mContext, getString(R.string.blank_password));

             } else if (StrPassword.length() < 5 || StrPassword.length() > 15) {
                CommonUtils.showToast(mContext, getString(R.string.min_char_password));

             } else {
                Login();

            }
        }if (v==signup){
            CommonUtils.intentToActivity(getApplicationContext(), RegisterNumb.class);
            finish();



        }if (v==forgor){
            forgotClick();
        }
    }

    private void forgotClick() {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_fleet_addmy_part_view);

        final EditText email = (EditText) dialog.findViewById(R.id.email);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        Button ok = (Button) dialog.findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailStr = email.getText().toString();

                if (emailStr.matches(Constants.EMAIL_PATTERN)) {
                    //hitForgotAPI(emailStr, dialog);
                } else {
                    email.setError(getString(R.string.invalid_email));
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

    private void Login() {

        avi.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"email", "password"},
                new String[]{StrEmail, StrPassword});
        Call<LoginResponse> call = apiInterface.Login(builder.build());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                avi.setVisibility(View.GONE);
                Log.d("11111", "11111");
                if (response.isSuccessful()) {
                    Log.d("2222", "2222");
                    try {
                        if (response.isSuccessful() && response.body().getStatus().equals("1") ) {
                            if (response.body().getInfo().getUser_type().equals("0") ) {

                                LoginBean loginBean = response.body().getInfo();

                                AppPreferences.SaveUserdetail(LoginActivity.this, loginBean);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();

                            }else if (response.body().getInfo().getUser_type().equals("1") ){
                                if (response.body().getInfo().getIs_active().equals("1") ){
                                    LoginBean loginBean = response.body().getInfo();
                              //        db.putString(Constants.USER_ID,loginBean.getId());


                                        AppPreferences.SaveUserdetail(LoginActivity.this, loginBean);
                                    Intent i=new Intent(LoginActivity.this, MainActivityOwnerDashboard.class);

                                    startActivity(i);
                                    finish();

                                 }else
                                    CommonUtils.showToast(mContext,  response.body().getMsg());

                               // Toast.makeText(LoginActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            }

                                else {
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


    }


}
