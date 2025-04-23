package com.core.screens.mainscreen.component.events.mainbtn;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.core.screens.component.events.ButtonClickWithFx;
import com.main.Main;

public class LearnBtnEvent extends ButtonClickWithFx {
    private final Main main;

    public LearnBtnEvent(Main main) {
        super(BtnSfxId.FX1);
        this.main = main;
    }

    @Override
    public void touchDownAfterFx(InputEvent event, float x, float y, int pointer, int button) {
        main.getScreen().pause();
    }

    @Override
    public void firstTouchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (((ImageButton) event.getListenerActor()).isDisabled()) return;
        main.screenManager.loadLearnScreen();
    }

}
