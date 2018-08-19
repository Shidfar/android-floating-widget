package com.floatingwidgetchathead_demo;

import android.hardware.input.InputManager;
import android.util.Log;

public class Watchdog extends Thread {
    public static final String TAG = "Watchdog";
    InputManager inputManager;
    long minPrime;
    Watchdog() {
        this.minPrime = 0;
    }

    public void run() {
        while (true) {
            Log.i(TAG, "still running ...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
