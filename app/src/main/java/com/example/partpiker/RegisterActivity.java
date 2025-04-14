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

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getName();
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        int key = getIntent().getIntExtra("KEY",0);
        if (key != 42){
            finish();
        }
        auth = FirebaseAuth.getInstance();
        Log.i(TAG, "onCreate");
    }

    public void register(View view) {
        EditText emailArea = findViewById(R.id.editTextEmail);
        EditText pwdArea = findViewById(R.id.editTextPasswordReg);
        EditText pwd2Area = findViewById(R.id.editTextPasswordReg2);

        String email = emailArea.getText().toString();
        String pwd = pwdArea.getText().toString();
        String pwd2 = pwd2Area.getText().toString();

        if(!pwd.equals(pwd2)){
            Log.e(TAG, "register: a jelszavak nem egyeznek");
            Toast.makeText(RegisterActivity.this,"Jelszavak nem egyeznek",Toast.LENGTH_LONG).show();
            return;
        }
        if (email.isEmpty() || pwd.isEmpty()){
            return;
        }

        auth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "sikeres register");
                    switchToLogin();

                }else{
                    Log.d(TAG, "sikertelen register");
                    Toast.makeText(RegisterActivity.this,"Sikertelen regisztráció",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void switchToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("KEY",41);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
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
