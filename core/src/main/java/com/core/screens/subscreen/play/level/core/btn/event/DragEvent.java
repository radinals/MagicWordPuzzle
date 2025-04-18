package com.core.screens.subscreen.play.level.core.btn.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.core.screens.subscreen.play.level.core.Level;
import com.core.screens.subscreen.play.level.core.btn.group.ButtonPair;
import com.core.screens.subscreen.play.level.core.lines.LineManager;

import java.util.Stack;

public class DragEvent extends DragListener {

    private final static Stack<Button> linedButtons = new Stack<>();
    private static int answCounter = 0;
    private final Level level;
    private ButtonPair dragStartPair;

    public DragEvent(Level level) {
        this.level = level;
    }

    @Override
    public void dragStart(InputEvent event, float x, float y, int pointer) {
        Button btn = (Button) event.getListenerActor();

        if (linedButtons.contains(btn)) return;

        boolean found = false;
        for (ButtonPair pair : level.getLevelObjects()) {
            if (pair.imgButton.equals(btn) || pair.wordButton.equals(btn)) {
                this.dragStartPair = pair;
                found = true;
                break;
            }
        }


        if (found) {
            this.level.getLineManager().setActiveLineStart(event.getStageX(), event.getStageY());
            Gdx.graphics.requestRendering();
        }


    }

    @Override
    public void drag(InputEvent event, float x, float y, int pointer) {
        this.level.getLineManager().setActiveLineEnd(event.getStageX(), event.getStageY());
        Gdx.graphics.requestRendering();
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer) {
        final Button dragSource = (Button) event.getListenerActor();

        // TODO:
        // CHECK IF DRAG STOP IS ON TOP A VALID BUTTON
        // IF TRUE: STORE THE DRAG BUTTON, MARK START & END BTN AS LINED
        // IF FALSE: RETURN

        boolean alignedCorrectly = false;
        boolean found = false;
        for (ButtonPair pair : level.getLevelObjects()) {
            if (dragSource instanceof ImageButton && pointInButtonArea(pair.wordButton, event.getStageX(), event.getStageY())) {
                if (linedButtons.contains(pair.wordButton) || linedButtons.contains(dragSource)) {
                    level.getLineManager().resetActiveLine();
                    return;
                }
                linedButtons.add(pair.wordButton);
                linedButtons.add(dragSource);
                alignedCorrectly = (pair.equals(dragStartPair));
                found = true;
                break;
            } else if (dragSource instanceof TextButton && pointInButtonArea(pair.imgButton, event.getStageX(), event.getStageY())) {
                if (linedButtons.contains(pair.imgButton) || linedButtons.contains(dragSource)) {
                    level.getLineManager().resetActiveLine();
                    return;
                }
                linedButtons.add(pair.imgButton);
                linedButtons.add(dragSource);
                alignedCorrectly = (pair.equals(dragStartPair));
                found = true;
                break;
            }
        }

        this.level.getLineManager().setActiveLineEnd(event.getStageX(), event.getStageY());

        if (found) {
            this.level.getLineManager().storeCurrentActiveLine((alignedCorrectly) ? LineManager.LineType.CorrectLine : LineManager.LineType.IncorrectLine);
            answCounter++;

            if (answCounter >= level.getLevelObjects().size()) {
                level.notifyListener("LevelComplete");
                answCounter = 0;
            }
        }

        level.getLineManager().resetActiveLine();

        Gdx.graphics.requestRendering();

    }

    private boolean pointInButtonArea(Button btn, float x, float y) {
        Vector2 local = btn.stageToLocalCoordinates(new Vector2(x, y));
        return (btn.hit(local.x, local.y, true) != null);
    }
}
