package com.example.devin.canscan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class registrationActivity extends AppCompatActivity {
    private Button mRegistration;
    private EditText mEmail, mPassword, mName;

    //working with database
    private FirebaseAuth mAuth;
    //tells us when they login so when we can move on to hte next step
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //just in case a user slips a user in past our other
                if(user != null){
                    Intent intent = new Intent(getApplication(),MainActivity.class);
                    //erase everything on top of activity
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    return;
                }

            }
        } ;

        mAuth = FirebaseAuth.getInstance();

        mRegistration = findViewById(R.id.registration);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mName = findViewById(R.id.name);



        mRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = mName.getText().toString();

                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(registrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //if the task is not successful
                        if(!task.isSuccessful()){
                            Toast.makeText(getApplication(), "Sign in Error", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            //get the id f the current user
                            String userId = mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUserDb  = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
                            //format user data for database entry
                            Map userInfo = new HashMap<>();
                            userInfo.put("email",email);
                            userInfo.put("name",name);
                            userInfo.put("profileImageUrl","default");

                            currentUserDb.updateChildren(userInfo);

                        }
                    }
                });
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}
