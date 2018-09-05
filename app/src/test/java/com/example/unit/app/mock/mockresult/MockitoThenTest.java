package com.example.unit.app.mock.mockresult;

import com.example.unit.app.mock.ModelImpl;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import static com.example.unit.app.base.PrintUtil.print;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author lisen
 * @since 09-03-2018
 */

public class MockitoThenTest {


    @Mock
    ModelImpl model;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void thenReturnTest(){
        when(model.getName()).thenReturn("thenReturn Name");

        print(model.getName());
    }

    @Test(expected = NullPointerException.class)
    public void thenThrowTest(){
        when(model.getName()).thenThrow(new NullPointerException("then throw  name is null"));

        model.getName();
    }

    @Test
    public void thenAnswerTest(){
        when(model.setName(anyString())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                String name = invocation.getArgument(0);
                return "then answer " + name;
            }
        });

        print(model.setName("lucy"));
    }

}
