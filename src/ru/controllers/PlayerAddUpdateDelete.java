package ru.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.objects.Player;

/**
 * Created by vyacheslav_kozlov on 03.08.2016.
 */
public class PlayerAddUpdateDelete {

    @FXML private Label lblNameWindow;
    @FXML private TextField tfSurname;
    @FXML private TextField tfName;
    @FXML private TextField tfPatronymic;
    @FXML private ComboBox cbNumber;

    private Player player;
    private int id_command = 0;

    public void initialize() {
        cbNumber.setMaxHeight(50);

        ObservableList<Integer> mas = FXCollections.observableArrayList(100);
        for(int i = 1; i < 100; i++) mas.add(i);
        mas.stream().forEach(System.out::println);
        cbNumber.setItems(mas);
        cbNumber.setVisibleRowCount(10);
    }

    public void goOk(ActionEvent actionEvent) {
        if (tfName.getText().trim().equalsIgnoreCase("") || tfSurname.getText().trim().equalsIgnoreCase("")) {
            System.out.println("fields = null");
            return;
        }
        if (player == null) {
            System.out.println("player = null");
            player = new Player(tfSurname.getText().trim(), tfName.getText().trim(), tfPatronymic.getText().trim(),
                    (Integer) cbNumber.getValue(), 0);
            System.out.println(player);
            goCancel(actionEvent);
            return;
        }
        System.out.println("editplayer");
        player.setSurname(tfSurname.getText().trim());
        player.setName(tfName.getText().trim());
        player.setPatronymic(tfPatronymic.getText().trim());
        player.setNumber((Integer) cbNumber.getValue());
        goCancel(actionEvent);
    }

    public void goCancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setLblNameWindow(String nameWindow) {
        this.lblNameWindow.setText(nameWindow);
    }

    public void setCommandId(int id_command) {
        this.id_command = id_command;
    }

    public void setPlayer(Player player) {
        System.out.println(player);
        this.player = player;
        tfSurname.setText(player.getSurname());
        tfName.setText(player.getName());
        tfPatronymic.setText(player.getPatronymic());
        cbNumber.setValue(player.getNumber());
//        cbNumber.setItems(FXCollections.observableArrayList(player.getNumber()));
//        cbNumber.setValue(cbNumber.getItems().get(0));
    }

    public Player getPlayer() {
        return player;
    }

    public void setDisable(){
        tfName.setEditable(false);
        tfSurname.setEditable(false);
        tfPatronymic.setEditable(false);
    }

    public int getId_command() {
        return id_command;
    }
}
