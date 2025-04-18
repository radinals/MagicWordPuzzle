package com.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.XmlReader;

import java.util.HashMap;

public class GameAssets {

    public HashMap<String, TextureRegionDrawable> textures;
    private BitNumbersSprites bitNumbers;

    public GameAssets() {
        this.textures = new HashMap<>();
        this.bitNumbers = new BitNumbersSprites();
    }

    public BitNumbersSprites getBitNumbers() {
        return bitNumbers;
    }

    public void loadConfig(String configXML) {
        FileHandle file = Gdx.files.internal(configXML);
        XmlReader reader = new XmlReader();

        try {
            XmlReader.Element root = reader.parse(file);
            for (XmlReader.Element category : root.getChildrenByName("asset")) {
                String filename = category.getAttribute("file");
                String alias = category.getAttribute("alias");

                this.textures.put(alias, new TextureRegionDrawable(new Texture(Gdx.files.internal(filename))));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }

    }

}
