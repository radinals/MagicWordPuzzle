package com.core.screens.subscreen.play.levelselect.core.btn.event;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.core.screens.subscreen.play.level.LevelScreen;
import com.core.screens.subscreen.play.levelselect.core.btn.LevelBtn;
import com.core.screens.subscreen.play.levelselect.core.factory.LevelFactory;
import com.core.screens.util.transition.SlideOutScreen;
import com.main.Main;

public class ClickedEvent extends ClickListener {

    private final LevelFactory levelFactory;
    private final Main main;

    public ClickedEvent(Main main, String levelCategoryName) {
        this.main = main;
        this.levelFactory = new LevelFactory(main, levelCategoryName);
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        main.getScreen().pause();
        return pointer == 0;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (pointer != 0) return;
        LevelBtn btn = (LevelBtn) event.getListenerActor();

        if (((LevelBtn) event.getListenerActor()).isDisabled()) return;
        try {
            main.screenManager.switchScreens(createLevel(btn.levelIdx), SlideOutScreen.SlideDirection.LEFT);
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        super.touchUp(event, x, y, pointer, button);
    }

    private LevelScreen createLevel(int idx) throws IndexOutOfBoundsException {
        return new LevelScreen(main, levelFactory.createLevel(idx));
    }
}
