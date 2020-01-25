package com.example.ecom.productInfoActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ecom.R;
import com.example.ecom.RetrofitClass;
import com.example.ecom.cartActivity.CartActivity;
import com.example.ecom.homeActivity.MainActivity;
import com.example.ecom.merchantActivity.MerchantActivity;
import com.example.ecom.productInfoActivity.apiInterface.ProductInfoInterface;
import com.example.ecom.productInfoActivity.models.apiExchanges.AddProductToCartRequest;
import com.example.ecom.productInfoActivity.models.CartProduct;
import com.example.ecom.productInfoActivity.models.apiExchanges.AddProductToCartResponse;
import com.example.ecom.searchableActivity.SearchableActivity;

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
    private  String userId;
    private  SharedPreferences shared;

    private AddProductToCartResponse addProductToCartResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        //Search View

        SearchView searchView = findViewById(R.id.search_bar);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        ComponentName componentName = new ComponentName(ProductInfoActivity.this, SearchableActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        TextView tvProductName = findViewById(R.id.productName);
        ImageView ivProductImage = findViewById(R.id.productImage);
        TextView tvProductDescription = findViewById(R.id.productDescription);
        TextView tvDefaultProductPrice = findViewById(R.id.price_default);
        TextView defaultMerchantName = findViewById(R.id.merchant_name);
        Button btnAddToCart = findViewById(R.id.addToCartButton);
        Button btnBuyNow = findViewById(R.id.buyNowButton);
        Button moreMerchants = findViewById(R.id.merchantList);

        //Receiving The values from the sent bundle
        Bundle extras = getIntent().getExtras();
        final String name;
        final String productId ;
        final String defaultMerchantId ;
        final String merchantName;
        final String description;
        final String imageURL;
        final float defaultPrice;
        final String categoryId;

        shared = getSharedPreferences("productInfo", MODE_PRIVATE);

//        if (extras != null) {
            //Storing the values from bundle in the local
            name = shared.getString("name","");
            productId = shared.getString("productId","");
            defaultMerchantId = shared.getString("defaultMerchantId","");
            merchantName = shared.getString("defaultMerchantName","");
            description = shared.getString("description","");
            imageURL  = shared.getString("imageURL","");
            defaultPrice = shared.getFloat("defaultPrice",0);
            categoryId = shared.getString("categoryId","");

            //Setting the page components from the values recieved from the bundle
            tvProductName.setText(name);
            Glide.with(this).load(imageURL).into(ivProductImage);
            tvProductDescription.setText(description);
            defaultMerchantName.setText(merchantName);

            tvDefaultProductPrice.setText(String.valueOf(defaultPrice));
//        }

//        else{
//            Toast.makeText(ProductInfoActivity.this,"OOPs Product Not found",Toast.LENGTH_LONG).show();
//        }


        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared = getSharedPreferences("productInfo", MODE_PRIVATE);
                String merchantId = shared.getString("defaultMerchantId","");
                float price = (shared.getFloat("defaultPrice",0));

                SharedPreferences sharedPref = getSharedPreferences("UserInfo",MODE_PRIVATE);
                userId = sharedPref.getString("userId","");
                Log.d("userId",userId);
                CartProduct cartProduct = new CartProduct(1,imageURL,productId,merchantId,price,name);
                AddProductToCartRequest addProductToCart = new AddProductToCartRequest(userId,cartProduct);

                // TODO: 2020-01-22 for now different retrofit class for differnet services 
//                RetrofitClass retrofitClass = new RetrofitClass();
//                Retrofit retrofit = retrofitClass.getRetrofit();
                RetrofitClass retrofitCart = new RetrofitClass();
                Retrofit retrofit = retrofitCart.getRetrofit();
                ProductInfoInterface productInfoInterface = retrofit.create(ProductInfoInterface.class);
                
               Call<AddProductToCartResponse> call = productInfoInterface.addItemToCart(addProductToCart);
               call.enqueue(new Callback<AddProductToCartResponse>() {
                   @Override
                   public void onResponse(@NonNull Call<AddProductToCartResponse> call, @NonNull Response<AddProductToCartResponse> response) {
                       Log.d("SignUpResponse","item added to cart");
                       addProductToCartResponse = new AddProductToCartResponse(response.body().getResultCode(),response.body().getCart());
                       Toast.makeText(ProductInfoActivity.this, "item added to cart", Toast.LENGTH_SHORT).show();
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
                intent.putExtra("productId",productId);
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

        SharedPreferences sharedPreferences = getSharedPreferences("merchant_product_info",MODE_PRIVATE);
        if(null != sharedPreferences) {
            String color = sharedPreferences.getString("color","");
            String theme = sharedPreferences.getString("theme","");
            String size = sharedPreferences.getString("size","");
            String rating = sharedPreferences.getString("rating","");
            String merchantName = sharedPreferences.getString("merchantName","");
            String merchantId = sharedPreferences.getString("merchantId","");
            Float cost  = sharedPreferences.getFloat("cost",0);

            TextView colorVal = findViewById(R.id.color);
            TextView themeVal = findViewById(R.id.theme);
            TextView sizeVal = findViewById(R.id.size);
            TextView ratingVal = findViewById(R.id.productRating);
            TextView merchantVal = findViewById(R.id.merchant_name_text);
            TextView costVal = findViewById(R.id.price_default);

            // update merchant Id in the product info
            SharedPreferences shared = getSharedPreferences("productInfo",MODE_PRIVATE);
            SharedPreferences.Editor editor = shared.edit();
            editor.putString("defaultMerchantId",merchantId);
            editor.putFloat("defaultPrice",cost);
            editor.commit();

            ratingVal.setText(rating);
            colorVal.setText(color);
            themeVal.setText(theme);
            sizeVal.setText(size);
            merchantVal.setText(merchantName);
            costVal.setText(String.valueOf(cost));
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}
