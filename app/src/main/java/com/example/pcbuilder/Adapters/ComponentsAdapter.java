package com.example.pcbuilder.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcbuilder.MainActivity;
import com.example.pcbuilder.R;
import com.example.pcbuilder.models.Components;
import com.example.pcbuilder.models.Shop;

import java.util.List;

public class ComponentsAdapter extends RecyclerView.Adapter<ComponentsAdapter.ViewHolder> {
    List<Components> components;
    OnComponenetsClickListener onComponenetsClickListener;

    Context cxt;

    public interface OnComponenetsClickListener{
        void onComponenetsClick(View view,int position);
    }

    public ComponentsAdapter(List<Components> components, Context cxt) {
        this.components = components;
        this.cxt = cxt;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.components_layout_sample,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(components.get(position).getName());
        holder.setOnComponentsClickListener(new OnComponenetsClickListener() {
            @Override
            public void onComponenetsClick(View view, int position) {
                Intent intent = new Intent(cxt, MainActivity.class);
                //Toast.makeText(cxt,components.get(position).getName(),Toast.LENGTH_LONG).show();
                intent.putExtra("componentsName",components.get(position).getName());
                cxt.startActivity(intent);
            }
        });
       // holder.img.setImageResource(shops.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return components.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        //ImageView img;
        OnComponenetsClickListener onComponentsClickListener;

        public void setOnComponentsClickListener(OnComponenetsClickListener listener){
            this.onComponentsClickListener = listener;
        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //img = itemView.findViewById(R.id.shopimgID);
            title = itemView.findViewById(R.id.shoptitleID);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onComponentsClickListener.onComponenetsClick(view,getAdapterPosition());
        }
    }
}
