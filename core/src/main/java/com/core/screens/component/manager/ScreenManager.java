package com.core.screens.component.manager;

import com.badlogic.gdx.Screen;
import com.core.screens.component.transition.SlideOutTransition;
import com.core.screens.component.transition.SlidingRevealTransition;
import com.core.screens.mainscreen.MainMenuScreen;
import com.core.screens.subscreen.learn.LearningScreen;
import com.core.screens.subscreen.play.category.CategorySelectScreen;
import com.core.screens.subscreen.play.levelselect.LevelSelectScreen;
import com.main.Main;

import java.util.Stack;

public class ScreenManager {
    private final static float TRANSITION_DURATION = 0.80f;
    private final Stack<Screen> screenHistory;
    private final Main main;

    public ScreenManager(Main main) {
        this.main = main;
        this.screenHistory = new Stack<>();
    }

    public void switchScreens(Screen newScreen, SlideOutTransition.SlideDirection direction) {
        screenHistory.push(main.getScreen());
        main.render();
        main.setScreen(new SlidingRevealTransition(main, main.getScreen(), newScreen, TRANSITION_DURATION, direction));
    }

    public void switchScreensDirectly(Screen newScreen, SlideOutTransition.SlideDirection direction) {
        main.setScreen(new SlidingRevealTransition(main, main.getScreen(), newScreen, TRANSITION_DURATION, direction));
    }

    public void loadLastScreen() {
        if (screenHistory.isEmpty()) return;
        int sz = screenHistory.size();
        Screen lastScreen = screenHistory.pop();
        Screen currentScreen = main.getScreen();
        main.setScreen(new SlidingRevealTransition(main, currentScreen, lastScreen, TRANSITION_DURATION, (lastScreen instanceof MainMenuScreen) || (sz > 1) ? SlideOutTransition.SlideDirection.RIGHT : SlideOutTransition.SlideDirection.LEFT));
    }

    public void loadCategorySelectScreen() {
        switchScreens(new CategorySelectScreen(main), SlideOutTransition.SlideDirection.LEFT);
    }

    public void loadLevelSelectScreen(String levelname, int levelCount, String bgImageFile) {
        main.gameData.getCategories().values().forEach(category -> {
            if (category.getCategoryName().equals(levelname)) {
                switchScreens(new LevelSelectScreen(main, levelname, levelCount, category.getLevelIconColor(), bgImageFile), SlideOutTransition.SlideDirection.LEFT);
            }
        });
    }

    public void loadMainMenuScreen() {
        switchScreens(new MainMenuScreen(main), SlideOutTransition.SlideDirection.RIGHT);
    }

    public void loadLearnScreen() {
        switchScreens(new LearningScreen(main), SlideOutTransition.SlideDirection.LEFT);
    }
}
