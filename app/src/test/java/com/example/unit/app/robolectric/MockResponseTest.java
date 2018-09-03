package com.example.unit.app.robolectric;

import com.example.unit.app.BuildConfig;
import com.example.unit.app.base.RxJavaRule;
import com.example.unit.app.model.User;
import com.example.unit.app.net.GithubApi;
import com.example.unit.app.net.LoggingInterceptor;
import com.example.unit.app.net.MockInterceptor;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

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

    private GithubApi githubApi;

    @Rule
    public RxJavaRule rxJavaRule = new RxJavaRule();

    @Before
    public void setUp(){
        ShadowLog.stream = System.out;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", "name");
            jsonObject.put("blog", "blog");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String json = jsonObject.toString();

        //定义 http client，并添加拦截器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new MockInterceptor(json))
                .build();

        //设置 http client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubApi.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        githubApi = retrofit.create(GithubApi.class);
    }

    @Test
    public void getUserTest(){
        githubApi
                .getUserModel("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        print("onNext " + user.name);
                    }

                    @Override
                    public void onError(Throwable e) {
                        print("onError " + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
