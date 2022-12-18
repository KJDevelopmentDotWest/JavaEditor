package com.z7.editor.drawableshapes;

import com.z7.shapes.Figure;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class SHit {
    static HBox createPositionProperty() {
        var box = new HBox();
        var grid = new GridPane();
        box.getChildren().add(grid);
        var labelX = new Label("X: ");
        var labelY = new Label("Y");
        var inputX = new TextField();
        var inputY = new TextField();
        grid.add(labelX, 0, 0);
        grid.add(inputX, 1, 0);
        grid.add(labelY, 0, 1);
        grid.add(inputY, 1, 1);

        return box;
    }
}

public abstract class AbstractTool<T extends Figure> implements Tool<T> {
    @Override
    public List<Node> getPropertiesList() {
        var list = new ArrayList<Node>();
        list.add(SHit.createPositionProperty());
        return list;
    }
}
