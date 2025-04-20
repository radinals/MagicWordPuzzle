package com.core.data.assets.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.XmlReader;
import com.core.data.assets.font.BitNumbersSprites;

public class GameAssets {

    private static GameAssets singleton = null;
    private final BitNumbersSprites bitNumbers;
    public AssetManager assetManager;

    private GameAssets() {
        this.assetManager = new AssetManager();
        this.bitNumbers = new BitNumbersSprites();
        loadConfig("data/assetConfig.xml");
    }

    public static boolean loadAssets() {
        if (singleton == null)
            singleton = new GameAssets();
        return singleton.assetManager.update();
    }

    public static GameAssets getInstance() {
        return singleton;
    }

    public BitNumbersSprites getBitNumbers() {
        return bitNumbers;
    }

    public void loadConfig(String configXML) {
        FileHandle file = Gdx.files.internal(configXML);
        XmlReader reader = new XmlReader();

        try {
            XmlReader.Element root = reader.parse(file);
            for (XmlReader.Element category : root.getChildrenByName("texture")) {
                String filename = category.getAttribute("file");
                assetManager.load(filename, Texture.class);
            }

            for (XmlReader.Element audio : root.getChildrenByName("audio")) {
                String filename = audio.getAttribute("file");
                assetManager.load(filename, Sound.class);
            }

            for (XmlReader.Element audio : root.getChildrenByName("music")) {
                String filename = audio.getAttribute("file");
                assetManager.load(filename, Music.class);
            }

        } catch (Exception e) {
            Gdx.app.exit();
        }

    }

}
