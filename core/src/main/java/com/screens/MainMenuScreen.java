package com.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.main.Main;

public class MainMenuScreen implements Screen {

    private Main main;
    private Texture backgroundImg, titleImg;

    private ImageButton startButton, learnButton;
    private Stage stage;
    private Table extraButtonsRow;

    private int titleWidth, titleHeight;

    public MainMenuScreen(Main main) {
        this.main = main;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        this.backgroundImg = new Texture(Gdx.files.internal("mainbg.png"));
        this.backgroundImg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.titleImg = new Texture(Gdx.files.internal("title.png"));
        this.titleImg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        createStartButton();
        createLearnButton();

        this.stage.addActor(startButton);
        this.stage.addActor(learnButton);

        this.titleWidth = Gdx.graphics.getWidth() / 2 + Gdx.graphics.getWidth()/3;
        this.titleHeight = Gdx.graphics.getHeight() / 3;
    }

    private void createStartButton() {
        int startBtnWidth = 450;
        int startBtnHeight = 120;

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
        int learnBtnWidth = 450;
        int learnBtnHeight = 120;

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

    private void createExtraButtons() {

    }

    @Override
    public void show() {

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
