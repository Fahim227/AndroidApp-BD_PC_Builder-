package com.example.pcbuilder.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pcbuilder.Activities.ComponentsActivity;
import com.example.pcbuilder.Adapters.BrandsAdapter;
import com.example.pcbuilder.Adapters.ProductAdapter;
import com.example.pcbuilder.Activities.MainActivity;
import com.example.pcbuilder.Activities.ProductDetails;
import com.example.pcbuilder.R;
import com.example.pcbuilder.api.ApiClient;
import com.example.pcbuilder.models.BrandsList;
import com.example.pcbuilder.models.Components;
import com.example.pcbuilder.models.ProductApi;
import com.example.pcbuilder.models.Products;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment{
    private EditText search;
    private RecyclerView components;
    private RecyclerView brands;
    private List<Components>  brandName;
    //private List<Brands> brandName;
    private BrandsAdapter componentsAdapter,brandsAdapter;
    private List<Products> products;
    private List<ProductApi> productApis;
    private RecyclerView productRecycle;
    private ProductAdapter productAdapter;
    public static String EXTRA_URL = "prodUrl";
    public static String EXTRA_PRICE = "price";
    public static String EXTRA_NAME = "name";
    private Context cxt = getActivity();
    private ApiClient apiClient;
    private LinearLayout component;
    private MainActivity mainActivity;
    Intent intent;
    private BrandsList brandsList;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private String brandUrl;
    private String productUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        brands = root.findViewById(R.id.brandsID);
        //components = root.findViewById(R.id.componentsID);

        brandUrl = ComponentsActivity.burl+"getbrands/";
        productUrl = ComponentsActivity.burl+"getproducts/";
        productRecycle = root.findViewById(R.id.productrecycleID);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
        brandName = new ArrayList<>();
        products = new ArrayList<>();

        //get components list by Api
        productApis = new ArrayList<>();
        getBrands(MainActivity.componentUrl);
        getProducts(MainActivity.componentUrl);

        //System.out.println("here"+" "+productApis.size());

        // add brands in recycler view
       // brandsAdapter = new BrandsAdapter(brandName,getActivity());
        RecyclerView.LayoutManager layoutManagerbrands = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        brands.setLayoutManager(layoutManagerbrands);
        brands.setAdapter(brandsAdapter);

        //Add products in Recycle View
        productAdapter = new ProductAdapter(productApis,getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        productRecycle.setLayoutManager(gridLayoutManager);
        productRecycle.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
       // productAdapter.setOnProductClickListener(this);
        return root;
    }

    public void getProducts(String comUrl){
        Call<List<ProductApi>> call = ApiClient.getInstance().getApi().getHomeProducts(productUrl,comUrl);
        call.enqueue(new Callback<List<ProductApi>>() {
            //List<ProductApi> productApiList;
            @Override
            public void onResponse(Call<List<ProductApi>> call, Response<List<ProductApi>> response) {

                progressDialog.dismiss();
                if(response.isSuccessful() && response.body() != null){
                    productApis = response.body();
                    productAdapter = new ProductAdapter(productApis,getActivity());
                    productRecycle.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();
                    productAdapter.setOnProductClickListener(new ProductAdapter.OnProductClickListener() {
                        @Override
                        public void onProductClick(int position) {
                            Toast.makeText(getActivity(),productApis.get(position).getUrls(),Toast.LENGTH_LONG).show();
                            Intent detailsIntents = new Intent(getActivity(), ProductDetails.class);
                            String prodUrl = productApis.get(position).getUrls();
                            //Toast.makeText(getActivity(),Integer.toString(prod.getImg()),Toast.LENGTH_LONG).show();
                            detailsIntents.putExtra(EXTRA_URL,prodUrl);
                            detailsIntents.putExtra("localUrl",ComponentsActivity.burl+"componentdetails/");
                            startActivity(detailsIntents);
                        }
                    });
                    //Toast.makeText(getActivity(),String.valueOf(productApis.size()),Toast.LENGTH_LONG).show();
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
    public void getBrands(String name){
        Call<BrandsList> call = ApiClient.getInstance().getApi().getBrandNames(brandUrl,MainActivity.componentUrl);
        call.enqueue(new Callback<BrandsList>() {
            @Override
            public void onResponse(Call<BrandsList> call, Response<BrandsList> response) {
                if(response.isSuccessful() && response.body() != null){

                    brandsList = response.body();
                    brandsAdapter = new BrandsAdapter(brandsList.getBrands(),getActivity(),Home.this);
                    brands.setAdapter(brandsAdapter);
                    brandsAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(),String.valueOf(brandsList.getBrands().size()), Toast.LENGTH_LONG).show();
                   /* for (int i=0;i<brandsList.getBrands().size();i++) {
                        Toast.makeText(getActivity(),brandsList.getBrands().get(i).getBrandsName() , Toast.LENGTH_LONG).show();

                    }*/

                }
                else{
                    Toast.makeText(getActivity(),response.message(),Toast.LENGTH_LONG).show();
                    System.out.println(response.message());
                }
            }

            @Override
            public void onFailure(Call<BrandsList> call, Throwable t) {

            }
        });
    }

    //get products by brands
    public void getProductsByBrands(String brandurl){
        String localUrl = ComponentsActivity.burl+"brandscomponents/";
        Call<List<ProductApi>> call = ApiClient.getInstance().getApi().getComponentsByBrands(localUrl,brandurl);
        call.enqueue(new Callback<List<ProductApi>>() {
            //List<ProductApi> productApiList;
            @Override
            public void onResponse(Call<List<ProductApi>> call, Response<List<ProductApi>> response) {

                progressDialog.dismiss();
                if(response.isSuccessful() && response.body() != null){
                    productApis = response.body();
                    productAdapter = new ProductAdapter(productApis,getActivity());
                    productRecycle.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();
                    productAdapter.setOnProductClickListener(new ProductAdapter.OnProductClickListener() {
                        @Override
                        public void onProductClick(int position) {
                            //Toast.makeText(getActivity(),productApis.get(position).getUrls(),Toast.LENGTH_LONG).show();
                            Intent detailsIntents = new Intent(getActivity(), ProductDetails.class);
                            String prodUrl = productApis.get(position).getUrls();
                            //Toast.makeText(getActivity(),Integer.toString(prod.getImg()),Toast.LENGTH_LONG).show();
                            detailsIntents.putExtra(EXTRA_URL,prodUrl);
                            startActivity(detailsIntents);
                        }
                    });
                    //Toast.makeText(getActivity(),String.valueOf(productApis.size()),Toast.LENGTH_LONG).show();
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
    public void startProgress(){
        progressDialog.show();
    }
    public void stopProgress(){
        progressDialog.dismiss();
    }
}