package com.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.screens.CategorySelectScreen;
import com.screens.GameplayScreen;
import com.screens.LearnScreen;
import com.screens.LevelSelectScreen;
import com.screens.MainMenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private SpriteBatch batch;

    private Texture backgroundImg;

    public SpriteBatch getBatch() {
        return batch;
    }

    @Override
    public void create() {
        this.backgroundImg = new Texture(Gdx.files.internal("mainbg.png"));
        this.backgroundImg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        setScreen(new MainMenuScreen(this));
        this.batch = new SpriteBatch();
    }

    public Texture getBackgroundImg() {
        return backgroundImg;
    }

    public void loadCategorySelectScreen() {
        this.setScreen(new CategorySelectScreen(this) );
    }

    public void loadLevelSelectScreen() {
        this.setScreen(new LevelSelectScreen(this));
    }

    public void loadMainMenuScreen() {
        this.setScreen(new MainMenuScreen(this));
    }

    public void loadGameplayScreen() {
        this.setScreen(new GameplayScreen(this));
    }

    public void loadLearnScreen() {
        this.setScreen(new LearnScreen(this));
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(backgroundImg,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
