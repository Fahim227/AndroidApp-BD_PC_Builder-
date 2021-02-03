package com.example.pcbuilder.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcbuilder.Common;
import com.example.pcbuilder.Fragments.Home;
import com.example.pcbuilder.R;
import com.example.pcbuilder.models.Brands;
import com.example.pcbuilder.models.Components;

import java.util.List;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.ViewHolder> {

    private List<Brands> componentsList;
    private Context cxt;
    int row_index = -1;
    Common common;
    private Fragment fragment;
    //private onComponentsClickListener onComponentsClickListener;


    public interface onComponentsClickListener{
        void onComponentsClick(View view,int position);


    }

    public BrandsAdapter(List<Brands> componentsName, Context cxt, Fragment fragment) {
        this.componentsList = componentsName;
        this.cxt = cxt;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.components_layout,parent,false);

        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.componentsName.setText(componentsList.get(position).getName());
        holder.setOnComponentsClickListener(new onComponentsClickListener() {
            @Override
            public void onComponentsClick(View view, int position) {
                Toast.makeText(cxt,componentsList.get(position).getLink(),Toast.LENGTH_LONG).show();
                ((Home)fragment).startProgress();
                ((Home)fragment).getProductsByBrands(componentsList.get(position).getLink());
                row_index = position;
                //Common.current = componentsList.get(position);
                notifyDataSetChanged();
            }
        });

        if (row_index==position){
            //Chage layout style
            LinearLayout linearLayout = holder.itemView.findViewById(R.id.componentID);
            // GradientDrawable drawable = (GradientDrawable) linearLayout.getBackground();
            GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,new int[] {0xFFFFFF,0xFFFFFF});
            gd.setCornerRadius(0f);
            //not working
            gd.setStroke(5,R.color.stroke);
            //drawable.setColor(R.color.colorWhite);
            linearLayout.setBackgroundDrawable(gd);
            holder.componentsName.setTextColor(R.color.colorPrimary);
        }
        else{
            LinearLayout linearLayout = holder.itemView.findViewById(R.id.componentID);
            linearLayout.setBackgroundDrawable(ContextCompat.getDrawable(cxt, R.drawable.item_shape) );

            holder.componentsName.setTextColor(Color.parseColor("#FFFFFF"));



        }
    }

    @Override
    public int getItemCount() {
        return componentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView componentsName;
        onComponentsClickListener onComponentsClickListener;

        public void setOnComponentsClickListener(onComponentsClickListener listener){
            this.onComponentsClickListener = listener;
        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            componentsName = itemView.findViewById(R.id.componentsNameID);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onComponentsClickListener.onComponentsClick(view,getAdapterPosition());
        }
    }
}
