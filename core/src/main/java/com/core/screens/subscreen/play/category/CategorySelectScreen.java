package com.core.screens.subscreen.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.data.game.container.CategoryData;
import com.core.screens.base.BaseSubScreen;
import com.main.Main;

public class CategorySelectScreen extends BaseSubScreen {

    private final static int CATEGORYCOLS = 2;
    private final Table table;
    private float categoryRowPadding;
    private float categoryColumnPadding;
    private float categoryButtonHeight;
    private float categoryButtonWidth;

    private final TextureRegionDrawable categoryButtonBg;

    public CategorySelectScreen(Main main) {
        super(main);
        this.table = new Table();
        this.table.setDebug(true);
        this.table.setPosition(
            Gdx.graphics.getWidth() / 2 - table.getWidth() / 2,
            Gdx.graphics.getHeight() / 2 - table.getHeight() / 2
        );

        calculateSizes();

        {
            Texture texture = new Texture(Gdx.files.internal("test.png"));
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            this.categoryButtonBg = new TextureRegionDrawable(texture);
            this.categoryButtonBg.setMinSize(this.categoryButtonWidth, this.categoryButtonHeight);
        }

        loadCategories();
        super.stage.addActor(table);
    }

    private void calculateSizes() {
        float sw = Gdx.graphics.getWidth(), sh = Gdx.graphics.getHeight();
        this.categoryButtonWidth = sw * 0.40f;
        //this.categoryButtonHeight = sh * 0.23f;
        this.categoryButtonHeight = this.categoryButtonWidth;
        this.categoryRowPadding = this.categoryButtonHeight * 0.1f;
        this.categoryColumnPadding = this.categoryButtonWidth * 0.1f;
    }

    private void addCategoryButton(Texture iconTexture, String levelname, int levelCount) {

        iconTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegionDrawable trd = new TextureRegionDrawable(iconTexture);
        trd.setMinSize(this.categoryButtonWidth, this.categoryButtonHeight);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();

        style.up = style.down = categoryButtonBg;

        style.imageUp = trd;

        style.imageDown = trd;

        ImageButton btn = new ImageButton(trd);
        btn.setSize(this.categoryButtonWidth, this.categoryButtonHeight);

        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (((ImageButton) event.getListenerActor()).isDisabled()) return;
                main.screenManager.loadLevelSelectScreen(levelname, levelCount);
                Gdx.graphics.requestRendering();
            }
        });

        table.add(btn).size(this.categoryButtonWidth, this.categoryButtonHeight)
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
            addCategoryButton(category.getCategoryIcon(), category.getCategoryName(), category.getLevelCount());
            if (++i % 2 == 0)
                table.row();

        }
    }

}
