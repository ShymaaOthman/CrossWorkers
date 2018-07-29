package com.shymaaothman.crossworkerstask.data.server;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Shymaa Othman on 2/12/2018.
 */

public class HeadersInterceptor implements Interceptor {

    Map<String,String> map ;

    public HeadersInterceptor(Map<String,String> map){

        super();
        this.map = map ;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();

        //dynamic header interceptor
        Request.Builder requestBuilder = null;
        Request request = null ;

        if(map !=null) {

            for (Map.Entry<String, String> entry : map.entrySet()) {

                requestBuilder = original.newBuilder().addHeader(entry.getKey(), entry.getValue());

                System.out.println(entry.getKey() + "/" + entry.getValue());

            }

            request = requestBuilder.build();
            return chain.proceed(request);


        }else{

            return chain.proceed(original);
        }

    }
}
