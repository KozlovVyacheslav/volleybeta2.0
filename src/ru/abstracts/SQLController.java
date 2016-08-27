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

    // ����������� � ���� ������
    public void Connection() throws SQLException{
        File file = new File("volleybeta2.0.h2.db");
        if(!file.exists()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("�����������!");
            alert.setHeaderText(null);
            alert.setContentText("� ���������� ��� ���� ������!!");
            alert.showAndWait();
            System.exit(0);
        }
        System.out.println("connect");
            connection = DriverManager.getConnection("jdbc:h2:file:volleybeta2.0", "root", "root");
    }

//    // ������� ������ �� ����
//    abstract public void select();
//    // ������� � ����
//    abstract public void insert();
//    // ���������
//    abstract public void update();
//    // �������� �� ����
//    abstract public void delete();

}
