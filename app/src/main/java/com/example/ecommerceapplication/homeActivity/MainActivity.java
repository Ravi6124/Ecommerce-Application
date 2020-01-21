package com.example.ecommerceapplication.homeActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ecommerceapplication.ProfileActivity.ProfileActivity;
import com.example.ecommerceapplication.loginActivity.LoginActivity;
import com.example.ecommerceapplication.productListActivity.ProductListActivity;
import com.example.ecommerceapplication.R;
import com.example.ecommerceapplication.RetrofitClass;
import com.example.ecommerceapplication.homeActivity.adaptor.LandingAdapter;
import com.example.ecommerceapplication.homeActivity.apiInterface.CategoryInterface;
import com.example.ecommerceapplication.homeActivity.models.Category;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

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

        Spinner dropdown = findViewById(R.id.drop_down);
        String items[] = new String[]{"Sign Out","Profile"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,items);
        dropdown.setAdapter(adapter);


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
            user.append(personName);
            //user.setText(personName);

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
                //Log.d("Response :",response.body().toString());
                if(null == response){
                    Log.d("response :","null");
                }
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

        Spinner spinner = findViewById(R.id.drop_down);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch (item){
                    case "Sign Out": signOut();
                    //case "Profile": userProfile();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });

    }

    private void userProfile(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void signOut() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        String user = "Guest";
                       TextView textView = findViewById(R.id.user_info);
                       textView.setText(user);
                    }
                });
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

    }
}
