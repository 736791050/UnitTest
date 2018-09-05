package com.example.unit.app.robolectric.rxjava;

import com.example.unit.app.BuildConfig;
import com.example.unit.app.base.RxJavaRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;

/**
 * @author lisen
 * @since 09-04-2018
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class RxJavaTest {

    @Rule
    public RxJavaRule rxJavaRule = new RxJavaRule();

    @Test
    public void testObserver(){
        TestObserver<Integer> testObserver = TestObserver.create();

        testObserver.onNext(1);
        testObserver.onNext(2);

        //断言值是否相等
        testObserver.assertValues(1, 2);

        testObserver.onComplete();
        //断言是否完成
        testObserver.assertComplete();
    }

    @Test
    public void testJust(){
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        //依次发射 A，B，C
        Flowable.just("A", "B", "C").subscribe(testSubscriber);

        //断言值是否不存在
        testSubscriber.assertNever("D");

        //断言值是否相等
        testSubscriber.assertValues("A", "B", "C");

        //断言值的数量是否相等
        testSubscriber.assertValueCount(3);

        //断言是否结束
        testSubscriber.assertTerminated();
    }

    @Test
    public void testForm(){
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        //依次发射list中的数字
        Flowable.fromIterable(Arrays.asList(1, 2)).subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2);
        testSubscriber.assertValueCount(2);
        testSubscriber.assertTerminated();
    }

    @Test
    public void testRange(){
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        //从 3 开始发射三个连续的 int
        Flowable.range(3, 3).subscribe(testSubscriber);

        testSubscriber.assertValues(3, 4, 5);
        testSubscriber.assertValueCount(3);
        testSubscriber.assertTerminated();
    }

    @Test
    public void testRepeat(){
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        Flowable.fromIterable(Arrays.asList(1, 2))
                .repeat(2)//重复发送两次
                .subscribe(testSubscriber);

        testSubscriber.assertValues(1, 2, 1, 2);
        testSubscriber.assertValueCount(4);
        testSubscriber.assertTerminated();
    }

    @Test
    public void testBuffer(){
        TestSubscriber<List<String>> testSubscriber = new TestSubscriber<>();

        //缓冲2个发射一次
        Flowable.just("A", "B", "C", "D")
                .buffer(2)
                .subscribe(testSubscriber);

        testSubscriber.assertResult(Arrays.asList("A", "B"), Arrays.asList("C", "D"));
        testSubscriber.assertValueCount(2);
        testSubscriber.assertTerminated();
    }

    @Test
    public void testError(){
        TestSubscriber testSubscriber = new TestSubscriber();
        Exception exception = new RuntimeException("error");

        Flowable.error(exception).subscribe(testSubscriber);

        //断言错误是否一致
        testSubscriber.assertError(exception);
        testSubscriber.assertErrorMessage("error");
    }

//    /**
//     * 这样需要等 10 s
//     */
//    @Test
//    public void testInterval(){
//        TestSubscriber<Long> testSubscriber = new TestSubscriber<>();
//        //隔 1 秒发射一次，一共 10 次
//        Flowable.interval(1, TimeUnit.SECONDS)
//                .take(10)
//                .subscribe(testSubscriber);
//
//        testSubscriber.assertValueCount(10);
//        testSubscriber.assertTerminated();
//    }

    /**
     * 不需要等待
     */
    @Test
    public void testInterval1(){
        TestScheduler testScheduler = new TestScheduler();
        TestSubscriber<Long> testSubscriber = new TestSubscriber<>();
        //隔 1 秒发射一次，一共十次
        Flowable.interval(1, TimeUnit.SECONDS, testScheduler)
                .take(10)
                .subscribe(testSubscriber);

        //时间经过 3 秒
        testScheduler.advanceTimeBy(3, TimeUnit.SECONDS);
        testSubscriber.assertValues(0L, 1L, 2L);
        testSubscriber.assertValueCount(3);
        testSubscriber.assertNotTerminated();


        //时间再经过 2 秒
        testScheduler.advanceTimeBy(2, TimeUnit.SECONDS);
        testSubscriber.assertValues(0L, 1L, 2L, 3L, 4L);
        testSubscriber.assertValueCount(5);
        testSubscriber.assertNotTerminated();

        //时间到 10 秒
        testScheduler.advanceTimeTo(10, TimeUnit.SECONDS);
        testSubscriber.assertValueCount(10);
        testSubscriber.assertTerminated();

    }

    @Test
    public void testTimer(){
        TestScheduler testScheduler = new TestScheduler();
        TestSubscriber<Long> testSubscriber = new TestSubscriber<>();

        //延时 5 秒发射
        Flowable.timer(5, TimeUnit.SECONDS, testScheduler)
                .subscribe(testSubscriber);

        //时间到 5 s
        testScheduler.advanceTimeTo(5, TimeUnit.SECONDS);
        testSubscriber.assertValueCount(1);
        testSubscriber.assertTerminated();
    }
}
