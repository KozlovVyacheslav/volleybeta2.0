package ru.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.controllers.Welcome;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

//        Rectangle2D primaryBounds = Screen.getPrimary().getBounds();
//        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("../fxmls/Welcome.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("volleybeta2.0");
        primaryStage.setResizable(false);
        primaryStage.setWidth(1360);
        primaryStage.setHeight(730);
        primaryStage.setScene(scene);

        Welcome welcome = fxmlLoader.getController();
        welcome.setStage(primaryStage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
