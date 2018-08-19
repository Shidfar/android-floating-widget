package com.floatingwidgetchathead_demo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

class KeyListener implements View.OnKeyListener {
    private Context _context;

    public void set_context(Context _context) {
        this._context = _context;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        CharSequence text = "Key pressed!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(_context, text, duration);
        toast.show();
        Log.i(">>>", "...................");
        return true;
    }
}
