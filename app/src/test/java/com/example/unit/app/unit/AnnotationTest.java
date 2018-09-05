package com.example.unit.app.unit;

import android.util.Log;

import com.example.unit.app.BuildConfig;
import com.example.unit.app.base.PrintRule;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.junit.Assert.fail;

/**
 * @author lisen
 * @since 09-05-2018
 *
    I/AnnotationTest: beforeClass:

    start test test1
    I/AnnotationTest: before:
    I/AnnotationTest: test1:
    I/AnnotationTest: after:
    end test test1

    start test test2
    I/AnnotationTest: before:
    I/AnnotationTest: test2:
    I/AnnotationTest: after:
    end test test2

    I/AnnotationTest: afterClass:

 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class AnnotationTest {

    private static final String TAG = "AnnotationTest";

    @Rule
    public PrintRule printRule = new PrintRule();

    @BeforeClass
    public static void beforeClass(){
        ShadowLog.stream = System.out;
        Log.i(TAG, "beforeClass: ");
    }

    @Before
    public void before(){
        Log.i(TAG, "before: ");
    }

    @After
    public void after(){
        Log.i(TAG, "after: ");
    }

    @AfterClass
    public static void afterClass(){
        Log.i(TAG, "afterClass: ");
    }

    @Test
    public void test1(){
        Log.i(TAG, "test1: ");
    }

    @Test
    public void test2(){
        Log.i(TAG, "test2: ");
    }

    @Test(timeout = 20)
    public void testTimeOut(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "testTimeOut: ");
    }

    @Test(expected = AssertionError.class)
    public void failTest(){
        fail("故意返回失败，验证走了此测试方法");
    }

}
