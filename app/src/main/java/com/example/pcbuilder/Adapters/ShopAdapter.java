package com.example.pcbuilder.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pcbuilder.R;
import com.example.pcbuilder.models.Shop;

import java.util.List;
import java.util.zip.Inflater;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    List<Shop> shops;

    Context cxt;
    private OnShopClickListener onShopClickListener;
    public interface OnShopClickListener{
        void onShopClickListener(int position);
    }

    public void setOnShopClickListener(OnShopClickListener onShopClickListener) {
        this.onShopClickListener = onShopClickListener;
    }

    public ShopAdapter(List<Shop> shops, Context cxt) {
        this.shops = shops;
        this.cxt = cxt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.shops_layout_sample,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(cxt).load(shops.get(position).getShop_img()).into(holder.img);
        holder.name.setText(shops.get(position).getShop_name());


    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.shopimgID);
            name = itemView.findViewById(R.id.shoptitleID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onShopClickListener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            onShopClickListener.onShopClickListener(position);
                        }
                    }
                }
            });

        }
    }
}
