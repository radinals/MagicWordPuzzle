package com.core.screens.mainscreen.component.events.optionbtn;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.core.screens.component.events.ButtonClickWithFx;
import com.main.Main;

public class GooglePlayBtnEvent extends ButtonClickWithFx {
    private final Main main;

    public GooglePlayBtnEvent(Main main) {
        super(BtnSfxId.FX1);
        this.main = main;
    }

    @Override
    public void touchDownAfterFx(InputEvent event, float x, float y, int pointer, int button) {

    }

    @Override
    public void firstTouchUp(InputEvent event, float x, float y, int pointer, int button) {
    }
}
