package com.z7.editor.properties;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class Rotation extends AbstractProperty {
    private final Pane panel;

    private final TextField angleField;

    public Rotation() {
        GridPane panel = new GridPane();

        panel.setPadding(new Insets(5));
        panel.setHgap(5);
        panel.setVgap(5);

        this.panel = panel;

        angleField = new TextField();
        Label angleLabel = new Label("Angle:");

        panel.add(angleLabel, 0, 0);
        panel.add(angleField, 1, 0);
    }

    @Override
    public Node getPanel() {
        return panel;
    }

    @Override
    public void apply(Shape shape) {
        shape.setRotate(getAngle());
    }

    protected Double getAngle() {
        return Double.parseDouble(angleField.getCharacters().toString());
    }
}
