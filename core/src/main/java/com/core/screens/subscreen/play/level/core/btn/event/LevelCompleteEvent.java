package com.core.screens.subscreen.play.level.core.btn.event;

import com.core.screens.subscreen.play.level.LevelScreen;
import com.core.screens.subscreen.play.levelselect.core.factory.LevelFactory;
import com.core.screens.util.transition.SlideOutScreen;
import com.main.Main;

public class LevelCompleteEvent implements LevelEventListener {

    private final Main main;
    private int levelIdx;
    private final LevelFactory levelFactory;

    public LevelCompleteEvent(Main main, LevelFactory levelFactory, int currentLevelIdx) {
        this.levelIdx = currentLevelIdx;
        this.levelFactory = levelFactory;
        this.main = main;
    }

    private void loadNextLevel() {
        if (levelFactory.isValidLevelIdx(++levelIdx)) {
            main.screenManager.switchScreensDirectly(
                new LevelScreen(main, levelFactory.createLevel(levelIdx)),
                SlideOutScreen.SlideDirection.LEFT
            );
        } else {
            main.screenManager.loadMainMenuScreen();
        }
    }

    @Override
    public void onLevelComplete() {
        loadNextLevel();
    }
}
