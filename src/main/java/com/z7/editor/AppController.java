package com.z7.editor;

import com.z7.editor.tools.Tool;

public class AppController {
    private Tool selectedTool = null;

    public Tool getSelectedTool() {
        return selectedTool;
    }

    public void setSelectedTool(Tool selectedTool) {
        this.selectedTool = selectedTool;
    }
}
