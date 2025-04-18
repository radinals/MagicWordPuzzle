package com.core.screens.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.main.Main;

public class BaseSubScreen extends BaseScreen {
    protected float contentYAxis;
    protected float backBtnSize;

    public BaseSubScreen(Main main) {
        super(main);
        Gdx.input.setInputProcessor(stage);
        calculateSizes();
        createBackButton();
    }

    private void calculateSizes() {
        this.backBtnSize = Gdx.graphics.getWidth() * 0.15f;
        contentYAxis = (Gdx.graphics.getHeight() - (backBtnSize + 10)) - backBtnSize;
    }

    private void createBackButton() {
        Texture backIconTexture = new Texture(Gdx.files.internal("backarrow.png"));

        backIconTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegionDrawable backButtonIcon = new TextureRegionDrawable(backIconTexture);

        backButtonIcon.setMinSize(backBtnSize, backBtnSize);

        ImageButton backButton = new ImageButton(backButtonIcon);

        backButton.setSize(backBtnSize, backBtnSize);

        backButton.getImage().setColor(Color.valueOf("#00f2ff"));

        backButton.setPosition(10, Gdx.graphics.getHeight() - (backBtnSize + 10));

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ImageButton btn = (ImageButton) event.getListenerActor();
                if (btn.isDisabled()) return;
                main.screenManager.loadLastScreen();
                Gdx.graphics.requestRendering();
            }
        });

        this.stage.addActor(backButton);
        this.stage.setActionsRequestRendering(false);
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
                    main.screenManager.loadLastScreen();
                    return true;
                }
                return false;
            }
        });

        // Then the stage, so UI still works
        multiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(multiplexer);
    }
}
