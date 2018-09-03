package com.example.unit.app.mock;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static com.example.unit.app.base.PrintUtil.print;
import static org.junit.Assert.assertNotNull;

/**
 * @author lisen
 * @since 09-03-2018
 */

public class MockitoRuleTest {

    @Mock
    ModelImpl model;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIsNotNull(){
        assertNotNull(model);
        model.setName("666");
        print(model.getName());
    }

}
