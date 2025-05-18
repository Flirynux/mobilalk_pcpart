package com.example.partpiker.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.partpiker.Parts.Config;
import com.example.partpiker.R;

public class PartsActivity extends AppCompatActivity {

    TableLayout tableLayout;
    Config config = new Config();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);

        tableLayout = findViewById(R.id.config_table);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_slide_up);
        tableLayout.setLayoutAnimation(animation);
        tableLayout.scheduleLayoutAnimation();
        Intent intent = new Intent();

        if(config.getMobo() != null){
            addRow(config.getMobo().getManufacturer(), config.getMobo().getName());
            intent.putExtra("mobo",config.getMobo().getName());
        } else {
            addRowButton("mobo");
        }
        if(config.getCpu() != null){
            addRow(config.getCpu().getManufacturer(), config.getCpu().getName());
            intent.putExtra("cpu",config.getCpu().getName());
        } else {
            addRowButton("cpu");
        }
        if(config.getGpu() != null){
            addRow(config.getGpu().getManufacturer(), config.getGpu().getName());
            intent.putExtra("gpu",config.getGpu().getName());
        } else {
            addRowButton("gpu");
        }
        if(config.getRam() != null){
            addRow(config.getRam().getManufacturer(), config.getRam().getName());
            intent.putExtra("ram",config.getRam().getName());
        } else {
            addRowButton("ram");
        }

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
}

