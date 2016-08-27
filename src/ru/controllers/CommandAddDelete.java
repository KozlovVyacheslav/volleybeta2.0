package ru.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ru.abstracts.SQLController;
import ru.objects.Command;
import ru.sql_controllers.SQLCommand;

import java.sql.SQLException;

/**
 * Created by vyacheslav_kozlov on 23.08.2016.
 */
public class CommandAddDelete extends SQLController {

    @FXML private TableView tblCommands;
    @FXML private TableColumn clmName;
    @FXML private TableColumn clmCoach;
    @FXML private TableColumn clmSponsor;

    private ObservableList<Command> commands = FXCollections.observableArrayList();
    private SQLCommand sqlCommand = new SQLCommand();
    private Command command;
    private boolean sex;

    public void initialize() {

        clmName.setCellValueFactory(new PropertyValueFactory<Command, String>("name"));
        clmCoach.setCellValueFactory(new PropertyValueFactory<Command, String>("coach"));
        clmSponsor.setCellValueFactory(new PropertyValueFactory<Command, String>("sponsor"));

        tblCommands.setItems(commands);

    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(ObservableList<Command> commands, boolean sex) {
        this.sex = sex;

        try {
            sqlCommand.Connection();
            sqlCommand.select(this.commands);
            tblCommands.setItems(this.commands.filtered(c -> c.isSex() == sex && commands.stream().
                    noneMatch(p -> p.getId() == c.getId()) ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void goOk(ActionEvent actionEvent) {
        System.out.println("Ok");
        if (tblCommands.getSelectionModel().getSelectedItem() == null) return;
        command = (Command) tblCommands.getSelectionModel().getSelectedItem();
        System.out.println(command);
        goCancel(actionEvent);
    }

    public void goCancel(ActionEvent actionEvent) {
        System.out.println("Cancel");
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
