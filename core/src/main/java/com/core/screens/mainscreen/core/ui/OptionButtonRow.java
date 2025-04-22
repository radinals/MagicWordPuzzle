package com.core.screens.mainscreen.core.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.data.assets.sprites.GameAssets;
import com.core.screens.mainscreen.core.btn.event.optionbtn.GooglePlayBtnEvent;
import com.core.screens.mainscreen.core.btn.event.optionbtn.RateBtnEvent;
import com.core.screens.mainscreen.core.btn.event.optionbtn.ShareBtnEvent;
import com.core.screens.mainscreen.core.btn.event.optionbtn.VolumeBtnEvent;
import com.main.Main;

public class OptionButtonRow extends Table {

    private final float btnSize;
    private final float columnPadding;
    private final Main main;

    public OptionButtonRow(Main main, float buttonSize) {
        super();
        this.main = main;
        this.btnSize = buttonSize;
        this.columnPadding = buttonSize * 0.25f;

        //createButton("googleplayicon.png", new GooglePlayBtnEvent(main));
        createButton("volumeicon.png", new VolumeBtnEvent(main));
        //createButton("shareicon.png", new ShareBtnEvent(main));
        //createButton("stars.png", new RateBtnEvent(main));
    }

    private void createButton(String iconFile, ClickListener eventHandler) {
        TextureRegionDrawable icon = new TextureRegionDrawable(
            GameAssets.getInstance().assetManager.get(iconFile, Texture.class)
        );
        icon.setMinSize(btnSize, btnSize);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = icon;
        style.imageDown = icon.tint(Color.GRAY);

        ImageButton btn = new ImageButton(style);
        btn.setSize(btnSize, btnSize);

        btn.addListener(eventHandler);

        this.add(btn).size(btnSize).pad(0.0f, columnPadding / 2, 0, columnPadding / 2);
    }
}
