package com.example.pcbuilder.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pcbuilder.R;
import com.example.pcbuilder.models.CartModel;
import com.example.pcbuilder.models.ComponentDetails;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
     List<ComponentDetails> carts;
     Context cxt;

    public CartAdapter(List<ComponentDetails> carts, Context cxt) {
        this.carts = carts;
        this.cxt = cxt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.cart_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(cxt).load(carts.get(position).getImg()).into(holder.prodimg);
        holder.name.setText(carts.get(position).getName());
        holder.price.setText(carts.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView prodimg,delete;
        TextView name,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prodimg = itemView.findViewById(R.id.prodimgID);
            delete = itemView.findViewById(R.id.deleteID);
            name = itemView.findViewById(R.id.prodnameID);
            price = itemView.findViewById(R.id.prodpriceID);
        }
    }
}
