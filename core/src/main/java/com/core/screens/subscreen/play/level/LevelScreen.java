package com.core.screens.gameplay.level.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.core.screens.base.BaseSubScreen;
import com.core.screens.gameplay.level.Level;
import com.main.Main;

public class LevelScreen extends BaseSubScreen {
    private final Table table;
    private final Level level;

    public LevelScreen(Main main, Level level) {
        super(main);
        this.level = level;
        this.table = new Table();
        calculateTableSize();
        initLevel();
        level.getLineManager().unHaltLineRendering();
        this.stage.addActor(table);
    }

    public void calculateTableSize() {
        this.table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.8f);
        this.table.setPosition(Gdx.graphics.getWidth() / 2 - table.getWidth() / 2, Gdx.graphics.getHeight() * 0.1f);
        this.table.setDebug(true);
    }

    private void initLevel() {
        int i = 1;
        for (Button btn : level.getShuffledButtons()) {
            table.add(btn);
            if (i % 2 == 0) table.row();
            i++;

        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        level.getLineManager().render();
    }
}
