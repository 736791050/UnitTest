package com.example.unit.app.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author lisen
 * @since 09-05-2018
 */

public class RetrofitService {

    private static Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl(Api.BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public static Api createService(){
        return retrofit.create(Api.class);
    }

    private static OkHttpClient getOkHttpClient(){
        return new OkHttpClient
                .Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
    }
}
