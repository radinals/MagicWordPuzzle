package com.core.screens.component.events;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public abstract class SingleClickInputListener extends ClickListener {
    @Override
    final public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pointer == 0 && pointInButtonArea(event.getListenerActor(), event.getStageX(), event.getStageY())) {
            firstTouchDown(event, x, y, pointer, button);
        }
        return pointer == 0;
    }

    @Override
    final public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (pointer != 0) return;
        firstTouchUp(event, x, y, pointer, button);
        super.touchUp(event, x, y, pointer, button);
    }

    public abstract void firstTouchDown(InputEvent event, float x, float y, int pointer, int button);

    public abstract void firstTouchUp(InputEvent event, float x, float y, int pointer, int button);

    private boolean pointInButtonArea(Actor btn, float x, float y) {
        Vector2 local = btn.stageToLocalCoordinates(new Vector2(x, y));
        return (btn.hit(local.x, local.y, true) != null);
    }

}
