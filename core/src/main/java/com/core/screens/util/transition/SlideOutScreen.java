package com.core.screens.util.transition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.main.Main;

public class SlideOutScreen implements Screen {

    private final Screen oldScreen;
    private final Screen newScreen;

    private final Main main;

    private final float duration;
    private final FrameBuffer oldFrameBuffer;
    private final FrameBuffer newFrameBuffer;
    private final boolean slideLeft;
    private float currentTime;
    private Texture oldScreenTexture;
    private Texture newScreenTexture;

    public SlideOutScreen(Main main, Screen oldScreen, Screen newScreen, float duration, SlideDirection direction) {
        Gdx.graphics.setContinuousRendering(true);
        this.main = main;

        this.duration = duration;
        this.currentTime = 0;

        this.oldScreen = oldScreen;
        this.newScreen = newScreen;

        this.oldScreenTexture = null;
        this.newScreenTexture = null;

        this.oldFrameBuffer = createFrameBuffer();
        this.newFrameBuffer = createFrameBuffer();

        switch (direction) {
            case LEFT:
                this.slideLeft = true;
                break;
            case RIGHT:
            default:
                this.slideLeft = false;
        }

        this.oldScreen.pause();
        this.newScreen.pause();

        newScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        newScreen.show();


    }

    private static FrameBuffer createFrameBuffer() {
        return new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
    }

    @Override
    public void show() {
        this.newScreen.show();
        this.newScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.newScreen.render(0);

    }

    @Override
    public void render(float delta) {
        currentTime = Math.min(currentTime + delta, duration);
        float alpha = currentTime / duration;

        oldFrameBuffer.begin();
        oldScreen.render(delta);
        oldFrameBuffer.end();
        oldScreenTexture = oldFrameBuffer.getColorBufferTexture();

        newFrameBuffer.begin();
        newScreen.render(delta);
        newFrameBuffer.end();
        newScreenTexture = newFrameBuffer.getColorBufferTexture();

        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float width = oldScreenTexture.getWidth();
        float offset = slideLeft ? -width * alpha : width * alpha;

        main.getBatch().begin();
        main.getBatch().draw(oldScreenTexture,
            offset, 0,
            oldScreenTexture.getWidth(), oldScreenTexture.getHeight(), // width, height
            0, 0, 1, 1);

        main.getBatch().draw(newScreenTexture,
            offset + (slideLeft ? oldScreenTexture.getWidth() : -oldScreenTexture.getWidth()), 0,
            newScreenTexture.getWidth(), newScreenTexture.getHeight(),
            0, 0, 1, 1);

        main.getBatch().end();

        if (currentTime >= duration) {
            newScreen.resume();
            oldScreen.resume();
            Gdx.graphics.setContinuousRendering(false);
            Gdx.graphics.requestRendering();
            main.setScreen(newScreen);
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public enum SlideDirection {
        LEFT, RIGHT
    }
}
