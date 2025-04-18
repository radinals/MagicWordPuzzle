package com.core.data.game.container;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class CategoryData {
    private final String categoryName;
    private final Texture categoryIcon;
    private final LevelData[] levels;
    private final Texture categoryBackgroundImage;
    private Color levelIconColor;
    private int levelCount;


    public CategoryData(String categoryName, String categoryIcon, int levelCount, String categoryBgFile) {
        this.categoryName = categoryName;
        this.levelCount = 0;
        this.levels = new LevelData[levelCount];
        for (int i = 0; i < levelCount; this.levels[i++] = null) ;
        this.categoryIcon = new Texture(Gdx.files.internal(categoryIcon));
        this.categoryBackgroundImage = new Texture(Gdx.files.internal(categoryBgFile));
        this.levelIconColor = null;
    }

    public Texture getCategoryBackgroundImage() {
        return categoryBackgroundImage;
    }

    public Color getLevelIconColor() {
        return levelIconColor;
    }

    public void setLevelIconColor(Color color) {
        this.levelIconColor = color;
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

    public void addCard(int levelIdx, CardData card) throws ArrayIndexOutOfBoundsException {
        if (this.levels[levelIdx] == null) {
            this.levels[levelIdx] = new LevelData();
            levelCount++;
        }
        this.levels[levelIdx].addCard(card);
    }

}
