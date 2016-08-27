package ru.controllers;

import javafx.collections.FXCollections;
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
import ru.objects.Player;
import ru.sql_controllers.SQLCommand;
import ru.sql_controllers.SQLPlayer;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by vyacheslav_kozlov on 27.07.2016.
 */
public class Mens extends MainController {

    @FXML private Button btnAddPlayer;
    @FXML private Button btnDeletePlayer;
    @FXML private Button btnEditPlayer;

    @FXML private Button btnDelete;
    @FXML private Button btnUpdate;
    @FXML private Button btnAdd;

    @FXML private TableView tblPlayers;
    @FXML private TableColumn clmSurname;
    @FXML private TableColumn clmName;
    @FXML private TableColumn clmPatronymic;
    @FXML private TableColumn clmNumber;

    @FXML private TextField tfName;
    @FXML private TextField tfCoach;
    @FXML private TextField tfSponsor;

    @FXML private Label lblNameWindow;
    @FXML private TableView tblCommand;
    @FXML private TableColumn clmnNameCommand;
    @FXML private TableColumn clmnCoachCommand;
    @FXML private TableColumn clmnSponsorCommand;

//    private List<Command> commands = FXCollections.observableArrayList();
    private ObservableList<Player> players = FXCollections.observableArrayList();
    private ObservableList<Command> commands = FXCollections.observableArrayList();
    private SQLCommand sqlCommand = new SQLCommand();
    private SQLPlayer sqlPlayers = new SQLPlayer();
    private Command command;
    private Boolean sex;

    // инициализация
    public void initialize() throws SQLException{

        clmnNameCommand.setCellValueFactory(new PropertyValueFactory<Command, String>("name"));
        clmnCoachCommand.setCellValueFactory(new PropertyValueFactory<Command, String>("coach"));
        clmnSponsorCommand.setCellValueFactory(new PropertyValueFactory<Command, String>("sponsor"));

        sqlCommand.Connection();
        sqlCommand.select(commands);

        clmName.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        clmSurname.setCellValueFactory(new PropertyValueFactory<Player, String>("surname"));
        clmPatronymic.setCellValueFactory(new PropertyValueFactory<Player, String>("patronymic"));
        clmNumber.setCellValueFactory(new PropertyValueFactory<Player, String>("number"));

        btnDelete.setDisable(true);
//        btnAddPlayer.setDisable(true);
//        btnEditPlayer.setDisable(true);
//        btnDeletePlayer.setDisable(true);

        commands.stream().filter( c -> c.isSex() ).forEach( System.out::println );
    }

    // возврат к главному меню
    public void goBack(ActionEvent actionEvent) throws Exception{
        System.out.println("next");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("../fxmls/Welcome.fxml"));
        Parent root = fxmlLoader.load();
        Welcome welcome = fxmlLoader.getController();
        stage.setScene(new Scene(root));
        welcome.setStage(stage);
    }

    // устанавливаем пол команды
    public void setNameWindow(String nameWindow){
        lblNameWindow.setText(nameWindow);
    }

    // устанавливаем пол для всех команд
    public void setSexCommand(Boolean sex) {
        this.sex = sex;
        System.out.println(sex);
        if(sex){
            tblCommand.setItems(commands.filtered(c -> c.isSex()));
        }
        else tblCommand.setItems(commands.filtered(c -> c.isSex() == false));

        tblCommand.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        if (tblCommand.getSelectionModel().getSelectedItem() != null) {
                            System.out.println("Double clicked");
                            command = (Command) tblCommand.getSelectionModel().getSelectedItem();
                            System.out.println(command);
                            tblCommand.getSelectionModel().clearSelection();

                            btnDelete.setDisable(false);
                            btnAdd.setDisable(true);
                            btnUpdate.setDisable(false);

                            fillFields();
                            try {
                                fillTablePlayers();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        else System.out.println("no");
                    }
                }
            }
        });
    }

    // придвойном нажитии на команду, заполнить все поля и таблицу игроков
    private void fillFields () {
        tfName.setText(command.getName());
        tfCoach.setText(command.getCoach());
        tfSponsor.setText(command.getSponsor());
    }

    // заполяем таблицу игроков
    private void fillTablePlayers() throws SQLException{
        System.out.println("fillTablePlayers");
        System.out.println("command_id " + command.getId());
        players.clear();
        sqlPlayers.Connection();
        sqlPlayers.select(players);
        tblPlayers.setItems(players.filtered(p -> p.getId_command() == (command.getId())));
        players.stream().filter(p -> p.getId_command() == (command.getId())).forEach(System.out :: println);
        tblPlayers.refresh();
    }

    // новая команда
    public void goNew(ActionEvent actionEvent) {
        System.out.println("goNew");
        clearTF();
        btnAdd.setDisable(false);
    }

    // очистить все поля
    public void goClear(ActionEvent actionEvent) {
        clearTF();
    }

    // очистить все поля
    public void clearTF() {
        System.out.println("btnClear");
        tfName.setText("");
        tfCoach.setText("");
        tfSponsor.setText("");
        players.clear();
        command = null;
        btnAdd.setDisable(false);
    }

    // сохранить новую команду
    public void goSave(ActionEvent actionEvent) throws SQLException {
        System.out.println("goSave");
        System.out.println("btnNew");
        if (tfName.getText().trim().equalsIgnoreCase("") || tfCoach.getText().trim().equalsIgnoreCase("") ||
                tfSponsor.getText().trim().equalsIgnoreCase("") || players.size() == 0) {
            System.out.println("Не все поля заполнены!");
            return;
        }
        commands.add(new Command(tfName.getText(), tfCoach.getText(), tfSponsor.getText(), sex));
        sqlCommand.add(commands.get(commands.size() - 1));
        int id = sqlCommand.getLastIdCommand();
        System.out.println("id_command after save " + id);
        commands.get(commands.size() - 1).setId(id);
        players.stream().forEach(p -> p.setId_command(id));
        for (Player player : players) {
            System.out.println(player);
            sqlPlayers.Connection();
            sqlPlayers.add(player);
        }
        clearTF();

        btnAdd.setDisable(true);

        System.out.println("commands after save");
        commands.stream().forEach(System.out :: println);
    }

    // сохранение при редактировании
    public void goEdit(ActionEvent actionEvent) throws SQLException {
        System.out.println("goEdit");
        if (tfName.getText().trim().equalsIgnoreCase("") || tfCoach.getText().trim().equalsIgnoreCase("") ||
                tfSponsor.getText().trim().equalsIgnoreCase("") || players.size() == 0) {
            System.out.println("Не все поля заполнены!");
            return;
        }

        command.setName(tfName.getText().trim());
        command.setCoach(tfCoach.getText().trim());
        command.setSponsor(tfSponsor.getText().trim());
        tblCommand.refresh();

        sqlCommand.update(command);

        System.out.println(command);

        sqlPlayers.Connection();

        ObservableList<Player> temp_players = FXCollections.observableArrayList();
        sqlPlayers.select(temp_players);

        System.out.println(" Список из базы !!!");
        temp_players.stream().filter( p -> p.getId_command() == command.getId() && players.stream().filter( p2 -> p2.getId_command() == command.getId()).noneMatch( p2 -> p2.getId() == p.getId()) ).
                forEach( p -> {
                    try {
                        sqlPlayers.delete(p);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

        players.stream().filter(p -> p.getId_command() == command.getId()).forEach(p -> {
            try {
                sqlPlayers.update(p);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        players.stream().filter(p -> p.getId_command() == 0).forEach( p -> {
                    p.setId_command(command.getId());
                    try {
                        sqlPlayers.add(p);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        );


//        for(Player player : players.filtered(p -> p.getId_command() == command.getId())){
//            sqlPlayers.Connection();
//            System.out.println("players: ");
//            System.out.println(player);
//            sqlPlayers.update(player);
//        }
//
//        for(Player player : players.filtered(p -> p.getId_command() == 0)) {
//            sqlPlayers.Connection();
//            System.out.println("players: ");
//            System.out.println(player);
//            player.setId_command(command.getId());
//            sqlPlayers.add(player);
//        }
        clearTF();

        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
    }

    // удалить команду
    public void goDelete(ActionEvent actionEvent) throws SQLException {
        System.out.println("goDelete");
        commands.remove(command);
        sqlCommand.delete(command);
        clearTF();
        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
    }

    // добавление игрока
    public void goAddPlayer(ActionEvent actionEvent) throws Exception{
        System.out.println("goAddPlayer");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("../fxmls/PlayerAddUpdateDelete.fxml"));
        Parent root = fxmlLoader.load();
        PlayerAddUpdateDelete playerAddUpdateDelete = fxmlLoader.getController();
        playerAddUpdateDelete.setLblNameWindow("Добавить игрока");
        if(command != null) playerAddUpdateDelete.setCommandId(command.getId());
        Scene scene = new Scene(root);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.initOwner(stage);
        stage1.showAndWait();

        if (playerAddUpdateDelete.getPlayer() == null) System.out.println("no player");
        else {
            System.out.println(playerAddUpdateDelete.getPlayer());
            players.add(playerAddUpdateDelete.getPlayer());
        }

        if (command == null) tblPlayers.setItems(players);
        else tblPlayers.setItems(players.filtered(p -> p.getId_command() == 0 || p.getId_command() == command.getId()));

        tblPlayers.refresh();
        players.stream().forEach(System.out :: println);
    }

    // добавить, удалить или редактировать игрока
    public void goEditPlayer(ActionEvent actionEvent) throws IOException {
        System.out.println("btnEditPlayer");
        if (tblPlayers.getSelectionModel().getSelectedItem() == null) return;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("../fxmls/PlayerAddUpdateDelete.fxml"));
        Parent root = fxmlLoader.load();
        PlayerAddUpdateDelete playerAddUpdateDelete = fxmlLoader.getController();
        playerAddUpdateDelete.setPlayer((Player)tblPlayers.getSelectionModel().getSelectedItem());
        playerAddUpdateDelete.setLblNameWindow("Редактировать игрока");
        if(command != null) playerAddUpdateDelete.setCommandId(command.getId());
        Scene scene = new Scene(root);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.initOwner(stage);
        stage1.showAndWait();
        tblPlayers.refresh();
    }

    // удаление игрока
    public void goDeletePlayer(ActionEvent actionEvent) {
        System.out.println("goDeletePlayer");
        if(tblPlayers.getSelectionModel().getSelectedItem() == null) return;

        players.remove((Player)tblPlayers.getSelectionModel().getSelectedItem());
    }
}
