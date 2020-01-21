package com.example.ecommerceapplication.productInfoActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ecommerceapplication.R;
import com.example.ecommerceapplication.homeActivity.MainActivity;
import com.example.ecommerceapplication.productInfoActivity.models.CartProduct;

public class ProductInfoActivity extends AppCompatActivity {

    //defining all the views in the Layout
    private TextView tvProductName;
    private ImageView ivProductImage;
    private TextView tvProductDescription;
    private TextView tvDefaultProductPrice;
    private Spinner spinnerMerchantList;
    private Button btnAddTocart;
    private Button btnBuyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        tvProductName = findViewById(R.id.productName);
        ivProductImage = findViewById(R.id.productImage);
        tvProductDescription = findViewById(R.id.productDescription);
        tvDefaultProductPrice = findViewById(R.id.price);
        btnAddTocart = findViewById(R.id.addToCartButton);
        btnBuyNow = findViewById(R.id.buyNowButton);



        //Receiving The values from the sent bundle
        Bundle extras = getIntent().getExtras();
        final String name ;
        final String productId ;
        final String defaultMerchantId ;
        String description ;
        String imageURL ;
        final double defaultPrice;
        String categoryId;

        if (extras != null) {

            //Storing the values from bundle in the local
            name = extras.getString("name");
            productId = extras.getString("productId");
            defaultMerchantId = extras.getString("defaultMerchantId");
            description = extras.getString("description");
            imageURL  = extras.getString("imageURL");
            defaultPrice = extras.getDouble("defaultPrice");



            //Setting the page components from the values recieved from the bundle
            tvProductName.setText(name);
            Glide.with(this).load(imageURL).into(ivProductImage);
            tvProductDescription.setText(description);
            tvDefaultProductPrice.setText(String.valueOf(defaultPrice));

        }

        else{
            Toast.makeText(ProductInfoActivity.this,"OOPs Product Not found",Toast.LENGTH_LONG).show();
        }

        //todo Call the Api to get Product listing and show list of merchant by populating merchant spinner component andd ratings


        //todo: Call the Add to cart API here onClick of the button

        btnAddTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //todo: make am Object of cartProduct and send it through AddToCartApi

                //CartProduct cartProduct = new CartProduct(1,productId,defaultMerchantId,defaultPrice);



            }
        });


        //todo : Call the BuyNow API here on click of the Button

        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        ImageButton home = findViewById(R.id.imageButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductInfoActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
