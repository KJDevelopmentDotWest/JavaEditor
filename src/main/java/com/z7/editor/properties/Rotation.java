package com.z7.editor.properties;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class Rotation extends AbstractProperty {
    private Pane panel;

    private TextField angleField;

    public Rotation() {
        GridPane panel = new GridPane();

        panel.setPadding(new Insets(5));
        panel.setHgap(5);
        panel.setVgap(5);

        angleField = new TextField();
        Label angleLabel = new Label("Angle:");

        this.panel = panel;
    }

    @Override
    public Node getPanel() {
        return panel;
    }

    @Override
    public void apply(Shape shape) {

    }

    protected Double getAngle() {
        return Double.parseDouble(angleField.getCharacters().toString());
    }
}
