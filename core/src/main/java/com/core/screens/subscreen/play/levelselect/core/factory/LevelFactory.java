package com.core.screens.subscreen.play.levelselect.core.factory;

import com.core.data.game.container.CardData;
import com.core.data.game.container.CategoryData;
import com.core.data.game.container.LevelData;
import com.core.screens.subscreen.play.level.core.Level;
import com.core.screens.subscreen.play.level.core.btn.event.LevelCompleteEvent;
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

        Level level = new Level();

        level.addListener(new LevelCompleteEvent(main, this, levelIdx));

        for (CardData card : levelData.getCards())
            level.addPair(card.getWord(), card.getImgFile(), card.getAudio());

        return level;
    }
}
