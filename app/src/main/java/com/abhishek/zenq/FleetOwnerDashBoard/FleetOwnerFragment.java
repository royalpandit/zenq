package com.abhishek.zenq.FleetOwnerDashBoard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.abhishek.zenq.Location.FragmentGetState;
import com.abhishek.zenq.R;

public class FleetOwnerFragment extends Fragment implements View.OnClickListener {
    RelativeLayout linfleetquat;
    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fleet_owner_dashboard, container, false);


        find();

        return rootView;
    }

    public void find() {
        linfleetquat=rootView.findViewById(R.id.linfleetquat);
        linfleetquat.setOnClickListener(this);

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
        }
    }
}
