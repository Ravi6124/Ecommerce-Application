package com.example.ecom.cartActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecom.R;
import com.example.ecom.RetrofitClass;
import com.example.ecom.cartActivity.adaptor.CartAdapter;
import com.example.ecom.cartActivity.apiInterface.CartInterface;
//import com.example.ecom.cartActivity.models.CartProduct;
import com.example.ecom.cartActivity.apiInterface.CheckoutInterface;
import com.example.ecom.cartActivity.models.CartProductRevised;
import com.example.ecom.cartActivity.models.checkout.CheckoutResponse;
import com.example.ecom.checkoutActivity.CheckoutActivity;
import com.example.ecom.homeActivity.MainActivity;
import com.example.ecom.loginActivity.LoginActivity;

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
    private CartProductRevised cartProductRevised = new CartProductRevised();
    private RecyclerView.Adapter mAdapter;
    private RetrofitClass retrofitClass = new RetrofitClass();
    private Button checkoutBtn ;
    private Button buyNowBtn;
    private Context context;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recycler_view_landing);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        retrofit = retrofitClass.getRetrofit();

        SharedPreferences  sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        userId = sharedPreferences.getString("userId","");

        CartInterface cartInterface = retrofit.create(CartInterface.class);
        Call<CartProductRevised> call = cartInterface.getFromCart( userId);

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

                final ProgressDialog progressDialog;
            progressDialog = new ProgressDialog(CartActivity.this);
            progressDialog.setMax(100);
            progressDialog.setMessage("Its loading....");
            progressDialog.setTitle("ProgressDialog bar example");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();

                SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
                String userId = sharedPreferences.getString("email","");
                if(!userId.equals("")){

                    CheckoutInterface checkoutInterface = retrofit.create(CheckoutInterface.class);
                    Call<CheckoutResponse> callCheckout = checkoutInterface.checkout(cartProductRevised);
                    callCheckout.enqueue(new Callback<CheckoutResponse>() {
                        @Override
                        public void onResponse(Call<CheckoutResponse> call, Response<CheckoutResponse> response) {
                            String orderId = response.body().getOrderId();
                            Toast.makeText(CartActivity.this, orderId, Toast.LENGTH_SHORT).show();
                            Intent intent = new  Intent(CartActivity.this, CheckoutActivity.class);
                            intent.putExtra("orderId",orderId);
                            progressDialog.dismiss();
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<CheckoutResponse> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(CartActivity.this, "order response failure: cannot checkout", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(CartActivity.this, "cannot checkout", Toast.LENGTH_LONG).show();
//                    new AlertDialog.Builder(context)
//                            .setTitle("Checkout")
//                            .setMessage("You need to be logged in first to make checkout !")
//                            .setNegativeButton(android.R.string.no, null)
//                            .setIcon(android.R.drawable.ic_dialog_alert)
//                            .show();

                    //Toast.makeText(CartActivity.this, "you need to be logged in first!!", Toast.LENGTH_SHORT).show();
                }


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
                finish();
            }
        });

        ImageButton home = findViewById(R.id.imageButton);
        home .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this,MainActivity.class);
                startActivity(intent);
                //finish();
            }
        });

  }



    @Override
    public void onClick(TextView stockText) {

    }
}
