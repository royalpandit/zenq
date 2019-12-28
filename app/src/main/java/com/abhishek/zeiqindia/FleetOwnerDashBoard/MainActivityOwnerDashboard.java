package com.abhishek.zeiqindia.FleetOwnerDashBoard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.abhishek.zeiqindia.Prefrence.AppPreferences;
import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Utility.AppBaseActivity;
import com.abhishek.zeiqindia.Utility.CommonUtils;
import com.abhishek.zeiqindia.activity.LoginActivity;
import com.squareup.picasso.Picasso;

import static com.abhishek.zeiqindia.rest.ApiClient.Temp_BAse_IMAGE_URL;

public class MainActivityOwnerDashboard extends AppBaseActivity implements View.OnClickListener {
    TextView tv_home, tv_state, tvDriver, tvTruck, tvUserFeedback, tvLogout, tvHelp;
    ImageView imgfleet_owner;
    TextView fleet_tv_username, fleet_tv_useremail, fleet_tv_dfleet_logout, fleet_tv_dfleettruck, fleet_tv_dfleetdriver,
            fleet_tv_dfleetbooking, fleet_tv_dfleetProfile, fleet_tv_dfleetMainten, fleet_tv_dfleetContact, fleet_tv_dfleetterms, fleet_tv_dfleetparty;

    String USERTYPE,UserName,UserEmail,UserImage;
    Animation uptodown, downtoup;
    DrawerLayout drawerLayout;
    LinearLayout left_drawer_left, left_drawerowner_layout;
    Toolbar toolbar;
    Fragment fragment = null;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fleet_owner);
        USERTYPE = AppPreferences.getSavedUser(MainActivityOwnerDashboard.this).getUser_type();
        UserName = AppPreferences.getSavedUser(MainActivityOwnerDashboard.this).getName();
        UserEmail = AppPreferences.getSavedUser(MainActivityOwnerDashboard.this).getEmail();
        UserImage = AppPreferences.getSavedUser(MainActivityOwnerDashboard.this).getImage();
        Log.e("userimage",Temp_BAse_IMAGE_URL+UserImage);



        find();
        // getLocation();
        //open drawer on navigation button click

    }

    public void find() {
        drawerLayout = findViewById(R.id.drawer_layout);
        left_drawerowner_layout = findViewById(R.id.left_drawerowner_layout);
        ///fleetOwner
        toolbar = findViewById(R.id.toolbar);

        imgfleet_owner = findViewById(R.id.user_image);
        fleet_tv_username = findViewById(R.id.tv_username);
        fleet_tv_useremail = findViewById(R.id.tv_useremail);
        fleet_tv_dfleet_logout = findViewById(R.id.tv_dfleet_logout);
        fleet_tv_dfleettruck = findViewById(R.id.tv_dfleettruck);
        fleet_tv_dfleetdriver = findViewById(R.id.tv_dfleetdriver);
        fleet_tv_dfleetbooking = findViewById(R.id.tv_dfleetbooking);
        fleet_tv_dfleetProfile = findViewById(R.id.tv_dfleetProfile);
        fleet_tv_dfleetMainten = findViewById(R.id.tv_dfleetMainten);
        fleet_tv_dfleetContact = findViewById(R.id.tv_dfleetContact);
        fleet_tv_dfleetterms = findViewById(R.id.tv_dfleetterms);

        fleet_tv_dfleetparty = findViewById(R.id.tv_dfleetparty);

        fleet_tv_username.setText(UserName);
        fleet_tv_useremail.setText(UserEmail);
        Picasso.with(mContext).load(Temp_BAse_IMAGE_URL+UserImage).placeholder(R.drawable.side_profile).error(R.drawable.side_profile).into(imgfleet_owner);

        loadFragment(new FleetOwnerFragment());

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        fleet_tv_dfleet_logout.setOnClickListener(this);
        fleet_tv_dfleettruck.setOnClickListener(this);
        fleet_tv_dfleetdriver.setOnClickListener(this);
        fleet_tv_dfleetMainten.setOnClickListener(this);
        fleet_tv_dfleetparty.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            CommonUtils.goToFragment(MainActivityOwnerDashboard.this, new FleetOwnerFragment(), R.id.fragmentContainer, false);
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == fleet_tv_dfleet_logout) {
            CommonUtils.showDialog(mContext, getString(R.string.logout_sure_text), getString(R.string.logout), getString(R.string.yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    AppPreferences.clearPrefsdata(mContext);

                    dialogInterface.dismiss();
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }, null, getString(R.string.no), null, null, true);


        }
        if (v == fleet_tv_dfleettruck) {

            CommonUtils.goToFragment(mContext, new FleetOwnerMyTruckList(), R.id.fragmentContainer,
                    true);

        }
        if (v == fleet_tv_dfleetdriver) {
            CommonUtils.goToFragment(mContext, new FleetOwnerMyDriverList(), R.id.fragmentContainer,
                    true);

        }
        if (v == fleet_tv_dfleetMainten) {
            CommonUtils.goToFragment(mContext, new FleetOwnerVendorMentainence(), R.id.fragmentContainer,
                    true);
        }
        if (v == fleet_tv_dfleetparty) {
            CommonUtils.goToFragment(mContext, new FleetOwnerMyParty(), R.id.fragmentContainer,
                    true);
        }

        drawerLayout.closeDrawer(GravityCompat.START);

    }


}
