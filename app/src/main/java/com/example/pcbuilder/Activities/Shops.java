package com.example.pcbuilder.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pcbuilder.Adapters.ShopAdapter;
import com.example.pcbuilder.Presenter.ShopPresenter;
import com.example.pcbuilder.R;
import com.example.pcbuilder.View.ShopView;
import com.example.pcbuilder.models.Shop;

import java.util.List;

public class Shops extends AppCompatActivity implements ShopView {


    private RecyclerView shopView;
    private List<Shop> shops;
    ShopPresenter shopPresenter;
    ShopAdapter shopAdapter;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        progressDialog = new ProgressDialog(this);

        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
        shopView = findViewById(R.id.shopID);
        shopPresenter = new ShopPresenter(this);
        shopPresenter.getShops();


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        shopView.setLayoutManager(gridLayoutManager);



    }

    @Override
    public void showLoading() {
        progressDialog.show();

    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();

    }

    @Override
    public void getShops(final List<Shop> shopList) {
        shopAdapter = new ShopAdapter(shopList,this);
        shopView.setAdapter(shopAdapter);
        shopAdapter.setOnShopClickListener(new ShopAdapter.OnShopClickListener() {
            @Override
            public void onShopClickListener(int position) {
                Toast.makeText(getApplicationContext(),shopList.get(position).getShop_address()+"----"+shopList.get(position).getShop_name().toLowerCase(),Toast.LENGTH_LONG).show();
            }
        });
        shops = shopList;

    }
}