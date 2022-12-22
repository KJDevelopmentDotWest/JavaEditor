package com.z7.editor.parameters;

import com.z7.editor.drawers.CircleDrawer;
import com.z7.shapes.CircleFigure;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class CircleParameter extends AbstractFigureParameter {
    private CircleDrawer drawer = new CircleDrawer();

    private Pane panel;

    private TextField radius;

    public CircleParameter() {
        panel = new HBox();
        radius = new TextField();

        panel.getChildren().add(radius);
    }

    @Override
    public Pane getPanel() {
        return panel;
    }

    @Override
    public Shape createShape() {
        var radius = getRadius();
        var figure = new CircleFigure(radius);
        return drawer.getShape(figure);
    }

    private Double getRadius() {
        return Double.parseDouble(radius.getCharacters().toString());
    }
}
