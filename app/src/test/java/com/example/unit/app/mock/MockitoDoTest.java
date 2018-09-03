package com.example.unit.app.mock;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import static com.example.unit.app.base.PrintUtil.print;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

/**
 * @author lisen
 * @since 09-03-2018
 */

public class MockitoDoTest {

    @Mock
    ModelImpl model;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void doReturnTest(){
        doReturn("do return name").when(model).getName();

        print(model.getName());
    }

    @Test(expected = NullPointerException.class)
    public void doThrowTest(){
        doThrow(new NullPointerException("do throw null")).when(model).getName();

        model.getName();
    }

    @Test
    public void doAnswerTest(){
        doAnswer(new Answer() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                String name = invocation.getArgument(0);
                return "do answer :" + name;
            }
        }).when(model).setName(anyString());

        print(model.setName("lucy"));
    }

    @Test
    public void doCallRealMethodTest(){
        doCallRealMethod().when(model).setName(anyString());

        print(model.setName("lucy"));
    }

    @Test
    public void doNothingTest(){
        doNothing().when(model).setAge(anyInt());
        doCallRealMethod().when(model).getAge();

        model.setAge(10);

        print(model.getAge());

        doCallRealMethod().when(model).setAge(anyInt());

        model.setAge(10);

        print(model.getAge());
    }

}
