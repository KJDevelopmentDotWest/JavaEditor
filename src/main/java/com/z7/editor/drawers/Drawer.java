package com.z7.editor.drawers;

import com.z7.shapes.Figure;
import javafx.scene.shape.Shape;

public interface Drawer<T extends Figure> {
    Shape getShape(T figure);
}
