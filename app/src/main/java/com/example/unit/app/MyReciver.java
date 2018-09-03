package com.example.unit.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * @author lisen
 * @since 09-04-2018
 */

public class MyReciver extends BroadcastReceiver {

    private static final String TAG = "MyReciver";

    public static final String ACTION = "com.example.unit.reviver.test";

    public static final String EXTRAS_MSG = "extras_msg";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: ");
        String msg = intent.getStringExtra(EXTRAS_MSG);
        PreferenceManager
                .getDefaultSharedPreferences(context)
                .edit()
                .putString(EXTRAS_MSG, msg)
                .apply();
    }
}
