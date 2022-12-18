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
}
