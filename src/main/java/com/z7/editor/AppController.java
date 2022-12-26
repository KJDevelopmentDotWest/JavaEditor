package com.z7.editor;

import com.z7.editor.api.Plugin;
import com.z7.editor.io.PluginLoader;
import com.z7.editor.properties.Property;
import com.z7.editor.tools.Tool;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AppController {
    private Tool selectedTool;

    private Pane canvas;

    private Pane propertiesPane;

    private Shape selectedShape;

    private Consumer<Tool> selectedToolChangingHandler;

    private final List<Plugin> plugins;

    private final List<Property> loadedProperties;

    public AppController() {
        var pluginLoader = PluginLoader.getInstance();
        plugins = pluginLoader.loadAll();
        loadedProperties = plugins.stream().map(Plugin::getProperty).toList();
    }

    public void setSelectedTool(Tool selectedTool) {
        this.selectedTool = selectedTool;

        propertiesPane.getChildren().clear();

        var constructionParams = selectedTool.getParameterPanel();
        propertiesPane.getChildren().add(constructionParams);

        List<Property> properties = selectedTool.getProperties();

        var propertiesPanes = properties.stream().map(Property::getPanel).toList();
        propertiesPane.getChildren().addAll(propertiesPanes);

        var loadedPanes = loadedProperties.stream().map(Property::getPanel).toList();
        propertiesPane.getChildren().addAll(loadedPanes);
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
        properties.forEach((p) -> p.apply(shape));
        loadedProperties.forEach((p) -> p.apply(shape));
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

    public void updatePropertiesOfSelectedShape() {
        if (Objects.isNull(selectedShape)) {
            return;
        }

        List<Property> properties = selectedTool.getProperties();
        properties.forEach((p) -> p.apply(selectedShape));

        loadedProperties.forEach((p) -> p.apply(selectedShape));
    }

    public void setOnToolChanged(Consumer<Tool> f) {
        selectedToolChangingHandler = f;
    }
}
