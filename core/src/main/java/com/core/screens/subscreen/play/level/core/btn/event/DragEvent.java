package com.core.screens.subscreen.play.level.core.btn.binding;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.core.screens.subscreen.play.level.core.Level;
import com.core.screens.subscreen.play.level.core.btn.group.ButtonPair;
import com.core.screens.subscreen.play.level.core.lines.LineManager;

public class DragBinding extends DragListener {

    private final Level level;

    private ButtonPair dragStartingBtnPair;
    private Button dragStartingBtn;

    public DragBinding(Level level) {
        this.level = level;
    }

    @Override
    public void dragStart(InputEvent event, float x, float y, int pointer) {
        Button btn = (Button) event.getListenerActor();

        for (ButtonPair levelButtons : level.getLevelObjects()) {

            if (btn instanceof ImageButton && btn == levelButtons.imgButton) {
                if (levelButtons.isImgButtonLined()) return;
            } else if (btn instanceof TextButton && btn == levelButtons.wordButton) {
                if (levelButtons.isWordButtonLined()) return;
            } else {
                continue;
            }

            this.dragStartingBtn = btn;
            this.dragStartingBtnPair = levelButtons;
            this.level.getLineManager().setActiveLineStart(event.getStageX(), event.getStageY());
        }

    }

    @Override
    public void drag(InputEvent event, float x, float y, int pointer) {
        this.level.getLineManager().setActiveLineEnd(event.getStageX(), event.getStageY());
        Gdx.graphics.requestRendering();
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer) {
        this.level.getLineManager().setActiveLineEnd(event.getStageX(), event.getStageY());

        for (ButtonPair levelButtons : level.getLevelObjects()) {
            Button dragStopBtn;

            if (dragStartingBtn instanceof ImageButton) {
                if (levelButtons.isWordButtonLined()) continue;
                dragStopBtn = levelButtons.wordButton;
            } else if (dragStartingBtn instanceof TextButton) {
                if (levelButtons.isImgButtonLined()) continue;
                dragStopBtn = levelButtons.imgButton;
            } else {
                return;
            }

            Rectangle dragStopButtonArea = new Rectangle(
                dragStopBtn.getX(),
                dragStopBtn.getY(),
                dragStopBtn.getWidth(),
                dragStopBtn.getHeight()
            );

            if (!Intersector.intersectSegmentRectangle(
                level.getLineManager().getActiveLine().second,
                level.getLineManager().getActiveLine().second,
                dragStopButtonArea)) {
                continue;
            }

            if (dragStopBtn instanceof ImageButton) {
                levelButtons.setImageButtonLined();
                dragStartingBtnPair.setWordButtonLined();
            } else {
                levelButtons.setWordButtonLined();
                dragStartingBtnPair.setImageButtonLined();
            }

            try {
                if (dragStartingBtnPair == levelButtons) {
                    this.level.getLineManager().storeCurrentActiveLine(LineManager.LineType.CorrectLine);
                } else {
                    this.level.getLineManager().storeCurrentActiveLine(LineManager.LineType.IncorrectLine);
                }
            } catch (Exception e) {
                return;
            }

            break;
        }

        level.getLineManager().resetActiveLine();

        Gdx.graphics.requestRendering();
    }
}
