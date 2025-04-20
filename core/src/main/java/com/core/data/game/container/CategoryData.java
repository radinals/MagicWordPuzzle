package com.core.data.game.container;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class CategoryData {
    private final String categoryName;
    private final String categoryIconFile;
    private final LevelData[] levels;
    private final String categoryBackgroundImage;
    private Color levelIconColor;
    private int levelCount;


    public CategoryData(String categoryName, String categoryIcon, int levelCount, String categoryBgFile) {
        this.categoryName = categoryName;
        this.levelCount = 0;
        this.levels = new LevelData[levelCount];
        for (int i = 0; i < levelCount; this.levels[i++] = null) ;
        this.categoryIconFile = categoryIcon;
        this.categoryBackgroundImage = categoryBgFile;
        this.levelIconColor = null;
    }

    public String getCategoryBackgroundImage() {
        return categoryBackgroundImage;
    }

    public Color getLevelIconColor() {
        return levelIconColor;
    }

    public void setLevelIconColor(Color color) {
        this.levelIconColor = color;
    }

    public String getCategoryIcon() {
        return categoryIconFile;
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

    public void addCard(int levelIdx, CardData card) throws ArrayIndexOutOfBoundsException {
        if (this.levels[levelIdx] == null) {
            this.levels[levelIdx] = new LevelData();
            levelCount++;
        }
        this.levels[levelIdx].addCard(card);
    }

}
