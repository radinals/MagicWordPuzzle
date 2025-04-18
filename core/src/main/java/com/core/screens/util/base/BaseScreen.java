package com.core.screens.util.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.main.Main;

public class BaseScreen implements Screen {

    protected Main main;
    protected Stage stage;

    protected Texture bgImage;

    public BaseScreen(Main main) {
        this.main = main;
        ScreenViewport viewport = new ScreenViewport();
        viewport.setWorldSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.stage = new Stage(viewport);
        this.bgImage = main.gameAssets.assetManager.get("mainbg.png", Texture.class);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        main.getBatch().begin();
        main.getBatch().draw(
            bgImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()
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
            disableAllButtons(actor);
        }
        Gdx.graphics.requestRendering();
    }

    @Override
    public void resume() {
        for (Actor actor : stage.getActors()) {
            enableAllButtons(actor);
        }
        Gdx.graphics.requestRendering();
    }

    private void disableAllButtons(Actor actor) {
        if (actor instanceof Button) {
            actor.setTouchable(Touchable.disabled);
            Gdx.graphics.requestRendering();
        }

        if (actor instanceof Group group) {
            for (Actor child : group.getChildren()) {
                disableAllButtons(child);
            }
        }
    }

    private void enableAllButtons(Actor actor) {
        if (actor instanceof Button) {
            actor.setTouchable(Touchable.enabled);
            Gdx.graphics.requestRendering();
        }

        if (actor instanceof Group) {
            for (Actor child : ((Group) actor).getChildren()) {
                enableAllButtons(child);
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
