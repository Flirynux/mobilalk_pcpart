package com.example.partpiker.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.partpiker.Parts.CPU;
import com.example.partpiker.Parts.Config;
import com.example.partpiker.Parts.GPU;
import com.example.partpiker.Parts.MOBO;
import com.example.partpiker.Parts.RAM;
import com.example.partpiker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CompareActivity extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore firestore;
    private CollectionReference dbCollection;
    CPU cpu;
    GPU gpu;

    Config config = new Config();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare);


        firestore = FirebaseFirestore.getInstance();
        initConfig();

    }

    private void initConfig(){
        dbCollection = firestore.collection("config");
        dbCollection.limit(1).whereEqualTo("uID",user.getUid()).get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (!queryDocumentSnapshots.isEmpty()) {
                config = queryDocumentSnapshots.getDocuments().get(0).toObject(Config.class);
                updateUIWithConfig();
            }
        });
    }

    private void updateUIWithConfig(){
        TextView cpuAName = findViewById(R.id.cpu_a_name);
        TextView cpuASpeed = findViewById(R.id.cpu_a_speed);
        TextView cpuABoost = findViewById(R.id.cpu_a_boost);
        TextView cpuACore = findViewById(R.id.cpu_a_core);
        TextView cpuAThread = findViewById(R.id.cpu_a_thread);

        TextView gpuAName = findViewById(R.id.gpu_a_name);
        TextView gpuAVram = findViewById(R.id.gpu_a_vram);
        TextView gpuAVramVer = findViewById(R.id.gpu_a_vram_ver);
        TextView gpuAPcie = findViewById(R.id.gpu_a_pcie);


        if(config.getCpu() != null && !config.getCpu().isEmpty()){
            DocumentReference docRef = firestore.collection("cpu").document(config.getCpu());

            docRef.get().addOnSuccessListener(documentSnapshot -> {
                cpu = documentSnapshot.toObject(CPU.class);
                cpuAName.setText(cpu.getName());
                cpuASpeed.setText(cpu.getBase_speed() + " GHz");
                cpuABoost.setText(cpu.getBoost_speed() + " GHz");
                cpuACore.setText(cpu.getCores() + " cores");
                cpuAThread.setText(cpu.getThreads() + " threads");
            });
        }
        if(config.getGpu() != null && !config.getGpu().isEmpty()){
            DocumentReference docRef = firestore.collection("gpu").document(config.getGpu());

            docRef.get().addOnSuccessListener(documentSnapshot -> {
                gpu = documentSnapshot.toObject(GPU.class);
                gpuAName.setText(gpu.getName());
                gpuAVram.setText(gpu.getVRAM_GB() + " GB");
                gpuAVramVer.setText(gpu.getVRAM_ver());
                gpuAPcie.setText(Float.toString(gpu.getPCIE_version()));
            });
        }

    }
}