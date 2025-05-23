package com.main;

import com.android.AndroidActions;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.core.assets.sprites.GameAssets;
import com.core.model.GameData;
import com.core.screens.LoadingScreen;
import com.core.screens.component.manager.ScreenManager;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends Game {

    public GameData gameData;
    public ScreenManager screenManager;
    private SpriteBatch batch;
    private Music backgroundMusic;
    private AndroidActions androidActions;

    public Main(AndroidActions androidActions) {
        this.androidActions = androidActions;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void loadBackgroundMusic() {
        this.backgroundMusic = GameAssets.getInstance().assetManager.get("audio/bgmusic.ogg", Music.class);
        this.backgroundMusic.setVolume(0.08f);
        this.backgroundMusic.setLooping(true);
        this.backgroundMusic.play();
    }

    public void toggleBackgroundMusic() {
        if(this.backgroundMusic.isPlaying())
            this.backgroundMusic.stop();
        else
            this.backgroundMusic.play();
    }

    public void openVolumeSettings() {
        this.androidActions.openVolumeSettings();
    }

    public GameData getGameConfig() {
        return gameData;
    }

    @Override
    public void create() {
        Gdx.graphics.setContinuousRendering(false);
        this.batch = new SpriteBatch();
        this.screenManager = new ScreenManager(this);
        this.setScreen(new LoadingScreen(this));
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
