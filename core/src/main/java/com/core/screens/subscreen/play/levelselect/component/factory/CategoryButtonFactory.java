package com.core.screens.subscreen.play.levelselect.component.factory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.assets.sprites.GameAssets;
import com.core.screens.subscreen.play.levelselect.component.btn.LevelBtn;
import com.core.screens.subscreen.play.levelselect.component.btn.event.ClickedEvent;
import com.main.Main;

public class CategoryButtonFactory {

    private final Main main;
    private final Color levelIconTint;
    private final ClickedEvent clickEvent;
    private float btnSize;
    private Drawable btnIconTexture;
    private Drawable btnIconTextureClicked;

    public CategoryButtonFactory(Main main, Color levelIconColor, String levelCategoryName) {
        this.main = main;
        this.clickEvent = new ClickedEvent(main, levelCategoryName);
        this.levelIconTint = levelIconColor;

        TextureRegionDrawable tmp = new TextureRegionDrawable(GameAssets.getInstance().assetManager.get("levelbtn.png", Texture.class));
        this.btnIconTexture = tmp.tint(levelIconColor);
        this.btnIconTextureClicked = tmp.tint(Color.GRAY);
    }

    public void setBtnSize(float btnSize) {
        TextureRegionDrawable tmp = new TextureRegionDrawable(GameAssets.getInstance().assetManager.get("levelbtn.png", Texture.class));
        tmp.setMinSize(btnSize, btnSize);
        this.btnIconTexture = tmp.tint(this.levelIconTint);
        this.btnIconTextureClicked = tmp.tint(Color.GRAY);
        this.btnSize = btnSize;
    }

    private TextureRegionDrawable createNumberTexture(int number) {
        float btnNumWidth = 0;
        float btnNumHeight = 0;

        if (number >= 10) {
            btnNumWidth = btnSize * 0.7f;
            btnNumHeight = btnSize;
        } else {
            btnNumWidth = btnNumHeight = btnSize * 0.5f;
        }

        TextureRegionDrawable numberTexture = new TextureRegionDrawable(
            GameAssets.getInstance().getBitNumbers().getNumber(number)
        );

        numberTexture.setMinSize(btnNumWidth, btnNumHeight);

        return numberTexture;
    }

    public ImageButton createLevelButton(int num) {

        final TextureRegionDrawable numberTexture = createNumberTexture(num);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();

        style.imageDown = numberTexture.tint(Color.GRAY);
        style.imageUp = numberTexture.tint(levelIconTint);

        style.up = btnIconTexture;
        style.down = btnIconTextureClicked;

        LevelBtn btn = new LevelBtn(style);

        btn.setSize(btnSize, btnSize);

        btn.levelIdx = num - 1;

        btn.addListener(clickEvent);

        return btn;
    }
}
