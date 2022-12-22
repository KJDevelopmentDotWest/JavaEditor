package com.z7.editor.tools;

import com.z7.editor.drawers.TriangleDrawer;
import com.z7.editor.parameters.TriangleParameter;
import com.z7.editor.properties.Property;
import com.z7.shapes.TriangleFigure;

import java.util.List;

public class TriangleTool extends AbstractTool<TriangleFigure> {
    private final TriangleDrawer drawer = new TriangleDrawer();

    public TriangleTool() {
        figureParameter = new TriangleParameter();
    }

    @Override
    public List<Property> getProperties() {
        var properties = cloneDefaultProperties();

        return properties;
    }
}
