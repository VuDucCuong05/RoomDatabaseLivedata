package com.example.roomdatabaselivedata.navigation_da.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.roomdatabaselivedata.R;

public class PrivacyPolicyFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_privacy_policy, container, false);
        Button btBack = view.findViewById(R.id.bt_back);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().onBackPressed();
                Navigation.findNavController(v).navigate(R.id.action_privacyPolicyFragment_to_updateFragment);
            }
        });
        return view;
    }
}