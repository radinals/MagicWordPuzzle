package com.game.data.container;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.LinkedList;

public class CategoryData {
    private String categoryName;
    private Texture categoryIcon;
    private LevelData[] levels;
    private int levelCount;


    public CategoryData(String categoryName, String categoryIcon, int levelCount) {
        this.categoryName = categoryName;
        this.levelCount = 0;
        this.levels = new LevelData[levelCount];
        for(int i = 0; i < levelCount; this.levels[i++] = null);
        this.categoryIcon = new Texture(Gdx.files.internal(categoryIcon));
    }

    public Texture getCategoryIcon() {
        return categoryIcon;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public LevelData[] getLevels() {
        return levels;
    }

    public int getLevelCount() {
        return this.levelCount;
    }

    public void addCard(int levelIdx, String word, String imgFile) throws ArrayIndexOutOfBoundsException {
        if (this.levels[levelIdx] == null ) {
            this.levels[levelIdx] = new LevelData();
            levelCount++;
        }
        this.levels[levelIdx].addCard(word, imgFile);
    }

    public void addCard(int levelIdx, CardData card) throws ArrayIndexOutOfBoundsException {
        if (this.levels[levelIdx] == null ) {
            this.levels[levelIdx] = new LevelData();
            levelCount++;
        }
        this.levels[levelIdx].addCard(card);
    }

}
