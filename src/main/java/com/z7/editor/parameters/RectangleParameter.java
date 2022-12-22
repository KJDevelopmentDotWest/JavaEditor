package com.z7.editor.parameters;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RectangleParameter extends AbstractFigureParameter {
    private Pane panel;
    private TextField widthField;
    private TextField heightField;

    public RectangleParameter() {
        panel = new HBox();
        widthField = new TextField();
        heightField = new TextField();

        panel.getChildren().add(widthField);
        panel.getChildren().add(heightField);
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
