package com.z7.editor.tools;

import com.z7.editor.properties.AbstractProperty;
import com.z7.editor.properties.Property;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.List;

public class CircleTool extends AbstractTool {
    @Override
    public List<Property> getProperties() {
        var properties = cloneDefaultProperties();
        properties.add(new RadiusProperty());
        return properties;
    }

    private static class RadiusProperty extends AbstractProperty {
        @Override
        public Node getPanel() {
            return new HBox();
        }
    }
}
