package com.core.screens.subscreen.play.level.component.lines;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Line {
    public Vector2 first;
    public Vector2 second;
    public Color color;

    public Line() {
        this.first = new Vector2();
        this.second = new Vector2();
        this.color = null;
    }

    public Line(final Line other) {
        this.first = new Vector2(other.first);
        this.second = new Vector2(other.second);
        if (other.color != null)
            this.color = new Color(other.color);
    }
}
