package com.example.unit.app.mock.any;

import com.example.unit.app.BuildConfig;
import com.example.unit.app.unit.IsMobilePhoneMatcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.doCallRealMethod;

/**
 * @author lisen
 * @since 09-03-2018
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class MockitoAnyTest {

    @Mock
    AnyImpl model;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp(){
        ShadowLog.stream = System.out;
    }

    @Test
    public void anyTest(){
        //anyObject
        doCallRealMethod().when(model).setObject(anyObject());
        model.setObject("s");

        //any
        doCallRealMethod().when(model).setObject(any());
        model.setObject("s");

        //any
        doCallRealMethod().when(model).setObject(any(Object.class));
        model.setObject("s");

        //anyBoolean
        doCallRealMethod().when(model).setaBoolean(anyBoolean());
        model.setaBoolean(true);

        //anyCollection
        doCallRealMethod().when(model).setCollection(ArgumentMatchers.anyCollection());
        List list = new ArrayList();
        model.setCollection(list);

        //anyDouble
        doCallRealMethod().when(model).setaDouble(anyDouble());
        model.setaDouble(1d);

        //anyFloat
        doCallRealMethod().when(model).setaFloat(anyFloat());
        model.setaFloat(1f);

        //anyInt
        doCallRealMethod().when(model).setAnInt(anyInt());
        model.setAnInt(1);

        //anyList
        doCallRealMethod().when(model).setList(ArgumentMatchers.anyList());
        model.setList(list);

        //anyLong
        doCallRealMethod().when(model).setaLong(anyLong());
        model.setaLong(1l);

        //anyMap
        Map map = new HashMap();
        doCallRealMethod().when(model).setMap(ArgumentMatchers.anyMap());
        model.setMap(map);

        //anyString
        doCallRealMethod().when(model).setString(anyString());
        model.setString("s");

        //contains
        doCallRealMethod().when(model).setString(contains("a"));
        model.setString("abc");

        //argThat
        doCallRealMethod().when(model).setString(argThat(new Matcher()));
        model.setString("abcd");

    }

    class Matcher implements ArgumentMatcher<String> {

        @Override
        public boolean matches(String argument) {
            return argument.contains("d");
        }
    }

}
