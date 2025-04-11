package com.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
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

public class GameScreen implements Screen {

    protected Main main;
    protected Stage stage;

    public GameScreen(Main main) {
        this.main = main;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        createBackButton();
    }

    private void createBackButton() {
        int backBtnWidth = 200;
        int backBtnHeight = 70;

        Texture backIconTexture = new Texture(Gdx.files.internal("arrow.png"));

        backIconTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegionDrawable backButtonIcon = new TextureRegionDrawable(backIconTexture);
        backButtonIcon.tint(Color.RED);

        backButtonIcon.setMinSize(backBtnWidth,backBtnHeight);

        ImageButton backButton = new ImageButton(backButtonIcon);

        backButton.setSize(backBtnWidth,backBtnHeight);

        backButton.setPosition(10, Gdx.graphics.getHeight() - (backBtnHeight + 10));

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.loadMainMenuScreen();
            }
        });

        this.stage.addActor(backButton);
    }

    @Override
    public void show() {
        Gdx.input.setCatchKey( Input.Keys.BACK, true);

        InputMultiplexer multiplexer = new InputMultiplexer();

        // First, your back key handler
        multiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.BACK) {
                    main.loadLastScreen();
                    return true;
                }
                return false;
            }
        });

        // Then the stage, so UI still works
        multiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(multiplexer);
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
