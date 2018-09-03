package com.example.unit.app.net;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author lisen
 * @since 09-04-2018
 */

public class LoggingInterceptor implements Interceptor {

    private static final String TAG = "LoggingInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        if(response.isSuccessful()){
            Log.i(TAG, "intercept: success");
        }else {
            Log.i(TAG, "intercept: error");
        }

        return response;
    }
}
