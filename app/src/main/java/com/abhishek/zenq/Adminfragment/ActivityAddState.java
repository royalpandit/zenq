package com.abhishek.zenq.Adminfragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.abhishek.zenq.Bean.LoginBean;
import com.abhishek.zenq.Location.FragmentGetState;
import com.abhishek.zenq.MainActivity;
import com.abhishek.zenq.Prefrence.AppPreferences;
import com.abhishek.zenq.R;
import com.abhishek.zenq.Response.AddStateResponse;
import com.abhishek.zenq.Response.LoginResponse;
import com.abhishek.zenq.Utility.CommonUtils;
import com.abhishek.zenq.activity.LoginActivity;
import com.abhishek.zenq.rest.ApiClient;
import com.abhishek.zenq.rest.ApiInterface;
import com.wang.avi.AVLoadingIndicatorView;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAddState extends AppCompatActivity implements View.OnClickListener {
    Button btnadd_state;
    String StrEditState, StrSditSateCode;
    EditText editstatename;
    EditText editstatecode;
    AVLoadingIndicatorView avi;
ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitivy_add_state);
        find();
    }


    public void find() {
        avi = findViewById(R.id.avi);
        editstatename = findViewById(R.id.editstatename);
        editstatecode = findViewById(R.id.editstatecode);
        btnadd_state = findViewById(R.id.btnadd_state);
        back=findViewById(R.id.back);
        btnadd_state.setOnClickListener(this);
        back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == btnadd_state) {
            StrEditState = editstatename.getText().toString().trim();
            StrSditSateCode = editstatecode.getText().toString().trim();
            if (StrEditState.equals("") || StrEditState.isEmpty()) {
                Toast.makeText(this, "Please Enter State", Toast.LENGTH_SHORT).show();
            } else if (StrSditSateCode.equals("") || StrSditSateCode.isEmpty()) {

                Toast.makeText(this, "Enter State Code.", Toast.LENGTH_SHORT).show();
            } else {
                AddState();
//                startActivity(new Intent(getApplicationContext(), FragmentGetState.class));

            }
        }if (v==back){
            onBackPressed();
        }
    }


    private void AddState() {

        avi.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"state_name", "state_code"},
                new String[]{StrEditState, StrSditSateCode});
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {

            Call<AddStateResponse> call = apiInterface.FleetAddState(builder.build());
        call.enqueue(new Callback<AddStateResponse>() {
            @Override
            public void onResponse(Call<AddStateResponse> call, Response<AddStateResponse> response) {
                avi.setVisibility(View.GONE);
                Log.d("11111", "11111");
                if (response.isSuccessful() && response.body().getSuccess().equals("1") && response.body().getMessage().equalsIgnoreCase("Sate Added successfully.")) {
                    Log.d("2222", "2222");
                    startActivity(new Intent(ActivityAddState.this,FragmentGetState.class));

                }
            }

            @Override
            public void onFailure(Call<AddStateResponse> call, Throwable t) {
                avi.setVisibility(View.GONE);
            }
        });

    }else {
            avi.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }




}
}
