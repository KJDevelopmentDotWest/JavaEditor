package com.z7.editor;

import com.z7.editor.drawers.Drawer;
import com.z7.editor.tools.Tool;
import com.z7.shapes.Figure;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class AppController {
    private Tool selectedTool = null;

    private Pane canvas = null;

    public Tool getSelectedTool() {
        return selectedTool;
    }

    public void setSelectedTool(Tool selectedTool) {
        this.selectedTool = selectedTool;
    }

    public Pane getCanvas() {
        return canvas;
    }

    public void setCanvas(Pane canvas) {
        this.canvas = canvas;
    }

    public <T extends Figure> void drawShape() {
        Tool<T> tool = selectedTool;
        Drawer<T> drawer = selectedTool.getDrawer();

        tool.getProperties();

        T figure = tool.createFigure();

        var shape = drawer.getShape(figure);

        canvas.getChildren().add(shape);
    }
}
