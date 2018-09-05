package com.example.unit.app.unit;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lisen
 * @since 09-05-2018
 */

public class IsMobilePhoneMatcher extends BaseMatcher<String> {

    public static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";


    @Override
    public boolean matches(Object item) {

        if(item == null){
            return false;
        }

        String s = (String) item;

        if(s.length() == 0){
            return false;
        }

        Pattern pattern = Pattern.compile(REGEX_MOBILE_EXACT);
        Matcher matcher = pattern.matcher((String) item);

        return matcher.find();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("期望得到手机号");
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        description.appendText(item.toString() + " 不是手机号 ");
    }
}
