package com.core.screens.subscreen.play.level.core.randomizer;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.core.screens.subscreen.play.level.core.btn.group.ButtonPair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LevelRandomizer {

    public static LinkedList<Button> generateShuffledButtons(ArrayList<ButtonPair> levelObjects) {

        LinkedList<Button> btns = new LinkedList<>();

        int[] imgrand = generateRandomNumbers(levelObjects.size());
        int[] wordrand = generateRandomNumbers(levelObjects.size());

        for (int i = 0; i < levelObjects.size(); i++) {
            int imgIdx = imgrand[i];
            int wordIdx = wordrand[i];

            btns.add(levelObjects.get(wordIdx).wordButton);
            btns.add(levelObjects.get(imgIdx).imgButton);
        }

        return btns;

    }

    private static int[] generateRandomNumbers(int n) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        Collections.shuffle(list);

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = list.get(i);
        }
        return result;

    }

}
