package com.core.assets.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteAnimation {
    private final int frameCount;
    private final float frameTime;
    private Animation<TextureRegion> frames;
    private float stateTime;

    public SpriteAnimation(String spritesheet, int frames, float frameTime) {
        this.frameCount = frames;
        this.frameTime = frameTime;
        this.stateTime = 0f;
        loadSpriteFrames(spritesheet);
    }

    private void loadSpriteFrames(String spritesheet) {
        Texture sheet = new Texture(Gdx.files.internal(spritesheet));

        TextureRegion[][] regionsMatrix = TextureRegion.split(sheet, sheet.getWidth() / frameCount, sheet.getHeight());

        TextureRegion[] horizontal = new TextureRegion[frameCount];

        System.arraycopy(regionsMatrix[0], 0, horizontal, 0, frameCount);

        this.frames = new Animation<>(frameTime, horizontal);

    }

    public TextureRegion getFrame(float delta, boolean looping) {
        stateTime += delta;
        return frames.getKeyFrame(stateTime, looping);
    }
}
