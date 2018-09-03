package com.example.unit.app.net;

import com.example.unit.app.model.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author lisen
 * @since 09-04-2018
 */

public interface GithubApi {

    String BASE_URL = "https://me.csdn.net/";
//    String BASE_URL = "https://api.github.com/";

    @GET("{username}")
    Observable<String> getUser(@Path("username") String username);

    @GET("users/{username}")
    Observable<User> getUserModel(@Path("username") String username);

}
