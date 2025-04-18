package com.core.screens.gameplay.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.core.screens.gameplay.level.btn.LevelPairButtons;
import com.core.screens.gameplay.level.btn.binding.LevelButtonFactory;
import com.core.screens.gameplay.level.lines.LineManager;
import com.core.screens.gameplay.level.randomizer.LevelRandomizer;
import com.main.Main;

import java.util.ArrayList;
import java.util.LinkedList;

public class Level {
    private final Main main;
    private ArrayList<LevelPairButtons> levelObjects;
    private final LevelButtonFactory btnFactory;

    private Sound currentPlayingSound;

    private final LineManager lineManager;

    private float levelBtnSize;

    public Level(Main main) {
        this.main = main;
        this.levelObjects = new ArrayList<>();
        this.lineManager = new LineManager();
        calculateBtnSizes();
        this.btnFactory = new LevelButtonFactory(this);
        this.btnFactory.setBtnHeight(levelBtnSize);
        this.btnFactory.setBtnWidth(levelBtnSize);
        this.currentPlayingSound = null;
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

    public void setCurrentPlayingSound(Sound currentPlayingSound) {
        this.currentPlayingSound = currentPlayingSound;
    }

    private LevelPairButtons createPairButtons(String word, Texture image, Sound audio) {
        return new LevelPairButtons(
            btnFactory.createWordButton(word, audio),
            btnFactory.createImageButton(image, audio)
        );
    }

    public void calculateBtnSizes() {
        this.levelBtnSize = Gdx.graphics.getWidth() * 0.3f;
    }

    public float getLevelBtnSize() {
        return levelBtnSize;
    }

    public LinkedList<Button> getShuffledButtons() {
        return LevelRandomizer.generateShuffledButtons(levelObjects);
    }

    public ArrayList<LevelPairButtons> getLevelObjects() {
        return levelObjects;
    }

    public LineManager getLineManager() {
        return lineManager;
    }
}
