package com.core.screens.subscreen.play.level.component.event;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.core.assets.sprites.GameAssets;
import com.core.screens.component.transition.SlideOutTransition;
import com.core.screens.mainscreen.MainMenuScreen;
import com.core.screens.subscreen.play.level.LevelScreen;
import com.core.screens.subscreen.play.level.component.screen.LevelCompleted;
import com.core.screens.subscreen.play.levelselect.component.factory.LevelFactory;
import com.main.Main;

public class LevelCompleteEvent implements LevelEventListener {

    private final Main main;
    private final LevelFactory levelFactory;
    private int levelIdx;
    private Dialog dialog;
    private LevelScreen screen;

    public LevelCompleteEvent(LevelScreen levelScreen, LevelFactory levelFactory) {
        this.screen = levelScreen;
        this.levelIdx = levelScreen.getLevel().getLevelIdx();
        this.levelFactory = levelFactory;
        this.main = levelScreen.getMain();
    }

    private void loadNextLevel(boolean levelFailed) {
        if (!levelFailed) {
            Screen nextScreen;
             if (!levelFactory.isValidLevelIdx(levelIdx + 1)) {
                 nextScreen = new MainMenuScreen(main);
             } else {
                 nextScreen = new LevelScreen(main, levelFactory.createLevel(++levelIdx), levelFactory);
             }
            main.screenManager.switchScreensDirectly(new LevelCompleted(main, nextScreen, levelFailed), SlideOutTransition.SlideDirection.LEFT);
        } else if (levelFailed) {
            LevelScreen nextScreen = new LevelScreen(main, levelFactory.createLevel(levelIdx), levelFactory);
            main.screenManager.switchScreensDirectly(new LevelCompleted(main,nextScreen, levelFailed), SlideOutTransition.SlideDirection.LEFT);
        }
        if (levelFailed)
            (GameAssets.getInstance().assetManager.get("audio/failfx.ogg", Sound.class)).play();
        else
            (GameAssets.getInstance().assetManager.get("audio/winfx.ogg", Sound.class)).play();
    }

    @Override
    public void onLevelComplete(boolean levelFailed) {
        loadNextLevel(levelFailed);
        // screen.getLineManager().pauseRenderering();
        // Gdx.graphics.requestRendering();
        // screen.getStage().addActor(dialog);
    }
}
