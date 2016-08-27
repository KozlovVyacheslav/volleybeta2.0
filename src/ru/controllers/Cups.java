package ru.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.abstracts.MainController;
import ru.main.Main;
import ru.objects.Command;
import ru.objects.Cup;
import ru.sql_controllers.SQLCommand;
import ru.sql_controllers.SQLCup;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Locale;

/**
 * Created by vyacheslav_kozlov on 27.07.2016.
 */
public class Cups extends MainController{

    @FXML private TableView tblCommandsWoman;
    @FXML private TableColumn clmNameCommandWoman;
    @FXML private TableColumn clmCoachCommandWoman;
    @FXML private TableColumn clmSponsorCommandWoman;
    @FXML private Button btnPlusCommandWoman;
    @FXML private Button btnMinusCommandWoman;

    @FXML private TableView tblCups;
    @FXML private TableColumn clmName;
    @FXML private TableColumn clmSponsor;
    @FXML private TableColumn clmDataStart;
    @FXML private TableColumn clmDataEnd;

    @FXML private TableView tblCommands;
    @FXML private TableColumn clmNameCommand;
    @FXML private TableColumn clmCoachCommand;
    @FXML private TableColumn clmSponsorCommand;

    @FXML private TextField tfName;
    @FXML private TextField tfSponsor;
    @FXML private DatePicker dpStart;
    @FXML private DatePicker dpEnd;
    @FXML private Button btnPlusCommand;
    @FXML private Button btnMinusCommand;

    @FXML private Button btnClear;
    @FXML private Button btnDelete;
    @FXML private Button btnEdit;
    @FXML private Button btnSave;
    @FXML private Button btnNew;

    private ObservableList<Cup> cups = FXCollections.observableArrayList();
    private ObservableList<Command> commands = FXCollections.observableArrayList();

    private SQLCup sqlCup = new SQLCup();
    private SQLCommand sqlCommand = new SQLCommand();
    private Cup cup;

    public void initialize() throws SQLException {

        btnEdit.setDisable(true);
        btnSave.setDisable(true);

        tblCups.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        if (tblCups.getSelectionModel().getSelectedItem() != null) {
                            System.out.println("Double clicked");
                            cup = (Cup) tblCups.getSelectionModel().getSelectedItem();
                            System.out.println(cup);
                            tblCups.getSelectionModel().clearSelection();

                            btnDelete.setDisable(false);
                            btnSave.setDisable(true);
                            btnEdit.setDisable(false);

                            fillFields();
                            try {
                                fillTableCommand();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else System.out.println("no");
                    }
                }
            }
        });

        clmName.setCellValueFactory(new PropertyValueFactory<Cup, String>("name"));
        clmSponsor.setCellValueFactory(new PropertyValueFactory<Cup, String>("sponsor"));
        clmDataStart.setCellValueFactory(new PropertyValueFactory<Cup, Date>("dateStart"));
        clmDataEnd.setCellValueFactory(new PropertyValueFactory<Cup, Date>("dateEnd"));

        clmNameCommand.setCellValueFactory(new PropertyValueFactory<Command, String>("name"));
        clmCoachCommand.setCellValueFactory(new PropertyValueFactory<Command, String>("coach"));
        clmSponsorCommand.setCellValueFactory(new PropertyValueFactory<Command, String>("sponsor"));

        tblCommandsWoman.setItems(commands.filtered(c -> c.isSex()));

        clmNameCommandWoman.setCellValueFactory(new PropertyValueFactory<Command, String>("name"));
        clmCoachCommandWoman.setCellValueFactory(new PropertyValueFactory<Command, String>("coach"));
        clmSponsorCommandWoman.setCellValueFactory(new PropertyValueFactory<Command, String>("sponsor"));

        tblCommandsWoman.setItems(commands.filtered(c -> !c.isSex()));

        sqlCup.Connection();
        sqlCup.select(cups);

        tblCups.setItems(cups);
    }

    // ���������� ������� �� �������, ��������� ��� ���� � ������� �������
    private void fillFields () {
        tfName.setText(cup.getName());
        tfSponsor.setText(cup.getSponsor());
        dpStart.setValue(cup.getDateStart().toLocalDate());
        dpEnd.setValue(cup.getDateEnd().toLocalDate());
    }

    // �������� ������� �������
    private void fillTableCommand() throws SQLException{
        System.out.println("fillTablePlayers");
        commands.clear();

        sqlCommand.Connection();
        sqlCommand.select(commands);

        tblCommands.setItems(commands.filtered(c -> c.isSex() && cup.getId_commands().stream().anyMatch(p -> p == c.getId())));
        System.out.println("������� �������");
        commands.stream().filter(c -> c.isSex()).forEach(System.out::println);
        tblCommandsWoman.setItems(commands.filtered(c -> !c.isSex() && cup.getId_commands().stream().anyMatch(p -> p == c.getId())));
        System.out.println("������� �������");
        commands.stream().filter(c -> !c.isSex()).forEach(System.out::println);
        tblCommands.refresh();
        tblCommandsWoman.refresh();
    }

    public void goBack(ActionEvent actionEvent) throws Exception{
        System.out.println("next");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("../fxmls/Welcome.fxml"));
        Parent root = fxmlLoader.load();
        Welcome welcome = fxmlLoader.getController();
        stage.setScene(new Scene(root));
        welcome.setStage(stage);
    }

    public void goPlusCommand(ActionEvent actionEvent) throws Exception{
        System.out.println("goPlusCommand");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("../fxmls/CommandAddDelete.fxml"));
        Parent root = fxmlLoader.load();
        CommandAddDelete commandAddDelete = fxmlLoader.getController();

        if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnPlusCommand")) {
            System.out.println("click btnPlusCommand");
            if (cup == null) {
                cup = new Cup();
                commandAddDelete.setSex(commands.filtered(c -> c.isSex()), true);
            }
            else commandAddDelete.setSex(commands.filtered(c -> c.isSex() && cup.getId_commands().
                    stream().anyMatch(p -> p == c.getId())), true);
        }
        else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnPlusCommandWoman")) {
            if (cup == null) {
                cup = new Cup();
                commandAddDelete.setSex(commands.filtered(c -> !c.isSex()), false);
            }
            else commandAddDelete.setSex(commands.filtered(c -> !c.isSex() && cup.getId_commands().
                    stream().anyMatch(p -> p == c.getId())), false);
        }

        Scene scene = new Scene(root);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.setResizable(false);
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.initOwner(stage);
        stage1.showAndWait();

        if (commandAddDelete.getCommand() != null) {
            if(cup.getId() != 0){
                cup.addIdCommand(commandAddDelete.getCommand().getId());
                cup.toStringIdCommands();
                System.out.println("return command: " + commandAddDelete.getCommand());
                tblCommands.setItems(commands.filtered(c -> c.isSex()&& cup.getId_commands().stream().anyMatch(p -> p == c.getId())));
                System.out.println("������� �������");
                commands.stream().filter(c -> c.isSex()).forEach(System.out::println);
                tblCommandsWoman.setItems(commands.filtered(c -> !c.isSex()&& cup.getId_commands().stream().anyMatch(p -> p == c.getId())));
                System.out.println("������� �������");
                commands.stream().filter(c -> !c.isSex()).forEach(System.out::println);
            }
            else if (cup.getId() == 0) {
                commands.add(commandAddDelete.getCommand());
                cup.addIdCommand(commandAddDelete.getCommand().getId());
                cup.toStringIdCommands();
                tblCommands.setItems(commands.filtered(c -> c.isSex()));
                tblCommandsWoman.setItems(commands.filtered(c -> !c.isSex()));

            }

            tblCommands.refresh();
            tblCommandsWoman.refresh();
        }
    }

    public void goMinusCommand(ActionEvent actionEvent) {
        System.out.println("goMinusCommand");
        if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnMinusCommand")) {
            if (tblCommands.getSelectionModel().getSelectedItem() == null) return;
            cup.removeIdCommand(((Command)tblCommands.getSelectionModel().getSelectedItem()).getId());
            commands.remove((Command)tblCommands.getSelectionModel().getSelectedItem());
            tblCommands.refresh();
        }
        else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnMinusCommandWoman")) {
            if (tblCommandsWoman.getSelectionModel().getSelectedItem() == null) return;
            cup.removeIdCommand(((Command)tblCommandsWoman.getSelectionModel().getSelectedItem()).getId());
            commands.remove((Command)tblCommandsWoman.getSelectionModel().getSelectedItem());
            tblCommandsWoman.refresh();
        }
    }

    public void goClear(ActionEvent actionEvent) {
        System.out.println("goClear");
        clearTF();
    }

    // �������� ��� ����
    public void clearTF() {
        System.out.println("btnClear");
        tfName.setText("");
        tfSponsor.setText("");
        dpStart.setValue(null);
        dpEnd.setValue(null);

        commands.clear();
        cup = null;
        btnEdit.setDisable(true);
    }

    public void goDelete(ActionEvent actionEvent) throws SQLException {
        System.out.println("goDelete");
        cups.remove(cup);
        sqlCup.Connection();
        sqlCup.delete(cup);
        btnSave.setDisable(true);
        clearTF();
    }

    public void goEdit(ActionEvent actionEvent) throws SQLException {
        System.out.println("goEdit");
        if (tfName.getText().trim().equalsIgnoreCase("") || tfSponsor.getText().trim().equalsIgnoreCase("") ||
                dpStart.getValue() == null || dpEnd.getValue() == null || commands.size() == 0) {
            System.out.println("�� ��� ���� ���������!");
            return;
        }
        cup.setName(tfName.getText().trim());
        cup.setSponsor(tfSponsor.getText().trim());
        cup.setDateStart(java.sql.Date.valueOf(dpStart.getValue()));
        cup.setDateEnd(java.sql.Date.valueOf(dpEnd.getValue()));

        sqlCup.Connection();
        sqlCup.update(cup);

        Cup tempCup = new Cup(cup.getId(), cup.getName(), cup.getSponsor(), cup.getDateStart(), cup.getDateEnd());

        System.out.println("loop");
        sqlCup.addCupCommand(cup.getId(), cup.getId_commands().filtered(p -> tempCup.getId_commands().stream().noneMatch(c -> c == p)));
        tempCup.getId_commands().stream().filter(p -> cup.getId_commands().stream().noneMatch(c -> c == p)).forEach(System.out::println);

        System.out.println("2loop");
        sqlCup.deleteCupCommand(cup.getId(), cup.getId_commands().filtered(p -> tempCup.getId_commands().stream().noneMatch(c -> c == p)));
        cup.getId_commands().stream().filter(p -> tempCup.getId_commands().stream().noneMatch(c -> c == p)).forEach(System.out::println);

        clearTF();
        tblCups.refresh();
    }

    public void goSave(ActionEvent actionEvent) throws SQLException {
        System.out.println("goSave");

        if (tfName.getText().trim().equalsIgnoreCase("") || tfSponsor.getText().trim().equalsIgnoreCase("") ||
                dpStart.getValue() == null || dpEnd.getValue() == null || commands.size() == 0) {
            System.out.println("�� ��� ���� ���������!");
            return;
        }

        cups.add(new Cup(tfName.getText(), tfSponsor.getText(), java.sql.Date.valueOf(dpStart.getValue()), java.sql.Date.valueOf(dpEnd.getValue())));
        sqlCup.add(cups.get(cups.size() - 1));
        cup.toStringIdCommands();
        cups.get(cups.size()-1).setId(sqlCup.lastCup());
        cups.get(cups.size()-1).setId_commands(cup.getId_commands());
        System.out.println("id_lastCup = " + sqlCup.lastCup());
        sqlCup.addCupCommand(sqlCup.lastCup(), cup.getId_commands());
        cups.stream().forEach(c -> {
            System.out.println(c);
            c.toStringIdCommands();
        });
        clearTF();

    }

    public void goNew(ActionEvent actionEvent) {
        System.out.println("goNew");
        btnSave.setDisable(false);
        clearTF();
    }

    public void goShowGame(ActionEvent actionEvent) throws Exception{
        if(tblCups.getSelectionModel().getSelectedItem() == null) return;
        System.out.println("goShowGame");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("../fxmls/GameShow.fxml"));
        Parent root = fxmlLoader.load();
        GamesShow gamesShow = fxmlLoader.getController();
        gamesShow.setId_cup(((Cup) tblCups.getSelectionModel().getSelectedItem()).getId());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        gamesShow.setStage(stage);
    }
}
