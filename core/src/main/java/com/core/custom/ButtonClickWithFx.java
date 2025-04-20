package com.core.custom;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.core.data.assets.sprites.GameAssets;

public abstract class ButtonClickWithFx extends SingleClickInputListener {

    private final Sound fx;

    public ButtonClickWithFx(final BtnSfxId fxid) {
        super();

        switch (fxid) {
            case FX1 ->
                    fx = GameAssets.getInstance().assetManager.get("audio/fxpop1.wav", Sound.class);
            case FX2 ->
                    fx = GameAssets.getInstance().assetManager.get("audio/fxpop2.wav", Sound.class);
            default -> fx = null;
        }

    }

    ;

    @Override
    final public void firstTouchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (fx != null) fx.play();
        touchDownAfterFx(event, x, y, pointer, button);
    }

    public abstract void touchDownAfterFx(InputEvent event, float x, float y, int pointer, int button);

    public enum BtnSfxId {
        FX1, FX2
    }

}
