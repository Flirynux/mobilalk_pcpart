package com.example.partpiker;

import android.content.Context;

import com.example.partpiker.Parts.CPU;
import com.example.partpiker.Parts.GPU;
import com.example.partpiker.Parts.MOBO;
import com.example.partpiker.Parts.Part;
import com.example.partpiker.Parts.RAM;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PartReader {
    private String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    public ArrayList<RAM> readRAM(Context context) {
        String json = loadJSONFromAsset(context, "parts.json");
        Gson gson = new Gson();

        // Read as Map first
        Type type = new TypeToken<Map<String, RAM>>(){}.getType();
        Map<String, RAM> ramMap = gson.fromJson(json, type);
        ArrayList<RAM> res = new ArrayList<RAM>();
        for (String key: ramMap.keySet()) {
            if(key.contains("ram")){
                res.add(ramMap.get(key));
            }
        }
        // Convert values to ArrayList
        return res;
    }
    public ArrayList<CPU> readCPU(Context context) {
        String json = loadJSONFromAsset(context, "parts.json");
        Gson gson = new Gson();

        // Read as Map first
        Type type = new TypeToken<Map<String, CPU>>(){}.getType();
        Map<String, CPU> cpuMap = gson.fromJson(json, type);
        ArrayList<CPU> res = new ArrayList<CPU>();
        for (String key: cpuMap.keySet()) {
            if(key.contains("cpu")){
                res.add(cpuMap.get(key));
            }
        }
        // Convert values to ArrayList
        return res;
    }
    public ArrayList<GPU> readGPU(Context context) {
        String json = loadJSONFromAsset(context, "parts.json");
        Gson gson = new Gson();

        // Read as Map first
        Type type = new TypeToken<Map<String, GPU>>(){}.getType();
        Map<String, GPU> gpuMap = gson.fromJson(json, type);
        ArrayList<GPU> res = new ArrayList<GPU>();
        for (String key: gpuMap.keySet()) {
            if(key.contains("gpu")){
                res.add(gpuMap.get(key));
            }
        }
        // Convert values to ArrayList
        return res;
    }
    public ArrayList<MOBO> readMOBO(Context context) {
        String json = loadJSONFromAsset(context, "parts.json");
        Gson gson = new Gson();

        // Read as Map first
        Type type = new TypeToken<Map<String, MOBO>>(){}.getType();
        Map<String, MOBO> moboMap = gson.fromJson(json, type);
        ArrayList<MOBO> res = new ArrayList<MOBO>();
        for (String key: moboMap.keySet()) {
            if(key.contains("mobo")){
                res.add(moboMap.get(key));
            }
        }
        // Convert values to ArrayList
        return res;
    }
}
