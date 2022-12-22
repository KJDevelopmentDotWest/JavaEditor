package com.z7.editor.drawers;

import com.z7.shapes.Figure;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class TriangleDrawer extends AbstractDrawer {
    @Override
    public Shape getShape(Figure figure) {
        Polygon polygon = new Polygon(0, 0, 10, 10, 20, 20);
        return polygon;
    }
}
