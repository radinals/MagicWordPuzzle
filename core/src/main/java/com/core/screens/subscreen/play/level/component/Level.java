package com.core.screens.subscreen.play.level.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.core.assets.sprites.GameAssets;
import com.core.screens.subscreen.play.level.component.event.LevelEventListener;
import com.core.screens.subscreen.play.level.component.btn.factory.LevelButtonFactory;
import com.core.screens.subscreen.play.level.component.btn.group.ButtonPair;
import com.core.screens.subscreen.play.level.component.lines.LineManager;
import com.core.screens.subscreen.play.level.component.randomizer.LevelRandomizer;

import java.util.ArrayList;
import java.util.LinkedList;

public class Level {
    private final LevelButtonFactory btnFactory;
    private final LineManager lineManager;
    private final ArrayList<ButtonPair> levelObjects;
    private final ArrayList<LevelEventListener> listeners;
    private float levelBtnSize;
    private int levelIdx;

    public Level(int levelIdx) {
        this.levelIdx = levelIdx;
        this.listeners = new ArrayList<>();
        this.levelObjects = new ArrayList<>();
        this.lineManager = new LineManager();
        calculateBtnSizes();
        this.btnFactory = new LevelButtonFactory(this);
        this.btnFactory.setBtnHeight(levelBtnSize);
        this.btnFactory.setBtnWidth(levelBtnSize);
    }

    public void addListener(LevelEventListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(LevelEventListener listener) {
        this.listeners.remove(listener);
    }

    public void notifyListener(String eventType) {
        for (LevelEventListener listener : listeners) {
            switch (eventType) {
                case "LevelCompleteFailed":
                    listener.onLevelComplete(true);
                    break;
                case "LevelComplete":
                    listener.onLevelComplete(false);
                    break;
                default:
                    return;
            }
        }
    }

    public void addPair(String word, String wordImageFile, String audio) {
        Sound sound;
        if (GameAssets.getInstance().assetManager.contains(audio, Sound.class)) {
            sound = GameAssets.getInstance().assetManager.get(audio, Sound.class);
        } else {
            sound = Gdx.audio.newSound(Gdx.files.internal(audio));
        }

        this.levelObjects.add(createPairButtons(word, new Texture(Gdx.files.internal(wordImageFile)), sound));
    }

    private ButtonPair createPairButtons(String word, Texture image, Sound audio) {
        return new ButtonPair(
            btnFactory.createWordButton(word, audio),
            btnFactory.createImageButton(image, audio),
            audio
        );
    }

    public void calculateBtnSizes() {
        this.levelBtnSize = Gdx.graphics.getWidth() * 0.3f;
    }

    public LinkedList<Button> getShuffledButtons() {
        return LevelRandomizer.generateShuffledButtons(levelObjects);
    }

    public ArrayList<ButtonPair> getLevelObjects() {
        return levelObjects;
    }

    public LineManager getLineManager() {
        return lineManager;
    }

    public int getLevelIdx() {
        return levelIdx;
    }
}
