package com.example.unit.app.robolectric;

import android.util.Log;

import com.example.unit.app.BuildConfig;
import com.example.unit.app.base.RxJavaRule;
import com.example.unit.app.net.GithubService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * @author lisen
 * @since 09-04-2018
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class ResponseTest {

    @Rule
    public RxJavaRule rxJavaRule = new RxJavaRule();

    @Before
    public void setUp(){
        ShadowLog.stream = System.out;
//        initRxJava2();
    }

    /**
     * 将异步转换成同步
     */
    private void initRxJava2() {
        RxJavaPlugins.reset();
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
        RxAndroidPlugins.reset();
        RxAndroidPlugins.setMainThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @Test
    public void getUserTest(){
        GithubService
                .createGithubService()
                .getUser("qq_17766199")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String user) {
//                        assertEquals("Chris Wanstrath", user.name);
//                        assertEquals("http://chriswanstrath.com/", user.blog);
                        Log.i("getUserTest", "onNext: " + user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("getUserTest", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
