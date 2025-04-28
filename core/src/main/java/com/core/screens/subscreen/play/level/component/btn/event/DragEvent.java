package com.core.screens.subscreen.play.level.component.btn.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.screens.subscreen.play.level.component.Level;
import com.core.screens.subscreen.play.level.component.btn.group.ButtonPair;
import com.core.screens.subscreen.play.level.component.lines.Line;
import com.core.screens.subscreen.play.level.component.lines.LineManager;

import java.util.Stack;

public class DragEvent extends DragListener {

    private final static Stack<Button> linedButtons = new Stack<>();
    private final static Sound correctFx = Gdx.audio.newSound(Gdx.files.internal("audio/success.mp3"));
    private final static Sound incorrectFx = Gdx.audio.newSound(Gdx.files.internal("audio/incorrect.mp3"));
    private static int answCounter = 0;
    private final Level level;
    private ButtonPair dragStartPair;
    private final LineManager lineManager;

    private static int wrongAnswers = 0;

    public DragEvent(Level level) {
        this.level = level;
        this.lineManager = level.getLineManager();
        answCounter = 0;
        wrongAnswers = 0;
    }

    private static boolean lineConnectsTwoButtons(Button btn1, Button btn2, Line line) {
        final Vector2 lineStart = line.first, lineEnd = line.second;
        return (pointInButtonArea(btn1, lineStart.x, lineStart.y) && pointInButtonArea(btn2, lineEnd.x, lineEnd.y)) ||
            (pointInButtonArea(btn2, lineStart.x, lineStart.y) && pointInButtonArea(btn1, lineEnd.x, lineEnd.y));
    }

    private static boolean pointInButtonArea(Button btn, float x, float y) {
        Vector2 local = btn.stageToLocalCoordinates(new Vector2(x, y));
        return (btn.hit(local.x, local.y, true) != null);
    }


    private static boolean buttonsIsLined(Button btn1, Button btn2) {
        return linedButtons.contains(btn1) || linedButtons.contains(btn2);
    }

    private static void tintButtonsBackground(Button btn1, Button btn2, Color color) {
        btn1.getStyle().up = btn1.getStyle().down = ((TextureRegionDrawable) btn1.getStyle().up).tint(color);
        btn2.getStyle().down = btn2.getStyle().up = ((TextureRegionDrawable) btn2.getStyle().up).tint(color);
    }

    private void saveCurrentActiveLine(boolean cardsPairedCorrectly, Button btn1, Button btn2) {
        if (cardsPairedCorrectly) {
            lineManager.storeCurrentActiveLine(LineManager.LineType.CorrectLine);
            tintButtonsBackground(btn1, btn2,Color.GREEN);
            correctFx.play();
        } else {
            lineManager.storeCurrentActiveLine(LineManager.LineType.IncorrectLine);
            tintButtonsBackground(btn1, btn2,Color.RED);
            incorrectFx.play();
            wrongAnswers++;
        }
        ++answCounter;
        if (answCounter >= level.getLevelObjects().size()) {
            level.notifyListener((wrongAnswers >= answCounter) ? "LevelCompleteFailed" : "LevelComplete");
            answCounter = 0;
            wrongAnswers = 0;
        }
    }

    @Override
    public void dragStart(InputEvent event, float x, float y, int pointer) {
        if (pointer != 0) return;

        Button btn = (Button) event.getListenerActor();

        if (linedButtons.contains(btn)) return;

        for (ButtonPair pair : level.getLevelObjects()) {
            if ((pair.imgButton.equals(btn) || pair.wordButton.equals(btn)) &&
                pointInButtonArea(btn, event.getStageX(), event.getStageY())) {
                this.dragStartPair = pair;
                lineManager.setActiveLineStart(event.getStageX(), event.getStageY());
                break;
            }
        }

        if (lineManager.isActiveLineFormed())
            Gdx.graphics.requestRendering();
    }

    @Override
    public void drag(InputEvent event, float x, float y, int pointer) {
        if (pointer != 0) return;

        lineManager.setActiveLineEnd(event.getStageX(), event.getStageY());
        if (lineManager.isActiveLineFormed())
            Gdx.graphics.requestRendering();
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer) {
        if (pointer != 0) return;

        lineManager.setActiveLineEnd(event.getStageX(), event.getStageY());

        if (!lineManager.isActiveLineFormed()) {
            lineManager.resetActiveLine();
            return;
        }

        final Button dragSource = (Button) event.getListenerActor();

        for (ButtonPair pair : level.getLevelObjects()) {
            Button dragEnd = (dragSource instanceof ImageButton) ? pair.wordButton : pair.imgButton;
            if (lineConnectsTwoButtons(dragEnd, dragSource, lineManager.getActiveLine()) &&
                !buttonsIsLined(dragEnd, dragSource))
            {
                linedButtons.add(dragEnd);
                linedButtons.add(dragSource);
                saveCurrentActiveLine((pair.equals(dragStartPair)), dragSource, dragEnd);
                break;
            }
        }

        if (this.level.getLineManager().isActiveLineFormed())
            Gdx.graphics.requestRendering();

        lineManager.resetActiveLine();
    }

}
