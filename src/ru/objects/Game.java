package ru.objects;

import java.sql.Date;

/**
 * Created by vyacheslav_kozlov on 27.07.2016.
 */
public class Game {

    private int id;
    private Date date;
    private int id_command;
    private String name_command;
    private boolean sex;
    private int set1;
    private int set2;
    private int set3;
    private int set4;
    private int set5;
    private int id_command2;
    private String name_command2;
    private int set12;
    private int set22;
    private int set32;
    private int set42;
    private int set52;
    private int id_cup;

    private String scoreString;

    public Game(int id, Date date, int id_command, String name_command, boolean sex,
                int set1, int set2, int set3, int set4, int set5, int id_cup) {
        this.id = id;
        this.date = date;
        this.id_command = id_command;
        this.name_command = name_command;
        this.sex = sex;
        this.set1 = set1;
        this.set2 = set2;
        this.set3 = set3;
        this.set4 = set4;
        this.set5 = set5;
        this.id_cup = id_cup;
    }

    public Game(Date date, int id_command, String name_command, boolean sex,
                int set1, int set2, int set3, int set4, int set5, int id_command2, String name_command2,
                int set12, int set22, int set32, int set42, int set52, int id_cup) {
        this.date = date;
        this.id_command = id_command;
        this.name_command = name_command;
        this.sex = sex;
        this.set1 = set1;
        this.set2 = set2;
        this.set3 = set3;
        this.set4 = set4;
        this.set5 = set5;
        this.id_command2 = id_command2;
        this.name_command2 = name_command2;
        this.set12 = set12;
        this.set22 = set22;
        this.set32 = set32;
        this.set42 = set42;
        this.set52 = set52;
        this.id_cup = id_cup;
    }

    public Game(int id, Date date, int id_command, String name_command, boolean sex,
                int set1, int set2, int set3, int set4, int set5, int id_command2, String name_command2,
                int set12, int set22, int set32, int set42, int set52, int id_cup) {
        this.id = id;
        this.date = date;
        this.id_command = id_command;
        this.name_command = name_command;
        this.sex = sex;
        this.set1 = set1;
        this.set2 = set2;
        this.set3 = set3;
        this.set4 = set4;
        this.set5 = set5;
        this.id_command2 = id_command2;
        this.name_command2 = name_command2;
        this.set12 = set12;
        this.set22 = set22;
        this.set32 = set32;
        this.set42 = set42;
        this.set52 = set52;
        this.id_cup = id_cup;
    }

    public void toStringScore() {
        scoreString = set1 + ":" + set12 + "," + set2 + ":" + set22 + "," + set3 + ":" + set32 + "," +
                set4 + ":" + set42 + "," + set5 + ":" + set52;
        System.out.println(scoreString);
    }

    public String getScoreString() {
        return scoreString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId_command() {
        return id_command;
    }

    public void setId_command(int id_command) {
        this.id_command = id_command;
    }

    public String getName_command() {
        return name_command;
    }

    public void setName_command(String name_command) {
        this.name_command = name_command;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getSet1() {
        return set1;
    }

    public void setSet1(int set1) {
        this.set1 = set1;
    }

    public int getSet2() {
        return set2;
    }

    public void setSet2(int set2) {
        this.set2 = set2;
    }

    public int getSet3() {
        return set3;
    }

    public void setSet3(int set3) {
        this.set3 = set3;
    }

    public int getSet4() {
        return set4;
    }

    public void setSet4(int set4) {
        this.set4 = set4;
    }

    public int getSet5() {
        return set5;
    }

    public void setSet5(int set5) {
        this.set5 = set5;
    }

    public int getId_command2() {
        return id_command2;
    }

    public void setId_command2(int id_command2) {
        this.id_command2 = id_command2;
    }

    public String getName_command2() {
        return name_command2;
    }

    public void setName_command2(String name_command2) {
        this.name_command2 = name_command2;
    }

    public int getSet12() {
        return set12;
    }

    public void setSet12(int set12) {
        this.set12 = set12;
    }

    public int getSet22() {
        return set22;
    }

    public void setSet22(int set22) {
        this.set22 = set22;
    }

    public int getSet32() {
        return set32;
    }

    public void setSet32(int set32) {
        this.set32 = set32;
    }

    public int getSet42() {
        return set42;
    }

    public void setSet42(int set42) {
        this.set42 = set42;
    }

    public int getSet52() {
        return set52;
    }

    public void setSet52(int set52) {
        this.set52 = set52;
    }

    public int getId_cup() {
        return id_cup;
    }

    public void setId_cup(int id_cup) {
        this.id_cup = id_cup;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", date=" + date +
                ", id_command=" + id_command +
                ", name_command='" + name_command + '\'' +
                ", sex=" + sex +
                ", set1=" + set1 +
                ", set2=" + set2 +
                ", set3=" + set3 +
                ", set4=" + set4 +
                ", set5=" + set5 +
                ", id_command2=" + id_command2 +
                ", name_command2='" + name_command2 + '\'' +
                ", set12=" + set12 +
                ", set22=" + set22 +
                ", set32=" + set32 +
                ", set42=" + set42 +
                ", set52=" + set52 +
                ", id_cup=" + id_cup +
                '}';
    }
}
