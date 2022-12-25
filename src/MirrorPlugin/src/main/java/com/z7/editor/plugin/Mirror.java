package com.z7.editor.plugin;

import com.z7.editor.properties.AbstractProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class Mirror extends AbstractProperty {
    private final Pane panel;
    private final CheckBox mirrorCheckbox;

    public Mirror() {
        GridPane panel = new GridPane();

        panel.setPadding(new Insets(5));
        panel.setVgap(5);
        panel.setHgap(5);

        this.panel = panel;
        mirrorCheckbox = new CheckBox();
        Label mirrorLabel = new Label("Mirror: ");

        panel.add(mirrorLabel, 0, 0);
        panel.add(mirrorCheckbox, 1, 0);
    }

    @Override
    public Node getPanel() {
        return this.panel;
    }

    @Override
    public void apply(Shape shape) {
        boolean isMirrored = getMirrorValue();

        double currentVerticalScaling = shape.getScaleY();
        if (isMirrored) {
            shape.setScaleY(-currentVerticalScaling);
        } else {
            shape.setScaleY(Math.abs(currentVerticalScaling));
        }
    }

    protected Boolean getMirrorValue() {
        return mirrorCheckbox.isSelected();
    }
}
