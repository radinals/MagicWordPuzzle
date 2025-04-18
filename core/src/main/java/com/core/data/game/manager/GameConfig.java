package com.game.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.game.data.container.CardData;
import com.game.data.container.CategoryData;
import com.game.data.container.LevelData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class GameConfig {

    private HashMap<String, CategoryData> categories;

    private LinkedList<CardData> cards;

    public GameConfig() {
        this.cards = new LinkedList<>();
        this.categories = new HashMap<>();
    }

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

                int levelCount = Integer.valueOf(category.getAttribute("levels", "0"));
                String categoryName = category.getAttribute("name", null);
                String categoryIcon = category.getAttribute("icon", null);

                if(categoryName == null || categoryIcon == null) continue;

                CategoryData categoryData = new CategoryData(categoryName, categoryIcon, levelCount);


                for (XmlReader.Element card : category.getChildrenByName("card")) {
                    Integer levelId = Integer.valueOf(card.getAttribute("group", "-1"));

                    String word = card.getAttribute("word", null);
                    String icon = card.getAttribute("img", null);
                    if(word == null || icon == null || levelId == -1) continue;

                    CardData cardData = new CardData(word,icon);
                    this.cards.add(cardData);
                    categoryData.addCard(levelId, cardData);
                }

                categories.put(categoryName, categoryData);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }

    }

}
