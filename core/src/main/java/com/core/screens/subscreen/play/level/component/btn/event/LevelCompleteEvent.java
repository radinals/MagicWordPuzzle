package com.core.screens.subscreen.play.level.component.btn.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.core.screens.subscreen.play.level.LevelScreen;
import com.core.screens.subscreen.play.levelselect.component.factory.LevelFactory;
import com.core.screens.component.transition.SlideOutScreen;
import com.main.Main;

public class LevelCompleteEvent implements LevelEventListener {

    private final Main main;
    private final LevelFactory levelFactory;
    private int levelIdx;
    private static Sound levelCompleteFx = Gdx.audio.newSound(Gdx.files.internal("audio/levelcomplete.mp3"));

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
        levelCompleteFx.play();
    }

    @Override
    public void onLevelComplete() {
        loadNextLevel();
    }
}
