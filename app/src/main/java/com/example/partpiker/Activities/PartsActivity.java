package com.example.partpiker.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.partpiker.Parts.CPU;
import com.example.partpiker.Parts.Config;
import com.example.partpiker.Parts.GPU;
import com.example.partpiker.Parts.MOBO;
import com.example.partpiker.Parts.RAM;
import com.example.partpiker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class PartsActivity extends AppCompatActivity {

    TableLayout tableLayout;
    private FirebaseAuth auth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore firestore;
    private CollectionReference dbCollection;
    Config config = new Config();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);


        tableLayout = findViewById(R.id.config_table);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_slide_up);
        tableLayout.setLayoutAnimation(animation);
        tableLayout.scheduleLayoutAnimation();


        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        initConfig();

        Intent intent = new Intent();


    }

    private void addRow(String col1, String col2) {
        TableRow row = new TableRow(this);

        row.addView(createTextView(col1));
        row.addView(createTextView(col2));

        tableLayout.addView(row);
    }

    private void addRowButton(String text) {
        TableRow row = new TableRow(this);

        Button button = createRedirectButton(text);
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = 2;
        button.setLayoutParams(params);
        row.addView(button);

        tableLayout.addView(row);
    }

    private TextView createTextView(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setPadding(16, 16, 16, 16);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    public Button createRedirectButton(String text) {
        Button button = new Button(this);
        button.setText(text.toUpperCase()+" hozzáadása");
        button.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, SelectActivity.class);
            intent.putExtra("type",text);
            this.startActivity(intent);
        });

        return button;
    }

    private void initConfig(){
        dbCollection = firestore.collection("config");
        dbCollection.limit(1).whereEqualTo("uID",user.getUid()).get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.isEmpty()) {
                // Create new config
                Config newConfig = new Config(null, null, null, null, user.getUid());
                dbCollection.add(newConfig)
                        .addOnSuccessListener(docRef -> {
                            // After it's added, assign config and update UI
                            config = newConfig;
                            updateUIWithConfig(config);
                        })
                        .addOnFailureListener(e -> Log.e("FIRESTORE", "Error adding config", e));
            } else {
                config = queryDocumentSnapshots.getDocuments().get(0).toObject(Config.class);
                updateUIWithConfig(config);
            }
        });
    }
    private void updateUIWithConfig(Config config){
        if(config.getMobo() != null && !config.getMobo().isEmpty()){
            DocumentReference docRef = firestore.collection("mobo").document(config.getMobo());

            docRef.get().addOnSuccessListener(documentSnapshot -> {
                MOBO part = documentSnapshot.toObject(MOBO.class);
                addRow(part.getManufacturer(),part.getName());
            });
        } else {
            addRowButton("mobo");
        }
        if(config.getCpu() != null && !config.getCpu().isEmpty()){
            DocumentReference docRef = firestore.collection("cpu").document(config.getCpu());

            docRef.get().addOnSuccessListener(documentSnapshot -> {
                CPU part = documentSnapshot.toObject(CPU.class);
                addRow(part.getManufacturer(),part.getName());
            });
        } else {
            addRowButton("cpu");
        }
        if(config.getGpu() != null && !config.getGpu().isEmpty()){
            DocumentReference docRef = firestore.collection("gpu").document(config.getGpu());

            docRef.get().addOnSuccessListener(documentSnapshot -> {
                GPU part = documentSnapshot.toObject(GPU.class);
                addRow(part.getManufacturer(),part.getName());
            });
        } else {
            addRowButton("gpu");
        }
        if(config.getRam() != null && !config.getRam().isEmpty()){
            DocumentReference docRef = firestore.collection("ram").document(config.getRam());

            docRef.get().addOnSuccessListener(documentSnapshot -> {
                RAM part = documentSnapshot.toObject(RAM.class);
                addRow(part.getManufacturer(),part.getName());
            });
        } else {
            addRowButton("ram");
        }
    }

    public void compare(View view){
        Intent intent = new Intent(this, CompareActivity.class);
        startActivity(intent);
    }

}

