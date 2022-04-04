package com.aryan.stumps11.api_integration;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckConnection {
//        public static String BASE_URL="http://13.235.92.159:4228";  // aws server
      ////NewBaseUrl
//        public static String BASE_URL="http://Stumps11.com:3000";  // aws server
      public static String BASE_URL="http://192.168.68.76:4228"; //  sachin local server

    ///public static String image="http://192.168.68.69:4229/documents/panCard/";
     public static String image="http://Stumps11.com:3000/documents/panCard/";
   ////Qasim
//    public static String BASE_URL="http://13.235.92.159:136"; // gourav local server

//    public static String BASE_URL="http://ecdb-122-160-47-222.ngrok.io";

    public static Retrofit retrofit=null;
    private OkHttpClient.Builder okHttpClient=new OkHttpClient.Builder();
    private HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
    public static  Retrofit getApi(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static API_INTERFACE api = getApi().create(API_INTERFACE.class);
}
