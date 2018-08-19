package com.floatingwidgetchathead_demo;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;

public class MainApplication extends Application {
    public static final String TAG = "MainApplication";
    KeyListener keyListener;
    Watchdog watchdog;
    Context context;
    InputManager inputManager;
    VolumeKeyController volumeKeyController;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        watchdog = new Watchdog();
        watchdog.start();
        inputManager = (InputManager) context.getSystemService(Context.INPUT_SERVICE);
        assert inputManager != null;
        int[] devices = inputManager.getInputDeviceIds();
        for (int device : devices) {
            System.out.print(" [" + device + "] ");
        }
        System.out.println();
        inputManager.registerInputDeviceListener(new InputManager.InputDeviceListener() {
            @Override
            public void onInputDeviceAdded(int deviceId) {

            }

            @Override
            public void onInputDeviceRemoved(int deviceId) {

            }

            @Override
            public void onInputDeviceChanged(int deviceId) {

            }
        }, new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        }));
        volumeKeyController = new VolumeKeyController(context);
        volumeKeyController.setActive(true);
    }
}
