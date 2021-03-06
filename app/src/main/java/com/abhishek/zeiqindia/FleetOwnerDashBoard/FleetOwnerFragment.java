package com.abhishek.zeiqindia.FleetOwnerDashBoard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.abhishek.zeiqindia.R;
import com.abhishek.zeiqindia.Utility.CommonUtils;

public class FleetOwnerFragment extends Fragment implements View.OnClickListener {
    RelativeLayout linfleetquat,linadtrips;
    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fleet_owner_dashboard, container, false);


        find();

        return rootView;
    }

    public void find() {
        linfleetquat=rootView.findViewById(R.id.linfleetquat);
        linadtrips=rootView.findViewById(R.id.linadtrips);

        linfleetquat.setOnClickListener(this);
        linadtrips.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if (v==linfleetquat){
            Fragment fragment = new FragmentFleetOwnerAllBid();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }if(v==linadtrips){
          CommonUtils.goToFragment(getActivity(), new FragmentFleetOwnerMyTrip( ), R.id.fragmentContainer, true);

        }
    }
}
