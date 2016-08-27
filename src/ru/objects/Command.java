package ru.objects;

/**
 * Created by vyacheslav_kozlov on 27.07.2016.
 */
public class Command {
    private int id;
    private String name;
    private String coach;
    private String sponsor;
    private boolean sex;

    public Command(String name, String coach, String sponsor, boolean sex) {
        this.name = name;
        this.coach = coach;
        this.sponsor = sponsor;
        this.sex = sex;
    }

    public Command(int id, String name, String coach, String sponsor, boolean sex) {
        this.id = id;
        this.name = name;
        this.coach = coach;
        this.sponsor = sponsor;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coach='" + coach + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", sex=" + sex +
                '}';
    }
}
