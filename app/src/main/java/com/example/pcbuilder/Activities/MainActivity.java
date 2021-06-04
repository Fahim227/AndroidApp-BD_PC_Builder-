package com.example.pcbuilder.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pcbuilder.Fragments.Cart;
import com.example.pcbuilder.Fragments.Home;
import com.example.pcbuilder.Fragments.Profile;
import com.example.pcbuilder.R;
import com.example.pcbuilder.api.ApiClient;
import com.example.pcbuilder.models.ProductApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    //public String comName;
    private BottomNavigationView bottomNavigationView;
    public static List<ProductApi> productApis;
    Intent intent;
    public static String componentUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = getIntent();
        componentUrl = String.valueOf(intent.getStringExtra("componentsUrl"));
        Toast.makeText(this,componentUrl,Toast.LENGTH_LONG).show();
        productApis = new ArrayList<>();
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
                Bundle bundle = new Bundle();
                bundle.putString("comName",componentUrl);
                fragment.setArguments(bundle);
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
}