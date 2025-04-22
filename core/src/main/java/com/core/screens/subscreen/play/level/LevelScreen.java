package com.core.screens.subscreen.play.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.core.screens.subscreen.play.level.core.Level;
import com.core.screens.subscreen.play.level.core.lines.LineManager;
import com.core.screens.util.base.BaseSubScreen;
import com.main.Main;

public class LevelScreen extends BaseSubScreen {
    private final Table table;
    private final LineManager lineManager;

    public LevelScreen(Main main, Level level) {
        super(main);
        this.lineManager = level.getLineManager();
        this.table = new Table();
        calculateTableSize();
        initLevel(level);
        this.stage.addActor(table);
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

    @Override
    public void render(float delta) {
        super.render(delta);
        lineManager.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }
}
