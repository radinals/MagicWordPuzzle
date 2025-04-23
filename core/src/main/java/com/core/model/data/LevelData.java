package com.core.model.data;

import java.util.ArrayList;

public class LevelData {

    private final ArrayList<CardData> cards;

    public LevelData() {
        this.cards = new ArrayList<>();
    }

    public ArrayList<CardData> getCards() {
        return cards;
    }

    public void addCard(CardData card) {
        this.cards.add(card);
    }
}
