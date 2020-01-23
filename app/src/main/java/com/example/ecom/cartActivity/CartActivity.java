package com.example.ecom.cartActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecom.R;
import com.example.ecom.RetrofitCart;
import com.example.ecom.RetrofitClass;
import com.example.ecom.cartActivity.adaptor.CartAdapter;
import com.example.ecom.cartActivity.apiInterface.CartInterface;
//import com.example.ecom.cartActivity.models.CartProduct;
import com.example.ecom.cartActivity.apiInterface.CheckoutInterface;
import com.example.ecom.cartActivity.models.CartProductRevised;
import com.example.ecom.cartActivity.models.ListCartProduct;
import com.example.ecom.cartActivity.models.checkout.CheckoutResponse;
import com.example.ecom.homeActivity.MainActivity;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartInterface {

    private RecyclerView recyclerView;
    private Retrofit retrofit;
    //private List<CartProduct> cartProductList = new ArrayList<>();
    private CartProductRevised cartProductRevised = new CartProductRevised();
    private RecyclerView.Adapter mAdapter;
    //private RetrofitClass retrofitClass = new RetrofitClass();
    private RetrofitCart retrofitCart = new RetrofitCart();
    private Button checkoutBtn ;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recycler_view_landing);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // TODO: 2020-01-22 retrofit cart for now
//        retrofit =  retrofitClass.getRetrofit();
//        CartInterface cartInterface = retrofit.create(CartInterface.class);
        retrofit = retrofitCart.getRetrofit();
        CartInterface cartInterface = retrofit.create(CartInterface.class);
        Call<CartProductRevised> call = cartInterface.getFromCart( "fakeId");

        call.enqueue(new Callback<CartProductRevised>() {
            @Override
            public void onResponse(Call<CartProductRevised> call, Response<CartProductRevised> response) {
                cartProductRevised = response.body();
                mAdapter = new CartAdapter(CartActivity.this,cartProductRevised,CartActivity.this);
                recyclerView.setAdapter(mAdapter);

                TextView totalAmount = findViewById(R.id.totalPriceValue);
                totalAmount.setText(String.valueOf(response.body().getTotalAmount()));
            }

            @Override
            public void onFailure(Call<CartProductRevised> call, Throwable t) {
                Log.d("Cart Api :","failure");
                Toast.makeText(getApplicationContext(),"cart api callback failure",Toast.LENGTH_LONG).show();
            }
        });
        checkoutBtn =findViewById(R.id.checkout);

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckoutInterface checkoutInterface = retrofit.create(CheckoutInterface.class);
                Call<CheckoutResponse> callCheckout = checkoutInterface.checkout(cartProductRevised);
                callCheckout.enqueue(new Callback<CheckoutResponse>() {
                    @Override
                    public void onResponse(Call<CheckoutResponse> call, Response<CheckoutResponse> response) {
                        String orderId = response.body().getOrderId();
                        Toast.makeText(CartActivity.this, orderId, Toast.LENGTH_SHORT).show();
                        //finish();
                        //startActivity(new Intent(context, CartActivity.class));
                        //Intent intent = getIntent();
                        Intent intent = new  Intent(CartActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        //startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<CheckoutResponse> call, Throwable t) {

                    }
                });
//                CartInterface cartInterface = retrofit.create(CartInterface.class);
//                Call<CartProductRevised> call = cartInterface.getFromCart( "fakeId");
//
//                call.enqueue(new Callback<CartProductRevised>() {
//                    @Override
//                    public void onResponse(Call<CartProductRevised> call, Response<CartProductRevised> response) {
//                        cartProductRevised = response.body();
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<CartProductRevised> call, Throwable t) {
//                        Log.d("Cart Api :","failure");
//                        Toast.makeText(getApplicationContext(),"cart api callback failure",Toast.LENGTH_LONG).show();
//                    }
//                });

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Cart Activity :","Started");
        ImageButton cart  = findViewById(R.id.imageButton2);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        ImageButton home = findViewById(R.id.imageButton);
        home .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

  }



    @Override
    public void onClick(TextView stockText) {

    }
}
