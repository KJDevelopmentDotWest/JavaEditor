package com.z7.editor.drawableshapes;

import com.z7.editor.drawers.Drawer;
import com.z7.shapes.Figure;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.List;

public interface Tool<T extends Figure> {
    List<Node> getPropertiesList();

    Drawer<T> getDrawer();
}
