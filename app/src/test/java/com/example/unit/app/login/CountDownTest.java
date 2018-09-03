package com.example.unit.app.login;

import android.widget.Button;

import com.example.unit.app.BuildConfig;
import com.example.unit.app.MainActivity;
import com.example.unit.app.R;
import com.example.unit.app.base.RxJavaRule;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.TestScheduler;

/**
 * @author lisen
 * @since 09-04-2018
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class CountDownTest {

    private MainActivity mainActivity;
    private Button mBtnCountDown;

    @Rule
    public RxJavaRule rxJavaRule = new RxJavaRule();

    @Before
    public void setUp(){
        mainActivity = Robolectric.setupActivity(MainActivity.class);

        mBtnCountDown = mainActivity.findViewById(R.id.btn_count_down);
    }

    @Test
    public void testCountDown(){
        Assert.assertEquals(mBtnCountDown.getText().toString(), "点击倒计时");

        //触发点击按钮
        mBtnCountDown.performClick();

        //时间到 10 s
        TestScheduler testScheduler = rxJavaRule.getTestScheduler();
        testScheduler.advanceTimeTo(10, TimeUnit.SECONDS);
        Assert.assertEquals(mBtnCountDown.isEnabled(), false);
        Assert.assertEquals(mBtnCountDown.getText().toString(), "51 s 后重试");

        //时间到 60 s
        testScheduler.advanceTimeTo(60, TimeUnit.SECONDS);

        Assert.assertEquals(mBtnCountDown.getText().toString(), "点击倒计时");
        Assert.assertEquals(mBtnCountDown.isEnabled(), true);

    }

}
