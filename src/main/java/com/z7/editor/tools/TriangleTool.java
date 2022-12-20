package com.z7.editor.tools;

import com.z7.editor.properties.AbstractProperty;
import com.z7.editor.properties.Property;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.List;

public class TriangleTool extends AbstractTool {
    @Override
    public List<Property> getProperties() {
        var properties = cloneDefaultProperties();

        return properties;
    }

    private static class TriangleSidesProperties extends AbstractProperty {
        @Override
        public Node getPanel() {
            return new HBox();
        }
    }
}
