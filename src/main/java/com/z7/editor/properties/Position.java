package com.z7.editor.properties;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class Position extends AbstractProperty {
    @Override
    public Node getPanel() {
        return new HBox();
    }
}
