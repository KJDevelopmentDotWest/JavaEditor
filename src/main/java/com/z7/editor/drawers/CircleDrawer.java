package com.z7.editor.drawers;

import com.z7.shapes.CircleFigure;
import javafx.scene.shape.Circle;

public class CircleDrawer extends AbstractDrawer<CircleFigure> {
    @Override
    public Circle getShape(CircleFigure circle) {
        var radius = circle.getRadius();
        var shape = new Circle(radius);
        shape.setCenterX(radius);
        shape.setCenterY(radius);

        return shape;
    }
}
