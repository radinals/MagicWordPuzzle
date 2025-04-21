package com.core.screens.mainscreen.core.btn.event.optionbtn;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.core.btnevents.ButtonClickWithFx;
import com.main.Main;

public class VolumeBtnEvent extends ButtonClickWithFx {
    private final Main main;

    public VolumeBtnEvent(Main main) {
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
