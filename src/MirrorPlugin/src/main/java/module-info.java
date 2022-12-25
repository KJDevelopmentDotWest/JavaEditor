module JavaEditor.src.MirrorPlugin.main {
    requires JavaEditor.main;
    requires javafx.graphics;
    requires javafx.controls;

    opens com.z7.editor.plugin;

    exports com.z7.editor.plugin;
}