package com.example.unit.app.login;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author lisen
 * @since 08-31-2018
 */
public class LoginPresenterTest {

    @Test
    public void login() throws Exception {
        LoginManager loginManager = mock(LoginManager.class);
        when(loginManager.validateName(anyString())).thenReturn(true);

        LoginPresenter loginPresenter = new LoginPresenter(loginManager);

        String name = "";
        String password = "admin123";
        LoginManager.Callback mCallback = new LoginManager.Callback() {
            @Override
            public void onSuccess(Object data) {
                System.out.println("onSucdess " + data);
            }

            @Override
            public void onFailed(int code, String msg) {
                System.out.println("onFailed " + code + " " + msg);
            }

            @Override
            public void onError(Exception e) {
                System.out.println("onError " + e.getMessage());
            }
        };
        loginPresenter.setCallback(mCallback);
        loginPresenter.login(name, password);
        verify(loginManager).performLogin(name, password, mCallback);


    }

    @Test
    public void mockDoAnswer(){
        LoginManager loginManager = mock(LoginManager.class);

        String name = "admin";
        String password = "admin123";
        LoginManager.Callback mCallback = new LoginManager.Callback() {
            @Override
            public void onSuccess(Object data) {
                System.out.println("onSucdess " + data);
            }

            @Override
            public void onFailed(int code, String msg) {
                System.out.println("onFailed " + code + " " + msg);
            }

            @Override
            public void onError(Exception e) {
                System.out.println("onError " + e.getMessage());
            }
        };
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();

                LoginManager.Callback callback = (LoginManager.Callback) arguments[2];

//                callback.onFailed(500, " 测试失败情况");
                callback.onSuccess("成功了");


                return invocation;
            }
        }).when(loginManager).performLogin(anyString(), anyString(), any(LoginManager.Callback.class));
        loginManager.performLogin(name, password, mCallback);
    }

    @Test
    public void testSpy(){
        LoginManager loginManager = spy(LoginManager.class);

        boolean result = loginManager.validateName("");
        assertEquals(false, result);

        boolean result1 = loginManager.validateName("name");
        assertEquals(true, result1);

        when(loginManager.validateName(anyString())).thenReturn(false);

        boolean result2 = loginManager.validateName("name");
        assertEquals(false, result2);

    }

}