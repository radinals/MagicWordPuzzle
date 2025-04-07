package com.game.level;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.game.actor.MatchingPair;
import com.game.level.events.LevelEventListener;

import org.w3c.dom.Text;

import java.awt.Image;
import java.awt.font.ImageGraphicAttribute;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import de.damios.guacamole.tuple.Pair;

public class Level {

    private ArrayList<MatchingPair> pairs;
    private ArrayList<LevelEventListener> eventListeners;
    private int matchCounter;
    private enum LevelEventType { ImagePressed, ImagePressReleased, WordPressed, WordPressReleased };
    private Stage stage;
    private Table table;
    private ArrayList<Input.Buttons> buttons;

    public Level() {
        this.table = new Table();
        this.table.setFillParent(true);

        this.stage = new Stage();
        this.stage.addActor(this.table);

        this.buttons = new ArrayList<>();

        this.pairs = new ArrayList<>();
    }

    public Level addPair(String word, String imgPath) {
        this.eventListeners = null;
        this.pairs.add(new MatchingPair(word, imgPath));
        return this;
    }

    public void clearPairs() {
        this.pairs.clear();
    }

    public void addLevelEventListener(LevelEventListener listener) {
       if(this.eventListeners == null) this.eventListeners = new ArrayList<>();
       this.eventListeners.add(listener);
    }

    public void removeListener(LevelEventListener listener) {
        this.eventListeners.remove(listener);
    }

    private void notifyLevelEvent(LevelEventType type, int id) {
        if (eventListeners == null) return;
        for(LevelEventListener listener : eventListeners) {
            switch (type) {
                case ImagePressed:
                    listener.onImagePressed(id);
                    break;

                case ImagePressReleased:
                    listener.onImageReleased(id);
                    break;

                case WordPressed:
                    listener.onWordPressed(id);
                    break;

                case WordPressReleased:
                    listener.onWordReleased(id);
                    break;
            }
        }
    }

    private void onImageButtonPressed(int id) {
        notifyLevelEvent(LevelEventType.ImagePressed, id);
    }
    private void onWordButtonPressed(int id) {
        notifyLevelEvent(LevelEventType.WordPressed, id);
    }

    private void onImageButtonPressedRelease(int id) {
        notifyLevelEvent(LevelEventType.ImagePressReleased, id);
    }

    private void onWordButtonPressedRelease(int id) {
        notifyLevelEvent(LevelEventType.WordPressReleased, id);
    }

    // TODO: NEED TO BE DESIGNED
    private ImageButton createImageButton(String imgPath) {

        return null;
    }

    // TODO: NEED TO BE DESIGNED
    private TextButton createWordButton(String word) {

        return null;
    }

    // TODO: randomizer
    private int randomInt(int min, int max) {
        return 0;
    }

    public void initStage() {

        table.clear();

        Stack<Integer> wordsIdx = new Stack<>();
        Stack<Integer> imgIdx = new Stack<>();

        // TODO: need to randomize
        for(int i = 0; i < pairs.size(); i++) {

            int randImg=-1;
            do {
                randImg = randomInt(0, pairs.size());
            }while(imgIdx.contains(randImg));

            int randWord=-1;
            do {
                randWord = randomInt(0, pairs.size());
            }while(wordsIdx.contains(randWord));

            String word = pairs.get(randWord).getWord();
            String image = pairs.get(randImg).getImagePath();

            table.add(createWordButton(word));
            table.add(createImageButton(image));

            table.row();

            wordsIdx.add(randWord);
            imgIdx.add(randImg);
        }
    }

    public boolean isMatchingPairs(String word, String img) {
        MatchingPair p = new MatchingPair(word,img);
        for(MatchingPair pair : pairs)
            if (pair.equals(p)) return true;
        return false;
    }

    public void incMatchCounter() {
        matchCounter++;
    }

    public void resetMatchCounter() {
        matchCounter = 0;
    }

    public boolean isAllPairsMatched() {
        return matchCounter >= pairs.size();
    }

    public int getMatchCounter() {
        return matchCounter;
    }
}
