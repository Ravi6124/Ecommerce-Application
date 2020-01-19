package com.example.ecommerceapplication;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass extends Application {

    OkHttpClient client = new OkHttpClient.Builder().build();
    Retrofit retrofit = null;

    public Retrofit getRetrofit() {
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.androidhive.info") //base url
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
