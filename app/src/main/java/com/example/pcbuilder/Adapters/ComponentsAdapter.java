package com.example.pcbuilder.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcbuilder.R;
import com.example.pcbuilder.models.Components;

import java.util.List;

public class ComponentsAdapter extends RecyclerView.Adapter<ComponentsAdapter.ViewHolder> {

    private List<Components> componentsList;
    private Context cxt;

    public ComponentsAdapter(List<Components> componentsName, Context cxt) {
        this.componentsList = componentsName;
        this.cxt = cxt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.components_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.componentsName.setText(componentsList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return componentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView componentsName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            componentsName = itemView.findViewById(R.id.componentsNameID);
        }
    }
}
