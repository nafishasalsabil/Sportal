package sample;

import javafx.scene.control.ListCell;

public class Each_Team_Detail_Class  {
    int player_age;
    String player_name,player_roll,player_gender,player_address,player_institution,player_contact,player_code,player_height,player_weight;

    public Each_Team_Detail_Class(int player_age, String player_name, String player_roll, String player_gender, String player_address, String player_institution, String player_contact, String player_code, String player_height, String player_weight) {
        this.player_age = player_age;
        this.player_name = player_name;
        this.player_roll = player_roll;
        this.player_gender = player_gender;
        this.player_address = player_address;
        this.player_institution = player_institution;
        this.player_contact = player_contact;
        this.player_code = player_code;
        this.player_height = player_height;
        this.player_weight = player_weight;
    }

    public int getPlayer_age() {
        return player_age;
    }

    public void setPlayer_age(int player_age) {
        this.player_age = player_age;
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

    public void setPlayer_address(String player_address) {
        this.player_address = player_address;
    }

    public String getPlayer_institution() {
        return player_institution;
    }

    public void setPlayer_institution(String player_institution) {
        this.player_institution = player_institution;
    }

    public String getPlayer_contact() {
        return player_contact;
    }

    public void setPlayer_contact(String player_contact) {
        this.player_contact = player_contact;
    }

    public String getPlayer_code() {
        return player_code;
    }

    public void setPlayer_code(String player_code) {
        this.player_code = player_code;
    }

    public String getPlayer_height() {
        return player_height;
    }

    public void setPlayer_height(String player_height) {
        this.player_height = player_height;
    }

    public String getPlayer_weight() {
        return player_weight;
    }

    public void setPlayer_weight(String player_weight) {
        this.player_weight = player_weight;
    }
}
