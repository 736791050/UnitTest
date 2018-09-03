package com.example.unit.app.mock;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.verification.VerificationMode;

/**
 * @author lisen
 * @since 09-03-2018
 */

public class MockitoInOrderTest {

    @Mock
    ModelImpl model;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void InOrderTest(){
        model.setName("111");
        model.setAge(1);

        InOrder inOrder = Mockito.inOrder(model);

        inOrder.verify(model).setName("111");
        inOrder.verify(model).setAge(1);
    }

}
