package com.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class BitNumbersSprites {

    private final TextureRegionDrawable[] sprites;

    private static final int NUMBERLIMIT = 100;
    private final static int TILEW = 333;
    private final static int TILEH = 500;

    public BitNumbersSprites() {
        Texture spritesheet = new Texture(Gdx.files.internal("nums.png"));
        TextureRegion[][] frames = TextureRegion.split(spritesheet, TILEW, TILEH);
        sprites = new TextureRegionDrawable[NUMBERLIMIT+1];
        for(int i=0; i < NUMBERLIMIT; i++) sprites[i] = createNumberTexture(i, frames);
    }

    public TextureRegionDrawable getNumber(int i) {
        if (i >= NUMBERLIMIT || i < 0) i = 0;
        return sprites[i];
    }

    private TextureRegion combineRegions(TextureRegion a, TextureRegion b) {
        int w = a.getRegionWidth() + b.getRegionWidth() + 1;
        int h = Math.max(a.getRegionHeight(), b.getRegionHeight());

        FrameBuffer fbo = new FrameBuffer(Pixmap.Format.RGBA8888, w, h, false);
        SpriteBatch batch = new SpriteBatch();

        fbo.begin();

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(a, 0, a.getRegionHeight());
        batch.draw(b, a.getRegionWidth() + (w * 0.1f), a.getRegionHeight()); // Adjust offset if needed
        batch.end();

        fbo.end();

        TextureRegion result = new TextureRegion(fbo.getColorBufferTexture());
        result.flip(false, true);

        return result;
    }

    private TextureRegionDrawable createNumberTexture(int number, TextureRegion[][] frames) {

        TextureRegion numberTRegion = null;

        if (number >= 100) {
            number = 0;
        }

        if (number >= 10) {
            final TextureRegion firstDigit = frames[0][number / 10];
            final TextureRegion secondDigit = frames[0][number % 10];
            numberTRegion = combineRegions(firstDigit, secondDigit);
        } else {
            numberTRegion = frames[0][number];
        }

        if (numberTRegion == null || numberTRegion.getTexture() == null) return null;

        return new TextureRegionDrawable(numberTRegion);
    }
}
