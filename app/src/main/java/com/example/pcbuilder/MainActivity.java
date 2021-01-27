package com.example.pcbuilder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pcbuilder.Fragments.Cart;
import com.example.pcbuilder.Fragments.Home;
import com.example.pcbuilder.Fragments.Profile;
import com.example.pcbuilder.api.ApiClient;
import com.example.pcbuilder.models.ProductApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    public static List<ProductApi> productApis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productApis = new ArrayList<>();
       // getProducts();
        bottomNavigationView = findViewById(R.id.bottomnavigationbarID);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerID,new Home()).commit();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.homemenuId:
                fragment = new Home();
                break;
            case R.id.accountmenuID:
                fragment = new Profile();
                break;
            case R.id.cartmenuID:
                fragment = new Cart();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.containerID,fragment).commit();
        return true;
    }

    public void getProducts(){
        Call<List<ProductApi>> call = ApiClient.getInstance().getApi().getHomeProducts();
        call.enqueue(new Callback<List<ProductApi>>() {
            //List<ProductApi> productApiList;
            @Override
            public void onResponse(Call<List<ProductApi>> call, Response<List<ProductApi>> response) {

                if(response.isSuccessful() && response.body() != null){
                    productApis = response.body();
                    Toast.makeText(getApplicationContext(),String.valueOf(productApis.size()),Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG).show();
                    System.out.println(response.message());
                }

            }

            @Override
            public void onFailure(Call<List<ProductApi>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                System.out.println(t.getLocalizedMessage());

            }
        });
    }
}