package com.core.screens.subscreen.learn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.data.game.container.CardData;
import com.core.screens.util.base.BaseSubScreen;
import com.main.Main;

public class LearningScreen extends BaseSubScreen {

    private final Table table;
    private float btnWidth;
    private float btnHeight;

    private Label label;

    private int idx = 0;

    public LearningScreen(Main main) {
        super(main);

        this.table = new Table();

        this.btnWidth = 0;
        this.btnHeight = 0;

        table.addListener(new ActorGestureListener() {
            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                if (x > (float) Gdx.graphics.getWidth() / 2) {
                    idx += 1;
                } else {
                    idx -= 1;
                }

                if (idx < 0) idx = main.getGameConfig().getCards().size() - 1;
                if (idx >= main.getGameConfig().getCards().size()) idx = 0;

                label.setText(main.getGameConfig().getCards().get(idx).getWord());
            }

            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {

                if (x > (float) Gdx.graphics.getWidth() / 2) {
                    idx += count;
                } else {
                    idx -= count;
                }

                if (idx < 0) idx = main.getGameConfig().getCards().size() - 1;
                if (idx >= main.getGameConfig().getCards().size()) idx = 0;

                label.setText(main.getGameConfig().getCards().get(idx).getWord());
            }
        });

        calculateSize();
        super.stage.addActor(table);
        loadCards();
    }

    private void addCard(String imgFile, String word) {
        table.add(createImg(imgFile));
        table.row();
        table.add(createLabel(word));

    }

    private void calculateSize() {
        this.table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.8f);
        this.table.setPosition((float) Gdx.graphics.getWidth() / 2 - this.table.getWidth() / 2, Gdx.graphics.getHeight() * 0.1f);
        this.table.setDebug(true);
        this.btnWidth = Gdx.graphics.getWidth() * 0.8f;
        this.btnHeight = Gdx.graphics.getHeight() * 0.7f;
    }

    private Label createLabel(String word) {

        Label.LabelStyle style = new Label.LabelStyle();

        // TODO: SETUP BUTTON BG
        style.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        style.font.getData().setScale(8f); // unreliable for UI
        style.fontColor = Color.BLACK;

        Label label = new Label(word, style);

        this.label = label;

        return label;
    }

    private Image createImg(String imgFile) {
        Texture texture = new Texture(Gdx.files.internal(imgFile));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegionDrawable drawable = new TextureRegionDrawable(texture);
        drawable.setMinSize(this.btnWidth, this.btnHeight);

        return new Image(drawable);
    }

    // IDEA: TODO:  OVERRIDING SINGLE BUTTON REPEATEDLY OR LOADING AT PRESS
    private void loadCards() {
        CardData card = main.getGameConfig().getCards().get(idx);
        addCard(card.getImgFile(), card.getWord());
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }
}
