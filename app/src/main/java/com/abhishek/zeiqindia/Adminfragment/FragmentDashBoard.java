package com.abhishek.zeiqindia.Adminfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.abhishek.zeiqindia.R;

public class FragmentDashBoard extends Fragment implements View.OnClickListener {
    RelativeLayout linadmin;
    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dashboard, container, false);
        // fragmentManager = getActivity().getSupportFragmentManager();

        //init();
        find();

        return rootView;
    }

    public void find() {
        linadmin = rootView.findViewById(R.id.linadmin);
        linadmin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==linadmin){


           /*     if (getSupportFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentAdminGetUser) {
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentAdminGetUser()).addToBackStack(null).commit();
                }
*/
            }
    }
}
