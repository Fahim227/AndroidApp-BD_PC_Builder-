package com.example.pcbuilder.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pcbuilder.Adapters.CartAdapter;
import com.example.pcbuilder.R;
import com.example.pcbuilder.models.CartModel;

import java.util.ArrayList;
import java.util.List;

public class Cart extends Fragment {


    private RecyclerView recyclerView;
    private List<CartModel> cart;
    private CartAdapter cartAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = root.findViewById(R.id.cartrecycleviewID);

        CartModel c1 = new CartModel("Ram","1","3500");
        CartModel c2 = new CartModel("MotherBoard","1","10000");
        CartModel c3 = new CartModel("GPU","1","12000");

        cart = new ArrayList<>();
        cart.add(c1);
        cart.add(c2);
        cart.add(c3);



        cartAdapter = new CartAdapter(cart,getActivity());
        Toast.makeText(getActivity(),"Working",Toast.LENGTH_LONG).show();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(cartAdapter);

        return root;
    }
}