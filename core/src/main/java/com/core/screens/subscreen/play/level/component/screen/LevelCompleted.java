package com.core.screens.subscreen.play.level.component.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.assets.sprites.GameAssets;
import com.core.screens.component.base.BaseSubScreen;
import com.core.screens.component.events.ButtonClickWithFx;
import com.core.screens.component.transition.SlideOutTransition;
import com.main.Main;


public class LevelCompleted extends BaseSubScreen {

    private Screen nextLevel;

    private boolean levelFailed;

    public LevelCompleted(Main main, Screen nextLevel, boolean levelFailed) {
        super(main);
        this.levelFailed = levelFailed;
        this.nextLevel = nextLevel;

        super.backButton.clearListeners();
        super.backButton.addListener(new ButtonClickWithFx(ButtonClickWithFx.BtnSfxId.FX1) {
            @Override
            public void touchDownAfterFx(InputEvent event, float x, float y, int pointer, int button) {
                main.screenManager.switchScreensDirectly(nextLevel, (levelFailed) ? SlideOutTransition.SlideDirection.LEFT : SlideOutTransition.SlideDirection.RIGHT);
            }

            @Override
            public void firstTouchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
        String levelImg = (!levelFailed) ? "LevelComplete.png" : "LevelFailed.png";

        TextureRegionDrawable texture = new TextureRegionDrawable(GameAssets.getInstance().assetManager.get(levelImg, Texture.class));
        texture.setMinSize(Gdx.graphics.getWidth() * 1.4f, Gdx.graphics.getHeight() * 0.45f);
        Image img = new Image(texture);

        img.setPosition(Gdx.graphics.getWidth()/2 - texture.getMinWidth()/2, Gdx.graphics.getHeight() * 0.6f - texture.getMinWidth()/2);

        this.stage.addActor(img);
        //this.createOKButton();
    }

    private void createOKButton() {
        TextureRegionDrawable icon = new TextureRegionDrawable(GameAssets.getInstance().assetManager.get("okbtn.png", Texture.class));

        icon.setMinSize(Gdx.graphics.getWidth() * 0.45f, Gdx.graphics.getHeight() * 0.1f);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();

        style.up = icon;
        style.down = icon.tint(Color.GRAY);

        ImageButton btn = new ImageButton(style);
        btn.setDebug(true);

        btn.setSize(Gdx.graphics.getWidth() * 0.45f, Gdx.graphics.getHeight() * 0.1f);

        btn.addListener(new ButtonClickWithFx(ButtonClickWithFx.BtnSfxId.FX2) {
            @Override
            public void touchDownAfterFx(InputEvent event, float x, float y, int pointer, int button) {
                main.screenManager.switchScreensDirectly(nextLevel, (levelFailed) ? SlideOutTransition.SlideDirection.LEFT : SlideOutTransition.SlideDirection.RIGHT);
            }

            @Override
            public void firstTouchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        btn.setPosition(Gdx.graphics.getWidth()/2 - btn.getMinWidth()/2, Gdx.graphics.getHeight() * 0.39f - btn.getMinWidth()/2);

        this.stage.addActor(btn);

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }
}
