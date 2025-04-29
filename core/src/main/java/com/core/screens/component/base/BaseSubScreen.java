package com.core.screens.component.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.assets.sprites.GameAssets;
import com.core.screens.component.events.ButtonClickWithFx;
import com.core.screens.mainscreen.component.events.optionbtn.VolumeBtnEvent;
import com.main.Main;

public abstract class BaseSubScreen extends BaseScreen {
    protected float backBtnSize;
    protected Label screenTitle;
    protected Table topRowTable;
    protected  ImageButton backButton;

    public BaseSubScreen(Main main) {
        super(main);
        this.topRowTable = new Table();
        Gdx.input.setInputProcessor(stage);
        topRowTable.left().top();
        calculateSizes();
        createBackButton();
       // createScreenTitle();
        this.stage.addActor(topRowTable);
    }

    private void createTitleBackground() {

    }

    private Button createButton(String iconFile, ClickListener eventHandler) {
        TextureRegionDrawable icon = new TextureRegionDrawable(
                GameAssets.getInstance().assetManager.get(iconFile, Texture.class)
        );
        icon.setMinSize(backBtnSize, backBtnSize);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = icon;
        style.imageDown = icon.tint(Color.GRAY);

        ImageButton btn = new ImageButton(style);
        btn.setSize(backBtnSize, backBtnSize);

        btn.addListener(eventHandler);

        return btn;
    }

    private void calculateSizes() {
        this.topRowTable.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getWidth() * 0.15f);
        this.topRowTable.setPosition(0, Gdx.graphics.getHeight() - topRowTable.getHeight() - 10);
        this.topRowTable.setOriginX(0);
        this.backBtnSize = Gdx.graphics.getWidth() * 0.15f;
    }

    private void createScreenTitle() {
        Label.LabelStyle style = new Label.LabelStyle();

        // TODO: SETUP BUTTON BG
        style.font = GameAssets.generateFont("Lato/Lato-Bold.ttf", 50);
        style.fontColor = Color.BLACK;

        TextureRegionDrawable bg = new TextureRegionDrawable(new Texture(Gdx.files.internal("titlebg.png")));

        screenTitle = new Label("", style);
        screenTitle.setAlignment(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        screenTitle.getStyle().background = bg;
        screenTitle.setSize(Gdx.graphics.getWidth() - ((backBtnSize * 2) + 40), backBtnSize);
        bg.setMinSize(screenTitle.getWidth(), backBtnSize);
        topRowTable.add(screenTitle).left().expandX().fillX().padTop(10);
        Button volumeBtn = createButton("volumeicon.png", new VolumeBtnEvent(main));
        topRowTable.add(volumeBtn).left().expandX().fillX().padLeft(10).padRight(10).padTop(10);
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

        this.backButton = backButton;
        topRowTable.add(backButton).left().fillX().padLeft(10).padRight(10).padTop(10);
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
