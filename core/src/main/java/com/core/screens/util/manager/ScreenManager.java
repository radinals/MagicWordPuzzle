package com.core.screens.util.manager;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.core.screens.mainscreen.MainMenuScreen;
import com.core.screens.subscreen.learn.LearningScreen;
import com.core.screens.subscreen.play.category.CategorySelectScreen;
import com.core.screens.subscreen.play.levelselect.LevelSelectScreen;
import com.core.screens.util.transition.SlideOutScreen;
import com.main.Main;

import java.util.Stack;

public class ScreenManager {
    private final static float TRANSITION_DURATION = 0.55f;
    private final Stack<Screen> screenHistory;
    private final Main main;

    public ScreenManager(Main main) {
        this.main = main;
        this.screenHistory = new Stack<>();
    }

    public void switchScreens(Screen newScreen, SlideOutScreen.SlideDirection direction) {
        screenHistory.push(main.getScreen());
        main.render();
        main.setScreen(new SlideOutScreen(main, main.getScreen(), newScreen, TRANSITION_DURATION, direction));
    }

    public void switchScreensDirectly(Screen newScreen, SlideOutScreen.SlideDirection direction) {
        main.setScreen(new SlideOutScreen(main, main.getScreen(), newScreen, TRANSITION_DURATION, direction));
    }

    public void loadLastScreen() {
        if (screenHistory.isEmpty()) return;
        int sz = screenHistory.size();
        Screen lastScreen = screenHistory.pop();
        main.setScreen(new SlideOutScreen(main, main.getScreen(), lastScreen, TRANSITION_DURATION, (lastScreen instanceof MainMenuScreen) || (sz > 1) ? SlideOutScreen.SlideDirection.RIGHT : SlideOutScreen.SlideDirection.LEFT));
        lastScreen.dispose();
    }

    public void loadCategorySelectScreen() {
        switchScreens(new CategorySelectScreen(main), SlideOutScreen.SlideDirection.LEFT);
    }

    public void loadLevelSelectScreen(String levelname, int levelCount, Texture bgImage) {
        main.gameConfig.getCategories().values().forEach(category -> {
            if (category.getCategoryName().equals(levelname)) {
                switchScreens(new LevelSelectScreen(main, levelname, levelCount, category.getLevelIconColor(), bgImage), SlideOutScreen.SlideDirection.LEFT);
            }
        });
    }

    public void loadMainMenuScreen() {
        switchScreens(new MainMenuScreen(main), SlideOutScreen.SlideDirection.RIGHT);
    }

    public void loadLearnScreen() {
        switchScreens(new LearningScreen(main), SlideOutScreen.SlideDirection.LEFT);
    }
}
