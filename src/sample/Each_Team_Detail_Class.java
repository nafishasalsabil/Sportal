package sample;

public class Each_Team_Detail_Class {
    int player_age;
    String player_name,player_roll,player_gender,player_address,player_institution,player_contact,player_course;

    public Each_Team_Detail_Class(int player_age, String player_name, String player_roll, String player_gender, String player_address, String player_institution, String player_contact, String player_course) {
        this.player_age = player_age;
        this.player_name = player_name;
        this.player_roll = player_roll;
        this.player_gender = player_gender;
        this.player_address = player_address;
        this.player_institution = player_institution;
        this.player_contact = player_contact;
        this.player_course = player_course;
    }

    public int getPlayer_age() {
        return player_age;
    }

    public void setPlayer_age(int player_age) {
        this.player_age = player_age;
    }

    public String getPlayer_contact() {
        return player_contact;
    }

    public void setPlayer_contact(String player_contact) {
        this.player_contact = player_contact;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPlayer_roll() {
        return player_roll;
    }

    public void setPlayer_roll(String player_roll) {
        this.player_roll = player_roll;
    }

    public String getPlayer_gender() {
        return player_gender;
    }

    public void setPlayer_gender(String player_gender) {
        this.player_gender = player_gender;
    }

    public String getPlayer_address() {
        return player_address;
    }

    public String getPlayer_course() {
        return player_course;
    }

    public void setPlayer_course(String player_course) {
        this.player_course = player_course;
    }

    public void setPlayer_address(String player_address) {
        this.player_address = player_address;
    }

    public String getPlayer_institution() {
        return player_institution;
    }

    public void setPlayer_institution(String player_institution) {
        this.player_institution = player_institution;
    }

}
