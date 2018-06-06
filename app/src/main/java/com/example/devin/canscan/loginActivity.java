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

import java.util.concurrent.Executor;

public class loginActivity extends AppCompatActivity {
    private Button mLogin;
    private EditText mEmail, mPassword;

    //working with database
    private FirebaseAuth mAuth;
    //tells us when they login so when we can move on to hte next step
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        mLogin = findViewById(R.id.login);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 final String email = mEmail.getText().toString();
                 final String password = mPassword.getText().toString();
                 mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(loginActivity.this,new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         //if the task is not successful
                         if(!task.isSuccessful()){
                             Toast.makeText(loginActivity.this, "Sign in Error", Toast.LENGTH_SHORT).show();
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
