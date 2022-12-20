package com.z7.editor;

import com.z7.editor.tools.Tool;
import javafx.scene.layout.Pane;

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
}
