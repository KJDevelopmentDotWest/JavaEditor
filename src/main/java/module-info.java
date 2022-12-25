module JavaEditor.main {
    requires javafx.controls;
    requires javafx.graphics;

    opens com.z7.editor;

    exports com.z7.editor;
    exports com.z7.editor.properties;
    exports com.z7.editor.api;
}