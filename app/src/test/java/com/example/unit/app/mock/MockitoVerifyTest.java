package com.example.unit.app.mock;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static com.example.unit.app.base.PrintUtil.print;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.description;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author lisen
 * @since 09-03-2018
 */

public class MockitoVerifyTest {
    @Mock
    ModelImpl model;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void afterTest(){
        model.getName();
        print("a>" + System.currentTimeMillis());
        //延时一秒
        verify(model, after(1000)).getName();
        print("b>" + System.currentTimeMillis());
    }

//    //time out 感觉有问题。。。
//    @Test public void timeOutTest(){
//        model.sleep();
//        verify(model, timeout(0).atLeastOnce()).sleep();
//    }

    @Test
    public void atLeastTest(){
        model.getName();
        model.getName();
        verify(model, atLeast(2)).getName();
    }

    @Test
    public void atMostTest(){
        model.getName();
//        model.getName();
        verify(model, atMost(1)).getName();
    }

    @Test(expected = MockitoAssertionError.class)
    public void descriptionTest() throws Exception{
        when(model.getName()).thenThrow(new NullPointerException("null"));
        verify(model, description("出错后，打印这里的代码")).getName();
        model.getName();
    }

    @Test
    public void timesTest(){
        model.getName();
        model.getName();
        verify(model, times(2)).getName();
    }

    @Test
    public void neverTest(){
//        model.getName();
        verify(model, never()).getName();
    }

    @Test
    public void onlyTest(){
        model.getName();
//        model.getName();
        verify(model, only()).getName();
    }

}
