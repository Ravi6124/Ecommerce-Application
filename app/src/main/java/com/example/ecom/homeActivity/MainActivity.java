package com.example.ecom.homeActivity;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.ImageButton;
//import android.widget.Spinner;
import android.widget.TextView;

//import com.example.ecom.ProfileActivity.ProfileActivity;
import com.example.ecom.cartActivity.CartActivity;
import com.example.ecom.loginActivity.LoginActivity;
import com.example.ecom.productListActivity.ProductListActivity;
import com.example.ecom.R;
import com.example.ecom.RetrofitClass;
import com.example.ecom.homeActivity.adaptor.LandingAdapter;
import com.example.ecom.homeActivity.apiInterface.CategoryInterface;
import com.example.ecom.homeActivity.models.Category;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements LandingAdapter.CategoryInterface{

    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private List<Category> categoryList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RetrofitClass retrofitClass = new RetrofitClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Spinner dropdown = findViewById(R.id.drop_down);
//        String items[] = new String[]{"Sign Out","Profile"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,items);
//        dropdown.setAdapter(adapter);


        //getting signed user account information
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
//            String personEmail = acct.getEmail();
//            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();

            TextView user = findViewById(R.id.user_info);
            user.setText(personName);

        }
        else {
            SharedPreferences shared = getSharedPreferences(getString(R.string.preference_file_key_signup), MODE_PRIVATE);
            String email = (shared.getString("emailAddress", ""));
            String userId = (shared.getString("userId",""));
            if(!email.equals("")){
                TextView user  = findViewById(R.id.user_info);
                user.setText(email);
            }
            else{
            TextView user  = findViewById(R.id.user_info);
            user.setText(R.string.guest);
            }
        }

        recyclerView = findViewById(R.id.recycler_view_landing);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        retrofit =  retrofitClass.getRetrofit();
        CategoryInterface categoryInterface = retrofit.create(CategoryInterface.class);
        Call<List<Category>> call = categoryInterface.getAllCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                //Log.d("SignUpResponse :",response.body().toString());
//                if(null == response){
//                    Log.d("response :","null");
//                }
                categoryList = response.body();
                mAdapter = new LandingAdapter(MainActivity.this,categoryList,MainActivity.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("getAllCategories"," : callback failure");
            }
        });
    }

    @Override
    public void onClick(Category category) {
        Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
        intent.putExtra("cId",category.getCategoryId());

        //This should start the ProductList activity
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();

        //home button on click
        ImageButton home = findViewById(R.id.imageButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //login button on click
        ImageButton login = findViewById(R.id.imageButton3);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //cart button on click
        ImageButton cart  = findViewById(R.id.imageButton2);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });


        SharedPreferences sharedPreferences =getSharedPreferences(getString(R.string.preference_file_key_login), Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("name","");
       // TextView textView = findViewById(R.id.login);
        //textView.setText(user);

    }


    @Override
    protected void onPause() {
        super.onPause();

        //home button on click
        ImageButton home = findViewById(R.id.imageButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //login button on click
        ImageButton login = findViewById(R.id.imageButton3);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //cart button on click
        ImageButton cart  = findViewById(R.id.imageButton2);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
//            String personEmail = acct.getEmail();
//            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();

            TextView user = findViewById(R.id.user_info);
            user.setText(personName);

        }
        else {
            TextView user  = findViewById(R.id.user_info);
            user.setText("Guest");
        }
    }
}
