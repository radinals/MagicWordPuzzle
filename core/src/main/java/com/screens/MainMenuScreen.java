package com.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.main.Main;

public class MainMenuScreen implements Screen {

    private Main main;
    private Texture backgroundImg, titleImg;

    private ImageButton startButton, learnButton;
    private Stage stage;
    private Table extraButtons;

    private int titleWidth, titleHeight;

    private int mainButtonWidth;
    private int mainButtonHeight;

    private float extraButtonsRowHeight;
    private float extraButtonsRowWidth;
    private float extraButtonSize;
    private float extraButtonsPadding;

    public MainMenuScreen(Main main) {
        this.main = main;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        this.mainButtonWidth = 0;
        this.mainButtonHeight = 0;

        this.backgroundImg = new Texture(Gdx.files.internal("mainbg.png"));
        this.backgroundImg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.titleImg = new Texture(Gdx.files.internal("title.png"));
        this.titleImg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
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

        this.mainButtonWidth = (int)(sw * 0.7f);
        this.mainButtonHeight = (int)(sh * 0.09f);
        this.titleWidth = (int)(sw * 0.8f);
        this.titleHeight = (int)(sh * 0.35f);

        this.extraButtonsRowHeight = mainButtonHeight;
        this.extraButtonsRowWidth = sw * 0.8f;
        this.extraButtonSize = sw * 0.15f;
        this.extraButtonsPadding = extraButtonSize * 0.15f;

    }

    private void createExtraButtons() {
        int sh = Gdx.graphics.getHeight(), sw = Gdx.graphics.getWidth();

        this.extraButtons.setPosition(
            sw/2 - extraButtons.getWidth()/2,
            sh/4 - extraButtons.getHeight() /2
        );

        createExtraButton("googleplayicon.png").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // todo open app page
            }
        });

        // TODO: WTF SHOULD THIS DO
        createExtraButton("volumeicon.png").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.openVolumeSettings();
            }
        });

        createExtraButton("shareicon.png").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // open share dialog
            }
        });

        createExtraButton("stars.png").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // todo open app page
            }
        });
;
    }

    private ImageButton createExtraButton(String normIcon) {
        Texture icon = new Texture(Gdx.files.internal(normIcon));
        icon.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegionDrawable iconD = new TextureRegionDrawable(icon);
        iconD.setMinSize(extraButtonSize,extraButtonSize);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = iconD;

        ImageButton btn = new ImageButton(style);
        btn.setSize(extraButtonSize,extraButtonSize);

        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                return;
            }
        });

        this.extraButtons.add(btn)
            .size(extraButtonSize).pad(0.0f,extraButtonsPadding/2,0,extraButtonsPadding/2);

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

        startButtonIcon.setMinSize(startBtnWidth,startBtnHeight);
        startButtonIconClicked.setMinSize(startBtnWidth,startBtnHeight);

        this.startButton = new ImageButton(startButtonIcon,startButtonIconClicked);

        this.startButton.setSize(startBtnWidth,startBtnHeight);

        this.startButton.setPosition(Gdx.graphics.getWidth()/2 - startBtnWidth/2, Gdx.graphics.getHeight()/2 - startBtnHeight/2);

        this.startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.loadCategorySelectScreen();
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

        learnButtonIcon.setMinSize(learnBtnWidth,learnBtnHeight);
        learnButtonIconClicked.setMinSize(learnBtnWidth,learnBtnHeight);

        this.learnButton = new ImageButton(learnButtonIcon,learnButtonIconClicked);

        this.learnButton.setSize(learnBtnWidth,learnBtnHeight);

        this.learnButton.setPosition(Gdx.graphics.getWidth()/2 - learnBtnWidth/2, Gdx.graphics.getHeight()/2 - ((learnBtnHeight * 1.5f) + 10));

        this.learnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.loadLearnScreen();
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setCatchKey( Input.Keys.BACK, true);
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
        main.getBatch().begin();
        main.getBatch().draw(titleImg, Gdx.graphics.getWidth()/2 - titleWidth/2, Gdx.graphics.getHeight()/2 + 100, titleWidth, titleHeight);
        main.getBatch().end();
        stage.act(delta);
        stage.draw(); // Renders UI
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
