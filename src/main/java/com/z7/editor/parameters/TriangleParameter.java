package com.z7.editor.parameters;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class TriangleParameter extends AbstractFigureParameter {
    private final TextField sideA;
    private final TextField sideB;
    private final Pane panel;

    public TriangleParameter() {
        panel = new HBox();
        sideA = new TextField();
        sideB = new TextField();

        panel.getChildren().add(sideA);
        panel.getChildren().add(sideB);
    }

    @Override
    public Pane getPanel() {
        return panel;
    }

    @Override
    public Shape createShape() {
        var polygon = new Polygon(0, 0, 0, getSideA(), getSideB(), 0);
        return polygon;
    }

    private Double getSideA() {
        return Double.parseDouble(sideA.getCharacters().toString());
    }

    private Double getSideB() {
        return Double.parseDouble(sideB.getCharacters().toString());
    }
}
