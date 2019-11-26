package riskgamegui;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

public class RiskGameGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        SVGPath svgPath = new SVGPath();

        for (String path : Map.getMap()) {
            svgPath = new SVGPath();
            svgPath.setContent(path);
           // svgPath.setP;
            svgPath.setFill(Color.BISQUE);

            root.getChildren().add(svgPath);
        }
        root.scaleXProperty().add(100);
        SVGPath svg = (SVGPath) root.getChildren().get(48);
        svg.setFill(Color.BLACK);
        Scene scene = new Scene(root, 1200, 1000);

        primaryStage.setTitle("Risk");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
