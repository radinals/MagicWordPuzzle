package com.core.screens.mainscreen.core.btn.event;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.main.Main;

public class ShareBtnEvent extends ClickListener {
    private Main main;

    public ShareBtnEvent(Main main) {
        this.main = main;
    }
}
