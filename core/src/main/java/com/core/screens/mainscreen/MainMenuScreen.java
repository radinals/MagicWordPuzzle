package com.core.screens.mainscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.assets.sprites.GameAssets;
import com.core.screens.mainscreen.component.events.optionbtn.VolumeBtnEvent;
import com.core.screens.mainscreen.component.ui.MainGameButtons;
import com.core.screens.component.base.BaseScreen;
import com.main.Main;

public class MainMenuScreen extends BaseScreen {

    private float titleWidth, titleHeight;
    private float titleX, titleY;
    private float mainButtonWidth, mainButtonHeight;
    private float extraButtonSize;

    public MainMenuScreen(Main main) {
        super(main);

        Gdx.input.setInputProcessor(stage);

        this.mainButtonWidth = 0;
        this.mainButtonHeight = 0;

        calculateSizes();

        MainGameButtons mainGameButtons = new MainGameButtons(main, mainButtonWidth, mainButtonHeight);
        // OptionButtonRow optionButtonRow = new OptionButtonRow(main, extraButtonSize);

        mainGameButtons.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.5f);
        // optionButtonRow.setSize(Gdx.graphics.getWidth(), extraButtonSize);

        mainGameButtons.setPosition((float) Gdx.graphics.getWidth() / 2 - mainGameButtons.getWidth() / 2, titleY - titleHeight);
        // optionButtonRow.setPosition(Gdx.graphics.getWidth() - (optionButtonRow.getWidth() + 10), Gdx.graphics.getHeight() * 0.9f);

        Button volumeBtn = createButton("volumeicon.png", new VolumeBtnEvent(main));
        this.stage.addActor(volumeBtn);
        volumeBtn.setPosition(Gdx.graphics.getWidth() - volumeBtn.getWidth() * 1.1f, Gdx.graphics.getHeight() * 0.90f);

        this.stage.addActor(mainGameButtons);
        // this.stage.addActor(optionButtonRow);
    }

    private Button createButton(String iconFile, ClickListener eventHandler) {
        TextureRegionDrawable icon = new TextureRegionDrawable(
            GameAssets.getInstance().assetManager.get(iconFile, Texture.class)
        );
        icon.setMinSize(extraButtonSize, extraButtonSize);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = icon;
        style.imageDown = icon.tint(Color.GRAY);

        ImageButton btn = new ImageButton(style);
        btn.setSize(extraButtonSize, extraButtonSize);

        btn.addListener(eventHandler);

        return btn;
    }

    public void calculateSizes() {

        int sh = Gdx.graphics.getHeight(), sw = Gdx.graphics.getWidth();

        final float topPadding = Gdx.graphics.getHeight() * 0.025f;

        this.mainButtonWidth = (sw * 0.6f);
        this.mainButtonHeight = (sh * 0.09f);
        this.titleWidth = (sw * 0.8f);
        this.titleHeight =  (sh * 0.35f);

        this.titleX = (Gdx.graphics.getWidth() / 2) - titleWidth / 2;
        this.titleY = (Gdx.graphics.getHeight() / 2) - topPadding;

        this.extraButtonSize = sw * 0.15f;
    }

    @Override
    public void show() {
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
            GameAssets.getInstance().assetManager.get("title.png", Texture.class),
            titleX, titleY, titleWidth, titleHeight);
        main.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }
}
