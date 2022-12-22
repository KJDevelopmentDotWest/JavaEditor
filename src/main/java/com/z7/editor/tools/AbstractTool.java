package com.z7.editor.tools;

import com.z7.editor.properties.Position;
import com.z7.editor.properties.Property;
import com.z7.shapes.Figure;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTool<T extends Figure> implements Tool<T> {
    private static final List<Property> baseProperties = new ArrayList<>();

    AbstractTool() {
        baseProperties.add(new Position());
    }

    @Override
    public List<Property> getProperties() {
        var properties = new ArrayList<>(baseProperties);
        return properties;
    }

    protected List<Property> cloneDefaultProperties() {
        return new ArrayList<>(baseProperties);
    }
}
