package com.z7.editor.plugin;

import com.z7.editor.properties.Property;

public class Plugin implements com.z7.editor.api.Plugin{
    @Override
    public Property getProperty() {
        return new Mirror();
    }
}
