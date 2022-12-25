package com.z7.editor.parameters;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class TriangleParameter extends AbstractFigureParameter {
    private final TextField sideAField;
    private final TextField sideBField;
    private final Pane panel;

    public TriangleParameter() {
        GridPane panel = new GridPane();
        panel.setPadding(new Insets(5));
        panel.setVgap(5);
        panel.setVgap(5);

        this.panel = panel;

        sideAField = new TextField();
        sideBField = new TextField();
        Label sideALabel = new Label("Side A: ");
        Label sideBLabel = new Label("Side B: ");

        panel.add(sideALabel, 0, 0);
        panel.add(sideAField, 1, 0);
        panel.add(sideBLabel, 0, 1);
        panel.add(sideBField, 1, 1);
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
        return Double.parseDouble(sideAField.getCharacters().toString());
    }

    private Double getSideB() {
        return Double.parseDouble(sideBField.getCharacters().toString());
    }
}
