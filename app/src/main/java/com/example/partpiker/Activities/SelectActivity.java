package com.example.partpiker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.partpiker.ListRowAdapter;
import com.example.partpiker.PartReader;
import com.example.partpiker.Parts.*;
import com.example.partpiker.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SelectActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListRowAdapter adapter;
    private List<Part> partList= new ArrayList<Part>();
    private PartReader partReader = new PartReader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_part);

        recyclerView = findViewById(R.id.part_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        switch (type){
            case "mobo":
                for (MOBO m: partReader.readMOBO(this)) {
                    partList.add(m);
                }
                break;
            case "cpu":
                for (CPU c: partReader.readCPU(this)) {
                    partList.add(c);
                }
                break;
            case "gpu":
                for (GPU g: partReader.readGPU(this)) {
                    partList.add(g);
                }
                break;
            case "ram":
                for (RAM r: partReader.readRAM(this)) {
                    partList.add(r);
                }
                break;
            default:
                intent = new Intent(this,PartsActivity.class);
                startActivity(intent);
                finish();
        }

        adapter = new ListRowAdapter(this, partList);

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.scheduleLayoutAnimation();

        recyclerView.setAdapter(adapter);
    }
}

