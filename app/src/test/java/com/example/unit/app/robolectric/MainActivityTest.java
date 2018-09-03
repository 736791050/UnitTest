package com.example.unit.app.robolectric;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.unit.app.BuildConfig;
import com.example.unit.app.LoginActivity;
import com.example.unit.app.MainActivity;
import com.example.unit.app.R;
import com.example.unit.app.SampleFragment;
import com.example.unit.app.base.MyRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author lisen
 * @since 09-04-2018
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class MainActivityTest {
    private MainActivity mainActivity;

    @Rule
    public MyRule myRule = new MyRule();

    @Before
    public void setUp(){
        //输出日志（将 Log 日志输出到控制面板中）
        ShadowLog.stream = System.out;
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void testMainActivity(){
        assertNotNull(mainActivity);
    }

    @Test
    public void testStartActivity(){
        //获取登陆按钮
        Button login = mainActivity.findViewById(R.id.login);
        //触发按钮点击
        login.performClick();
        //获取对应的 shadow 类
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        //借助 shadow 类获取启动下一个 Activity 的 Intent
        Intent nextIntent = shadowActivity.getNextStartedActivity();
        //校验 intent 的正确性
        assertEquals(nextIntent.getComponent().getClassName(), LoginActivity.class.getName());
    }

    @Test
    public void testToast() throws Exception{
        Toast toast = ShadowToast.getLatestToast();
        //判断 Toast 尚未弹出
        assertNull(toast);

        View btnToast = mainActivity.findViewById(R.id.tv_toast);
        btnToast.performClick();

        toast = ShadowToast.getLatestToast();
        //判断 Toast 已经弹出
        assertNotNull(toast);
        //获取 Shadow 类进行验证
        ShadowToast shadowToast = Shadows.shadowOf(toast);
        assertEquals(Toast.LENGTH_SHORT, shadowToast.getDuration());
        assertEquals("toast test", ShadowToast.getTextOfLatestToast());
    }

    /**
     * 这里的弹窗只能是：android.app.AlertDialog
     */
    @Test
    public void testDialog(){
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        //判断 dialog 尚未弹出
        assertNull(dialog);

        View showDialog = mainActivity.findViewById(R.id.btn_dialog);
        showDialog.performClick();

        dialog = ShadowAlertDialog.getLatestAlertDialog();
        //判断 dialog 已经弹出
        assertNotNull(dialog);
        //获取 shadow 进行验证
        ShadowAlertDialog shadowAlertDialog = Shadows.shadowOf(dialog);
        assertEquals("this is a dialog", shadowAlertDialog.getMessage());
    }

    @Test
    public void testCheckBoxState() throws Exception{
       CheckBox checkBox = mainActivity.findViewById(R.id.cb_box);
        //验证 CheckBox 初始状态
        assertFalse(checkBox.isChecked());

        //点击按钮反转 checkbox 状态
        checkBox.performClick();

        //验证状态是否正确
        assertTrue(checkBox.isChecked());

        //再次点击
        checkBox.performClick();

        //验证状态
        assertFalse(checkBox.isChecked());
    }

    @Test
    public void testFragment(){
        ViewGroup container = mainActivity.findViewById(R.id.fl_container);

        SampleFragment fragment = (SampleFragment) mainActivity.getFragmentManager()
                .findFragmentByTag(SampleFragment.class.getSimpleName());

        assertNull(fragment);

        container.performClick();

        fragment = (SampleFragment) mainActivity.getFragmentManager()
                .findFragmentByTag(SampleFragment.class.getSimpleName());

        assertNotNull(fragment);
        assertNotNull(fragment.getView());
    }

    @Test
    public void testLifecycle() throws Exception{
        //创建 Activity 控制器
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        MainActivity activity = controller.get();
        assertNull(activity.getLifecycleState());

        //调用 activity 的 performCreate 方法
        controller.create();
        assertEquals("onCreate", activity.getLifecycleState());

        //调用 activity 的 performStart 方法
        controller.start();
        assertEquals("onStart", activity.getLifecycleState());

        //调用 activity 的 performResume 方法
        controller.resume();
        assertEquals("onResume", activity.getLifecycleState());

        // 调用Activity的performPause方法
        controller.pause();
        assertEquals("onPause", activity.getLifecycleState());

        // 调用Activity的performStop方法
        controller.stop();
        assertEquals("onStop", activity.getLifecycleState());

        // 调用Activity的performRestart方法
        controller.restart();
        // 注意此处应该是onStart，因为performRestart不仅会调用restart，还会调用onStart
        assertEquals("onStart", activity.getLifecycleState());

        // 调用Activity的performDestroy方法
        controller.destroy();
        assertEquals("onDestroy", activity.getLifecycleState());
    }
}
