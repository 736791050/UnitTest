package com.example.unit.app.robolectric;

import android.util.Log;

import com.example.unit.app.BuildConfig;
import com.example.unit.app.base.RxJavaRule;
import com.example.unit.app.model.User;
import com.example.unit.app.net.GithubApi;
import com.example.unit.app.net.LoggingInterceptor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
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
public class MockWebServerTest {

    private GithubApi mockGithubService;
    private MockWebServer mockWebServer;

    @Rule
    public RxJavaRule rxJavaRule = new RxJavaRule();

    @Before
    public void setUp(){
        ShadowLog.stream = System.out;

        //创建一个 MockWebServer
        mockWebServer = new MockWebServer();

        String body = "{\"name\":\"lucy\"}";
        //设置响应，默认返回 http code 是 200
        final MockResponse mockResponse = new MockResponse()
                .addHeader("Content-type", "application/json;charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .setBody(body);

        final MockResponse mockResponse1 = new MockResponse()
                .addHeader("Content-type", "application/json;charset=utf-8")
                .setResponseCode(404)
                .throttleBody(5, 1, TimeUnit.SECONDS)//一秒传递五个字节，模拟网速慢的情况
                .setBody("{\"error\":\"网络异常\"}");

//        mockWebServer.enqueue(mockResponse);
//        mockWebServer.enqueue(mockResponse1);

        mockWebServer.setDispatcher(new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                print(request.getPath());
                if(request.getPath().equals("/users/test")){
                    return mockResponse;
                }
                return mockResponse1;
            }
        });

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + mockWebServer.getHostName() + ":" + mockWebServer.getPort() + "/")//设置对应的 host 与端口号
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mockGithubService = retrofit.create(GithubApi.class);
    }

    @Test
    public void getUserTest() throws Exception{

        mockGithubService.getUserModel("test")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User s) {
                        Log.i("getUserTest", "onNext: " + s.name);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getUserTest", "onError: " + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        rxJavaRule.getTestScheduler().advanceTimeTo(100, TimeUnit.SECONDS);

        //验证我们的请求客户端是否按预期生成了请求
        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        print(recordedRequest.getRequestLine());
        print(recordedRequest.getHeader("User-Agent"));


        //关闭服务
        mockWebServer.shutdown();

    }

}
