package com.core.screens.subscreen.play.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.assets.sprites.GameAssets;
import com.core.screens.component.base.BaseSubScreen;
import com.core.screens.component.events.ButtonClickWithFx;
import com.core.screens.subscreen.play.level.component.Level;
import com.core.screens.subscreen.play.level.component.btn.group.ButtonPair;
import com.core.screens.subscreen.play.level.component.event.LevelCompleteEvent;
import com.core.screens.subscreen.play.level.component.lines.LineManager;
import com.core.screens.subscreen.play.levelselect.component.factory.LevelFactory;
import com.main.Main;

public class LevelScreen extends BaseSubScreen {
    private final Table table;
    private final LineManager lineManager;
    private Level level;
    private static TextureRegionDrawable instruction = new TextureRegionDrawable(GameAssets.getInstance().assetManager.get("LevelMsg.png", Texture.class));

    private static Image instructionImage;

    public LevelScreen(Main main, Level level, LevelFactory factory) {
        super(main);
        //super.screenTitle.setText("Level " + (level.getLevelIdx() + 1));
        this.level = level;
        this.lineManager = level.getLineManager();
        this.table = new Table();
        instructionImage = null;
        calculateTableSize();
        initLevel(level);
        this.stage.addActor(table);
        level.addListener(new LevelCompleteEvent(this, factory));

    }

    public void showInstructionImage() {
        if (instructionImage == null) {
            instruction.setMinSize(Gdx.graphics.getWidth() * 1.4f, Gdx.graphics.getHeight() * 0.4f);
            instructionImage = new Image(instruction);
            instructionImage.setPosition(Gdx.graphics.getWidth()/2 - instructionImage.getWidth()/2, Gdx.graphics.getHeight()/2 - instructionImage.getHeight()/2);
            instructionImage.addListener(new ButtonClickWithFx(ButtonClickWithFx.BtnSfxId.FX2) {
                @Override
                public void touchDownAfterFx(InputEvent event, float x, float y, int pointer, int button) {
                    hideInstructionImage();
                }

                @Override
                public void firstTouchUp(InputEvent event, float x, float y, int pointer, int button) {
                }
            });

            super.stage.addActor(instructionImage);
        }
        instructionImage.setVisible(true);
        this.table.setVisible(false);
    }

    public void hideInstructionImage() {
        if (instructionImage == null) return;
        instructionImage.setVisible(false);
        this.table.setVisible(true);
    }

    @Override
    public void show() {
        super.show();
    }

    public Level getLevel() {
        return level;
    }

    public void calculateTableSize() {
        this.table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.8f);
        this.table.setPosition((float) Gdx.graphics.getWidth() / 2 - table.getWidth() / 2, Gdx.graphics.getHeight() * 0.1f);
    }

    private void initLevel(Level level) {
        int i = 1;
        for (Button btn : level.getShuffledButtons()) {
            if (i % 2 == 0) {
                table.add(btn).padLeft(Gdx.graphics.getWidth() * 0.25f).padBottom(Gdx.graphics.getHeight() * 0.010f);
                table.row();
            } else {
                table.add(btn).padBottom(Gdx.graphics.getHeight() * 0.010f);
            }
            i++;

        }
    }

    public LineManager getLineManager() {
        return lineManager;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        lineManager.render();
    }

    @Override
    public void dispose() {
        for (ButtonPair pair : level.getLevelObjects()) {
            pair.sound.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }

}
