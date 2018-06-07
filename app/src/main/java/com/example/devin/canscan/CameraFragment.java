package com.example.devin.canscan;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//this class holds the fragment for chat from the main view
public class CameraFragment extends android.support.v4.app.Fragment{
    //the function that we call from inside the main activity
    public static CameraFragment newInstance(){
        CameraFragment fragment = new CameraFragment();
        return fragment;
    }


    @Override
    public View onCreateView( LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        //inflate the view that we want
        //use view.findby view to find the view
        View view = inflater.inflate(R.layout.fragment_camera,container,false);
        //return appropriate view

        return view;
    }
}
