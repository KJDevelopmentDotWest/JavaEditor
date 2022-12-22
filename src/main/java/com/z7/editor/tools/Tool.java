package com.z7.editor.tools;

import com.z7.editor.properties.Property;
import com.z7.shapes.Figure;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.util.List;

public interface Tool<T extends Figure> {
    Pane getParameterPanel();

    List<Property> getProperties();

    Shape createShape();
}
