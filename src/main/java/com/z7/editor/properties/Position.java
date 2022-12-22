package com.z7.editor.properties;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class Position extends AbstractProperty {
    private final Pane panel;
    private final TextField fieldX;
    private final TextField fieldY;

    public Position() {
        panel = new HBox();
        fieldX = new TextField();
        fieldY = new TextField();

        panel.getChildren().add(fieldX);
        panel.getChildren().add(fieldY);
    }

    @Override
    public Node getPanel() {
        return panel;
    }

    @Override
    public void apply(Shape shape) {
        var x = getX();
        var y = getY();

        shape.setTranslateX(x);
        shape.setTranslateY(y);
    }

    protected Double getX() {
        return Double.parseDouble(fieldX.getCharacters().toString());
    }

    protected Double getY() {
        return Double.parseDouble(fieldY.getCharacters().toString());
    }
}
