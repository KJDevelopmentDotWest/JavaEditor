package com.z7.editor.tools;

import com.z7.editor.parameters.FigureParameter;
import com.z7.editor.properties.*;
import com.z7.shapes.Figure;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTool<T extends Figure> implements Tool {
    protected FigureParameter figureParameter;
    private static final List<Property> baseProperties = new ArrayList<>();

    static {
        baseProperties.add(new Position());
        baseProperties.add(new Rotation());
        baseProperties.add(new Scaling());
        baseProperties.add(new Mirror());
    }

    public Pane getParameterPanel() {
        return figureParameter.getPanel();
    }

    @Override
    public List<Property> getProperties() {
        return cloneDefaultProperties();
    }
    public final Shape createShape() {
        return figureParameter.createShape();
    }

    protected List<Property> cloneDefaultProperties() {
        return new ArrayList<>(baseProperties);
    }

}
