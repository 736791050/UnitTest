package com.example.unit.app.mock.mockobj;

import com.example.unit.app.mock.ModelImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

/**
 * @author lisen
 * @since 09-03-2018
 *
 * @Mock 注解 + MockitoAnnotations.initMocks(this) 进行注入
 */

public class MockitoAnnotationsTest {

    @Mock
    ModelImpl model;

    @Before
    public void setup(){
        //初始化
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIsNotNull(){
        assertNotNull(model);
    }

}
