package com.z7.editor.tools;

import com.z7.editor.drawers.Drawer;
import com.z7.editor.drawers.RectangleDrawer;
import com.z7.editor.properties.AbstractProperty;
import com.z7.editor.properties.Property;
import com.z7.shapes.Rectangle;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.List;

public class RectangleTool extends AbstractTool<Rectangle> {
    private final RectangleDrawer drawer = new RectangleDrawer();

    @Override
    public List<Property> getProperties() {
        var properties = cloneDefaultProperties();
        return null;
    }

    @Override
    public Drawer<Rectangle> getDrawer() {
        return drawer;
    }

    @Override
    public Rectangle createFigure() {
        return new Rectangle();
    }

    private static class SidesProperty extends AbstractProperty {
        @Override
        public Node getPanel() {
            return new HBox();
        }
    }
}
