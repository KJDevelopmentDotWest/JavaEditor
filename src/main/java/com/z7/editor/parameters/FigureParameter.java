package com.z7.editor.parameters;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public interface FigureParameter {
    Pane getPanel();

    Shape createShape();
}
