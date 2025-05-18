package com.example.partpiker.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.partpiker.Parts.Config;
import com.example.partpiker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ConfigActivity extends AppCompatActivity {
    private static final String TAG = ConfigActivity.class.getName();

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);

        auth = FirebaseAuth.getInstance();

    }

    protected void selectPart(String type){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }


}