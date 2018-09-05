package com.example.unit.app.mock.spy;

import com.example.unit.app.mock.ModelImpl;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static com.example.unit.app.base.PrintUtil.print;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;

/**
 * @author lisen
 * @since 09-03-2018
 */

public class MockitoSpyTest {

    @Spy
    ModelImpl model;

    @Mock
    ModelImpl mockModel;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void testIsNotNull(){
        assertNotNull(model);
    }

    @Test
    public void testSpy(){
        print(model.getName());
    }

    @Test
    public void testMock(){
        print(mockModel.getName());
    }

    @Test
    public void testDorealCall(){
        doCallRealMethod().when(mockModel).setName(anyString());
        mockModel.setName("666");
        doCallRealMethod().when(mockModel).getName();
        print(mockModel.getName());
    }
}
