package com.core.screens.subscreen.play.levelselect.core.btn.event;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.core.btnevents.ButtonClickWithFx;
import com.core.screens.subscreen.play.level.LevelScreen;
import com.core.screens.subscreen.play.levelselect.core.btn.LevelBtn;
import com.core.screens.subscreen.play.levelselect.core.factory.LevelFactory;
import com.core.screens.util.transition.SlideOutScreen;
import com.main.Main;

public class ClickedEvent extends ButtonClickWithFx {

    private final LevelFactory levelFactory;
    private final Main main;

    public ClickedEvent(Main main, String levelCategoryName) {
        super(BtnSfxId.FX1);
        this.main = main;
        this.levelFactory = new LevelFactory(main, levelCategoryName);
    }

    @Override
    public void touchDownAfterFx(InputEvent event, float x, float y, int pointer, int button) {
        main.getScreen().pause();
    }

    @Override
    public void firstTouchUp(InputEvent event, float x, float y, int pointer, int button) {
        LevelBtn btn = (LevelBtn) event.getListenerActor();

        if (((LevelBtn) event.getListenerActor()).isDisabled()) return;
        try {
            main.screenManager.switchScreens(createLevel(btn.levelIdx), SlideOutScreen.SlideDirection.LEFT);
        } catch (IndexOutOfBoundsException e) {
            return;
        }

    }

    private LevelScreen createLevel(int idx) throws IndexOutOfBoundsException {
        return new LevelScreen(main, levelFactory.createLevel(idx));
    }
}
