package com.example.pcbuilder.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pcbuilder.R;
import com.example.pcbuilder.models.User;


public class Profile extends Fragment {
    private EditText username,email;
    private Button save;
    private ImageView profileimg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        username = root.findViewById(R.id.profileusernameID);
        email = root.findViewById(R.id.profilemailID);
        save  = root.findViewById(R.id.saveID);
        profileimg  = root.findViewById(R.id.profileimgID);

        User user = new User("Fahim227","islamfahim227@gmail.com","........",R.drawable.profileuser);
        Log.d("profile: ","1");



        profileimg.setImageResource(user.getImg());
        username.setText(user.getUsername());
        email.setText(user.getEmail());

        Toast.makeText(getActivity(),user.getUsername(),Toast.LENGTH_LONG).show();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Save and update profile info
            }
        });

        return root;
    }
}