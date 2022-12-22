package com.z7.editor.tools;

import com.z7.editor.drawers.RectangleDrawer;
import com.z7.editor.parameters.RectangleParameter;
import com.z7.editor.properties.Property;
import com.z7.shapes.RectangleFigure;

import java.util.List;

public class RectangleTool extends AbstractTool<RectangleFigure> {
    private final RectangleDrawer drawer = new RectangleDrawer();

    public RectangleTool() {
        figureParameter = new RectangleParameter();
    }

    @Override
    public List<Property> getProperties() {
        return cloneDefaultProperties();
    }
}
