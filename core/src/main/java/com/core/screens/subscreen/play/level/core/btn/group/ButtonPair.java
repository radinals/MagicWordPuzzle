package com.core.screens.subscreen.play.level.core.btn.group;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ButtonPair {
    public TextButton wordButton;
    public ImageButton imgButton;

    public ButtonPair(TextButton wordButton, ImageButton imgButton) {
        this.wordButton = wordButton;
        this.imgButton = imgButton;
    }
}
