package ru.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ru.abstracts.MainController;
import ru.main.Main;

/**
 * Created by vyacheslav_kozlov on 27.07.2016.
 */
public class Welcome extends MainController{
    @FXML private ImageView imgVolley;
    @FXML private Button btnCups;
    @FXML private Button btnMens;
    @FXML private Button btnWomens;
    @FXML private Button btnStatCom;
    @FXML private Button btnStatInd;

    public void initialize() {
    }

    //������� � ��������
    public void goCups(ActionEvent actionEvent) throws Exception{
        System.out.println("next");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("../fxmls/Cups.fxml"));
        Parent root = fxmlLoader.load();
        Cups cups = fxmlLoader.getController();
        stage.setScene(new Scene(root));
        cups.setStage(stage);
    }

    //������� � ��������
    public void goMens(ActionEvent actionEvent) throws Exception{
        System.out.println("next");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("../fxmls/Mens.fxml"));
        Parent root = fxmlLoader.load();
        Mens mens = fxmlLoader.getController();
        System.out.println(((Button)actionEvent.getSource()).getId());
        if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnMens")) {
            mens.setSexCommand(true);
            mens.setNameWindow("МУЖСКИЕ КОМАНДЫ");
        }
        else {
            mens.setNameWindow("ЖЕНСКИЕ КОМАНДЫ");
            mens.setSexCommand(false);
        }

        stage.setScene(new Scene(root));
        mens.setStage(stage);
    }

    //������� � ��������� ����������
    public void goStatCom(ActionEvent actionEvent) {
    }

    //������� � �������������� ����������
    public void goStatInd(ActionEvent actionEvent) {

    }
}
