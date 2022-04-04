package com.aryan.stumps11.api_integration;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MatchConnection {
    private static String comingSoonApi="https://rest.entitysport.com/";
    public static Retrofit retrofit=null;
    private OkHttpClient.Builder okHttpClient=new OkHttpClient.Builder();
    private HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
    public static  Retrofit getMatch(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(comingSoonApi)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
//    public static API_INTERFACE api = getApi().create(API_INTERFACE.class);
    public static API_INTERFACE match=getMatch().create(API_INTERFACE.class);
}

