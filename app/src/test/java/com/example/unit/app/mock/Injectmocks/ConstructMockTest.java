package com.example.unit.app.mock.Injectmocks;

import com.example.unit.app.mock.ModelImpl;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static com.example.unit.app.base.PrintUtil.print;
import static org.mockito.Mockito.when;

/**
 * @author lisen
 * @since 09-05-2018
 */

public class ConstructMockTest {
    @Mock
    ModelImpl model;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void test(){
        PresenterImpl presenter = new PresenterImpl(model);

        when(model.getName()).thenReturn("return name");
        print(presenter.getName());
    }

}
