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
import com.core.data.assets.sprites.GameAssets;
import com.main.Main;

public abstract class BaseScreen implements Screen {

    protected Main main;
    protected Stage stage;
    private Texture bgImage;

    public BaseScreen(Main main) {
        this.main = main;
        setBgImageFile("mainbg.png");
        ScreenViewport viewport = new ScreenViewport();
        viewport.setWorldSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.stage = new Stage(viewport);
    }

    public void setBgImageFile(String bgImageFile) {
        this.bgImage = GameAssets.getInstance().assetManager.get(bgImageFile, Texture.class);
    }

    @Override
    public void render(float delta) {
        main.getBatch().begin();
        main.getBatch().draw( bgImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        main.getBatch().end();
        stage.act(delta);
        stage.draw();

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
    public void dispose() {
        stage.dispose();
    }
}
