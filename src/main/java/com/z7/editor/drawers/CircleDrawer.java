package com.z7.editor.drawers;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CircleDrawer extends AbstractDrawer<com.z7.shapes.Circle> {
    @Override
    public Circle getShape(com.z7.shapes.Circle circle) {
        var radius = circle.getRadius();

        return new Circle(radius);
    }
}
