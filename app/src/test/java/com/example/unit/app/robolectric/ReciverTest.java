package com.example.unit.app.robolectric;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.preference.PreferenceManager;

import com.example.unit.app.BuildConfig;
import com.example.unit.app.MyReciver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author lisen
 * @since 09-04-2018
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class ReciverTest {
    @Test
    public void testRegister() throws Exception{
        ShadowApplication shadowApplication = ShadowApplication.getInstance();
        Intent intent = new Intent(MyReciver.ACTION);
        //验证是否注册了相应的 Reciver
        assertTrue(shadowApplication.hasReceiverForIntent(intent));

        List<ResolveInfo> list = shadowApplication.getApplicationContext()
                .getPackageManager()
                .queryBroadcastReceivers(intent, PackageManager.GET_RECEIVERS);
        assertEquals(1, list.size());
        ResolveInfo resolveInfo = list.get(0);
    }

    @Test
    public void testReciver() throws Exception{
        //发广播
        Intent intent = new Intent(MyReciver.ACTION);
        intent.putExtra(MyReciver.EXTRAS_MSG, "send msg");
        MyReciver myReciver = new MyReciver();
        myReciver.onReceive(RuntimeEnvironment.application, intent);

        //验证逻辑
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application);
        assertEquals("send msg", preferences.getString(MyReciver.EXTRAS_MSG, ""));
    }
}
