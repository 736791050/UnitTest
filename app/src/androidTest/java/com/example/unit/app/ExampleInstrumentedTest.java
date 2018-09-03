package com.example.unit.app;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.unit.app", appContext.getPackageName());

    }

    @Test
    public void mockTest(){
        List mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).clear();
        verify(mockedList).add("one");

        when(mockedList.get(anyInt())).thenReturn(true);

        Object resu = mockedList.get(0);
        assertEquals(resu, true);
        assertTrue("结果应该是 true 的", (Boolean) resu);
    }
}
