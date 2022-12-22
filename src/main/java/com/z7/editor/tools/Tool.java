package com.z7.editor.tools;

import com.z7.editor.drawers.Drawer;
import com.z7.editor.properties.Property;
import com.z7.shapes.Figure;
import javafx.scene.shape.Shape;

import java.util.List;

public interface Tool<T extends Figure> {
    List<Property> getProperties();

    Drawer<T> getDrawer();

    T createFigure();

    Shape createShape();
}
