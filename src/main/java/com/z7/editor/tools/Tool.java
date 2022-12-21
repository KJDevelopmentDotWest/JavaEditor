package com.z7.editor.tools;

import com.z7.editor.drawers.Drawer;
import com.z7.editor.properties.Property;

import java.util.List;

public interface Tool {
    List<Property> getProperties();

    Drawer getDrawer();
}
