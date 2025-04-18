package com.core.screens.gameplay.level.btn;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class LevelPairButtons {
    public TextButton wordButton;
    public ImageButton imgButton;

    private boolean imgButtonLined;
    private final boolean wordButtonLined;

    public LevelPairButtons(TextButton wordButton, ImageButton imgButton) {
        this.wordButton = wordButton;
        this.imgButton = imgButton;
        this.wordButtonLined = false;
        this.imgButtonLined = false;
    }

    public void setImageButtonLined() {
        this.imgButtonLined = true;
    }

    public void setWordButtonLined() {
        this.imgButtonLined = true;
    }

    public boolean isImgButtonLined() {
        return imgButtonLined;
    }

    public boolean isWordButtonLined() {
        return wordButtonLined;
    }
}
