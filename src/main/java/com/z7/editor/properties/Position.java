package com.z7.editor.properties;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class Position extends AbstractProperty {
    private final Pane panel;
    private final TextField fieldX;
    private final TextField fieldY;

    public Position() {
        GridPane panel = new GridPane();
        panel.setPadding(new Insets(5));
        panel.setVgap(5);
        panel.setHgap(5);

        this.panel = panel;

        fieldX = new TextField();
        fieldY = new TextField();

        Label fieldXLabel = new Label("X");
        Label fieldYLabel = new Label("Y");

        panel.add(fieldXLabel, 0, 0);
        panel.add(fieldX, 1, 0);
        panel.add(fieldYLabel, 0, 1);
        panel.add(fieldY, 1, 1);
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
