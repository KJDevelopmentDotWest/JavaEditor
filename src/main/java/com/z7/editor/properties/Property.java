package com.z7.editor.properties;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

public interface Property {
    Node getPanel();

    void apply(Shape shape);
}
