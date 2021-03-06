package com.abhishek.zeiqindia;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abhishek.zeiqindia.Adminfragment.FragmentAdminGetUser;
import com.abhishek.zeiqindia.Adminfragment.FragmentDashBoard;
import com.abhishek.zeiqindia.Location.FragmentGetState;
import com.abhishek.zeiqindia.Prefrence.AppPreferences;
import com.abhishek.zeiqindia.Utility.AppBaseActivity;
import com.abhishek.zeiqindia.activity.LoginActivity;


public class MainActivity extends AppBaseActivity implements View.OnClickListener {
    TextView tv_home, tv_state, tvDriver, tvTruck, tvUserFeedback, tvLogout, tvHelp;
    ImageView imgfleet_owner;

    String USERTYPE;
    Animation uptodown, downtoup;
    DrawerLayout drawerLayout;
    LinearLayout left_drawer_left, left_drawerowner_layout;
    Toolbar toolbar;
    Fragment fragment = null;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        USERTYPE = AppPreferences.getSavedUser(MainActivity.this).getUser_type();


        find();
        // getLocation();
        //open drawer on navigation button click

    }

    public void find() {
        left_drawer_left = findViewById(R.id.left_drawer_layout);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        tv_state = findViewById(R.id.tv_state);
        tv_home = (TextView) findViewById(R.id.tv_home);
        tvDriver = findViewById(R.id.tvDriver);
        tvHelp = findViewById(R.id.tvHelp);
        tvTruck = findViewById(R.id.tvTruck);
        tvUserFeedback = findViewById(R.id.tvUserFeedback);
        tvLogout = findViewById(R.id.tvLogout);

        ///fleetOwner


        loadFragment(new FragmentDashBoard());

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                left_drawer_left.setVisibility(View.VISIBLE);
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        tv_home.setOnClickListener(this);
        tv_state.setOnClickListener(this);
        tvDriver.setOnClickListener(this);
        tvTruck.setOnClickListener(this);
        tvUserFeedback.setOnClickListener(this);
        tvHelp.setOnClickListener(this);
        tvLogout.setOnClickListener(this);

    }


    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            //CommonUtils.goToFragment(getApplicationContext(), new FragmentDashBoard( ), R.id.fragmentContainer, false);


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
        if (view == tv_state) {

            if (getSupportFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentGetState) {
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentGetState()).addToBackStack(null).commit();
            }
        }
        if (view == tv_home) {

            if (getSupportFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentAdminGetUser) {
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentAdminGetUser()).addToBackStack(null).commit();
            }
        } else if (view == tvLogout) {


            AppPreferences.clearPrefsdata(MainActivity.this);

            startActivity(new Intent(MainActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        }


        /*  else if (view == tvReceivedOrder) {
            if (getFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentOffer) {
            } else {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentOffer()).addToBackStack(null).commit();
            }
        } else if (view == tvPreviousOrders) {
            if (getFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentPastOrder) {
            } else {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentPastOrder()).addToBackStack(null).commit();
            }
        } else if (view == tvManageRevenue) {
            if (getFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentRevenue) {
            } else {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentRevenue()).addToBackStack(null).commit();
            }
        }else if (view == tvHome) {
            if (getFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentReceivedOrder) {
            } else {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentReceivedOrder()).addToBackStack(null).commit();
            }
        } else if (view == tvUserReview) {
            if (getFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentRestaurentReview) {
            } else {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentRestaurentReview()).addToBackStack(null).commit();
            }
        }else if (view == tvLogout) {
            AppPreferences.clearPrefsdata(MainActivity.this);

            startActivity(new Intent(MainActivity.this, LoginFragment.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        }
*/
        drawerLayout.closeDrawer(GravityCompat.START);

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(left_drawer_left)) {
            //drawer_slide();
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }


    }

}
