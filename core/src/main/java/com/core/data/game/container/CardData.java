package com.core.data.game.container;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class CardData {
    private final String word;
    private final Texture img;
    private final String audiofile;

    public CardData(String word, String imgFile, String audiofile) {
        this.audiofile = audiofile;
        this.word = word;
        this.img = new Texture(Gdx.files.internal(imgFile));
    }

    public String getWord() {
        return this.word;
    }

    public Texture getImg() {
        return this.img;
    }

    public String getAudio() {
        return audiofile;
    }
}
