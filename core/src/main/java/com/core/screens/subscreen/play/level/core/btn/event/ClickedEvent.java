package com.core.screens.subscreen.play.level.core.btn.binding;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.core.screens.subscreen.play.level.core.Level;

public class ClickedBinding extends ClickListener {

    private final Level level;
    private final Sound clickSound;

    public ClickedBinding(Sound sound, Level level) {
        this.level = level;
        this.clickSound = sound;
    }


    @Override
    public void clicked(InputEvent event, float x, float y) {
        Sound currentPlayingSound = this.level.getCurrentPlayingSound();
        if (currentPlayingSound != null)
            currentPlayingSound.stop();
        currentPlayingSound = clickSound;
        currentPlayingSound.play();
    }
}
