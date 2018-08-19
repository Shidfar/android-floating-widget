package com.floatingwidgetchathead_demo;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.media.AudioManager;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.media.session.PlaybackState.Builder;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.security.Key;

public class VolumeKeyController {

    private MediaSession mMediaSession;
    private final Context mContext;
    private final static String TAG = "VolumeController";

    VolumeKeyController(Context context) {
        mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void createMediaSession() {
        mMediaSession = new MediaSession(mContext, TAG);

        mMediaSession.setFlags(MediaSession.FLAG_HANDLES_MEDIA_BUTTONS |
                MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mMediaSession.setPlaybackState(new Builder()
                .setState(PlaybackState.STATE_PLAYING, 0, 0)
                .build());
        mMediaSession.setPlaybackToRemote(getVolumeProvider());
        mMediaSession.setActive(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private VolumeProvider getVolumeProvider() {
        final AudioManager audio = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);

        final int STREAM_TYPE = AudioManager.STREAM_MUSIC;
        assert audio != null;
        int currentVolume = audio.getStreamVolume(STREAM_TYPE);
        int maxVolume = audio.getStreamMaxVolume(STREAM_TYPE);
        final int VOLUME_UP = 1;
        final int VOLUME_DOWN = -1;

        return new VolumeProvider(VolumeProvider.VOLUME_CONTROL_RELATIVE, maxVolume, currentVolume) {
            @Override
            public void onAdjustVolume(int direction) {
                // Up = 1, Down = -1, Release = 0
                // Replace with your action, if you don't want to adjust system volume
                Log.d(TAG, "volume adjustment..." + direction);
//                if (direction == VOLUME_UP) {
//                    audio.adjustStreamVolume(STREAM_TYPE,
//                            AudioManager.ADJUST_RAISE, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
//                }
//                else if (direction == VOLUME_DOWN) {
//                    audio.adjustStreamVolume(STREAM_TYPE,
//                            AudioManager.ADJUST_LOWER, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
//                }
//                setCurrentVolume(audio.getStreamVolume(STREAM_TYPE));
            }
        };
    }

    // Call when control needed, add a call to constructor if needed immediately
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setActive(boolean active) {
        if (mMediaSession != null) {
            mMediaSession.setActive(active);
            return;
        }
        createMediaSession();
    }

    // Call from Service's onDestroy method
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void destroy() {
        if (mMediaSession != null) {
            mMediaSession.release();
        }
    }
}
