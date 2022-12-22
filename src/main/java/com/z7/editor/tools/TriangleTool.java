package com.z7.editor.tools;

import com.z7.editor.drawers.Drawer;
import com.z7.editor.drawers.TriangleDrawer;
import com.z7.editor.properties.AbstractProperty;
import com.z7.editor.properties.Property;
import com.z7.shapes.Triangle;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Shape;

import java.util.List;

public class TriangleTool extends AbstractTool<Triangle> {
    private final parameters = new TriangleParameters();

    private final TriangleDrawer drawer = new TriangleDrawer();

    @Override
    public List<Property> getProperties() {
        var properties = cloneDefaultProperties();

        return properties;
    }

    @Override
    public Drawer<Triangle> getDrawer() {
        return drawer;
    }

    @Override
    public Triangle createFigure() {
        return null;
    }

    @Override
    public Shape createShape() {
        return null;
    }

    private static class TriangleSidesProperties extends AbstractProperty {
        @Override
        public Node getPanel() {
            return new HBox();
        }
    }
}
