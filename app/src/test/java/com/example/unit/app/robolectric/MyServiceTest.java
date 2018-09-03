package com.example.unit.app.robolectric;

import com.example.unit.app.BuildConfig;
import com.example.unit.app.MyService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ServiceController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

/**
 * @author lisen
 * @since 09-04-2018
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class MyServiceTest {

    private ServiceController<MyService> controller;
    private MyService mService;

    @Before
    public void setUp(){
        ShadowLog.stream = System.out;

        controller = Robolectric.buildService(MyService.class);
        mService = controller.get();

    }

    @Test
    public void testServiceLifecycle() throws Exception{
        controller.create();
        controller.startCommand(0, 0);
        controller.bind();
        controller.unbind();
        controller.destroy();
    }

}
