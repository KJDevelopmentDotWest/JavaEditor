package com.z7.editor;

import com.z7.editor.properties.Property;
import com.z7.editor.tools.Tool;
import javafx.event.EventDispatcher;
import javafx.event.EventTarget;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppController {
    private Tool selectedTool = null;

    private Pane canvas = null;

    private Pane propertiesPane;

    private Shape selectedShape;

    private Consumer<Tool> selectedToolChangingHandler;

    public void setSelectedTool(Tool selectedTool) {
        this.selectedTool = selectedTool;

        propertiesPane.getChildren().clear();

        var constructionParams = selectedTool.getParameterPanel();
        propertiesPane.getChildren().add(constructionParams);

        List<Property> properties = selectedTool.getProperties();
        var propertiesPanes = properties.stream().map((p) -> p.getPanel()).collect(Collectors.toList());

        propertiesPane.getChildren().addAll(propertiesPanes);
    }

    public Pane getCanvas() {
        return canvas;
    }

    public void setCanvas(Pane canvas) {
        this.canvas = canvas;
    }

    public void drawShape() {
        var shape = selectedTool.createShape();
        shape.setFill(Color.RED);
        shape.setStrokeType(StrokeType.OUTSIDE);
        shape.setStroke(Color.GREEN);

        var savedSelectedTool = selectedTool;

        shape.setOnMouseClicked((e) -> {
            setSelectedTool(savedSelectedTool);
            selectedToolChangingHandler.accept(savedSelectedTool);
            selectShape(shape);
        });

        List<Property> properties = selectedTool.getProperties();
        properties.stream().forEach((p) -> p.apply(shape));
        canvas.getChildren().add(shape);
    }

    public Pane getPropertiesPane() {
        return propertiesPane;
    }

    public void setPropertiesPane(Pane propertiesPanel) {
        this.propertiesPane = propertiesPanel;
    }

    public void selectShape(Shape shape) {
        if (!Objects.isNull(selectedShape)) {
            selectedShape.setStrokeWidth(0);
        }
        selectedShape = shape;
        selectedShape.setStrokeWidth(5);
    }

    public void updateProperitesOfSelectedShape() {
        if (Objects.isNull(selectedShape)) {
            return;
        }
    }

    public void setOnToolChanged(Consumer<Tool> f) {
        selectedToolChangingHandler = f;
    }
}
