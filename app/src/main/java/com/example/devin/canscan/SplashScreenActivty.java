package com.example.devin.canscan;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreenActivty extends AppCompatActivity{
    public Boolean started = false;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //can get users information
        mAuth = FirebaseAuth.getInstance();
        //there is a user it is not null
        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(getApplication(),MainActivity.class);
            //erase everything on top of activity
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return;
        }
        else{
            Intent intent = new Intent(getApplication(),ChooseLoginRegistrationActivity.class);
            //erase everything on top of activity
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return;

        }
    }
}
