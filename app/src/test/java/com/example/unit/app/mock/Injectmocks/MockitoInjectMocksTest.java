package com.example.unit.app.mock.Injectmocks;

import com.example.unit.app.mock.ModelImpl;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static com.example.unit.app.base.PrintUtil.print;
import static org.mockito.Mockito.when;

/**
 * @author lisen
 * @since 09-03-2018
 *
 * Mockito框架不支持mock匿名类、final类、static方法、private方法
 */

public class MockitoInjectMocksTest {

    @Spy
    ModelImpl model;

    @InjectMocks
    PresenterImpl presenter;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void injectMocksTest(){
        when(model.getName()).thenReturn("return name");
        print(presenter.getName());
    }

}
