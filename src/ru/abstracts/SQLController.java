package ru.abstracts;

import javafx.scene.control.Alert;

import java.io.File;
import java.sql.*;

/**
 * Created by vyacheslav_kozlov on 01.08.2016.
 */
abstract public class SQLController{
    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;

    // подключение к Базе данных
    public void Connection() throws SQLException{
        File file = new File("volleybeta2.0.h2.db");
        if(!file.exists()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Уведомление!");
            alert.setHeaderText(null);
            alert.setContentText("В директории нет базы данных!!");
            alert.showAndWait();
            System.exit(0);
        }
        System.out.println("connect");
            connection = DriverManager.getConnection("jdbc:h2:file:volleybeta2.0", "root", "root");
    }

//    // выборка данных из Базы
//    abstract public void select();
//    // вставка в базу
//    abstract public void insert();
//    // изменения
//    abstract public void update();
//    // удаление их базы
//    abstract public void delete();

}
