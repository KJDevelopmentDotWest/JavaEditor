package com.z7.editor.tools;

import com.z7.editor.drawers.CircleDrawer;
import com.z7.editor.drawers.Drawer;
import com.z7.editor.properties.AbstractProperty;
import com.z7.editor.properties.Property;
import com.z7.shapes.Circle;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.List;

public class CircleTool extends AbstractTool<Circle> {
    private final CircleDrawer drawer = new CircleDrawer();

    @Override
    public List<Property> getProperties() {
        var properties = cloneDefaultProperties();
        properties.add(new RadiusProperty());
        return properties;
    }

    @Override
    public Drawer<Circle> getDrawer() {
        return drawer;
    }

    @Override
    public Circle createFigure() {
        return new Circle();
    }

    private static class RadiusProperty extends AbstractProperty {
        @Override
        public Node getPanel() {
            return new HBox();
        }
    }
}
