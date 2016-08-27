package ru.controllers;

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
import ru.sql_controllers.SQLGame;

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
    @FXML private TableColumn clmErroeServe;
    @FXML private TableColumn clmPointsServe;
    @FXML private TableColumn clmTotReception;
    @FXML private TableColumn clmErrorReception;
    @FXML private TableColumn clmNiceReseption;
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
    @FXML private TableColumn clmErroeServe1;
    @FXML private TableColumn clmPointsServe1;
    @FXML private TableColumn clmTotReception1;
    @FXML private TableColumn clmErrorReception1;
    @FXML private TableColumn clmNiceReseption1;
    @FXML private TableColumn clmTotAttack1;
    @FXML private TableColumn clmErrorAttack1;
    @FXML private TableColumn clmPointsAttack1;
    @FXML private TableColumn clmPercentAttack1;
    @FXML private TableColumn clmBlock1;

    private ObservableList<Game2_0> games = FXCollections.observableArrayList();
    private SQLGame sqlGame = new SQLGame();
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
                        game = (Game2_0) tblGames.getSelectionModel().getSelectedItem();
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
    }

    // отнять у статов
    public void goMinusStat(ActionEvent actionEvent) {
        System.out.println("goMinus");
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
