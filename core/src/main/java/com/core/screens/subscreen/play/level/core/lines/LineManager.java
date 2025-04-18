package com.core.screens.gameplay.level.lines;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class LineManager {

    private final ShapeRenderer renderer;
    private boolean renderHalted;
    private boolean activeLineHasStartingPoint;
    private boolean activeLineHasEndPoint;
    private ArrayList<Line> lines;
    private final Line activeLine;
    private Color normalLineColor;
    private Color incorrectLineColor;
    private Color correctLineColor;
    private float lineSize;
    public LineManager() {
        this.renderer = new ShapeRenderer();
        this.lines = new ArrayList<>();
        this.renderHalted = true;
        this.lineSize = 1.0f;
        this.activeLine = new Line();
        this.activeLineHasStartingPoint = false;
        this.activeLineHasEndPoint = false;
        this.incorrectLineColor = Color.BLACK;
        this.correctLineColor = Color.BLACK;
        this.normalLineColor = Color.BLACK;
        this.activeLine.color = normalLineColor;
    }

    public void render() {
        if (isLineRenderingHalted()) return;
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        if (isActiveLineFormed())
            drawLine(activeLine);
        for (Line line : lines) {
            drawLine(line);
        }
        renderer.end();
    }

    public void storeCurrentActiveLine(LineType type) throws Exception {
        if (!isActiveLineFormed()) throw new Exception("Cannot Store malformed line");
        Line tmp = new Line(this.activeLine);
        switch (type) {
            case IncorrectLine -> tmp.color = incorrectLineColor;
            case CorrectLine -> tmp.color = correctLineColor;
            case NormalLine -> tmp.color = normalLineColor;
        }
        this.lines.add(tmp);
    }

    public void setActiveLineStart(int x, int y) {
        this.activeLine.first.x = x;
        this.activeLine.first.y = y;
        this.activeLineHasStartingPoint = true;
    }

    public void setActiveLineEnd(int x, int y) {
        this.activeLine.second.x = x;
        this.activeLine.second.y = y;
        this.activeLineHasEndPoint = true;
    }

    public void setActiveLineStart(float x, float y) {
        this.activeLine.first.x = x;
        this.activeLine.first.y = y;
        this.activeLineHasStartingPoint = true;
    }

    public void setActiveLineEnd(float x, float y) {
        this.activeLine.second.x = x;
        this.activeLine.second.y = y;
        this.activeLineHasEndPoint = true;
    }

    public void resetActiveLine() {
        this.activeLineHasStartingPoint = false;
        this.activeLineHasEndPoint = false;
    }

    public final Line getActiveLine() {
        return new Line(activeLine);
    }

    public boolean isActiveLineFormed() {
        return activeLineHasStartingPoint && activeLineHasEndPoint;
    }

    private void drawLine(final Line line) {
        if (line == null || line.first == null || line.second == null) return;
        renderer.setColor((line.color != null) ? line.color : normalLineColor);
        renderer.rectLine(line.first, line.second, lineSize);
    }

    public LineManager setNormalLineColor(Color normalLineColor) {
        this.normalLineColor = normalLineColor;
        return this;
    }

    public LineManager setCorrectLineColor(Color correctLineColor) {
        this.correctLineColor = correctLineColor;
        return this;
    }

    public LineManager setIncorrectLineColor(Color incorrectLineColor) {
        this.incorrectLineColor = incorrectLineColor;
        return this;
    }

    public LineManager setLineSize(float lineSize) {
        this.lineSize = lineSize;
        return this;
    }

    public boolean isLineRenderingHalted() {
        return this.renderHalted;
    }

    public int getTotalStaticLines() {
        return lines.size();
    }

    public void haltLineRendering() {
        this.renderHalted = true;
    }

    public void unHaltLineRendering() {
        this.renderHalted = false;
    }

    public enum LineType {
        IncorrectLine,
        CorrectLine,
        NormalLine,
    }

}
