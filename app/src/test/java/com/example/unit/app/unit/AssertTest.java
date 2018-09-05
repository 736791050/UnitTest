package com.example.unit.app.unit;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author lisen
 * @since 09-05-2018
 */

public class AssertTest {

    @Test
    public void equalsTest(){
        assertEquals(1, 1);

        //期望 1，实际 2，允许误差 1
        assertEquals(1, 2, 1);
        assertEquals(1, 0, 1);
    }

    @Test
    public void notEqualsTest(){
        assertNotEquals(1, 2);
    }

    @Test
    public void arrayEqualsTest(){
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        assertArrayEquals(a, b);
    }

    @Test
    public void assertNullTest(){
        String s = null;
        assertNull(s);
    }

    @Test
    public void assertNotNullTest(){
        String s = "";
        assertNotNull(s);
    }

    @Test
    public void assertTrueTest(){
        assertTrue(true);
    }

    @Test
    public void assertFalseTest(){
        assertFalse(false);
    }

    @Test
    public void assertSameTest(){
        List list = new ArrayList();
        List a = list;
        List b = list;
        assertSame(a, b);
    }

    @Test
    public void assertNotSameTest(){
        List a = new ArrayList();
        List b = new ArrayList();
        assertNotSame(a, b);
    }

    @Test
    public void assertThatTest(){
        assertThat(0, is(0));

        List<String> list = new ArrayList<>();
        list.add("a");
        assertThat("a 不存在", list, hasItem("a"));
    }

    @Test
    public void assertThatMatcherTest(){
        assertThat("15696876389", new IsMobilePhoneMatcher());
    }

    @Test(expected = AssertionError.class)
    public void failTest(){
        fail("故意返回失败，验证走了此测试方法");
    }


}
