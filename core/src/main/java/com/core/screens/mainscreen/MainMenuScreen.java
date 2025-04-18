package com.core.screens.home;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.screens.base.BaseScreen;
import com.main.Main;

public class MainMenuScreen extends BaseScreen {

    private ImageButton startButton, learnButton;
    private final Table extraButtons;

    private int titleWidth, titleHeight;

    private int mainButtonWidth;
    private int mainButtonHeight;

    private float extraButtonSize;
    private float extraButtonsPadding;

    public MainMenuScreen(Main main) {
        super(main);

        Gdx.input.setInputProcessor(stage);

        this.mainButtonWidth = 0;
        this.mainButtonHeight = 0;

        this.extraButtons = new Table();

        calculateSizes();

        createStartButton();
        createLearnButton();
        createExtraButtons();

        this.stage.addActor(startButton);
        this.stage.addActor(learnButton);
        this.stage.addActor(extraButtons);
    }

    public void calculateSizes() {

        int sh = Gdx.graphics.getHeight(), sw = Gdx.graphics.getWidth();

        this.mainButtonWidth = (int) (sw * 0.7f);
        this.mainButtonHeight = (int) (sh * 0.09f);
        this.titleWidth = (int) (sw * 0.8f);
        this.titleHeight = (int) (sh * 0.35f);

        this.extraButtonSize = sw * 0.15f;
        this.extraButtonsPadding = extraButtonSize * 0.15f;

    }

    private void createExtraButtons() {
        int sh = Gdx.graphics.getHeight(), sw = Gdx.graphics.getWidth();

        this.extraButtons.setPosition(
            sw / 2 - extraButtons.getWidth() / 2,
            sh / 4 - extraButtons.getHeight() / 2
        );

        createExtraButton(new TextureRegionDrawable(main.getBaseAssets().assetManager.get("googleplayicon.png", Texture.class))).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // todo open app page
                ImageButton btn = (ImageButton) event.getListenerActor();
                if (btn.isDisabled()) return;
                Gdx.graphics.requestRendering();
            }
        });

        // TODO: WTF SHOULD THIS DO
        createExtraButton(new TextureRegionDrawable(main.getBaseAssets().assetManager.get("volumeicon.png", Texture.class))).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ImageButton btn = (ImageButton) event.getListenerActor();
                if (btn.isDisabled()) return;
                main.openVolumeSettings();
                Gdx.graphics.requestRendering();
            }
        });

        createExtraButton(new TextureRegionDrawable(main.getBaseAssets().assetManager.get("shareicon.png", Texture.class))).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // open share dialog
                ImageButton btn = (ImageButton) event.getListenerActor();
                if (btn.isDisabled()) return;

                Gdx.graphics.requestRendering();
            }
        });

        createExtraButton(new TextureRegionDrawable(main.getBaseAssets().assetManager.get("stars.png", Texture.class))).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // open share dialog
                ImageButton btn = (ImageButton) event.getListenerActor();
                if (btn.isDisabled()) return;

                Gdx.graphics.requestRendering();
            }
        });

    }

    private ImageButton createExtraButton(TextureRegionDrawable icon) {
        icon.setMinSize(extraButtonSize, extraButtonSize);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = icon;

        ImageButton btn = new ImageButton(style);
        btn.setSize(extraButtonSize, extraButtonSize);

        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.graphics.requestRendering();
            }
        });

        this.extraButtons.add(btn)
            .size(extraButtonSize).pad(0.0f, extraButtonsPadding / 2, 0, extraButtonsPadding / 2);

        return btn;
    }

    private void createStartButton() {
        int startBtnWidth = this.mainButtonWidth;
        int startBtnHeight = this.mainButtonHeight;

        Texture startIconTexture = new Texture(Gdx.files.internal("startbtnicon.png"));
        Texture startIconClickedTexture = new Texture(Gdx.files.internal("startbtnicon_clicked.png"));

        startIconTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        startIconClickedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegionDrawable startButtonIcon = new TextureRegionDrawable(startIconTexture);
        TextureRegionDrawable startButtonIconClicked = new TextureRegionDrawable(startIconClickedTexture);

        startButtonIcon.setMinSize(startBtnWidth, startBtnHeight);
        startButtonIconClicked.setMinSize(startBtnWidth, startBtnHeight);

        this.startButton = new ImageButton(startButtonIcon, startButtonIconClicked);

        this.startButton.setSize(startBtnWidth, startBtnHeight);

        this.startButton.setPosition(Gdx.graphics.getWidth() / 2 - startBtnWidth / 2, Gdx.graphics.getHeight() / 2 - startBtnHeight / 2);

        this.startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ImageButton btn = (ImageButton) event.getListenerActor();
                if (btn.isDisabled()) return;
                main.screenManager.loadCategorySelectScreen();
                Gdx.graphics.requestRendering();
            }
        });
    }

    private void createLearnButton() {
        int learnBtnWidth = this.mainButtonWidth;
        int learnBtnHeight = this.mainButtonHeight;

        Texture learnIconTexture = new Texture(Gdx.files.internal("learnbtnicon.png"));
        Texture learnIconClickedTexture = new Texture(Gdx.files.internal("learnbtnicon_clicked.png"));

        learnIconTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        learnIconClickedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegionDrawable learnButtonIcon = new TextureRegionDrawable(learnIconTexture);
        TextureRegionDrawable learnButtonIconClicked = new TextureRegionDrawable(learnIconClickedTexture);

        learnButtonIcon.setMinSize(learnBtnWidth, learnBtnHeight);
        learnButtonIconClicked.setMinSize(learnBtnWidth, learnBtnHeight);

        this.learnButton = new ImageButton(learnButtonIcon, learnButtonIconClicked);

        this.learnButton.setSize(learnBtnWidth, learnBtnHeight);

        this.learnButton.setPosition(Gdx.graphics.getWidth() / 2 - learnBtnWidth / 2, Gdx.graphics.getHeight() / 2 - ((learnBtnHeight * 1.5f) + 10));

        this.learnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ImageButton btn = (ImageButton) event.getListenerActor();
                if (btn.isDisabled()) return;
                main.screenManager.loadLearnScreen();
                Gdx.graphics.requestRendering();
            }
        });
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
        main.getBatch().draw(main.getBaseAssets().assetManager.get("title.png", Texture.class),
            Gdx.graphics.getWidth() / 2 - titleWidth / 2,
            Gdx.graphics.getHeight() / 2 + 100,
            titleWidth, titleHeight);
        main.getBatch().end();
    }
}
