package com.example.unit.app.robolectric.net;

import android.util.Log;

import com.example.unit.app.BuildConfig;
import com.example.unit.app.base.RxJavaRule1;
import com.example.unit.app.net.Article;
import com.example.unit.app.net.ArticleResponse;
import com.example.unit.app.net.RetrofitService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.List;

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
    private static final String TAG = "ResponseTest";
    @Rule
    public RxJavaRule1 rxJavaRule = new RxJavaRule1();

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
        RetrofitService
                .createService()
                .getArticles(0)
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
                        Log.e("getUserTest", e.toString());
                    }

                    @Override
                    public void onComplete() {}
                });
    }
}
