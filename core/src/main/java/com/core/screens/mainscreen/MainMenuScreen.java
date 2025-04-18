package com.core.screens.mainscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.core.screens.mainscreen.core.ui.MainGameButtons;
import com.core.screens.mainscreen.core.ui.OptionButtonRow;
import com.core.screens.util.base.BaseScreen;
import com.main.Main;

public class MainMenuScreen extends BaseScreen {

    private int titleWidth, titleHeight;
    private int titleX, titleY;

    private int mainButtonWidth;
    private int mainButtonHeight;

    private float extraButtonSize;

    public MainMenuScreen(Main main) {
        super(main);

        Gdx.input.setInputProcessor(stage);

        this.mainButtonWidth = 0;
        this.mainButtonHeight = 0;

        calculateSizes();

        MainGameButtons mainGameButtons = new MainGameButtons(main, mainButtonWidth, mainButtonHeight);
        OptionButtonRow optionButtonRow = new OptionButtonRow(main, extraButtonSize);

        mainGameButtons.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.5f);
        optionButtonRow.setSize(Gdx.graphics.getWidth(), extraButtonSize);

        mainGameButtons.setPosition((float)Gdx.graphics.getWidth() / 2 - mainGameButtons.getWidth() / 2, titleY - titleHeight);
        optionButtonRow.setPosition((float)Gdx.graphics.getWidth() / 2 - optionButtonRow.getWidth() / 2, Gdx.graphics.getHeight() * 0.25f);

        this.stage.addActor(mainGameButtons);
        this.stage.addActor(optionButtonRow);
    }

    public void calculateSizes() {

        int sh = Gdx.graphics.getHeight(), sw = Gdx.graphics.getWidth();

        this.mainButtonWidth = (int) (sw * 0.7f);
        this.mainButtonHeight = (int) (sh * 0.09f);
        this.titleWidth = (int) (sw * 0.8f);
        this.titleHeight = (int) (sh * 0.35f);

        this.titleX = Gdx.graphics.getWidth() / 2 - titleWidth / 2;
        this.titleY = Gdx.graphics.getHeight() / 2 + 100;

        this.extraButtonSize = sw * 0.15f;
    }

    @Override
    public void show() {

        super.show();

        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        InputMultiplexer multiplexer = new InputMultiplexer();

        // First, your back key handler
        multiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.BACK) {
                    // Handle back key
                    Gdx.app.exit();
                    return true;
                }
                return false;
            }
        });

        // Then the stage, so UI still works
        multiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        main.getBatch().begin();
        main.getBatch().draw(
            main.getBaseAssets().assetManager.get("title.png", Texture.class),
            titleX, titleY, titleWidth, titleHeight);
        main.getBatch().end();
    }
}
