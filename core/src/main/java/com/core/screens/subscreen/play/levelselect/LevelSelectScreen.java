package com.core.screens.subscreen.play.levelselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.core.screens.component.base.BaseSubScreen;
import com.core.screens.subscreen.play.levelselect.component.factory.CategoryButtonFactory;
import com.main.Main;

public class LevelSelectScreen extends BaseSubScreen {

    private final Table levelButtons;
    private final Table rootTable;
    private final int levelCount;
    private final CategoryButtonFactory buttonFactory;
    private float btnPaddingH, btnPaddingV;

    public LevelSelectScreen(Main main, String levelCategoryName, int levelCount, Color levelIconColor, String bgImageFile) {
        super(main);
        //super.screenTitle.setText(levelCategoryName);
        super.setBgImageFile(bgImageFile);
        this.buttonFactory = new CategoryButtonFactory(main, levelIconColor, levelCategoryName);
        this.levelCount = levelCount;
        this.levelButtons = new Table();
        this.rootTable = new Table();
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
            (float) Gdx.graphics.getWidth() / 2 - rootTable.getWidth() / 2,
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

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }
}
