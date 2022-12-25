package com.z7.editor.parameters;

import com.z7.editor.drawers.CircleDrawer;
import com.z7.shapes.CircleFigure;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class CircleParameter extends AbstractFigureParameter {
    private final CircleDrawer drawer = new CircleDrawer();

    private final Pane panel;

    private final TextField radius;

    public CircleParameter() {
        GridPane panel = new GridPane();
        panel.setPadding(new Insets(5));
        panel.setVgap(5);
        panel.setHgap(5);

        this.panel = panel;

        radius = new TextField();
        Label label = new Label("Radius:");

        panel.add(label, 0, 0);
        panel.add(radius, 1, 0);
    }

    @Override
    public Pane getPanel() {
        return panel;
    }

    @Override
    public Shape createShape() {
        var radius = getRadius();
        var figure = new CircleFigure(radius);
        return drawer.getShape(figure);
    }

    private Double getRadius() {
        return Double.parseDouble(radius.getCharacters().toString());
    }
}
