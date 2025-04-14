package com.example.partpiker;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PartsActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parts);



        Log.d(TAG, "onCreate");
    }
}
