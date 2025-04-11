package com.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.main.Main;

public class CategorySelectScreen extends GameScreen {

    private Table table;

    private final static int CATEGORYCOLS = 2;
    private float categoryRowPadding;
    private float categoryColumnPadding;
    private float categoryButtonHeight;
    private float categoryButtonWidth;

    public CategorySelectScreen(Main main) {
        super(main);
        this.table = new Table();
        this.table.setDebug(true);
        table.setPosition(Gdx.graphics.getWidth()/2 - table.getWidth() / 2, Gdx.graphics.getHeight()/2 - table.getHeight()/2);
        calculateSizes();
        loadCategories();
        stage.addActor(table);
    }

    private void calculateSizes() {
        float sw = Gdx.graphics.getWidth(), sh = Gdx.graphics.getHeight();
        this.categoryRowPadding = sh * 0.1f;
        this.categoryColumnPadding = sw * 0.1f;
        this.categoryButtonHeight = sh * 0.2f;
        this.categoryButtonWidth = sw * 0.3f;
    }

    private void addCategoryButton(String iconFilename) {
        Texture t = new Texture(Gdx.files.internal(iconFilename));
        t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegionDrawable trd = new TextureRegionDrawable(t);
        trd.setMinSize(this.categoryButtonWidth,this.categoryButtonHeight);

        ImageButton btn = new ImageButton(trd);
        btn.setSize(this.categoryButtonWidth,this.categoryButtonHeight);

        table.add(btn).size(this.categoryButtonWidth,this.categoryButtonHeight).pad(categoryRowPadding/2, categoryColumnPadding/2, categoryRowPadding/2, categoryColumnPadding/2);
    }

    private void loadCategories() {
        FileHandle file = Gdx.files.internal("data/category/categories.xml");

        XmlReader reader = new XmlReader();
        try {
            XmlReader.Element root = reader.parse(file);

            int i = 0;
            for (XmlReader.Element category : root.getChildrenByName("category")) {
                String name = category.getAttribute("name", "Unknown");
                String icon = category.getAttribute("icon", "default.png");
                addCategoryButton(icon);
                i++;
                if (i >= 2) {
                    i = 0;
                    table.row();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

}
