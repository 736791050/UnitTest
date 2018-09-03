package com.example.unit.app.robolectric;

import com.example.unit.app.BuildConfig;
import com.example.unit.app.base.RxJavaRule1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

/**
 * @author lisen
 * @since 09-04-2018
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class RxJavaTestInterval {

    @Rule
    public RxJavaRule1 rxjavarule1 = new RxJavaRule1();
    /**
     * 这样需要等 10 s
     */
    @Test
    public void testInterval(){
        TestSubscriber<Long> testSubscriber = new TestSubscriber<>();
        //隔 1 秒发射一次，一共 10 次
        Flowable.interval(1, TimeUnit.SECONDS)
                .take(10)
                .subscribe(testSubscriber);

        testSubscriber.assertValueCount(10);
        testSubscriber.assertTerminated();
    }
}
