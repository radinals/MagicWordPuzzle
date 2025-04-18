package com.core.customcomponent.button;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class LevelBtn extends ImageButton {
    public int levelIdx;

    public LevelBtn(Skin skin) {
        super(skin);
    }

    public LevelBtn(Skin skin, String styleName) {
        super(skin, styleName);
    }

    public LevelBtn(ImageButtonStyle style) {
        super(style);
    }

    public LevelBtn(Drawable imageUp) {
        super(imageUp);
    }

    public LevelBtn(Drawable imageUp, Drawable imageDown) {
        super(imageUp, imageDown);
    }

    public LevelBtn(Drawable imageUp, Drawable imageDown, Drawable imageChecked) {
        super(imageUp, imageDown, imageChecked);
    }
}
