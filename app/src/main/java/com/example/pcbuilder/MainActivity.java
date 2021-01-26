package com.example.pcbuilder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.pcbuilder.Fragments.Cart;
import com.example.pcbuilder.Fragments.Home;
import com.example.pcbuilder.Fragments.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}