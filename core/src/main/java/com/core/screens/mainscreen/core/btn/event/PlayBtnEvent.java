package com.core.screens.mainscreen.core.btn.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.main.Main;

public class PlayBtnEvent extends ClickListener {

    private final Main main;

    public PlayBtnEvent(Main main) {
        this.main = main;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        main.getScreen().pause();
        return pointer == 0;
    }


    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (pointer != 0) return;
        if (((ImageButton) event.getListenerActor()).isDisabled()) return;
        main.screenManager.loadCategorySelectScreen();
        Gdx.graphics.requestRendering();
        super.touchUp(event, x, y, pointer, button);
    }
}
