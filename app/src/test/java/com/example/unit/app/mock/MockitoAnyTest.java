package com.example.unit.app.mock;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static com.example.unit.app.base.PrintUtil.print;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;

/**
 * @author lisen
 * @since 09-03-2018
 */

public class MockitoAnyTest {

    @Mock
    ModelImpl model;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void anyTest(){
        //anyString 任何非空 String
        when(model.setName(anyString())).thenReturn(null);
        print(model.setName("666"));


        List<String> list = new ArrayList<>();
        list.add("语文");
        list.add("英语");
        //anyList 匹配任何非空 List
        when(model.setSubjects(ArgumentMatchers.<String>anyList())).thenReturn(list);
        List<String> list1 = new ArrayList<>();
        list1.add("地理");
        List<String> result = model.setSubjects(list1);
        print("" + result.get(0));

        //anyObject
        //any
        //anyByte
        //anyCollection
        //anyDouble
        //anyFloat
        //anyInt
        //anyList
        //anyMap
        //anyLong
        //contains
        //artThat

        when(model.setName(contains("a"))).thenReturn("有 A");
        print(model.setName("abcd"));
    }

}
