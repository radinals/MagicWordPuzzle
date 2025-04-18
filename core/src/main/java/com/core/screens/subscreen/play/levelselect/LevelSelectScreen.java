package com.core.screens.subscreen.play.levelselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.core.screens.subscreen.play.levelselect.core.factory.CategoryButtonFactory;
import com.core.screens.util.base.BaseSubScreen;
import com.main.Main;

public class LevelSelectScreen extends BaseSubScreen {

    private final Table levelButtons;
    private final Table rootTable;
    private final int levelCount;
    private float btnPaddingH;
    private float btnPaddingV;
    private final CategoryButtonFactory buttonFactory;

    public LevelSelectScreen(Main main, String levelCategoryName, int levelCount, Color levelIconColor, Texture bgImage) {
        super(main);
        super.bgImage = bgImage;
        this.buttonFactory = new CategoryButtonFactory(main, levelIconColor, levelCategoryName);
        this.levelCount = levelCount;
        this.levelButtons = new Table();
        this.rootTable = new Table();
        this.rootTable.setDebug(true);
        this.rootTable.top();
        calculateSizes();
        ScrollPane scrollPane = new ScrollPane(levelButtons);
        scrollPane.setScrollingDisabled(true, false);
        this.rootTable.add(scrollPane);
        loadLevel();
        super.stage.addActor(rootTable);
    }

    private void calculateSizes() {
        this.rootTable.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.6f);
        this.levelButtons.setSize(Gdx.graphics.getWidth() * 0.9f, Gdx.graphics.getHeight() * 0.6f);
        this.rootTable.setPosition(
            (float)Gdx.graphics.getWidth() / 2 - rootTable.getWidth() / 2,
            Gdx.graphics.getHeight() * 0.2f
        );
        this.btnPaddingH = Gdx.graphics.getWidth() * 0.05f;
        this.btnPaddingV = Gdx.graphics.getWidth() * 0.01f;

        buttonFactory.setBtnSize((this.levelButtons.getWidth() / 3) - (btnPaddingV + btnPaddingH));
    }


    public void loadLevel() {
        for (int i = 1; i <= levelCount; i++) {
            levelButtons.add(buttonFactory.createLevelButton(i)).pad(btnPaddingV, btnPaddingH, btnPaddingV, btnPaddingH);
            if (i % 3 == 0) levelButtons.row();
        }
    }

}
