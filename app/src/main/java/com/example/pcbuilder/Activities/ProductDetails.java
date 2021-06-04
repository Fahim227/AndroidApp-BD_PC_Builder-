package com.example.pcbuilder.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pcbuilder.R;
import com.example.pcbuilder.api.ApiClient;
import com.example.pcbuilder.models.CartModel;
import com.example.pcbuilder.models.ComponentDetails;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.travijuu.numberpicker.library.NumberPicker;


//import static com.example.pcbuilder.Fragments.Home.EXTRA_IMG;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pcbuilder.Fragments.Home.EXTRA_URL;

public class ProductDetails extends AppCompatActivity implements View.OnClickListener {

    private ImageView productimg;
    private TextView price,status,productcode,brand,description,prodName;
    private NumberPicker quantity;
    private Button addtolist;
    String cost,name;
    private ApiClient apiClient;
    private String url,localUrl;
    private ShimmerFrameLayout shimmerFrameLayout;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private int qnty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        //Integer imgResource = intent.getIntExtra(EXTRA_IMG,0);
        url = intent.getStringExtra(EXTRA_URL);
        localUrl = intent.getStringExtra("localUrl");
        Toast.makeText(this,url,Toast.LENGTH_LONG).show();
        /*cost = intent.getStringExtra(EXTRA_PRICE);
        name = intent.getStringExtra(EXTRA_NAME);*/
        progressDialog = new ProgressDialog(ProductDetails.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        productimg = findViewById(R.id.detailsimgID);
        price = findViewById(R.id.priceID);
        status = findViewById(R.id.statusID);
        productcode = findViewById(R.id.productcodeID);
        brand = findViewById(R.id.brandID);
        description = findViewById(R.id.descriptionID);
        quantity = findViewById(R.id.quantityID);
        quantity.setValue(1);
        quantity.setMin(0);
        quantity.setMax(10);
        addtolist = findViewById(R.id.addlistID);
        prodName = findViewById(R.id.productnameID);
        progressBar = findViewById(R.id.shimmerId);
        //progressBar.setVisibility(View.VISIBLE);

        /*productimg.setImageResource(imgResource);
        price.setText(cost);
        prodName.setText(name);*/
        getDetails();

        addtolist.setOnClickListener(this);





    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.addlistID){
            qnty = quantity.getValue();
            CartModel cartModel = new CartModel(name,Integer.toString(qnty),cost);
            //Save this to cart_list
            Toast.makeText(this,url+" "+cartModel.getQuantity(),Toast.LENGTH_LONG).show();
            addtoCart();

        }
    }

    public void getDetails(){
        Call<ComponentDetails> call = ApiClient.getInstance().getApi().getCompopnentsDetails(localUrl,url);
        call.enqueue(new Callback<ComponentDetails>() {
            @Override
            public void onResponse(Call<ComponentDetails> call, Response<ComponentDetails> response) {
                //progressBar.setVisibility(View.GONE);
                progressDialog.dismiss();
                /*shimmerFrameLayout.hideShimmer();
                shimmerFrameLayout.stopShimmer();*/
                if(response.isSuccessful() && response.body() != null){
                    ComponentDetails componentDetails = response.body();
                    prodName.setText(componentDetails.getName());
                    price.setText(componentDetails.getPrice());
                    description.setText(componentDetails.getDescription());
                    Glide.with(getApplicationContext()).load(componentDetails.getImg()).into(productimg);


                }
                else {
                    Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ComponentDetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

    public void addtoCart(){
        Call<com.example.pcbuilder.models.Response> call = ApiClient.getInstance().getApi().addToCart(11,url,Shops.shopName,qnty);
        call.enqueue(new Callback<com.example.pcbuilder.models.Response>() {
            @Override
            public void onResponse(Call<com.example.pcbuilder.models.Response> call, Response<com.example.pcbuilder.models.Response> response) {
                if(response.isSuccessful() && response.body() != null){
                    com.example.pcbuilder.models.Response res = response.body();
                    if(res.getSuccess()){
                        Toast.makeText(getApplicationContext(),res.getMessage(),Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),res.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.pcbuilder.models.Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
}