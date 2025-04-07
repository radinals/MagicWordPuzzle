package com.game.level.events;

public interface LevelEventListener {
    public void onImagePressed(int id);
    public void onWordPressed(int id);

    public void onImageReleased(int id);
    public void onWordReleased(int id);

}
