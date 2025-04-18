package com.core.screens.subscreen.play.category;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.data.game.container.CategoryData;
import com.core.screens.util.base.BaseSubScreen;
import com.main.Main;

public class CategorySelectScreen extends BaseSubScreen {

    private final Table table;
    private float categoryRowPadding;
    private float categoryColumnPadding;
    private float categoryButtonSize;

    public CategorySelectScreen(Main main) {
        super(main);
        this.table = new Table();
        this.table.setDebug(true);
        this.table.setPosition(
            (float)Gdx.graphics.getWidth() / 2 - table.getWidth() / 2,
            (float)Gdx.graphics.getHeight() / 2 - table.getHeight() / 2
        );

        calculateSizes();

        loadCategories();
        super.stage.addActor(table);
    }

    private void calculateSizes() {
        float sw = Gdx.graphics.getWidth();
        this.categoryButtonSize = sw * 0.40f;
        this.categoryRowPadding = this.categoryButtonSize * 0.1f;
        this.categoryColumnPadding = this.categoryButtonSize * 0.1f;
    }

    private void addCategoryButton(Texture iconTexture, String levelname, int levelCount, Texture bgImage) {

        iconTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegionDrawable trd = new TextureRegionDrawable(iconTexture);
        trd.setMinSize(this.categoryButtonSize, this.categoryButtonSize);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();

        style.up = trd;
        style.down = trd.tint(Color.GRAY);
        style.imageChecked = null;
        style.checked = null;

        ImageButton btn = new ImageButton(style);
        btn.setSize(this.categoryButtonSize, this.categoryButtonSize);

        btn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                CategorySelectScreen.super.pause();
                return pointer == 0;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (pointer != 0) return;
                ImageButton btn = (ImageButton) event.getListenerActor();
                if (!btn.isDisabled()) {
                    main.screenManager.loadLevelSelectScreen(levelname, levelCount, bgImage);
                    Gdx.graphics.requestRendering();
                }
                super.touchUp(event, x, y, pointer, button);
            }
        });

        table.add(btn).size(this.categoryButtonSize, this.categoryButtonSize)
            .pad(
                categoryRowPadding / 2,
                categoryColumnPadding / 2,
                categoryRowPadding / 2,
                categoryColumnPadding / 2)
        ;
    }

    private void loadCategories() {
        int i = 0;
        for (CategoryData category : main.getGameConfig().getCategories().values()) {
            addCategoryButton(category.getCategoryIcon(), category.getCategoryName(), category.getLevelCount(), category.getCategoryBackgroundImage());
            if (++i % 2 == 0)
                table.row();

        }
    }

}
