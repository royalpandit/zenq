package com.abhishek.zenq.FleetOwnerDashBoard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.abhishek.zenq.Adminfragment.FragmentAdminGetUser;
import com.abhishek.zenq.Adminfragment.FragmentDashBoard;
import com.abhishek.zenq.Location.FragmentGetState;
import com.abhishek.zenq.MainActivity;
import com.abhishek.zenq.Prefrence.AppPreferences;
import com.abhishek.zenq.R;
import com.abhishek.zenq.activity.LoginActivity;

public class MainActivityOwnerDashboard extends AppCompatActivity implements View.OnClickListener {
    TextView tv_home,tv_state, tvDriver, tvTruck, tvUserFeedback, tvLogout,tvHelp;
    ImageView imgfleet_owner;
    TextView fleet_tv_username,fleet_tv_useremail,fleet_tv_dfleet_logout,fleet_tv_dfleettruck,fleet_tv_dfleetdriver,
            fleet_tv_dfleetbooking,fleet_tv_dfleetProfile,fleet_tv_dfleetMainten,fleet_tv_dfleetContact,fleet_tv_dfleetterms;

    String USERTYPE;
    Animation uptodown,downtoup;
    DrawerLayout drawerLayout;
    LinearLayout left_drawer_left,left_drawerowner_layout;
    Toolbar toolbar;
    Fragment fragment = null;

    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fleet_owner);
        USERTYPE = AppPreferences.getSavedUser(MainActivityOwnerDashboard.this).getUser_type();


        find();
        // getLocation();
        //open drawer on navigation button click

    }

    public void find(){
         drawerLayout = findViewById(R.id.drawer_layout);
         left_drawerowner_layout = findViewById(R.id.left_drawerowner_layout);


        ///fleetOwner
        toolbar = findViewById(R.id.toolbar);

        imgfleet_owner=findViewById(R.id.user_image);
        fleet_tv_username=findViewById(R.id.tv_username);
        fleet_tv_useremail=findViewById(R.id.tv_useremail);
        fleet_tv_dfleet_logout=findViewById(R.id.tv_dfleet_logout);
        fleet_tv_dfleettruck=findViewById(R.id.tv_dfleettruck);
        fleet_tv_dfleetdriver=findViewById(R.id.tv_dfleetdriver);
        fleet_tv_dfleetbooking=findViewById(R.id.tv_dfleetbooking);
        fleet_tv_dfleetProfile=findViewById(R.id.tv_dfleetProfile);
        fleet_tv_dfleetMainten=findViewById(R.id.tv_dfleetMainten);
        fleet_tv_dfleetContact=findViewById(R.id.tv_dfleetContact);
        fleet_tv_dfleetterms=findViewById(R.id.tv_dfleetterms);






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

    }



    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();

            return true;
        }
        return false;
    }
    @Override
    public void onClick(View view) {
       /* if (view == tv_state) {

            if (getSupportFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentGetState) {
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentGetState()).addToBackStack(null).commit();
            }
        } if (view == tv_home) {

            if (getSupportFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentAdminGetUser) {
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentAdminGetUser()).addToBackStack(null).commit();
            }
        }else if (view == tvLogout) {
            AppPreferences.clearPrefsdata(MainActivityOwnerDashboard.this);

            startActivity(new Intent(MainActivityOwnerDashboard.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        }*/



      //  drawerLayout.closeDrawer(GravityCompat.START);

    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(left_drawerowner_layout)) {
            //drawer_slide();
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }


    }

}
