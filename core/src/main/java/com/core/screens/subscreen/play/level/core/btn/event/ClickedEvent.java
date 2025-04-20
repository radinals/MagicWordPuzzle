package com.core.screens.subscreen.play.level.core.btn.event;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.core.custom.SingleClickInputListener;
import com.core.screens.subscreen.play.level.core.Level;

public class ClickedEvent extends SingleClickInputListener {

    private final  Sound clickSound;
    private static Sound currentSound;

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
    public void firstTouchUp(InputEvent event, float x, float y, int pointer, int button) { }

}
