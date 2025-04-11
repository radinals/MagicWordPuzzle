package com.main.android;

import android.content.Context;
import android.media.AudioManager;

import android.content.Intent;
import android.os.Bundle;

import com.android.AndroidActions;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.main.Main;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication implements AndroidActions {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.useImmersiveMode = true; // Recommended, but not required.
        initialize(new Main(this), configuration);
    }

    @Override
    public void openVolumeSettings() {
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audio.adjustStreamVolume(
            AudioManager.STREAM_MUSIC,
            AudioManager.ADJUST_SAME,
            AudioManager.FLAG_SHOW_UI
        );
    }
}
