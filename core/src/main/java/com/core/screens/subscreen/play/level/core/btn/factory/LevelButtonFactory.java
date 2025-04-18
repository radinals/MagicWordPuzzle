package com.core.screens.gameplay.level.btn.binding;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.screens.gameplay.level.Level;

public class LevelButtonFactory {

    private float btnWidth;
    private float btnHeight;

    private final Level level;

    public LevelButtonFactory(Level level) {
        this.level = level;
        this.btnHeight = 0;
        this.btnWidth = 0;
    }

    public float getBtnHeight() {
        return btnHeight;
    }

    public void setBtnHeight(float btnHeight) {
        this.btnHeight = btnHeight;
    }

    public float getBtnWidth() {
        return btnWidth;
    }

    public void setBtnWidth(float btnWidth) {
        this.btnWidth = btnWidth;
    }

    public TextButton createWordButton(String word, Sound sound) {

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        style.font.getData().setScale(2f); // unreliable for UI
        style.fontColor = Color.BLACK;

        TextButton btn = new TextButton(word, style);

        btn.setSize(btnWidth, btnHeight);

        btn.addListener(new ClickedBinding(sound, level));
        btn.addListener(new DragBinding(level));

        return btn;

    }

    public ImageButton createImageButton(Texture image, Sound sound) {

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        ImageButton btn = new ImageButton(style);

        TextureRegionDrawable t = new TextureRegionDrawable(image);

        t.setMinSize(btnWidth * 0.9f,btnHeight * 0.9f);

        style.imageUp = t;

        btn.setSize(btnWidth, btnHeight);

        btn.addListener(new ClickedBinding(sound, level));
        btn.addListener(new DragBinding(level));

        return btn;
    }
}
