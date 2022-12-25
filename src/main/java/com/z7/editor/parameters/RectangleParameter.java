package com.z7.editor.parameters;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RectangleParameter extends AbstractFigureParameter {
    private final Pane panel;
    private final TextField widthField;
    private final TextField heightField;

    public RectangleParameter() {
        GridPane panel = new GridPane();
        panel.setPadding(new Insets(5));
        panel.setHgap(5);
        panel.setVgap(5);

        this.panel = panel;

        widthField = new TextField();
        heightField = new TextField();

        Label widthLabel = new Label("Width:");
        Label heightLabel = new Label("Height:");

        panel.add(widthLabel, 0, 0);
        panel.add(widthField, 1, 0);
        panel.add(heightLabel, 0, 1);
        panel.add(heightField, 1, 1);
    }

    @Override
    public Pane getPanel() {
        return panel;
    }

    @Override
    public Shape createShape() {
        var width = getWidth();
        var height = getHeight();

        return new Rectangle(width, height);
    }

    private Double getWidth() {
        return Double.parseDouble(widthField.getCharacters().toString());
    }

    private Double getHeight() {
        return Double.parseDouble(heightField.getCharacters().toString());
    }
}
