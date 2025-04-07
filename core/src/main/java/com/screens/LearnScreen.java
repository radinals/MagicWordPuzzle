package com.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.main.Main;

public class LearnScreen implements Screen {

    private Main main;

    private Stage stage;
    private ImageButton backButton;

    public LearnScreen(Main main) {
        this.main = main;

        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        createBackButton();

        this.stage.addActor(backButton);
    }

    private void createBackButton() {
        int backBtnWidth = 150;
        int backBtnHeight = 50;

        Texture backIconTexture = new Texture(Gdx.files.internal("arrow.png"));

        backIconTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegionDrawable backButtonIcon = new TextureRegionDrawable(backIconTexture);
        backButtonIcon.tint(Color.RED);

        backButtonIcon.setMinSize(backBtnWidth,backBtnHeight);

        this.backButton = new ImageButton(backButtonIcon);

        this.backButton.setSize(backBtnWidth,backBtnHeight);

        this.backButton.setPosition(10, Gdx.graphics.getHeight() - (backBtnHeight + 10));

        this.backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.loadMainMenuScreen();
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw(); // Renders UI
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
