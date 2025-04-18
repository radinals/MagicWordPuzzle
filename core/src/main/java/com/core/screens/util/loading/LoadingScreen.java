package com.core.screens.util.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.core.data.assets.animation.SpriteAnimation;
import com.core.data.assets.sprites.GameAssets;
import com.core.data.game.manager.GameConfig;
import com.core.screens.mainscreen.MainMenuScreen;
import com.main.Main;

public class LoadingScreen implements Screen {

    private final Texture texture;
    private final SpriteAnimation loadingText;
    private final static float BASE_LOADING_SCREEN_TIME = 2f;
    public Main main;
    private boolean loadingFinished;
    private float loadingTimer = 0f;
    private GameAssets assets;

    public LoadingScreen(Main main) {
        Gdx.graphics.setContinuousRendering(true);
        this.main = main;
        this.texture = new Texture(Gdx.files.internal("loading.png"));
        this.loadingText = new SpriteAnimation("loadingtext.png",  4, 0.1f);
    }

    @Override
    public void show() {
        this.assets = new GameAssets("data/assetConfig.xml");
        this.main.gameConfig = new GameConfig("data/config.xml");
    }

    @Override
    public void render(float delta) {
        TextureRegion frame = loadingText.getFrame(delta, true);
        float frameH = Gdx.graphics.getHeight() * 0.05f;
        float frameW = Gdx.graphics.getWidth() * 0.6f;
        float centerX = (float)Gdx.graphics.getWidth() / 2;
        float centerY = (float)Gdx.graphics.getHeight() / 2;

        main.getBatch().begin();
        main.getBatch().draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        main.getBatch().draw(frame, centerX - frameW / 2, centerY - frameH / 2, frameW, frameH);
        main.getBatch().end();

        loadingTimer += delta;

        if (this.assets.assetManager.update()) {
            loadingFinished = true;
        }

        if (loadingFinished && loadingTimer >= BASE_LOADING_SCREEN_TIME) {
            Gdx.graphics.setContinuousRendering(false);
            Gdx.graphics.requestRendering();
            main.gameAssets = assets;
            main.setScreen(new MainMenuScreen(main));
        }
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
