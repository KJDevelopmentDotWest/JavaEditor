package com.z7.editor;


import com.whatever.editor.api.Plugin;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Manifest;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane gp = new GridPane();

        gp.setPadding( new Insets(10) );

        gp.setHgap( 4 );
        gp.setVgap( 4 );

        gp.setGridLinesVisible(true);

        var firstColumn = new ColumnConstraints();
        var secondColumn = new ColumnConstraints();
        firstColumn.setPercentWidth(10);
        secondColumn.setPercentWidth(90);

        gp.getColumnConstraints().addAll(firstColumn, secondColumn);

        gp.getRowConstraints().add(new RowConstraints(50));

        var secondRowConstrains = new RowConstraints();
        secondRowConstrains.setVgrow(Priority.ALWAYS);

        gp.getRowConstraints().add(secondRowConstrains);
        VBox.setVgrow(gp, Priority.ALWAYS);

        var figureSelection = new ChoiceBox<Pair<String, String>>();
        figureSelection.setMinWidth(120);
        GridPane.setHalignment(figureSelection, HPos.CENTER);


        var toolPalette = new HBox();
        GridPane.setVgrow(toolPalette, Priority.ALWAYS);

        toolPalette.getChildren().add(new Text("Tool Paletter"));

        var canvasContainer = new Pane();
        var rect = new Rectangle(40, 50, 30, 30);
        rect.setFill(Color.GREEN);

        var circle = new Circle(100, 100, 300, Color.PINK);

        canvasContainer.getChildren().addAll(rect, circle);
        canvasContainer.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));

        gp.add(figureSelection, 0, 0);
        gp.add(toolPalette, 1, 0);
        gp.add(canvasContainer, 0, 1, 2, 1);
        Scene scene = new Scene(gp);

        primaryStage.setTitle("Grid Pane App");
        primaryStage.setScene(scene);
        primaryStage.setWidth( 1366 );
        primaryStage.setHeight( 768  );
        primaryStage.show();


    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }

    public Plugin load(String fileName) {
        if (!fileName.endsWith(".jar")){
            throw new IllegalArgumentException("File is not a jar");
        }
        File file = new File(fileName);
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
