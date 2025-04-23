package com.core.screens.subscreen.play.category;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.screens.component.events.ButtonClickWithFx;
import com.core.assets.sprites.GameAssets;
import com.core.model.data.CategoryData;
import com.core.screens.component.base.BaseSubScreen;
import com.main.Main;

public class CategorySelectScreen extends BaseSubScreen {

    private final Table table;
    private float categoryRowPadding;
    private float categoryColumnPadding;
    private float categoryButtonSize;

    public CategorySelectScreen(Main main) {
        super(main);
        this.table = new Table();
        this.table.setPosition(
            (float) Gdx.graphics.getWidth() / 2 - table.getWidth() / 2,
            (float) Gdx.graphics.getHeight() / 2 - table.getHeight() / 2
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

    private void addCategoryButton(String iconImageFile, String levelname, int levelCount, String bgImageFile) {

        Texture iconTexture = GameAssets.getInstance().assetManager.get(iconImageFile, Texture.class);

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

        btn.addListener(new ButtonClickWithFx(ButtonClickWithFx.BtnSfxId.FX1) {
            @Override
            public void touchDownAfterFx(InputEvent event, float x, float y, int pointer, int button) {
                CategorySelectScreen.super.pause();
            }

            @Override
            public void firstTouchUp(InputEvent event, float x, float y, int pointer, int button) {

                ImageButton btn = (ImageButton) event.getListenerActor();
                if (!btn.isDisabled()) {
                    main.screenManager.loadLevelSelectScreen(levelname, levelCount, bgImageFile);
                    Gdx.graphics.requestRendering();
                }
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
            addCategoryButton(
                category.getCategoryIcon(), category.getCategoryName(),
                category.getLevelCount(), category.getCategoryBackgroundImage()
            );
            if (++i % 2 == 0)
                table.row();

        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }
}
