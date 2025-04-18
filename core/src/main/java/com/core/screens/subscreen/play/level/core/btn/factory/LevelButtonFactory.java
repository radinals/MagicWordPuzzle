package com.core.screens.subscreen.play.level.core.btn.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.screens.subscreen.play.level.core.Level;
import com.core.screens.subscreen.play.level.core.btn.event.ClickedEvent;
import com.core.screens.subscreen.play.level.core.btn.event.DragEvent;
import com.main.Main;

public class LevelButtonFactory {

    private final Main main;
    private final Level level;
    private float btnWidth;
    private float btnHeight;

    public LevelButtonFactory(Main main, Level level) {
        this.level = level;
        this.btnHeight = 0;
        this.btnWidth = 0;
        this.main = main;
    }

    public void setBtnHeight(float btnHeight) {
        this.btnHeight = btnHeight;
    }

    public void setBtnWidth(float btnWidth) {
        this.btnWidth = btnWidth;
    }

    public TextButton createWordButton(String word, Sound sound) {

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        style.font.getData().setScale(2f); // unreliable for UI
        style.fontColor = Color.BLACK;

        TextureRegionDrawable t = new TextureRegionDrawable(main.getBaseAssets().assetManager.get("cardbg.png", Texture.class));
        t.setMinSize(btnWidth, btnHeight);
        style.up = t;
        style.down = t;

        TextButton btn = new TextButton(word, style);

        btn.setSize(btnWidth, btnHeight);

        btn.addListener(new ClickedEvent(sound, level));
        btn.addListener(new DragEvent(level));

        return btn;

    }

    public ImageButton createImageButton(Texture image, Sound sound) {

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        ImageButton btn = new ImageButton(style);

        TextureRegionDrawable t = new TextureRegionDrawable(image);
        TextureRegionDrawable s = new TextureRegionDrawable(main.getBaseAssets().assetManager.get("cardbg.png", Texture.class));

        t.setMinSize(btnWidth * 0.9f, btnHeight * 0.9f);
        s.setMinSize(btnWidth, btnHeight);

        style.imageUp = t;
        style.up = s;

        btn.setSize(btnWidth, btnHeight);

        btn.addListener(new ClickedEvent(sound, level));
        btn.addListener(new DragEvent(level));

        return btn;
    }
}
