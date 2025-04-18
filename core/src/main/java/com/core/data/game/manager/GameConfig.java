package com.core.data.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.XmlReader;
import com.core.data.game.container.CardData;
import com.core.data.game.container.CategoryData;

import java.util.HashMap;
import java.util.LinkedList;

public class GameConfig {

    private final HashMap<String, CategoryData> categories;

    private final LinkedList<CardData> cards;

    public GameConfig(String configXML) {
        this.cards = new LinkedList<>();
        this.categories = new HashMap<>();
        loadConfig(configXML);
    }

    public HashMap<String, CategoryData> getCategories() {
        return categories;
    }

    public LinkedList<CardData> getCards() {
        return cards;
    }

    public void loadConfig(String configXML) {
        FileHandle file = Gdx.files.internal(configXML);
        XmlReader reader = new XmlReader();

        try {
            XmlReader.Element root = reader.parse(file);
            for (XmlReader.Element category : root.getChildrenByName("category")) {

                int levelCount = Integer.parseInt(category.getAttribute("levels", "0"));
                String categoryName = category.getAttribute("name", null);
                String categoryIcon = category.getAttribute("icon", null);
                String levelIconColor = category.getAttribute("LevelIconColor", "#FFFFFF");
                String bgImage = category.getAttribute("categorybg", "test.png");

                if (categoryName == null || categoryIcon == null) continue;

                CategoryData categoryData = new CategoryData(categoryName, categoryIcon, levelCount, bgImage);

                categoryData.setLevelIconColor(Color.valueOf(levelIconColor));

                for (XmlReader.Element card : category.getChildrenByName("card")) {
                    int levelId = Integer.parseInt(card.getAttribute("group", "-1"));

                    String word = card.getAttribute("word", null);
                    String icon = card.getAttribute("img", null);
                    String audio = card.getAttribute("audio", null);

                    if (word == null || icon == null || levelId == -1) continue;

                    CardData cardData = new CardData(word, icon, audio);

                    this.cards.add(cardData);
                    categoryData.addCard(levelId, cardData);
                }

                categories.put(categoryName, categoryData);
            }

        } catch (Exception e) {
            Gdx.app.exit();
        }

    }

}
