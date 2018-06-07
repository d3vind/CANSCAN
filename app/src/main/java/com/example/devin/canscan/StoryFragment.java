package com.example.devin.canscan;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//this class holds the fragment for chat from the main view
public class StoryFragment extends android.support.v4.app.Fragment{
    //the function that we call from inside the main activity
    public static StoryFragment newInstance(){
        StoryFragment fragment = new StoryFragment();
        return fragment;
    }


    @Override
    public View onCreateView( LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        //inflate the view that we want
        //use view.findby view to find the view
        View view = inflater.inflate(R.layout.activity_story,container,false);
        //return appropriate view

        return view;
    }
}
