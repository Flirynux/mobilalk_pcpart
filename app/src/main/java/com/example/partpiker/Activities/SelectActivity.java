package com.example.partpiker.Activities;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.partpiker.ListRowAdapter;
import com.example.partpiker.PartReader;
import com.example.partpiker.Parts.*;
import com.example.partpiker.R;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SelectActivity extends AppCompatActivity implements ListRowAdapter.OnPartSelectedListener {
    private RecyclerView recyclerView;
    private ListRowAdapter adapter;
    private List<Part> partList= new ArrayList<Part>();
    private List<String> ids= new ArrayList<>();

    //private PartReader partReader = new PartReader();
    private FirebaseFirestore firestore;
    private CollectionReference dbCollection;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private Config config = new Config(user.getUid());
    private String socket;
    private String ddr;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_part);

        firestore = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        getUserConfig();

        recyclerView = findViewById(R.id.part_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListRowAdapter( partList,this,this);

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.scheduleLayoutAnimation();

        recyclerView.setAdapter(adapter);
    }

    private void query(String type){
        dbCollection = firestore.collection(type);
        @SuppressWarnings("unchecked")
        final  Class<? extends Part> c;
        Boolean hasMobo = config.getMobo() !=null && !config.getMobo().isEmpty();
        Boolean hasCPU = config.getCpu() !=null && !config.getCpu().isEmpty();
        Boolean hasRAM = config.getRam() !=null && !config.getRam().isEmpty();
        Boolean ran = false;
        switch (type) {
            case "mobo":
                c = MOBO.class; //cpu +ram
                if(hasRAM){
                    if(hasCPU){
                        dbCollection.whereEqualTo("socket",socket).whereEqualTo("ram_ver",ddr).get().addOnSuccessListener(queryDocumentSnapshots ->{

                            for (QueryDocumentSnapshot doc: queryDocumentSnapshots) {
                                Part p = doc.toObject(c);
                                partList.add(p);
                                ids.add(doc.getId());
                            }
                            adapter.notifyDataSetChanged();
                        });
                    } else {
                        dbCollection.whereEqualTo("ram_ver",ddr).get().addOnSuccessListener(queryDocumentSnapshots ->{

                            for (QueryDocumentSnapshot doc: queryDocumentSnapshots) {
                                Part p = doc.toObject(c);
                                partList.add(p);
                                ids.add(doc.getId());
                            }
                            adapter.notifyDataSetChanged();
                        });
                    }
                }else if(hasCPU){
                    dbCollection.whereEqualTo("socket",socket).whereEqualTo("ram_ver",ddr).get().addOnSuccessListener(queryDocumentSnapshots ->{

                        for (QueryDocumentSnapshot doc: queryDocumentSnapshots) {
                            Part p = doc.toObject(c);
                            partList.add(p);
                            ids.add(doc.getId());
                        }
                        adapter.notifyDataSetChanged();
                    });
                }
                break;
            case "cpu":
                c = CPU.class; //mobo
                if(hasMobo){
                    ran=true;
                    dbCollection.whereEqualTo("socket",socket).whereEqualTo("ram_ver",ddr).get().addOnSuccessListener(queryDocumentSnapshots ->{

                        for (QueryDocumentSnapshot doc: queryDocumentSnapshots) {
                            Part p = doc.toObject(c);
                            partList.add(p);
                            ids.add(doc.getId());
                        }
                        adapter.notifyDataSetChanged();
                    });
                }
                break;
            case "gpu":
                c = GPU.class;
                break;
            case "ram":
                c = RAM.class; // mobo
                if (hasRAM){
                    ran=true;
                    dbCollection.whereEqualTo("ram_ver",ddr).get().addOnSuccessListener(queryDocumentSnapshots ->{

                        for (QueryDocumentSnapshot doc: queryDocumentSnapshots) {
                            Part p = doc.toObject(c);
                            partList.add(p);
                            ids.add(doc.getId());
                        }
                        adapter.notifyDataSetChanged();
                    });
                }
                break;
        }
        if(!ran){
            dbCollection.get().addOnSuccessListener(queryDocumentSnapshots ->{

                for (QueryDocumentSnapshot doc: queryDocumentSnapshots) {
                    Part p = doc.toObject(MOBO.class);
                    partList.add(p);
                    ids.add(doc.getId());
                }
                adapter.notifyDataSetChanged();
            });
        }


    }

    @Override
    public void onPartSelected(int part) {
        dbCollection = firestore.collection("config");
        dbCollection.limit(1).whereEqualTo("uID",user.getUid()).get().addOnSuccessListener(queryDocumentSnapshots -> {
            String id =queryDocumentSnapshots.getDocuments().get(0).getId();
            DocumentReference docRef = firestore.collection("config").document(id);
            docRef.update(type, ids.get(part),"updatedAt", FieldValue.serverTimestamp());
        });
        finish();
    }

    private void getUserConfig(){
        dbCollection = firestore.collection("config");

        dbCollection.limit(1)
                .whereEqualTo("uID", user.getUid())
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        config = queryDocumentSnapshots.getDocuments().get(0).toObject(Config.class);

                        List<Task<DocumentSnapshot>> tasks = new ArrayList<>();

                        // Task for mobo
                        if (config.getMobo() != null && !config.getMobo().isEmpty()) {
                            Task<DocumentSnapshot> moboTask = firestore.collection("mobo")
                                    .document(config.getMobo()).get();
                            tasks.add(moboTask);
                        }

                        // Task for CPU
                        if (config.getCpu() != null && !config.getCpu().isEmpty()) {
                            Task<DocumentSnapshot> cpuTask = firestore.collection("cpu")
                                    .document(config.getCpu()).get();
                            tasks.add(cpuTask);
                        }

                        // Task for RAM
                        if (config.getRam() != null && !config.getRam().isEmpty()) {
                            Task<DocumentSnapshot> ramTask = firestore.collection("ram")
                                    .document(config.getRam()).get();
                            tasks.add(ramTask);
                        }

                        Tasks.whenAllSuccess(tasks).addOnSuccessListener(results -> {
                            for (Object result : results) {
                                DocumentSnapshot doc = (DocumentSnapshot) result;

                                if (doc.exists()) {
                                    if (doc.getReference().getPath().contains("mobo")) {
                                        MOBO mobo = doc.toObject(MOBO.class);
                                        socket = mobo.getCpu_socket();
                                        ddr = mobo.getRam_ver();
                                    } else if (doc.getReference().getPath().contains("cpu")) {
                                        CPU cpu = doc.toObject(CPU.class);
                                        socket = cpu.getSocket();
                                    } else if (doc.getReference().getPath().contains("ram")) {
                                        RAM ram = doc.toObject(RAM.class);
                                        ddr = ram.getDatarate();
                                    }
                                }
                            }
                            // Now all document fetches are done
                            query(type);
                        });

                    } else {
                        // Config not found, proceed
                        query(type);
                    }
                });
    }
}