package com.example.unit.app;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private String lifecycle;
    private DisposableObserver<Long> mDisposable;

    public String getLifecycleState(){
        return lifecycle;
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycle = "onStart";
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycle = "onResume";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lifecycle = "onCreate";
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycle = "onPause";
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifecycle = "onStop";
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        lifecycle = "onRestart";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycle = "onDestroy";
        if(mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public void login(View view) {
        Log.i(TAG, "login: ");
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void toast(View view) {
        Log.i(TAG, "toast: ");
        Toast.makeText(this, "toast test", Toast.LENGTH_SHORT).show();
    }

    public void showDialog(View view) {
        Log.i(TAG, "showDialog: ");
        new AlertDialog
                .Builder(this)
                .setMessage("this is a dialog")
                .setTitle("dialog title")
                .create()
                .show();
    }

    public void setFragment(View view) {
        SampleFragment fragment = new SampleFragment();
        getFragmentManager().beginTransaction().replace(R.id.fl_container,
                fragment, fragment.getClass().getSimpleName()).commit();
    }

    public void countDown(final View view) {
        view.setEnabled(false);

        mDisposable = Observable
                .interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .take(60)
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(Long aLong) {
                        ((Button)view).setText((60 - aLong) + " s 后重试");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        ((Button)view).setText("点击倒计时");
                        view.setEnabled(true);
                    }
                });

    }
}
