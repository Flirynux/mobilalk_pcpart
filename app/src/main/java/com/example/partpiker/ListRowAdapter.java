package com.example.partpiker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partpiker.Parts.Part;
import com.example.partpiker.Parts.RAM;

import java.util.List;
public class ListRowAdapter extends RecyclerView.Adapter<ListRowAdapter.PartViewHolder>{

    private Context context;
    private List<Part> partList;

    public ListRowAdapter(Context context, List<Part> partList) {
        this.context = context;
        this.partList = partList;
    }

    @NonNull
    @Override
    public PartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false);
        return new PartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartViewHolder holder, int position) {
        Part part = partList.get(position);
        if(part.getClass() == RAM.class){
            holder.partName.setText(part.getName() + " " + ((RAM) part).getSize() + " GB");
        } else {
            holder.partName.setText(part.getName());
        }
        holder.partSpec.setText(part.getManufacturer());

        holder.selectButton.setOnClickListener(v -> {
            Toast.makeText(context, part.getName() + " selected!", Toast.LENGTH_SHORT).show();
            // You can add callback to pass selection to your activity
        });
    }

    @Override
    public int getItemCount() {
        return partList.size();
    }

    static class PartViewHolder extends RecyclerView.ViewHolder {
        TextView partName, partSpec;
        Button selectButton;

        public PartViewHolder(@NonNull View itemView) {
            super(itemView);
            partName = itemView.findViewById(R.id.partName);
            partSpec = itemView.findViewById(R.id.partSpec);
            selectButton = itemView.findViewById(R.id.selectButton);
        }
    }
}
