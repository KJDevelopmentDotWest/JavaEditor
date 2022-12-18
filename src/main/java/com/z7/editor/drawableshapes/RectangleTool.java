package com.z7.editor.drawableshapes;

import com.z7.editor.drawers.Drawer;
import com.z7.editor.drawers.RectangleDrawer;
import com.z7.shapes.Rectangle;

public class RectangleTool extends AbstractTool<Rectangle> {
    private RectangleDrawer drawer = new RectangleDrawer();

    @Override
    public Drawer<Rectangle> getDrawer() {
        return drawer;
    }
}
