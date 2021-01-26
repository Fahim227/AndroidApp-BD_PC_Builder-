package com.example.pcbuilder.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcbuilder.R;
import com.example.pcbuilder.models.CartModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
     List<CartModel> carts;
     Context cxt;

    public CartAdapter(List<CartModel> carts, Context cxt) {
        this.carts = carts;
        this.cxt = cxt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.cartrecycleview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.components.setText(carts.get(position).getComponents());
        holder.quantity.setText(carts.get(position).getQuantity());
        holder.price.setText(carts.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView components,quantity,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            components = itemView.findViewById(R.id.cartcomponentsID);
            quantity = itemView.findViewById(R.id.cartquantityID);
            price = itemView.findViewById(R.id.cartpriceID);
        }
    }
}
