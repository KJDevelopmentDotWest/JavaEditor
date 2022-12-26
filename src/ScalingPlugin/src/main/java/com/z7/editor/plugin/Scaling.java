package com.z7.editor.plugin;

import com.z7.editor.properties.AbstractProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class Scaling extends AbstractProperty {
    private final Pane panel;

    private final TextField scalingFactorField;

    public Scaling() {
        GridPane panel = new GridPane();
        panel.setPadding(new Insets(5));
        panel.setHgap(5);
        panel.setVgap(5);

        this.panel = panel;

        scalingFactorField = new TextField();
        Label scalingFactorLabel = new Label("Scale: ");

        panel.add(scalingFactorLabel, 0, 0);
        panel.add(scalingFactorField, 1, 0);
    }

    @Override
    public Node getPanel() {
        return panel;
    }

    @Override
    public void apply(Shape shape) {
        double scalingFactor = getScalingFactor();

        var currentScale = shape.getScaleY();
        shape.setScaleX(scalingFactor);

        if (currentScale < 0) {
            shape.setScaleY(-scalingFactor);
        } else {
            shape.setScaleY(scalingFactor);
        }
    }

    protected Double getScalingFactor() {
        return Double.parseDouble(scalingFactorField.getCharacters().toString());
    }
}
