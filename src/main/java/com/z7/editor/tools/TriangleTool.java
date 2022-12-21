package com.z7.editor.tools;

import com.z7.editor.drawers.Drawer;
import com.z7.editor.drawers.TriangleDrawer;
import com.z7.editor.properties.AbstractProperty;
import com.z7.editor.properties.Property;
import com.z7.shapes.Triangle;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.List;

public class TriangleTool extends AbstractTool {
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

    private static class TriangleSidesProperties extends AbstractProperty {
        @Override
        public Node getPanel() {
            return new HBox();
        }
    }
}
