package com.example.pcbuilder.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pcbuilder.Adapters.ComponentsAdapter;
import com.example.pcbuilder.Adapters.ProductAdapter;
import com.example.pcbuilder.MainActivity;
import com.example.pcbuilder.ProductDetails;
import com.example.pcbuilder.R;
import com.example.pcbuilder.api.ApiClient;
import com.example.pcbuilder.models.Brands;
import com.example.pcbuilder.models.Components;
import com.example.pcbuilder.models.ProductApi;
import com.example.pcbuilder.models.Products;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment implements ProductAdapter.OnProductClickListener {
    private EditText search;
    private RecyclerView components;
    private RecyclerView brands;
    private List<Components> componentsName, brandName;
    //private List<Brands> brandName;
    private ComponentsAdapter componentsAdapter,brandsAdapter;
    private List<Products> products;
    private List<ProductApi> productApis;
    private RecyclerView productRecycle;
    private ProductAdapter productAdapter;
    public static String EXTRA_IMG = "imgurl";
    public static String EXTRA_PRICE = "price";
    public static String EXTRA_NAME = "name";
    private ApiClient apiClient;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        brands = root.findViewById(R.id.brandsID);
        components = root.findViewById(R.id.componentsID);
        productRecycle = root.findViewById(R.id.productrecycleID);
        componentsName = new ArrayList<>();
        brandName = new ArrayList<>();
        products = new ArrayList<>();
        //Components
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
        componentsName.add(SSD);

        //Brands
        Log.d("Checking : ","1");
        Components gigabyte = new Components("Gigabyte");
        Components msi = new Components("MSI");
        Components zotac = new Components("Zotac");
        Components pny = new Components("PNY");
        Components asus = new Components("ASUS");
        Components sapphire = new Components("Sapphire");

        brandName.add(gigabyte);
        brandName.add(msi);
        brandName.add(zotac);
        brandName.add(pny);
        brandName.add(asus);
        brandName.add(sapphire);

        //Prducts
        Products pRam = new Products("Ram",R.drawable.ram,"Price-3500");
        Products pMotherboard = new Products("Motherboard",R.drawable.motherboard,"Price-10000");
        Products pPowersupply = new Products("Powersupply",R.drawable.powersupply,"Price-3500");
        Products pCasing = new Products("Casing",R.drawable.casing,"Price-4000");
        Products pGpu = new Products("GPU",R.drawable.gpu,"Price-16000");
        Products pGpu2 = new Products("GPU",R.drawable.gpu,"Price-16000");

        products.add(pRam);
        products.add(pMotherboard);
        products.add(pPowersupply);
        products.add(pCasing);
        products.add(pGpu);
        products.add(pGpu2);

        //get components list by Api
        productApis = new ArrayList<>();
        getProducts();
        //Toast.makeText(getActivity(),productApis.size(),Toast.LENGTH_LONG).show();

        //add Components in RecycleView
        componentsAdapter = new ComponentsAdapter(componentsName,getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        components.setLayoutManager(layoutManager);
        components.setAdapter(componentsAdapter);
        // add brands in recycler view
        brandsAdapter = new ComponentsAdapter(brandName,getActivity());
        RecyclerView.LayoutManager layoutManagerbrands = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        brands.setLayoutManager(layoutManagerbrands);
        brands.setAdapter(brandsAdapter);

        //Add products in Recycle View
        productAdapter = new ProductAdapter(productApis,getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        productRecycle.setLayoutManager(gridLayoutManager);
        productRecycle.setAdapter(productAdapter);
        productAdapter.setOnProductClickListener(this);

        return root;
    }

    @Override
    public void onProductClick(int position) {
        Intent detailsIntents = new Intent(getActivity(), ProductDetails.class);
        Products prod = products.get(position);
        //prod.getImg()
        //prod.getPrice()
        //prod.getTitle()
        //Toast.makeText(getActivity(),Integer.toString(prod.getImg()),Toast.LENGTH_LONG).show();
        detailsIntents.putExtra(EXTRA_IMG,prod.getImg());
        detailsIntents.putExtra(EXTRA_PRICE,prod.getPrice().toString());
        detailsIntents.putExtra(EXTRA_NAME,prod.getTitle());

        startActivity(detailsIntents);

    }

    public void getProducts(){
        Call<List<ProductApi>> call = ApiClient.getInstance().getApi().getHomeProducts();
        call.enqueue(new Callback<List<ProductApi>>() {
            //List<ProductApi> productApiList;
            @Override
            public void onResponse(Call<List<ProductApi>> call, Response<List<ProductApi>> response) {
                if(response.isSuccessful() && response.body() != null){
                    productApis = response.body();
                }
                else{
                    Toast.makeText(getActivity(),response.message(),Toast.LENGTH_LONG).show();
                    System.out.println(response.message());
                }

            }

            @Override
            public void onFailure(Call<List<ProductApi>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                System.out.println(t.getLocalizedMessage());

            }
        });
    }
}