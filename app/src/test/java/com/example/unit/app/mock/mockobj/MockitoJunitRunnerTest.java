package com.example.unit.app.mock.mockobj;

import com.example.unit.app.mock.ModelImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

/**
 * @author lisen
 * @since 09-03-2018
 */

@RunWith(MockitoJUnitRunner.class)
public class MockitoJunitRunnerTest {

    @Mock
    ModelImpl model;

    @Test
    public void testIsNotNull(){
        assertNotNull(model);
    }

}
