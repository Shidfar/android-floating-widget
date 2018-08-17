package com.floatingwidgetchathead_demo;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class KeyListenner implements View.OnKeyListener {
    private Context _context;

    public void setContext(Context _context) {
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
