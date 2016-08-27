package ru.controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ru.abstracts.MainController;
import ru.main.Main;
import ru.objects.Game2_0;
import ru.objects.Stat;
import ru.sql_controllers.SQLGame;
import ru.sql_controllers.SQLStat;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by secret on 25.08.2016.
 */
public class GamesShow extends MainController{


    @FXML private AnchorPane paneSetScore;

    @FXML private Button btnPlusScore1;
    @FXML private Button btnMinusScore1;
    @FXML private Button btnMinusScore2;
    @FXML private Button btnPlusScore2;
    @FXML private Button btnNextSet;

    @FXML private Button btnAddCommand;
    @FXML private Button btnAddCommand2;
    @FXML private Button btnPlusStat1;
    @FXML private Button btnMinusStat1;
    @FXML private Button btnMinusStat2;
    @FXML private Button btnPlusStat2;
    @FXML private Button btnClear;
    @FXML private Button btnSave;
    @FXML private Button btnEdit;
    @FXML private Button btnDelete;
    @FXML private Button btnNew;
    @FXML private Button btnPrint;

    @FXML private Button btnBack;

    @FXML private TableView tblGames;
    @FXML private TableColumn clmCommand1;
    @FXML private TableColumn clmCommand2;
    @FXML private TableColumn clmScore;
    @FXML private TableColumn clmDate;

    @FXML private TableView tblGames1;
    @FXML private TableColumn clmCommand11;
    @FXML private TableColumn clmCommand21;
    @FXML private TableColumn clmScore1;
    @FXML private TableColumn clmDate1;

    @FXML private Label lblCommand1;
    @FXML private TableView tblCommand;
    @FXML private TableColumn clmSurname;
    @FXML private TableColumn clmName;
    @FXML private TableColumn clmPoints;
    @FXML private TableColumn clmTotServe;
    @FXML private TableColumn clmErrorServe;
    @FXML private TableColumn clmPointsServe;
    @FXML private TableColumn clmTotReception;
    @FXML private TableColumn clmErrorReception;
    @FXML private TableColumn clmNiceReception;
    @FXML private TableColumn clmTotAttack;
    @FXML private TableColumn clmErrorAttack;
    @FXML private TableColumn clmPointsAttack;
    @FXML private TableColumn clmPercentAttack;
    @FXML private TableColumn clmBlock;

    @FXML private Label lblCommand2;
    @FXML private TableView tblCommand1;
    @FXML private TableColumn clmSurname1;
    @FXML private TableColumn clmName1;
    @FXML private TableColumn clmPoints1;
    @FXML private TableColumn clmTotServe1;
    @FXML private TableColumn clmErrorServe1;
    @FXML private TableColumn clmPointsServe1;
    @FXML private TableColumn clmTotReception1;
    @FXML private TableColumn clmErrorReception1;
    @FXML private TableColumn clmNiceReception1;
    @FXML private TableColumn clmTotAttack1;
    @FXML private TableColumn clmErrorAttack1;
    @FXML private TableColumn clmPointsAttack1;
    @FXML private TableColumn clmPercentAttack1;
    @FXML private TableColumn clmBlock1;

    private ObservableList<Game2_0> games = FXCollections.observableArrayList();
    private ObservableList<Stat> stats = FXCollections.observableArrayList();
    private SQLGame sqlGame = new SQLGame();
    private SQLStat sqlStat = new SQLStat();
    private Game2_0 game;
    private int id_cup;
    private int numberSet = 0;
    private Label[] lblScore1 = new Label[5];
    private Label[] lblScore2 = new Label[5];

    public void initialize() throws SQLException {

        for (int i = 0; i < 5; i++) {
            int x = 90*i;

            lblScore1[i] = new Label("0");
            lblScore1[i].setLayoutX(50 + x);
            lblScore1[i].setLayoutY(330);
            lblScore1[i].minHeight(20);
            lblScore1[i].minHeight(30);
            lblScore1[i].setMnemonicParsing(false);

            lblScore2[i] = new Label("0");
            lblScore2[i].setLayoutX(50 + x);
            lblScore2[i].setLayoutY(360);
            lblScore2[i].minHeight(20);
            lblScore2[i].minHeight(30);
            lblScore2[i].setMnemonicParsing(false);

            paneSetScore.getChildren().addAll(lblScore1[i], lblScore2[i]);
        }

        btnEdit.setDisable(true);
        btnSave.setDisable(true);

        clmName.setCellValueFactory(new PropertyValueFactory<Stat, String>("name_player"));
        clmSurname.setCellValueFactory(new PropertyValueFactory<Stat, String>("surname_player"));
        clmPoints.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("points"));
        clmTotServe.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("tot_serve"));
        clmErrorServe.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("error_serve"));
        clmPointsServe.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("points_serve"));
        clmTotReception.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("tot_reception"));
        clmErrorReception.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("error_reception"));
        clmNiceReception.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("nice_reception"));
        clmTotAttack.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("tot_attack"));
        clmErrorAttack.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("error_attack"));
        clmPointsAttack.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("points_attack"));
        clmBlock.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("block"));

        clmName1.setCellValueFactory(new PropertyValueFactory<Stat, String>("name_player"));
        clmSurname1.setCellValueFactory(new PropertyValueFactory<Stat, String>("surname_player"));
        clmPoints1.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("points"));
        clmTotServe1.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("tot_serve"));
        clmErrorServe1.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("error_serve"));
        clmPointsServe1.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("points_serve"));
        clmTotReception1.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("tot_reception"));
        clmErrorReception1.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("error_reception"));
        clmNiceReception1.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("nice_reception"));
        clmTotAttack1.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("tot_attack"));
        clmErrorAttack1.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("error_attack"));
        clmPointsAttack1.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("points_attack"));
        clmBlock1.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("block"));

        clmCommand1.setCellValueFactory(new PropertyValueFactory<Game2_0, String>("name_command"));
        clmCommand2.setCellValueFactory(new PropertyValueFactory<Game2_0, String>("name_command2"));
        clmDate.setCellValueFactory(new PropertyValueFactory<Game2_0, Date>("date"));
        clmScore.setCellValueFactory(new PropertyValueFactory<Game2_0, String>("scoreString"));

        clmCommand11.setCellValueFactory(new PropertyValueFactory<Game2_0, String>("name_command"));
        clmCommand21.setCellValueFactory(new PropertyValueFactory<Game2_0, String>("name_command2"));
        clmDate1.setCellValueFactory(new PropertyValueFactory<Game2_0, Date>("date"));
        clmScore1.setCellValueFactory(new PropertyValueFactory<Game2_0, String>("scoreString"));

        tblGames.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        System.out.println("Double clicked");
                        stats.clear();
                        tblCommand.refresh();
                        tblCommand1.refresh();
                        game = (Game2_0) tblGames.getSelectionModel().getSelectedItem();
                        System.out.println(game);
                        System.out.println(game.getId());
                        try {
                            sqlStat.Connection();
                            sqlStat.select(stats);

                            tblCommand.setItems(stats.filtered(s -> s.getId_command() == game.getId_command() && s.getId_game() == game.getId()));
                            tblCommand1.setItems(stats.filtered(s -> s.getId_command() == game.getId_command2() && s.getId_game() == game.getId()));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        lblCommand1.setText(game.getName_command());
                        lblCommand2.setText(game.getName_command2());
                        for (int i = 0; i < 5; i++) {
                            lblScore1[i].setText(java.lang.String.valueOf(game.getIScore(i)));
                            lblScore2[i].setText(java.lang.String.valueOf(game.getIScore2(i)));
                        }
                        System.out.println(((Game2_0) tblGames.getSelectionModel().getSelectedItem()).toString());
                        btnEdit.setDisable(false);
                        btnSave.setDisable(true);
                    }
                }
            }
        });
    }

    // добавить к статам
    public void goPlusStat(ActionEvent actionEvent) {
        System.out.println("goPlus");
        if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnPlusStat1")) {
            if (tblCommand.getSelectionModel().getSelectedItem() == null) return;
            ObservableValue value = tblCommand.getFocusModel().getFocusedCell().getTableColumn().getCellObservableValue(tblCommand.getSelectionModel().getSelectedIndex());
            System.out.println(tblCommand.getFocusModel().getFocusedCell().getTableColumn().getId());
            System.out.println(tblCommand.getFocusModel().getFocusedCell().getTableColumn().getCellObservableValue(tblCommand.getSelectionModel().getSelectedIndex()));
            switch (tblCommand.getFocusModel().getFocusedCell().getTableColumn().getId()) {
                case "clmPoints": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setPoints( (int) value.getValue() + 1 ); break;
                case "clmTotServe": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setTot_serve( (int) value.getValue() + 1 ); break;
                case "clmErrorServe": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setError_serve( (int) value.getValue() + 1 ); break;
                case "clmPointsServe": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setPoints_serve( (int) value.getValue() + 1 ); break;
                case "clmTotReception": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setTot_reception( (int) value.getValue() + 1 ); break;
                case "clmErrorReception": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setError_reception( (int) value.getValue() + 1 ); break;
                case "clmNiceReception": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setNice_reception( (int) value.getValue() + 1 ); break;
                case "clmTotAttack": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setTot_attack( (int) value.getValue() + 1 ); break;
                case "clmErrorAttack": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setError_attack( (int) value.getValue() + 1 ); break;
                case "clmPointsAttack": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setPoints_attack( (int) value.getValue() + 1 ); break;
                case "clmBlock": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setBlock( (int) value.getValue() + 1 ); break;
            }
            tblCommand.refresh();
        }
        else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnPlusStat2")) {
            if (tblCommand1.getSelectionModel().getSelectedItem() == null) return;
            ObservableValue value = tblCommand1.getFocusModel().getFocusedCell().getTableColumn().getCellObservableValue(tblCommand1.getSelectionModel().getSelectedIndex());
            System.out.println(tblCommand1.getFocusModel().getFocusedCell().getTableColumn().getId());
            System.out.println(tblCommand1.getFocusModel().getFocusedCell().getTableColumn().getCellObservableValue(tblCommand1.getSelectionModel().getSelectedIndex()));
            switch (tblCommand1.getFocusModel().getFocusedCell().getTableColumn().getId()) {
                case "clmPoints1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setPoints((int) value.getValue() + 1); break;
                case "clmTotServe1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setTot_serve( (int) value.getValue() + 1 ); break;
                case "clmErrorServe1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setError_serve( (int) value.getValue() + 1 ); break;
                case "clmPointsServe1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setPoints_serve( (int) value.getValue() + 1 ); break;
                case "clmTotReception1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setTot_reception( (int) value.getValue() + 1 ); break;
                case "clmErrorReception1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setError_reception( (int) value.getValue() + 1 ); break;
                case "clmNiceReception1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setNice_reception( (int) value.getValue() + 1 ); break;
                case "clmTotAttack1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setTot_attack( (int) value.getValue() + 1 ); break;
                case "clmErrorAttack1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setError_attack( (int) value.getValue() + 1 ); break;
                case "clmPointsAttack1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setPoints_attack( (int) value.getValue() + 1 ); break;
                case "clmBlock1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setBlock( (int) value.getValue() + 1 ); break;
            }
            tblCommand1.refresh();
        }
    }

    // отнять у статов
    public void goMinusStat(ActionEvent actionEvent) {
        System.out.println("goMinus");
        if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnMinusStat1")) {
            if (tblCommand.getSelectionModel().getSelectedItem() == null) return;
            ObservableValue value = tblCommand.getFocusModel().getFocusedCell().getTableColumn().getCellObservableValue(tblCommand.getSelectionModel().getSelectedIndex());
            if ((int)value.getValue() == 0) return;
            System.out.println(tblCommand.getFocusModel().getFocusedCell().getTableColumn().getId());
            System.out.println(tblCommand.getFocusModel().getFocusedCell().getTableColumn().getCellObservableValue(tblCommand.getSelectionModel().getSelectedIndex()));
            switch (tblCommand.getFocusModel().getFocusedCell().getTableColumn().getId()) {
                case "clmPoints": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setPoints( (int) value.getValue() - 1 ); break;
                case "clmTotServe": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setTot_serve( (int) value.getValue() - 1 ); break;
                case "clmErrorServe": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setError_serve( (int) value.getValue() - 1 ); break;
                case "clmPointsServe": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setPoints_serve( (int) value.getValue() - 1 ); break;
                case "clmTotReception": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setTot_reception( (int) value.getValue() - 1 ); break;
                case "clmErrorReception": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setError_reception( (int) value.getValue() - 1 ); break;
                case "clmNiceReception": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setNice_reception( (int) value.getValue() - 1 ); break;
                case "clmTotAttack": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setTot_attack( (int) value.getValue() - 1 ); break;
                case "clmErrorAttack": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setError_attack( (int) value.getValue() - 1 ); break;
                case "clmPointsAttack": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setPoints_attack( (int) value.getValue() - 1 ); break;
                case "clmBlock": ((Stat)tblCommand.getSelectionModel().getSelectedItem()).setBlock( (int) value.getValue() - 1 ); break;
            }
            tblCommand.refresh();
        }
        else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnMinusStat2")) {
            if (tblCommand1.getSelectionModel().getSelectedItem() == null) return;
            ObservableValue value = tblCommand1.getFocusModel().getFocusedCell().getTableColumn().getCellObservableValue(tblCommand1.getSelectionModel().getSelectedIndex());
            if ((int)value.getValue() == 0) return;
            System.out.println(tblCommand1.getFocusModel().getFocusedCell().getTableColumn().getId());
            System.out.println(tblCommand1.getFocusModel().getFocusedCell().getTableColumn().getCellObservableValue(tblCommand1.getSelectionModel().getSelectedIndex()));
            switch (tblCommand1.getFocusModel().getFocusedCell().getTableColumn().getId()) {
                case "clmPoints1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setPoints( (int) value.getValue() - 1 ); break;
                case "clmTotServe1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setTot_serve( (int) value.getValue() - 1 ); break;
                case "clmErrorServe1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setError_serve( (int) value.getValue() - 1 ); break;
                case "clmPointsServe1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setPoints_serve( (int) value.getValue() - 1 ); break;
                case "clmTotReception1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setTot_reception( (int) value.getValue() - 1 ); break;
                case "clmErrorReception1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setError_reception( (int) value.getValue() - 1 ); break;
                case "clmNiceReception1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setNice_reception( (int) value.getValue() - 1 ); break;
                case "clmTotAttack1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setTot_attack( (int) value.getValue() - 1 ); break;
                case "clmErrorAttack1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setError_attack( (int) value.getValue() - 1 ); break;
                case "clmPointsAttack1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setPoints_attack( (int) value.getValue() - 1 ); break;
                case "clmBlock1": ((Stat)tblCommand1.getSelectionModel().getSelectedItem()).setBlock( (int) value.getValue() - 1 ); break;
            }
            tblCommand1.refresh();
        }
    }

    // распечатать все данные об игре
    public void goPrint(ActionEvent actionEvent) {
        System.out.println("goPrint");
    }

    // очистить все поля для новой игры
    public void goNew(ActionEvent actionEvent) {
        System.out.println("goNew");
        btnSave.setDisable(false);
    }

    // сохранить игру
    public void goSave(ActionEvent actionEvent) {
        System.out.println("goSave");
        btnSave.setDisable(true);
        btnEdit.setDisable(true);
    }

    // редактирование игры
    public void goEdit(ActionEvent actionEvent) {
        System.out.println("goEdit");
        btnSave.setDisable(true);
        btnEdit.setDisable(true);
        try {
            sqlStat.update(stats, game.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        goClear(actionEvent);
    }

    // удалить игру
    public void goDelete(ActionEvent actionEvent) {
        System.out.println("goDelete");
        btnEdit.setDisable(true);
        btnSave.setDisable(true);
    }

    // очистить все поля
    public void goClear(ActionEvent actionEvent) {
        System.out.println("goClear");
        btnEdit.setDisable(true);
        btnSave.setDisable(true);
        game = null;

        for (int i = 0; i < 5; i++) {
            lblScore1[i].setText("0");
            lblScore2[i].setText("0");
        }
        stats.clear();
        tblCommand.refresh();
        tblCommand1.refresh();
        lblCommand1.setText("Команда 1");
        lblCommand2.setText("Команда 2");
    }

    // возврат к списку кубков
    public void goBack(ActionEvent actionEvent) throws Exception{
        System.out.println("goBack");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("../fxmls/Cups.fxml"));
        Parent root = fxmlLoader.load();
        Cups cups= fxmlLoader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        cups.setStage(stage);
    }

    // сеттер идентификатора кубка
    public void setId_cup(int id_cup) {
        this.id_cup = id_cup;
        try {
            sqlGame.Connection();
            sqlGame.select2(games, id_cup);
            tblGames.setItems(games.filtered(g -> g.isSex()));
            tblGames1.setItems(games.filtered(g -> !g.isSex()));

            tblGames.refresh();
            tblGames1.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // добавить команду в игру
    public void goAddCommand(ActionEvent actionEvent) {
        System.out.println("goAddCommand");
    }

    // изменяем счет в партиях
    public void goChangeScore(ActionEvent actionEvent) {
        System.out.println("goChangeScore");
        if (game == null) return;
        switch (numberSet) {
            case 0:
                System.out.println("numberSet = 0!");
                if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnPlusScore1")) {
                    game.setIScore(0, java.lang.Integer.valueOf(lblScore1[0].getText()) + 1);
                    lblScore1[0].setText(String.valueOf(game.getIScore(0)));
                }
                else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnPlusScore2")){
                    game.setIScore2(0, java.lang.Integer.valueOf(lblScore2[0].getText()) + 1);
                    lblScore2[0].setText(String.valueOf(game.getIScore2(0)));
                }
                else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnMinusScore1")) {
                    if (Integer.valueOf(lblScore1[0].getText()) == 0) return;
                    game.setIScore(0, java.lang.Integer.valueOf(lblScore1[0].getText()) - 1);
                    lblScore1[0].setText(String.valueOf(game.getIScore(0)));
                }
                else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnMinusScore2")){
                    if (Integer.valueOf(lblScore2[0].getText()) == 0) return;
                    game.setIScore2(0, java.lang.Integer.valueOf(lblScore2[0].getText()) - 1);
                    lblScore2[0].setText(String.valueOf(game.getIScore2(0)));
                }
                break;

            case 1:
                System.out.println("numberSet = 1!");
                if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnPlusScore1")) {
                    game.setIScore(1, java.lang.Integer.valueOf(lblScore1[1].getText()) + 1);
                    lblScore1[1].setText(String.valueOf(game.getIScore(1)));
                }
                else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnPlusScore2")){
                    game.setIScore2(1, java.lang.Integer.valueOf(lblScore2[1].getText()) + 1);
                    lblScore2[1].setText(String.valueOf(game.getIScore2(1)));
                }
                else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnMinusScore1")) {
                    if (Integer.valueOf(lblScore1[1].getText()) == 0) return;
                    game.setIScore(1, java.lang.Integer.valueOf(lblScore1[1].getText()) - 1);
                    lblScore1[1].setText(String.valueOf(game.getIScore(1)));
                }
                else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnMinusScore2")){
                    if (Integer.valueOf(lblScore2[1].getText()) == 0) return;
                    game.setIScore2(1, java.lang.Integer.valueOf(lblScore2[1].getText()) - 1);
                    lblScore2[1].setText(String.valueOf(game.getIScore2(1)));
                }

                break;
            case 2:
                System.out.println("numberSet = 2!");
                if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnPlusScore1")) {
                    game.setIScore(2, java.lang.Integer.valueOf(lblScore1[2].getText()) + 1);
                    lblScore1[2].setText(String.valueOf(game.getIScore(2)));
                }
                else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnPlusScore2")){
                    game.setIScore2(2, java.lang.Integer.valueOf(lblScore2[2].getText()) + 1);
                    lblScore2[2].setText(String.valueOf(game.getIScore2(2)));
                }
                else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnMinusScore1")) {
                    if (Integer.valueOf(lblScore1[2].getText()) == 0) return;
                    game.setIScore(2, java.lang.Integer.valueOf(lblScore1[2].getText()) - 1);
                    lblScore1[2].setText(String.valueOf(game.getIScore(2)));
                }
                else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnMinusScore2")){
                    if (Integer.valueOf(lblScore2[2].getText()) == 0) return;
                    game.setIScore2(2, java.lang.Integer.valueOf(lblScore2[2].getText()) - 1);
                    lblScore2[2].setText(String.valueOf(game.getIScore2(2)));
                }
                break;
            case 3:
                System.out.println("numberSet = 3!");
                if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnPlusScore1")) {
                    game.setIScore(3, java.lang.Integer.valueOf(lblScore1[3].getText()) + 1);
                    lblScore1[3].setText(String.valueOf(game.getIScore(3)));
                }
                else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnPlusScore2")){
                    game.setIScore2(3, java.lang.Integer.valueOf(lblScore2[3].getText()) + 1);
                    lblScore2[3].setText(String.valueOf(game.getIScore2(3)));
                }
                else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnMinusScore1")) {
                    if (Integer.valueOf(lblScore1[3].getText()) == 0) return;
                    game.setIScore(3, java.lang.Integer.valueOf(lblScore1[3].getText()) - 1);
                    lblScore1[3].setText(String.valueOf(game.getIScore(3)));
                }
                else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnMinusScore2")){
                    if (Integer.valueOf(lblScore2[3].getText()) == 0) return;
                    game.setIScore2(3, java.lang.Integer.valueOf(lblScore2[3].getText()) - 1);
                    lblScore2[3].setText(String.valueOf(game.getIScore2(3)));
                }
                break;
            case 4:
                System.out.println("numberSet = 4!");
                if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnPlusScore1")) {
                    game.setIScore(4, java.lang.Integer.valueOf(lblScore1[4].getText()) + 1);
                    lblScore1[4].setText(String.valueOf(game.getIScore(4)));
                }
                else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnPlusScore2")){
                    game.setIScore2(4, java.lang.Integer.valueOf(lblScore2[4].getText()) + 1);
                    lblScore2[4].setText(String.valueOf(game.getIScore2(4)));
                }
                else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnMinusScore1")) {
                    if (Integer.valueOf(lblScore1[4].getText()) == 0) return;
                    game.setIScore(4, java.lang.Integer.valueOf(lblScore1[4].getText()) - 1);
                    lblScore1[4].setText(String.valueOf(game.getIScore(4)));
                }
                else if (((Button)actionEvent.getSource()).getId().equalsIgnoreCase("btnMinusScore2")){
                    if (Integer.valueOf(lblScore2[4].getText()) == 0) return;
                    game.setIScore2(4, java.lang.Integer.valueOf(lblScore2[4].getText()) - 1);
                    lblScore2[4].setText(String.valueOf(game.getIScore2(4)));
                }
                break;
        }
    }

    // перемещяемся от партии к партии
    public void goNextSet(ActionEvent actionEvent) {
        System.out.println("goNextSet");
        if (numberSet == 4) numberSet = 0;
        else numberSet++;
    }
}
