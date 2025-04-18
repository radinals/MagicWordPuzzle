package com.core.screens.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.main.Main;

public class BaseScreen implements Screen {

    protected Main main;
    protected Stage stage;

    public BaseScreen(Main main) {
        this.main = main;
        this.stage = new Stage(new ScreenViewport());

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        main.getBatch().begin();
        main.getBatch().draw(
            main.gameAssets.assetManager.get("mainbg.png", Texture.class),
            0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()
        );
        main.getBatch().end();
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        for (Actor actor : stage.getActors()) {
            if (actor instanceof Button btn) {
                btn.setDisabled(true);
            }
        }
    }

    @Override
    public void resume() {
        for (Actor actor : stage.getActors()) {
            if (actor instanceof Button btn && btn.isDisabled()) {
                btn.setDisabled(false);
            }
        }
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
