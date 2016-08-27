package ru.objects;

import java.sql.Date;
import java.util.Arrays;

/**
 * Created by secret on 26.08.2016.
 */
public class Game2_0 {
    private int id;
    private Date date;
    private int id_command;
    private String name_command;
    private boolean sex;
    private int[] score = new int[5];
    private int id_command2;
    private String name_command2;
    private int[] score2 = new int[5];
    private int id_cup;

    private String scoreString;

    public Game2_0(Date date, int id_command, String name_command, boolean sex, int id_cup) {
        this.date = date;
        this.id_command = id_command;
        this.name_command = name_command;
        this.sex = sex;
        this.id_cup = id_cup;
    }

    public Game2_0(int id, Date date, int id_command, String name_command, boolean sex, int id_cup) {
        this.id = id;
        this.date = date;
        this.id_command = id_command;
        this.name_command = name_command;
        this.sex = sex;
        this.id_cup = id_cup;
    }

    public int getIScore(int i) {
        return score[i];
    }

    public void setIScore(int i, int value) {
        score[i] = value;
    }

    public void setIScore2(int i, int value) {
        score2[i] = value;
    }

    public int getIScore2(int i) {
        return score2[i];
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

    public int[] getScore() {
        return score;
    }

    public void setScore(int[] score) {
        this.score = score;
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

    public int[] getScore2() {
        return score2;
    }

    public void setScore2(int[] score2) {
        this.score2 = score2;
    }

    public int getId_cup() {
        return id_cup;
    }

    public void setId_cup(int id_cup) {
        this.id_cup = id_cup;
    }

    public void scoreToString(){
        int s1 = 0, s2 = 0;
        for (int i = 0; i < 5; i++) {
            if (score[i] != 0 && score2[i] != 0){
                if (score[i] > score2[i]) s1++;
                else s2++;
            }
        }
        scoreString = s1 + ":" + s2;
//        scoreString = score[0] + ":" + score2[0] + "," + score[1] + ":" + score2[1] + "," + score[2] + ":" + score2[2] + "," +
//                score[3] + ":" + score2[3] + "," + score[4] + ":" + score2[4];
    }

    public String getScoreString() {
        return scoreString;
    }

    public void setScoreString(String scoreString) {
        this.scoreString = scoreString;
    }

    @Override
    public String toString() {
        return "Game2_0{" +
                "id=" + id +
                ", date=" + date +
                ", id_command=" + id_command +
                ", name_command='" + name_command + '\'' +
                ", sex=" + sex +
                ", score=" + Arrays.toString(score) +
                ", id_command2=" + id_command2 +
                ", name_command2='" + name_command2 + '\'' +
                ", score2=" + Arrays.toString(score2) +
                ", id_cup=" + id_cup +
                ", scoreString='" + scoreString + '\'' +
                '}';
    }
}
