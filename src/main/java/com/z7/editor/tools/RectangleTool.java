package com.z7.editor.tools;

import com.z7.editor.properties.AbstractProperty;
import com.z7.editor.properties.Property;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.List;

public class RectangleTool extends AbstractTool {
    @Override
    public List<Property> getProperties() {
        var properties = cloneDefaultProperties();
        return null;
    }

    private static class SidesProperty extends AbstractProperty {
        @Override
        public Node getPanel() {
            return new HBox();
        }
    }
}
