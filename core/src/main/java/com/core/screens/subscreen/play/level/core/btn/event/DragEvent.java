package com.core.screens.subscreen.play.level.core.btn.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.screens.subscreen.play.level.core.Level;
import com.core.screens.subscreen.play.level.core.btn.group.ButtonPair;
import com.core.screens.subscreen.play.level.core.lines.Line;
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

    private static boolean lineConnectsTwoPair(ButtonPair pair, Line line) {
        Vector2 lineStart = line.first;
        Vector2 lineEnd = line.second;

        return (pointInButtonArea(pair.wordButton, lineStart.x, lineStart.y) &&
            pointInButtonArea(pair.imgButton, lineEnd.x, lineEnd.y)) || (pointInButtonArea(pair.imgButton, lineStart.x, lineStart.y) &&
            pointInButtonArea(pair.wordButton, lineEnd.x, lineEnd.y));
    }

    private static boolean pointInButtonArea(Button btn, float x, float y) {
        Vector2 local = btn.stageToLocalCoordinates(new Vector2(x, y));
        return (btn.hit(local.x, local.y, true) != null);
    }

    @Override
    public void dragStart(InputEvent event, float x, float y, int pointer) {
        if (pointer != 0) return;

        if (!Level.drawingLine)
            Level.drawingLine = true;

        Button btn = (Button) event.getListenerActor();

        if (linedButtons.contains(btn)) return;

        for (ButtonPair pair : level.getLevelObjects()) {
            if ((pair.imgButton.equals(btn) || pair.wordButton.equals(btn)) &&
                pointInButtonArea(btn, event.getStageX(), event.getStageY())) {
                this.dragStartPair = pair;
                this.level.getLineManager().setActiveLineStart(event.getStageX(), event.getStageY());
                if (this.level.getLineManager().isActiveLineFormed())
                    Gdx.graphics.requestRendering();
                return;
            }
        }
    }

    @Override
    public void drag(InputEvent event, float x, float y, int pointer) {
        if (pointer != 0) return;

        if (!Level.drawingLine)
            Level.drawingLine = true;

        this.level.getLineManager().setActiveLineEnd(event.getStageX(), event.getStageY());
        if (this.level.getLineManager().isActiveLineFormed())
            Gdx.graphics.requestRendering();
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer) {
        if (pointer != 0) return;

        final Button dragSource = (Button) event.getListenerActor();

        // TODO:
        // CHECK IF DRAG STOP IS ON TOP A VALID BUTTON
        // IF TRUE: STORE THE DRAG BUTTON, MARK START & END BTN AS LINED
        // IF FALSE: RETURN

        this.level.getLineManager().setActiveLineEnd(event.getStageX(), event.getStageY());

        if (!this.level.getLineManager().isActiveLineFormed()) {
            this.level.getLineManager().resetActiveLine();
            return;
        }
        ;

        boolean alignedCorrectly = false;
        boolean found = false;

        Button dragEnd = null;

        for (ButtonPair pair : level.getLevelObjects()) {
            if (dragSource instanceof ImageButton &&
                lineConnectsTwoPair(new ButtonPair(pair.wordButton, dragStartPair.imgButton), level.getLineManager().getActiveLine()) &&
                !linedButtons.contains(pair.wordButton) && !linedButtons.contains(dragSource)) {
                linedButtons.add(pair.wordButton);
                linedButtons.add(dragSource);
                alignedCorrectly = (pair.equals(dragStartPair));
                dragEnd = pair.wordButton;
                found = true;
                break;

            } else if (dragSource instanceof TextButton &&
                lineConnectsTwoPair(new ButtonPair(dragStartPair.wordButton, pair.imgButton), level.getLineManager().getActiveLine()) &&
                !linedButtons.contains(pair.imgButton) && !linedButtons.contains(dragSource)) {
                linedButtons.add(pair.imgButton);
                linedButtons.add(dragSource);
                alignedCorrectly = (pair.equals(dragStartPair));
                dragEnd = pair.imgButton;
                found = true;
                break;
            }
        }

        if (found) {
            LineManager.LineType linetype;
            if (alignedCorrectly) {
                linetype = LineManager.LineType.CorrectLine;
                TextureRegionDrawable tr_end = (TextureRegionDrawable) dragEnd.getStyle().up;
                TextureRegionDrawable tr_start = (TextureRegionDrawable) dragSource.getStyle().up;

                dragEnd.getStyle().up = tr_end.tint(Color.GREEN);
                dragSource.getStyle().up = tr_start.tint(Color.GREEN);
                dragEnd.getStyle().down = tr_end.tint(Color.GREEN);
                dragSource.getStyle().down = tr_start.tint(Color.GREEN);

            } else {
                linetype = LineManager.LineType.IncorrectLine;
                TextureRegionDrawable tr_end = (TextureRegionDrawable) dragEnd.getStyle().up;
                TextureRegionDrawable tr_start = (TextureRegionDrawable) dragSource.getStyle().up;
                dragEnd.getStyle().up = tr_end.tint(Color.RED);
                dragSource.getStyle().up = tr_start.tint(Color.RED);
                dragEnd.getStyle().down = tr_end.tint(Color.RED);
                dragSource.getStyle().down = tr_start.tint(Color.RED);
            }

            this.level.getLineManager().storeCurrentActiveLine(linetype);

            answCounter++;

            if (answCounter >= level.getLevelObjects().size()) {
                level.notifyListener("LevelComplete");
                answCounter = 0;
            }
        }

        level.getLineManager().resetActiveLine();

        if (Level.drawingLine)
            Level.drawingLine = false;

        Gdx.graphics.requestRendering();

    }
}
