package com.example.ecommerceapplication.cartActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ecommerceapplication.R;
import com.example.ecommerceapplication.RetrofitClass;
import com.example.ecommerceapplication.cartActivity.adaptor.CartAdapter;
import com.example.ecommerceapplication.cartActivity.apiInterface.CartInterface;
import com.example.ecommerceapplication.cartActivity.models.CartProduct;

import java.util.ArrayList;
import java.util.List;
//import java.util.Set;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartInterface {

    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private List<CartProduct> cartProductList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RetrofitClass retrofitClass = new RetrofitClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recycler_view_landing);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        retrofit =  retrofitClass.getRetrofit();
        CartInterface cartInterface = retrofit.create(CartInterface.class);
        Call<List<CartProduct>> call = cartInterface.getFromCart( "fakeId");

        call.enqueue(new Callback<List<CartProduct>>() {
            @Override
            public void onResponse(Call<List<CartProduct>> call, Response<List<CartProduct>> response) {
                cartProductList = response.body();
                mAdapter = new CartAdapter(CartActivity.this,cartProductList,CartActivity.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<CartProduct>> call, Throwable t) {
                Log.d("Cart Api :","failure");
                Toast.makeText(getApplicationContext(),"cart api callback failure",Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onClick(CartProduct cartProduct) {
        // TODO: 2020-01-20 go to product description page
    }
}
