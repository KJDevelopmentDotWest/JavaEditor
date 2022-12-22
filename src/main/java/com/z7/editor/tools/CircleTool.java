package com.z7.editor.tools;

import com.z7.editor.drawers.CircleDrawer;
import com.z7.editor.parameters.CircleParameter;
import com.z7.shapes.CircleFigure;

public class CircleTool extends AbstractTool<CircleFigure> {
    private final CircleDrawer drawer = new CircleDrawer();

    public CircleTool() {
        figureParameter = new CircleParameter();
    }
}
