package com.example.partpiker;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getName();
    private static final int KEY = 42;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        int key = getIntent().getIntExtra("KEY",0);
        if (key != 41){
            finish();
        }
        auth = FirebaseAuth.getInstance();
        Log.i(TAG, "onCreate");
    }

    public void login(View view) {
        EditText emailArea = findViewById(R.id.editTextEmail);
        EditText pwdArea = findViewById(R.id.editTextPassword);

        String email = emailArea.getText().toString();
        String pwd = pwdArea.getText().toString();
        if (email.isEmpty() || pwd.isEmpty()){
            return;
        }
        auth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "sikeres login");
                    switchToParts();
                }else{
                    Log.d(TAG, "sikertelen login");
                    Toast.makeText(LoginActivity.this,"Sikertelen bejelentkezés",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void switchToParts() {
        Intent intent = new Intent(this, PartsActivity.class);
        intent.putExtra("KEY",KEY);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void switchToRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("KEY",KEY);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }


}
