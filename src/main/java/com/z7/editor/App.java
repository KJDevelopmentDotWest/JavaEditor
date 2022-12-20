package com.z7.editor;


import com.whatever.editor.api.Plugin;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.StringConverter;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.Manifest;
import java.util.stream.Collectors;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        var controller = new AppController();
        var grid = createGrid();
        var tools = createTools();

        var figureSelectionContainer = new VBox();

        var figureSelection = new ChoiceBox<Pair<String, Tool>>();
        figureSelectionContainer.getChildren().add(figureSelection);

        figureSelection.setMinWidth(120);

        GridPane.setHalignment(figureSelection, HPos.CENTER);

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
        figureSelectionContainer.getChildren().add(drawingButton);

        var toolPalette = new HBox();
        GridPane.setVgrow(toolPalette, Priority.ALWAYS);

        var canvas = new Pane();

        grid.add(figureSelectionContainer, 0, 0);
        grid.add(toolPalette, 1, 0);
        grid.add(canvas, 0, 1, 2, 1);

        Scene scene = new Scene(grid);

        primaryStage.setTitle("Grid Pane App");
        primaryStage.setScene(scene);
        primaryStage.setWidth( 1366 );
        primaryStage.setHeight( 768  );
        primaryStage.show();

        loadAll().forEach(Plugin::print);
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
        var tools = new ArrayList<Pair<String, Tool>>();

        tools.add(new Pair<>("Circle", new CircleTool()));
        tools.add(new Pair<>("Rectangle", new RectangleTool()));
        tools.add(new Pair<>("Triangle", new TriangleTool()));

        return tools;
    }

    public List<Plugin> loadAll() {
        String path = App.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = URLDecoder.decode(path, StandardCharsets.UTF_8)
                .replaceAll(new File(path).getName(), "/plugins");

        File folder = new File(decodedPath);
        File[] files = folder.listFiles();
        if (files == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(files).map(file -> load(file.toString())).collect(Collectors.toList());
    }

    public Plugin load(String filePath) {
        if (!filePath.endsWith(".jar")){
            throw new IllegalArgumentException("File is not a jar");
        }
        File file = new File(filePath);
        URL url;
        try {
            url = file.toURI().toURL();
        } catch (MalformedURLException e){
            throw new IllegalArgumentException(e);
        }

        ClassLoader pluginClassLoader = Plugin.class.getClassLoader();
        try(URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url}, pluginClassLoader)){
            Manifest manifest = new Manifest(urlClassLoader.findResource("META-INF/MANIFEST.MF").openStream());
            String mainClassName = manifest.getMainAttributes().getValue("Main-Class").replaceAll(".java", "").replaceAll("/", ".");
            Class<?> loadedClass = urlClassLoader.loadClass(mainClassName);
            return (Plugin) loadedClass.getDeclaredConstructor().newInstance();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("File is not found, Manifest is not found or Manifest does not contain Main-Class attribute");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Main-Class attribute is invalid");
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Main class is not a Plugin");
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
