package com.example.pcbuilder.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pcbuilder.Adapters.ComponentsAdapter;
import com.example.pcbuilder.Adapters.ProductAdapter;
import com.example.pcbuilder.Adapters.ShopAdapter;
import com.example.pcbuilder.R;
import com.example.pcbuilder.api.ApiClient;
import com.example.pcbuilder.models.BrandsList;
import com.example.pcbuilder.models.Components;
import com.example.pcbuilder.models.Shop;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComponentsActivity extends AppCompatActivity {
    private RecyclerView componentsView;
    private List<Components> componentsName;
    ComponentsAdapter componentsAdapter;
    private BrandsList brandsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components);
        componentsView = findViewById(R.id.componentsID);

       /* Shop shop1 = new Shop("Techland", R.drawable.techlnd);
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
        shops.add(shop4);*/
       /* componentsName = new ArrayList<>();
        Components ram = new Components("Ram");
        Components Processor = new Components("Processor");
        Components MotherBoard = new Components("MotherBoard");
        Components GPU = new Components("GPU");
        Components Powersupply = new Components("Powersupply");
        Components SSD = new Components("SSD");

        componentsName.add(ram);
        componentsName.add(Processor);
        componentsName.add(MotherBoard);
        componentsName.add(GPU);
        componentsName.add(Powersupply);
        componentsName.add(SSD);*/

        getComponents("https://www.startech.com.bd/component/");


        Log.d("Checking 1","Not Working");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);

        componentsView.setLayoutManager(gridLayoutManager);

        Log.d("Checking 2","Not Working");
    }

    public void getComponents(String name){
        Call<BrandsList> call = ApiClient.getInstance().getApi().getComponentsName(name);

        call.enqueue(new Callback<BrandsList>() {
            @Override
            public void onResponse(Call<BrandsList> call, Response<BrandsList> response) {
                if(response.isSuccessful() && response.body() != null){
                    brandsList = response.body();
                    int len = brandsList.getBrands().size();
                    componentsAdapter = new ComponentsAdapter(brandsList,getApplicationContext());
                    componentsView.setAdapter(componentsAdapter);
                    Toast.makeText(getApplicationContext(),String.valueOf(len),Toast.LENGTH_LONG).show();
                    //notify();
                }
            }

            @Override
            public void onFailure(Call<BrandsList> call, Throwable t) {

            }
        });

    }
}