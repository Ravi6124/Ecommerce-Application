package com.example.ecom;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass extends Application {


    Retrofit retrofit = null;

    public Retrofit getRetrofit() {
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://172.16.20.119:8091/") //base url
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient.Builder().build())
                    .build();
        }
        return retrofit;
    }
}
