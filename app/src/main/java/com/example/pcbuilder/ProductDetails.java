package com.example.pcbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcbuilder.Fragments.Home;
import com.example.pcbuilder.models.CartModel;

import static com.example.pcbuilder.Fragments.Home.EXTRA_IMG;
import static com.example.pcbuilder.Fragments.Home.EXTRA_NAME;
import static com.example.pcbuilder.Fragments.Home.EXTRA_PRICE;

public class ProductDetails extends AppCompatActivity implements View.OnClickListener {

    private ImageView productimg;
    private TextView price,status,productcode,brand,description,prodName;
    private NumberPicker quantity;
    private Button addtolist;
    String cost,name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        Integer imgResource = intent.getIntExtra(EXTRA_IMG,0);
        cost = intent.getStringExtra(EXTRA_PRICE);
        name = intent.getStringExtra(EXTRA_NAME);

        productimg = findViewById(R.id.detailsimgID);
        price = findViewById(R.id.priceID);
        status = findViewById(R.id.statusID);
        productcode = findViewById(R.id.productcodeID);
        brand = findViewById(R.id.brandID);
        description = findViewById(R.id.descriptionID);
        quantity = findViewById(R.id.quantityID);
        quantity.setValue(1);
        quantity.setMaxValue(10);
        quantity.setMinValue(0);
        addtolist = findViewById(R.id.addlistID);
        prodName = findViewById(R.id.productnameID);

        productimg.setImageResource(imgResource);
        price.setText(cost);
        prodName.setText(name);

        addtolist.setOnClickListener(this);





    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.addlistID){
            int qnty = quantity.getValue();
            CartModel cartModel = new CartModel(name,Integer.toString(qnty),cost);
            //Save this to cart
            Toast.makeText(this,cartModel.getComponents()+" "+cartModel.getQuantity()+" "+cartModel.getPrice(),Toast.LENGTH_LONG).show();

        }
    }
}