package com.core.screens.util.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.btnevents.ButtonClickWithFx;
import com.core.data.assets.sprites.GameAssets;
import com.main.Main;

public abstract class BaseSubScreen extends BaseScreen {
    protected float backBtnSize;
    protected Label screenTitle;
    protected Table topRowTable;

    public BaseSubScreen(Main main) {
        super(main);
        this.topRowTable = new Table();
        Gdx.input.setInputProcessor(stage);
        topRowTable.setDebug(true);
        topRowTable.left().top();
        calculateSizes();
        createBackButton();
        createScreenTitle();
        this.stage.addActor(topRowTable);
    }

    private void calculateSizes() {
        this.topRowTable.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getWidth() * 0.15f);
        this.topRowTable.setPosition(0, Gdx.graphics.getHeight() - topRowTable.getHeight());
        this.topRowTable.setOriginX(0);
        this.backBtnSize = Gdx.graphics.getWidth() * 0.15f;
    }

    private void createScreenTitle() {
        Label.LabelStyle style = new Label.LabelStyle();

        // TODO: SETUP BUTTON BG
        style.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        style.font.getData().setScale(3f); // unreliable for UI
        style.fontColor = Color.BLACK;

        screenTitle = new Label("", style);

        screenTitle.setWidth(topRowTable.getWidth());

        topRowTable.add(screenTitle).left().expandX().fillX().pad(10);
    }

    private void createBackButton() {
        TextureRegionDrawable backButtonIcon = new TextureRegionDrawable(GameAssets.getInstance().assetManager.get("backarrow.png", Texture.class));
        backButtonIcon.setMinSize(backBtnSize, backBtnSize);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();

        style.imageUp = backButtonIcon.tint(Color.valueOf("#00f2ff"));
        style.imageDown = backButtonIcon.tint(Color.GRAY);

        ImageButton backButton = new ImageButton(style);

        backButton.setSize(backBtnSize, backBtnSize);

        backButton.addListener(new ButtonClickWithFx(ButtonClickWithFx.BtnSfxId.FX2) {
            @Override
            public void touchDownAfterFx(InputEvent event, float x, float y, int pointer, int button) {
                pause();
            }

            @Override
            public void firstTouchUp(InputEvent event, float x, float y, int pointer, int button) {
                ImageButton btn = (ImageButton) event.getListenerActor();
                if (btn.isDisabled()) return;
                main.screenManager.loadLastScreen();
                Gdx.graphics.requestRendering();
            }
        });

        topRowTable.add(backButton).left().fillX().pad(10);
    }

    @Override
    public void show() {

        Gdx.input.setCatchKey(Input.Keys.BACK, true);

        InputMultiplexer multiplexer = new InputMultiplexer();

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

        multiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(multiplexer);
    }
}
