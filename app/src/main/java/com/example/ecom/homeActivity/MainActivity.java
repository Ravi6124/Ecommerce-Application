package com.example.ecom.homeActivity;

//import androidx.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.ComponentName;
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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.ecom.ProfileActivity.ProfileActivity;
import com.example.ecom.cartActivity.CartActivity;
import com.example.ecom.loginActivity.LoginActivity;
import com.example.ecom.loginActivity.apiInterface.LoginInterface;
import com.example.ecom.loginActivity.modules.LoginResponse;
import com.example.ecom.productListActivity.ProductListActivity;
import com.example.ecom.R;
import com.example.ecom.RetrofitClass;
import com.example.ecom.homeActivity.adaptor.LandingAdapter;
import com.example.ecom.homeActivity.apiInterface.CategoryInterface;
import com.example.ecom.homeActivity.models.Category;
import com.example.ecom.searchableActivity.SearchableActivity;
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
    SharedPreferences sharedPreferences;
    TextView userInfo;
    String guestId;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RetrofitClass retrofitLogin = new RetrofitClass();
        retrofit = retrofitLogin.getRetrofit();
        LoginInterface loginInterface = retrofit.create(LoginInterface.class);
        Call<LoginResponse> call = loginInterface.guestLogin("android");
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {

                guestId = (null!=response.body().getGuestId())?response.body().getGuestId():"Guest";
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("GuestApi:","callback failure");
            }
        });

        sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        userId = (sharedPreferences.getString("userId",guestId));
        String email = sharedPreferences.getString("email","Guest");
        userInfo = findViewById(R.id.user_info);
        userInfo.setText(email);



        //getting signed user account information
//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//
//        if (acct != null) {
//            String personName = acct.getDisplayName();
////            String personGivenName = acct.getGivenName();
////            String personFamilyName = acct.getFamilyName();
////            String personEmail = acct.getEmail();
////            String personId = acct.getId();
////            Uri personPhoto = acct.getPhotoUrl();
//            TextView user = findViewById(R.id.user_info);
//            user.setText(personName);
//
//        }
//        else {
//            if(!userId.equals("")){
//                TextView user  = findViewById(R.id.user_info);
//                user.setText(email);
//            }
//            else{
//            TextView user  = findViewById(R.id.user_info);
//            user.setText(R.string.guest);
//            }
//        }

        recyclerView = findViewById(R.id.recycler_view_landing);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        retrofit =  retrofitClass.getRetrofit();
        CategoryInterface categoryInterface = retrofit.create(CategoryInterface.class);
        Call<List<Category>> callGuest = categoryInterface.getAllCategories();
        callGuest.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categoryList = response.body();
                mAdapter = new LandingAdapter(MainActivity.this,categoryList,MainActivity.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("getAllCategories"," : callback failure");
            }
        });

        //Search View

        SearchView searchView = findViewById(R.id.search_bar);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        ComponentName componentName = new ComponentName(MainActivity.this, SearchableActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
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

//        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
//        userId = sharedPreferences.getString("userId","");
//        String email = sharedPreferences.getString("email","");
//        if(!email.equals("")){
//            TextView user  = findViewById(R.id.user_info);
//            user.setText(email);
//        }
//        else{
//            TextView user  = findViewById(R.id.user_info);
//            user.setText(R.string.guest);
//        }


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
                startActivityForResult(intent,1);
                finish();
                //startActivity(intent);
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


//        SharedPreferences sharedPreferences =getSharedPreferences(getString(R.string.preference_file_key_login), Context.MODE_PRIVATE);
//        String user = sharedPreferences.getString("name","");
       // TextView textView = findViewById(R.id.login);
        //textView.setText(user);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
                userId = (sharedPreferences.getString("userId",""));
                String email = sharedPreferences.getString("email","Guest");
                userInfo =  findViewById(R.id.user_info);
                userInfo.setText(email);
            }
            if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, "nothing done on login page", Toast.LENGTH_SHORT).show();
            }
        }
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
//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//        if (acct != null) {
//            String personName = acct.getDisplayName();
////            String personGivenName = acct.getGivenName();
////            String personFamilyName = acct.getFamilyName();
////            String personEmail = acct.getEmail();
////            String personId = acct.getId();
////            Uri personPhoto = acct.getPhotoUrl();
//
//            TextView user = findViewById(R.id.user_info);
//            user.setText(personName);
//
//        }
//        else {
//            TextView user  = findViewById(R.id.user_info);
//            user.setText("Guest");
//        }
    }
}
