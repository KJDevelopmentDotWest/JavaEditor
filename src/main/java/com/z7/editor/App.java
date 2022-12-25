package com.z7.editor;


import com.whatever.editor.api.Plugin;
import com.z7.editor.io.PluginLoader;
import com.z7.editor.tools.CircleTool;
import com.z7.editor.tools.RectangleTool;
import com.z7.editor.tools.Tool;
import com.z7.editor.tools.TriangleTool;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private static final ArrayList<Pair<String, Tool>> availableTools;

    private final PluginLoader pluginLoader = PluginLoader.getInstance();

    private List<Plugin> plugins;

    static {
        availableTools = new ArrayList<Pair<String, Tool>>();

        availableTools.add(new Pair<>("Circle", new CircleTool()));
        availableTools.add(new Pair<>("Rectangle", new RectangleTool()));
        availableTools.add(new Pair<>("Triangle", new TriangleTool()));
    }

    @Override
    public void start(Stage primaryStage) {
        var controller = new AppController();
        var grid = createGrid();
        var tools = createTools();

        var propertiesPane = new HBox();
        propertiesPane.setPadding(new Insets(10));

        var figureSelectionContainer = new VBox();
        controller.setPropertiesPane(propertiesPane);

        var figureSelection = new ChoiceBox<Pair<String, Tool>>();
        figureSelectionContainer.getChildren().add(figureSelection);

        figureSelection.setMinWidth(120);

        GridPane.setHalignment(figureSelection, HPos.CENTER);

        controller.setOnToolChanged((tool) -> {
            var variant =  availableTools.stream().filter((p) -> p.getValue() == tool).findFirst();
            figureSelection.setValue(variant.get());
        });

        figureSelection.setConverter(new StringConverter<>() {
            @Override
            public String toString(Pair<String, Tool> object) {
                return object.getKey();
            }

            @Override
            public Pair<String, Tool> fromString(String string) {
                return null;
            }
        });

        figureSelection.setOnAction((e) -> {
            controller.setSelectedTool(figureSelection.getValue().getValue());
        });

        figureSelection.getItems().addAll(tools);
        figureSelection.setValue(tools.get(0));


        var drawingButton = new Button("Create");
        var updatePropertiesButton = new Button("Update");
        figureSelectionContainer.getChildren().add(new HBox(drawingButton, updatePropertiesButton));

        updatePropertiesButton.setOnAction(e -> controller.updatePropertiesOfSelectedShape());

        drawingButton.setOnAction((e) -> {
            controller.drawShape();
        });

        GridPane.setVgrow(propertiesPane, Priority.ALWAYS);

        var canvas = new Pane();
        controller.setCanvas(canvas);

        grid.add(figureSelectionContainer, 0, 0);
        grid.add(propertiesPane, 1, 0);
        grid.add(canvas, 0, 1, 2, 1);

        Scene scene = new Scene(grid);

        primaryStage.setTitle("Grid Pane App");
        primaryStage.setScene(scene);
        primaryStage.setWidth( 1366 );
        primaryStage.setHeight( 768  );
        primaryStage.show();

        plugins = pluginLoader.loadAll();
    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }

    private static GridPane createGrid() {
        GridPane grid = new GridPane();

        grid.setPadding( new Insets(10) );

        grid.setHgap( 4 );
        grid.setVgap( 4 );

        grid.setGridLinesVisible(true);

        var firstColumn = new ColumnConstraints();
        firstColumn.setPercentWidth(10);

        var secondColumn = new ColumnConstraints();
        secondColumn.setPercentWidth(90);

        var firstRowConstrains = new RowConstraints(60);

        var secondRowConstrains = new RowConstraints();
        secondRowConstrains.setVgrow(Priority.ALWAYS);

        grid.getColumnConstraints().addAll(firstColumn, secondColumn);
        grid.getRowConstraints().add(firstRowConstrains);
        grid.getRowConstraints().add(secondRowConstrains);

        VBox.setVgrow(grid, Priority.ALWAYS);

        return grid;
    }

    private static List<Pair<String, Tool>> createTools() {
        return availableTools;
    }

}
