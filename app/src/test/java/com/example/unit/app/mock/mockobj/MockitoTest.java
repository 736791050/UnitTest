package com.example.unit.app.mock.mockobj;

import com.example.unit.app.mock.ModelImpl;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * @author lisen
 * @since 09-03-2018
 */

public class MockitoTest {

    @Test
    public void testIsNotNull(){
        //mock 一个对象
        ModelImpl model = mock(ModelImpl.class);
        assertNotNull(model);
    }
}
