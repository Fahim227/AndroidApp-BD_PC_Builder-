package com.example.pcbuilder.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.pcbuilder.Adapters.ShopAdapter;
import com.example.pcbuilder.R;
import com.example.pcbuilder.models.Shop;

import java.util.ArrayList;
import java.util.List;

public class Shops extends AppCompatActivity {


    private RecyclerView shopView;
    private List<Shop> shops;
    ShopAdapter shopAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        shopView = findViewById(R.id.shopID);

        Shop shop1 = new Shop("Techland", R.drawable.techlnd);
        Shop shop2 = new Shop("Startech", R.drawable.startech);
        Shop shop3 = new Shop("Creatus", R.drawable.creatus);
        Shop shop4 = new Shop("UCC", R.drawable.ucc);
        Log.d("cheking 2", Integer.toString(shop1.img));
        shops = new ArrayList<>();
        shops.add(shop1);
        shops.add(shop2);
        shops.add(shop3);
        shops.add(shop4);
        shops.add(shop4);
        shops.add(shop4);
        shops.add(shop4);

        shopAdapter = new ShopAdapter(shops,this);
        Log.d("Checking 1","Not Working");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        shopView.setLayoutManager(gridLayoutManager);
        shopView.setAdapter(shopAdapter);


    }
}