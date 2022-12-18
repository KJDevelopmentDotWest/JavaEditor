package com.z7.editor.drawableshapes;

import com.z7.editor.drawers.CircleDrawer;
import com.z7.editor.drawers.Drawer;
import com.z7.shapes.Circle;

public class CircleTool extends AbstractTool<Circle> {
    private CircleDrawer drawer = new CircleDrawer();

    @Override
    public Drawer<Circle> getDrawer() {
        return drawer;
    }
}
