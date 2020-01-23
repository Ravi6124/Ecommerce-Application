package com.example.ecom.productInfoActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ecom.R;
import com.example.ecom.RetrofitCart;
import com.example.ecom.cartActivity.CartActivity;
import com.example.ecom.homeActivity.MainActivity;
import com.example.ecom.merchantActivity.MerchantActivity;
import com.example.ecom.productInfoActivity.apiInterface.ProductInfoInterface;
import com.example.ecom.productInfoActivity.models.apiExchanges.AddProductToCartRequest;
import com.example.ecom.productInfoActivity.models.CartProduct;
import com.example.ecom.productInfoActivity.models.apiExchanges.AddProductToCartResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductInfoActivity extends AppCompatActivity {

    //defining all the views in the Layout
//    private TextView tvProductName;
//    private ImageView ivProductImage;
//    private TextView tvProductDescription;
//    private TextView tvDefaultProductPrice;
//    private Button btnAddTocart;
//    private Button btnBuyNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        TextView tvProductName = findViewById(R.id.productName);
        ImageView ivProductImage = findViewById(R.id.productImage);
        TextView tvProductDescription = findViewById(R.id.productDescription);
        TextView tvDefaultProductPrice = findViewById(R.id.price);
        Button btnAddToCart = findViewById(R.id.addToCartButton);
        Button btnBuyNow = findViewById(R.id.buyNowButton);
        Button moreMerchants = findViewById(R.id.merchantList);

        //Receiving The values from the sent bundle
        Bundle extras = getIntent().getExtras();
        final String name;
        final String productId ;
        final String defaultMerchantId ;
        final String description;
        final String imageURL;
        final double defaultPrice;
        final String categoryId;

//        if (extras != null) {
            //Storing the values from bundle in the local
            name = extras.getString("name","");
            productId = extras.getString("productId","");
            defaultMerchantId = extras.getString("defaultMerchantId","");
            description = extras.getString("description","");
            imageURL  = extras.getString("imageURL","");
            defaultPrice = extras.getDouble("defaultPrice",0);
            categoryId = extras.getString("categoryId","");

            //Setting the page components from the values recieved from the bundle
            tvProductName.setText(name);
            Glide.with(this).load(imageURL).into(ivProductImage);
            tvProductDescription.setText(description);
            tvDefaultProductPrice.setText(String.valueOf(defaultPrice));
//        }

//        else{
//            Toast.makeText(ProductInfoActivity.this,"OOPs Product Not found",Toast.LENGTH_LONG).show();
//        }


        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartProduct cartProduct = new CartProduct(1,imageURL,productId,defaultMerchantId,defaultPrice,name);
                AddProductToCartRequest addProductToCart = new AddProductToCartRequest("fakeId",cartProduct);

                // TODO: 2020-01-22 for now different retrofit class for differnet services 
//                RetrofitClass retrofitClass = new RetrofitClass();
//                Retrofit retrofit = retrofitClass.getRetrofit();
                RetrofitCart retrofitCart = new RetrofitCart();
                Retrofit retrofit = retrofitCart.getRetrofit();
                ProductInfoInterface productInfoInterface = retrofit.create(ProductInfoInterface.class);
                
               Call<AddProductToCartResponse> call = productInfoInterface.addItemToCart(addProductToCart);
               call.enqueue(new Callback<AddProductToCartResponse>() {
                   @Override
                   public void onResponse(@NonNull Call<AddProductToCartResponse> call, @NonNull Response<AddProductToCartResponse> response) {
                       Log.d("SignUpResponse","item added to cart");
                   }

                   @Override
                   public void onFailure(@NonNull Call<AddProductToCartResponse> call, Throwable t) {
                       Log.d("SignUpResponse","item could not be added");
                   }
               });

            }
        });


        //todo : Call the BuyNow API here on click of the Button

        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        moreMerchants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductInfoActivity.this, MerchantActivity.class);
                startActivity(intent);
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

        ImageButton cart  = findViewById(R.id.imageButton2);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductInfoActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();

        if(null != extras) {
            String color = extras.getString("color","");
            String theme = extras.getString("theme","");
            String size = extras.getString("size","");
            String rating = extras.getString("rating","");
            String merchantName = extras.getString("merchantName","");

            TextView colorVal = findViewById(R.id.color);
            TextView themeVal = findViewById(R.id.theme);
            TextView sizeVal = findViewById(R.id.size);
            TextView ratingVal = findViewById(R.id.productRating);
            TextView merchantVal = findViewById(R.id.merchant_name_text);

            ratingVal.setText(rating);
            colorVal.setText(color);
            themeVal.setText(theme);
            sizeVal.setText(size);
            merchantVal.setText(merchantName);
        }
    }

}
