package com.shymaaothman.crossworkerstask.data.server;


import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shymaa Othman on 25/7/2018.
 */

public class APIClient {
    private static EndPoints endPoints;
    private static Retrofit retrofit;


    public static EndPoints getEndPoints(Map<String,String> headers){

           endPoints = getRetrofit(headers).create(EndPoints.class);

           return endPoints;
    }

    private static Retrofit getRetrofit(Map<String,String> headers){

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HeadersInterceptor headersInterceptor = new HeadersInterceptor(headers);
            httpClient.addInterceptor(headersInterceptor);

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);

            OkHttpClient client = httpClient.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();

        return retrofit;
    }

}
