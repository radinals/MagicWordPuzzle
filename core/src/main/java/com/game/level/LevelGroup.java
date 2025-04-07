package com.game.level;

import java.util.ArrayList;

public class LevelGroup {
    private ArrayList<Level> levels;

    private String groupName;

    public LevelGroup(String levelGroupDesignJSONPath) {
        levels = new ArrayList<>();
        loadLevelGroupDesign(levelGroupDesignJSONPath);
    }

    private void loadLevelGroupDesign(String file) {

    }

    public ArrayList<Level> getLevels() {
        return levels;
    }
}
