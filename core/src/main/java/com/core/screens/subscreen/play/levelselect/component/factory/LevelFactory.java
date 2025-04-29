package com.core.screens.subscreen.play.levelselect.component.factory;

import com.core.model.data.CardData;
import com.core.model.data.CategoryData;
import com.core.model.data.LevelData;
import com.core.screens.subscreen.play.level.component.Level;
import com.main.Main;

public class LevelFactory {

    private final Main main;
    private final CategoryData categoryData;

    public LevelFactory(Main main, String levelCategory) {
        this.main = main;
        this.categoryData = main.getGameConfig().getCategories().get(levelCategory);
    }

    public boolean isValidLevelIdx(int levelIdx) {
        LevelData[] levelData = categoryData.getLevels();
        return levelIdx < levelData.length && levelData[levelIdx] != null;
    }

    public Level createLevel(int levelIdx) throws IndexOutOfBoundsException {
        if (!isValidLevelIdx(levelIdx))
            throw new IndexOutOfBoundsException(String.format("level index of (%d) is not found", levelIdx));

        LevelData levelData = categoryData.getLevels()[levelIdx];

        Level level = new Level(levelIdx);

        for (CardData card : levelData.getCards())
            level.addPair(card.getWord(), card.getImgFile(), card.getAudio());

        return level;
    }
}
