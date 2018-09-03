package com.example.unit.app.mock;

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
        ModelImpl model = mock(ModelImpl.class);
        assertNotNull(model);
    }
}
