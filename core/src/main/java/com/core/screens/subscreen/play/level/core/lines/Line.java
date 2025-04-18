package com.core.screens.gameplay.level.lines;

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

    public Line(Vector2 first, Vector2 second, Color color) {
        this.first = first;
        this.second = second;
        this.color = color;
    }

    public Line(int x1, int y1, int x2, int y2, String color) {
        this.first = new Vector2(x1, y1);
        this.second = new Vector2(x2, y2);
        this.color = Color.valueOf(color);
    }
}
