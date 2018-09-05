package com.example.unit.app.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author lisen
 * @since 09-05-2018
 */

@RunWith(Parameterized.class)
public class RunWithParameterizedTest {

    private int a;
    private int b;

    public RunWithParameterizedTest(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Parameterized.Parameters
    public static Collection parameterNums(){
        return Arrays.asList(new Integer[][]{{1, 5}, {2, 4}, {3, 3}});
    }

    @Test
    public void test(){
        assertEquals(6, a + b);
    }

}
