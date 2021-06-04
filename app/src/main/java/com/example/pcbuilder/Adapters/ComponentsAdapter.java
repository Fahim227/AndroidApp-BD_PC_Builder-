package com.example.pcbuilder.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcbuilder.Activities.ComponentsActivity;
import com.example.pcbuilder.Activities.MainActivity;
import com.example.pcbuilder.R;
import com.example.pcbuilder.models.BrandsList;
import com.example.pcbuilder.models.ComponentsList;

public class ComponentsAdapter extends RecyclerView.Adapter<ComponentsAdapter.ViewHolder> {
    ComponentsList components;
    OnComponenetsClickListener onComponenetsClickListener;

    Context cxt;

    public interface OnComponenetsClickListener{
        void onComponenetsClick(View view,int position);
    }

    public ComponentsAdapter(ComponentsList components, Context cxt) {
        this.components = components;
        this.cxt = cxt;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.components_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(components.getComponents().get(position).getName());
        holder.setOnComponentsClickListener(new OnComponenetsClickListener() {
            @Override
            public void onComponenetsClick(View view, int position) {
                Intent intent = new Intent(cxt, MainActivity.class);
                intent.putExtra("componentsUrl",components.getComponents().get(position).getLink());
                intent.putExtra("localUrl", ComponentsActivity.burl);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                cxt.startActivity(intent);
            }
        });
       // holder.img.setImageResource(shops.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return components.getComponents().size();
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
            title = itemView.findViewById(R.id.comID);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onComponentsClickListener.onComponenetsClick(view,getAdapterPosition());
        }
    }
}
