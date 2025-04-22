package com.core.screens.subscreen.play.level.core.btn.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.core.data.assets.sprites.GameAssets;
import com.core.screens.subscreen.play.level.core.Level;
import com.core.screens.subscreen.play.level.core.btn.event.ClickedEvent;
import com.core.screens.subscreen.play.level.core.btn.event.DragEvent;

import java.util.Locale;

public class LevelButtonFactory {

    private final Level level;
    private float btnWidth;
    private float btnHeight;
    private TextureRegionDrawable cardBackground;
    private BitmapFont FONT;

    public LevelButtonFactory(Level level) {
        this.level = level;
        this.btnHeight = 0;
        this.btnWidth = 0;
        this.cardBackground = new TextureRegionDrawable(
            GameAssets.getInstance().assetManager.get("cardbg.png", Texture.class)
        );
        FONT = generateFont("altone/Altone Trial-Bold.ttf");
    }

    public void setBtnHeight(float btnHeight) {
        this.btnHeight = btnHeight;
    }

    public void setBtnWidth(float btnWidth) {
        this.btnWidth = btnWidth;
    }

    private BitmapFont generateFont(String fontfile) {
        FileHandle fontFile = Gdx.files.internal(fontfile);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        BitmapFont font = generator.generateFont(parameter);
        return font;
    }

    public TextButton createWordButton(String word, Sound sound) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();

        style.font = FONT;

        style.fontColor = Color.BLACK;

        cardBackground.setMinSize(btnWidth, btnHeight);
        style.up = style.down = cardBackground;

        TextButton btn = new TextButton(word.toUpperCase(Locale.ENGLISH), style);

        // Enable wrapping
        btn.getLabel().setWrap(true);
        btn.getLabel().setWidth(btnWidth * 0.8f);
        btn.getLabel().setAlignment(Align.center);

        btn.setSize(btnWidth, btnHeight);

        btn.addListener(new ClickedEvent(sound));
        btn.addListener(new DragEvent(level));

        return btn;

    }

    public ImageButton createImageButton(Texture image, Sound sound) {

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        ImageButton btn = new ImageButton(style);

        TextureRegionDrawable t = new TextureRegionDrawable(image);

        final float imgArea = btnWidth * 0.9f;
        final float imgScale = Math.min(imgArea / image.getWidth(), imgArea / image.getHeight());

        t.setMinSize(image.getWidth() * imgScale, image.getHeight() * imgScale);
        cardBackground.setMinSize(btnWidth, btnHeight);

        style.imageUp = t;
        style.up = cardBackground;

        btn.setSize(btnWidth, btnHeight);

        btn.addListener(new ClickedEvent(sound));
        btn.addListener(new DragEvent(level));

        return btn;
    }
}
