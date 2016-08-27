package ru.abstracts;

import javafx.stage.Stage;

/**
 * Created by vyacheslav_kozlov on 23.07.2016.
 */
abstract public class MainController {
    protected Stage stage;
    protected double stage_width;
    protected double stage_height;

    public MainController(){

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
