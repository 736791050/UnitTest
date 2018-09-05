package com.example.unit.app.robolectric.net.interceptmock;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author lisen
 * @since 09-04-2018
 */

public class MockInterceptor implements Interceptor {

    private final String resonseString;

    public MockInterceptor(String resonseString){
        this.resonseString = resonseString;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = new Response.Builder()
                .code(200)
                .message(resonseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), resonseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();
        return response;
    }
}
