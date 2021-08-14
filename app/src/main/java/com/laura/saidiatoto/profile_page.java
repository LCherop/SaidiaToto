package com.laura.saidiatoto;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class profile_page extends Fragment {
    private TextInputLayout fullName,email,phoneNo,password;
    private TextView fullNameLabel;
    private static final String TAG = "EmailPassword";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile_page, container, false);

        fullName =  rootView.findViewById(R.id.fullname_Profile);
        email = rootView.findViewById(R.id.emailAd_Profile);
        phoneNo = rootView.findViewById(R.id.phoneNo_Profile);

        showUserData();
        return rootView;
    }

    private void showUserData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String name = "",emailString = "",number = "";
        if (user != null) {
            // User is signed in
            name = user.getDisplayName();
            emailString = user.getEmail();
            number = user.getPhoneNumber();

            boolean emailVerified = user.isEmailVerified();
            String uid = user.getUid();
        } else {
            // No user is signed in
        }


        fullNameLabel.setText(name);

        fullName.getEditText().setText(name);
        email.getEditText().setText(emailString);
        phoneNo.getEditText().setText(number);


    }
}