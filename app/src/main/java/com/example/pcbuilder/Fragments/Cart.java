package com.example.pcbuilder.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pcbuilder.Activities.ProductDetails;
import com.example.pcbuilder.Adapters.CartAdapter;
import com.example.pcbuilder.Adapters.ProductAdapter;
import com.example.pcbuilder.R;
import com.example.pcbuilder.api.ApiClient;
import com.example.pcbuilder.models.CartModel;
import com.example.pcbuilder.models.ComponentDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends Fragment {


    private RecyclerView recyclerView;
    private List<ComponentDetails> carts;
    private CartAdapter cartAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_cart, container, false);
        carts = new ArrayList<>();
        getCartItems(11);

        recyclerView = root.findViewById(R.id.cartrecycleviewID);

        Toast.makeText(getActivity(),"Working",Toast.LENGTH_LONG).show();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return root;
    }
    public void getCartItems(int userid){
        Call<List<ComponentDetails>> call = ApiClient.getInstance().getApi().getCartItems(userid);
        call.enqueue(new Callback<List<ComponentDetails>>() {
            @Override
            public void onResponse(Call<List<ComponentDetails>> call, Response<List<ComponentDetails>> response) {
                if(response.isSuccessful() && response.body() != null){
                    carts = response.body();
                    Toast.makeText(getActivity(),String.valueOf(carts.size()),Toast.LENGTH_LONG).show();
                    cartAdapter = new CartAdapter(carts,getActivity());
                    recyclerView.setAdapter(cartAdapter);
                    cartAdapter.notifyDataSetChanged();

                }
                else{
                    Toast.makeText(getActivity(),response.message(),Toast.LENGTH_LONG).show();
                    System.out.println(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ComponentDetails>> call, Throwable t) {

            }
        });
    }
}