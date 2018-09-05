package com.example.unit.app.mock.any;

import android.util.Log;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author lisen
 * @since 09-05-2018
 */

public class AnyImpl {

    private static final String TAG = "AnyImpl";

    private Object object;
    private boolean aBoolean;
    private Byte aByte;
    private Collection collection;
    private double aDouble;
    private float aFloat;
    private int anInt;
    private List list;
    private long aLong;
    private Map map;
    private String string;

    public void setObject(Object object) {
        Log.i(TAG, "setObject: " + object);
        this.object = object;
    }

    public void setaBoolean(boolean aBoolean) {
        Log.i(TAG, "setaBoolean: " + aBoolean);
        this.aBoolean = aBoolean;
    }

    public void setaByte(Byte aByte) {
        Log.i(TAG, "setaByte: " + aByte);
        this.aByte = aByte;
    }

    public void setCollection(Collection collection) {
        Log.i(TAG, "setCollection: " + collection);
        this.collection = collection;
    }

    public void setaDouble(double aDouble) {
        Log.i(TAG, "setaDouble: " + aDouble);
        this.aDouble = aDouble;
    }

    public void setaFloat(float aFloat) {
        Log.i(TAG, "setaFloat: " + aFloat);
        this.aFloat = aFloat;
    }

    public void setAnInt(int anInt) {
        Log.i(TAG, "setAnInt: " + anInt);
        this.anInt = anInt;
    }

    public void setList(List list) {
        Log.i(TAG, "setList: " + list);
        this.list = list;
    }

    public void setaLong(long aLong) {
        Log.i(TAG, "setaLong: " + aLong);
        this.aLong = aLong;
    }

    public void setMap(Map map) {
        Log.i(TAG, "setMap: " + map);
        this.map = map;
    }

    public void setString(String string) {
        Log.i(TAG, "setString: " + string);
        this.string = string;
    }
}
