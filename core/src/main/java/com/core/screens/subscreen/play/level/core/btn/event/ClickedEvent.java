package com.core.screens.subscreen.play.level.core.btn.event;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.core.btnevents.SingleClickInputListener;

public class ClickedEvent extends SingleClickInputListener {

    private static Sound currentSound;
    private final Sound clickSound;

    public ClickedEvent(Sound sound) {
        this.clickSound = sound;
    }

    @Override
    public void firstTouchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (currentSound != null)
            currentSound.stop();
        currentSound = clickSound;
        currentSound.play();
    }

    @Override
    public void firstTouchUp(InputEvent event, float x, float y, int pointer, int button) {
    }

}
