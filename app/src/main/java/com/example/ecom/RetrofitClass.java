package com.example.ecom;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass extends Application {


    Retrofit retrofit = null;

    private  OkHttpClient client = new OkHttpClient.Builder().callTimeout(5, TimeUnit.SECONDS).build();
    public Retrofit getRetrofit() {
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://172.16.20.119:8091/") //base url
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
