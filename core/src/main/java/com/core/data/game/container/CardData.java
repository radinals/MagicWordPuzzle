package com.game.data.container;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CardData {
    private String word;
    private Texture img;

    public CardData(String word, String imgFile) {
        this.word = word;
        this.img = new Texture(Gdx.files.internal(imgFile));
    }

    public String getWord() {
        return this.word;
    }

    public Texture getImg() {
        return this.img;
    }
}
