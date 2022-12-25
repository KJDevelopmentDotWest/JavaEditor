module JavaEditor.src.RotationPlugin.main {
    requires JavaEditor.main;
    requires javafx.controls;
    requires javafx.graphics;

    opens com.z7.editor.plugin;

    exports com.z7.editor.plugin;
}