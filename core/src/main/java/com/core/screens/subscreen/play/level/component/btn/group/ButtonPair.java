package com.core.screens.subscreen.play.level.component.btn.group;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ButtonPair {
    public TextButton wordButton;
    public ImageButton imgButton;
    public Sound sound;

    public ButtonPair(TextButton wordButton, ImageButton imgButton, Sound sound) {
        this.wordButton = wordButton;
        this.imgButton = imgButton;
        this.sound = sound;
    }
}
