package com.example.unit.app.robolectric.net.interceptmock;

import android.app.Application;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.unit.app.BuildConfig;
import com.example.unit.app.base.RxJavaRule1;
import com.example.unit.app.net.Api;
import com.example.unit.app.net.Article;
import com.example.unit.app.net.ArticleResponse;
import com.example.unit.app.net.LoggingInterceptor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.unit.app.base.PrintUtil.print;

/**
 * @author lisen
 * @since 09-04-2018
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class MockResponseTest {

    private static final String TAG = "MockResponseTest";

    private Api api;

    @Rule
    public RxJavaRule1 rxJavaRule = new RxJavaRule1();

    @Before
    public void setUp(){
        ShadowLog.stream = System.out;
        String s = "";
        s = readMockData();


        //定义 http client，并添加拦截器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new MockInterceptor(s))
                .build();

        //设置 http client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public static String readMockData() {
        Application application = RuntimeEnvironment.application;
        try {
            AssetManager assetManager = application.getAssets();
            String[] locals = assetManager.getLocales();
            for (int i = 0; i < locals.length; i++) {
                Log.i(TAG, "setUp: " + locals[i]);
            }
            InputStream data = assetManager.open("data");
            InputStreamReader reader = new InputStreamReader(data);
            StringBuilder stringBuilder = new StringBuilder();
            char[] buffer = new char[1024];
            while (reader.read(buffer) > 0){
                stringBuilder.append(buffer);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Test
    public void getUserTest(){
        api.getArticles(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticleResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(ArticleResponse articleResponse) {
                        int code = articleResponse.errorCode;
                        String msg = articleResponse.errorMsg;
                        Log.i(TAG, "code: " + code + " msg: " + msg + " " + articleResponse);
                        ArticleResponse.Data data = articleResponse.data;
                        if(data != null){
                            List<Article> datas = data.datas;
                            if(datas != null && datas.size() > 0){
                                int curPage = data.curPage;
                                int pageCount = data.pageCount;
                                int total = data.total;
                                Log.i(TAG, "curPage:" + curPage + " pageCount:" + pageCount + " total:" + total);
                                Article article = datas.get(0);
                                if(article != null){
                                    String author = article.author;
                                    String title = article.title;
                                    Log.i(TAG, "author: " + author + "  title: " + title);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        print("onError " + e.toString());
                    }

                    @Override
                    public void onComplete() {}
                });
    }

}
