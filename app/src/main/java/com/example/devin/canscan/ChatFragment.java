package com.example.devin.canscan;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

//this class holds the fragment for chat from the main view
public class ChatFragment extends android.support.v4.app.Fragment{
    //the function that we call from inside the main activity


    private Button mLogout;
    private Button mFindUsers;
    public static ChatFragment newInstance(){
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }


    @Override
    public View onCreateView( LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        //inflate the view that we want
        //use view.findby view to find the view
        View view = inflater.inflate(R.layout.fragment_chat,container,false);


        mLogout = view.findViewById(R.id.logout);
        mFindUsers = view.findViewById(R.id.findUsers);

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogOut();
            }
        });

        mFindUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FindUsers();
            }
        });

        //return appropriate view

        return view;
    }
    private void LogOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), SplashScreenActivty.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return;
    }

    private void FindUsers() {
        Intent intent = new Intent(getContext(),findUsersActivity.class);
        startActivity(intent);
        return;
    }

    }
