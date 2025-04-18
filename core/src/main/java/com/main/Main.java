package com.main;

import com.android.AndroidActions;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.core.data.assets.sprites.GameAssets;
import com.core.data.game.manager.GameConfig;
import com.core.screens.util.loading.LoadingScreen;
import com.core.screens.util.manager.ScreenManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private SpriteBatch batch;


    private AndroidActions androidActions;

    public SpriteBatch getBatch() {
        return batch;
    }

    private final static float TRANSITION_DURATION = 0.55f;

    public GameAssets gameAssets;
    public GameConfig gameConfig;

    public Main(AndroidActions androidActions) {
        this.androidActions = androidActions;
    }

    public void openVolumeSettings() {
        this.androidActions.openVolumeSettings();
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }

    public ScreenManager screenManager;

    @Override
    public void create() {
        Gdx.graphics.setContinuousRendering(false);
        this.batch = new SpriteBatch();
        this.screenManager = new ScreenManager(this);
        this.setScreen(new LoadingScreen(this));
    }

    public GameAssets getBaseAssets() {
        return gameAssets;
    }

    @Override
    public void setScreen(Screen screen) {
        Gdx.graphics.requestRendering();
        super.setScreen(screen);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}
