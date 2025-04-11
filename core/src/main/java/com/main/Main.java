package com.main;

import com.android.AndroidActions;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.screens.CategorySelectScreen;
import com.screens.GameplayScreen;
import com.screens.LearnScreen;
import com.screens.LevelSelectScreen;
import com.screens.MainMenuScreen;
import com.screens.SlideOutScreen;

import java.awt.Menu;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private SpriteBatch batch;

    private Texture backgroundImg;

    private AndroidActions androidActions;

    public SpriteBatch getBatch() {
        return batch;
    }

    private final static float TRANSITION_DURATION = 0.5f;

    // FIXME: WONT WORK IF VISITED SCREENS > 1 DEPTH;
    private Screen lastScreen;

    public Main(AndroidActions androidActions) {
        this.androidActions = androidActions;
    }

    public void openVolumeSettings() {
        this.androidActions.openVolumeSettings();
    }

    @Override
    public void create() {
        this.backgroundImg = new Texture(Gdx.files.internal("mainbg.png"));
        this.backgroundImg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.lastScreen = null;

        this.setScreen(new MainMenuScreen(this));

        this.batch = new SpriteBatch();
    }

    @Override
    public void setScreen(Screen screen) {
        if (getScreen() != null && !(getScreen() instanceof SlideOutScreen)) lastScreen = getScreen();
        super.setScreen(screen);
    }

    private void switchScreens(Screen screen, SlideOutScreen.SlideDirection direction) {
        this.setScreen(new SlideOutScreen(this, getScreen(), screen, TRANSITION_DURATION, direction));
    }

    public void loadLastScreen() {
        if (!hasLastScreen()) return;
        switchScreens(lastScreen,
            (lastScreen instanceof  MainMenuScreen) ?
                SlideOutScreen.SlideDirection.RIGHT : SlideOutScreen.SlideDirection.LEFT);
    }

    public boolean hasLastScreen() {
        return this.lastScreen != null;
    }

    public Texture getBackgroundImg() {
        return backgroundImg;
    }

    public void loadCategorySelectScreen() {
        switchScreens(new CategorySelectScreen(this), SlideOutScreen.SlideDirection.LEFT);
    }

    public void loadLevelSelectScreen() {
        switchScreens(new LevelSelectScreen(this), SlideOutScreen.SlideDirection.LEFT);
    }

    public void loadMainMenuScreen() {
        switchScreens(new MainMenuScreen(this), SlideOutScreen.SlideDirection.RIGHT);
        this.lastScreen = null;
    }

    public void loadGameplayScreen() {
        switchScreens(new GameplayScreen(this), SlideOutScreen.SlideDirection.LEFT);
    }

    public void loadLearnScreen() {
        switchScreens(new LearnScreen(this), SlideOutScreen.SlideDirection.LEFT);
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
