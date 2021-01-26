package com.example.pcbuilder.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pcbuilder.R;
import com.example.pcbuilder.models.ProductApi;
import com.example.pcbuilder.models.Products;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<ProductApi> products;
    //List<ProductApi> productApis;
    Context cxt;
    private OnProductClickListener onProductClickListener;

    public interface OnProductClickListener{
        void onProductClick(int position);
    }

    public void setOnProductClickListener(OnProductClickListener listener){
        onProductClickListener = listener;
    }

    public ProductAdapter(List<ProductApi> products, Context cxt) {
        this.products = products;
        this.cxt = cxt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.sample_products,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*holder.imageView.setImageResource(products.get(position).getImg());
        holder.name.setText(products.get(position).getTitle());
        holder.price.setText(products.get(position).getPrice());*/
        Glide.with(cxt).load(products.get(position).getImages()).into(holder.imageView);
        holder.name.setText(products.get(position).getName());
        holder.price.setText(products.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.productimgID);
            name = itemView.findViewById(R.id.producttitleID);
            price =  itemView.findViewById(R.id.productpriceID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onProductClickListener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            onProductClickListener.onProductClick(position);
                        }
                    }
                }
            });
        }
    }
}
