package com.core.screens.subscreen.learn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.core.assets.sprites.GameAssets;
import com.core.model.data.CardData;
import com.core.screens.component.base.BaseSubScreen;
import com.core.screens.component.events.SingleClickInputListener;
import com.main.Main;

import java.util.LinkedList;

public class LearningScreen extends BaseSubScreen {

    private LinkedList<CardData> cards;
    private Table table;
    private Label wordLabel;
    private Image wordImage;
    private int currentCardIdx;

    private float cardSize;
    private float cardBgSize;

    private Sound wordSound;

    private Texture currentTexture;

    public LearningScreen(Main main) {
        super(main);
        calculateSizes();
        this.table = new Table();
        this.table.setSize(cardSize, Gdx.graphics.getHeight());
        this.table.setPosition(0, 0);
        this.wordLabel = null;
        this.currentTexture = null;
        this.wordImage = null;
        this.currentCardIdx = 0;
        this.cards = main.getGameConfig().getCards();
        this.wordSound = null;
        this.wordImage = null;
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = GameAssets.generateFont("Lato/Lato-Bold.ttf", 80);
        style.fontColor = Color.BLACK;
        this.wordLabel = new Label("",style);
        this.wordLabel.setWrap(true);
        this.wordLabel.setWidth(cardSize);
        this.wordLabel.setAlignment(Align.center, Align.center); // Align horizontally and vertically
        createCardBackground();
        loadCard();
        this.table.addActor(wordImage);
        this.table.row();
        this.table.addActor(wordLabel);

        this.table.addListener(new SingleClickInputListener() {

            @Override
            public void firstTouchDown(InputEvent event, float x, float y, int pointer, int button) {
                final float sx = event.getStageX();
                final float centerX = Gdx.graphics.getWidth() / 2;

                if (sx < centerX - (cardSize * 0.25f)) {
                    currentCardIdx--;
                } else if (sx > centerX + (cardSize * 0.25f)) {
                    currentCardIdx++;
                } else {
                    if (wordSound != null) {
                        wordSound.play(1.0f);
                    }
                    return;
                }

                if (currentCardIdx >= cards.size()) {
                    currentCardIdx = 0;
                } else if (currentCardIdx < 0) {
                    currentCardIdx = cards.size() -1;
                }

                loadCard();

            }

            @Override
            public void firstTouchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });

        this.stage.addActor(table);
    }

    private void calculateSizes() {
        this.cardSize = Gdx.graphics.getWidth()*0.8f;
        this.cardBgSize = Gdx.graphics.getWidth()*0.9f;
    }

    private void createCardBackground() {
         TextureRegionDrawable cardBackground = new TextureRegionDrawable(
            GameAssets.getInstance().assetManager.get("cardbg.png", Texture.class)
        );

         cardBackground.setMinSize(this.cardBgSize, this.cardBgSize);

         Image image = new Image(cardBackground);

        image.setPosition(Gdx.graphics.getWidth()/2 - cardBgSize/2, Gdx.graphics.getHeight()*0.6f - cardBgSize/2);

        this.stage.addActor(image);
    }

    private void loadCard() {
        CardData currentCard = this.cards.get(currentCardIdx);

        if (this.currentTexture != null) {
            this.currentTexture.dispose();
            this.currentTexture = null;
        }

        this.currentTexture = new Texture(Gdx.files.internal(currentCard.getImgFile()));

        if (wordSound != null) {
            wordSound.stop();
            wordSound.dispose();
        }

        this.wordSound = Gdx.audio.newSound(Gdx.files.internal(currentCard.getAudio()));

        TextureRegionDrawable texture = new TextureRegionDrawable(currentTexture);

        texture.setMinSize(cardSize,cardSize);

        if (this.wordImage == null)
            this.wordImage = new Image(texture);
        else
            this.wordImage.setDrawable(texture);

        this.wordImage.setPosition(Gdx.graphics.getWidth()/2 - cardSize/2, Gdx.graphics.getHeight()*0.6f - cardSize/2);
        this.wordLabel.setText(currentCard.getWord().toUpperCase());
        this.wordLabel.setPosition(Gdx.graphics.getWidth()/2 - this.wordLabel.getWidth()/2, Gdx.graphics.getHeight()*0.3f - this.wordLabel.getHeight()/2);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }
}
