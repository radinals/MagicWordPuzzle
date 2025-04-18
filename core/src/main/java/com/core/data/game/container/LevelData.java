package com.game.data.container;

import com.game.level.Level;

import java.util.ArrayList;

public class LevelData {

    private ArrayList<CardData> cards;

    public LevelData() {
        this.cards = new ArrayList<>();
    }

    public void addCard(String word, String imgFile) {
        this.cards.add(new CardData(word, imgFile));
    }

    public ArrayList<CardData> getCards() {
        return cards;
    }

    public void addCard(CardData card) {
        this.cards.add(card);
    }
}
