package com.example.unit.app.net;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author lisen
 * @since 09-05-2018
 */

public interface Api {
    String BASE_URL = "http://www.wanandroid.com/";

    @GET("article/list/{page}/json")
    Observable<ArticleResponse> getArticles(@Path("page") int page);

}
