package com.core.screens.component.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.core.assets.sprites.GameAssets;
import com.main.Main;

public abstract class BaseScreen implements Screen {

    protected Main main;
    protected Stage stage;
    private Image bgImage;

    public BaseScreen(Main main) {
        this.main = main;
        setBgImageFile("mainbg.png");
        ScreenViewport viewport = new ScreenViewport();
        viewport.setWorldSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.stage = new Stage(viewport);
        this.stage.addActor(bgImage);
    }

    public Main getMain() {
        return main;
    }

    public Stage getStage() {
        return stage;
    }


    public void setBgImageFile(String bgImageFile) {
        TextureRegionDrawable tmp = new TextureRegionDrawable(GameAssets.getInstance().assetManager.get(bgImageFile, Texture.class));
        tmp.setMinSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        if (this.bgImage == null)
            this.bgImage = new Image(tmp);
        else
            this.bgImage.setDrawable(tmp);
        this.bgImage.setPosition(0,0);
        this.bgImage.toBack();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
