package com.core.screens.mainscreen.component.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.screens.mainscreen.component.events.mainbtn.LearnBtnEvent;
import com.core.screens.mainscreen.component.events.mainbtn.PlayBtnEvent;
import com.main.Main;

public class MainGameButtons extends Table {

    private final float btnWidth;
    private final float btnHeight;

    public MainGameButtons(Main main, float btnWidth, float btnHeight) {
        super();
        this.btnWidth = btnWidth;
        this.btnHeight = btnHeight;

        createButton("startbtnicon.png", "startbtnicon_clicked.png", new PlayBtnEvent(main));
        createButton("learnbtnicon.png", "learnbtnicon_clicked.png", new LearnBtnEvent(main));
    }

    private void createButton(String iconFile, String clickedIconFile, ClickListener eventHandler) {
        Texture learnIconTexture = new Texture(Gdx.files.internal(iconFile));
        Texture learnIconClickedTexture = new Texture(Gdx.files.internal(clickedIconFile));

        learnIconTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        learnIconClickedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegionDrawable learnButtonIcon = new TextureRegionDrawable(learnIconTexture);
        TextureRegionDrawable learnButtonIconClicked = new TextureRegionDrawable(learnIconClickedTexture);

        learnButtonIcon.setMinSize(btnWidth, btnHeight);
        learnButtonIconClicked.setMinSize(btnWidth, btnHeight);

        ImageButton btn = new ImageButton(learnButtonIcon, learnButtonIconClicked);

        btn.setSize(btnWidth, btnHeight);

        btn.addListener(eventHandler);

        this.add(btn).padTop(btnHeight * 0.1f);
        this.row();
    }
}
