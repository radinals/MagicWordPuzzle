package com.core.screens.subscreen.play.level.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.core.screens.subscreen.play.level.core.btn.event.LevelEventListener;
import com.core.screens.subscreen.play.level.core.btn.factory.LevelButtonFactory;
import com.core.screens.subscreen.play.level.core.btn.group.ButtonPair;
import com.core.screens.subscreen.play.level.core.lines.LineManager;
import com.core.screens.subscreen.play.level.core.randomizer.LevelRandomizer;
import com.main.Main;

import java.util.ArrayList;
import java.util.LinkedList;

public class Level {
    private final Main main;
    private final LevelButtonFactory btnFactory;
    private final LineManager lineManager;
    private final ArrayList<ButtonPair> levelObjects;
    private Sound currentPlayingSound;
    private float levelBtnSize;
    private final ArrayList<LevelEventListener> listeners;

    public Level(Main main) {
        this.main = main;
        this.listeners = new ArrayList<>();
        this.levelObjects = new ArrayList<>();
        this.lineManager = new LineManager();
        calculateBtnSizes();
        this.btnFactory = new LevelButtonFactory(main, this);
        this.btnFactory.setBtnHeight(levelBtnSize);
        this.btnFactory.setBtnWidth(levelBtnSize);
        this.currentPlayingSound = null;
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
                case "LevelComplete":
                    listener.onLevelComplete();
                    break;
                default:
                    return;
            }
        }
    }

    public void addPair(String word, Texture wordImage, String audio) {
        Sound sound;
        if (main.gameAssets.assetManager.contains(audio, Sound.class)) {
            sound = main.gameAssets.assetManager.get(audio, Sound.class);
        } else {
            sound = Gdx.audio.newSound(Gdx.files.internal(audio));
        }
        this.levelObjects.add(createPairButtons(word, wordImage, sound));
    }

    public Sound getCurrentPlayingSound() {
        return currentPlayingSound;
    }

    private ButtonPair createPairButtons(String word, Texture image, Sound audio) {
        return new ButtonPair(
            btnFactory.createWordButton(word, audio),
            btnFactory.createImageButton(image, audio)
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
}
