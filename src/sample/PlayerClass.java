package sample;

public class PlayerClass {
    String player_name,tournament_name;
    int goals_scored,assist,yellow_card,red_card,starting_11,sub_on,sub_off,goals_saved;

    public PlayerClass(String player_name, int goals_scored, int assist, int yellow_card, int red_card, int starting_11, int sub_on, int sub_off, int goals_saved) {
        this.player_name = player_name;
        this.goals_scored = goals_scored;
        this.assist = assist;
        this.yellow_card = yellow_card;
        this.red_card = red_card;
        this.starting_11 = starting_11;
        this.sub_on = sub_on;
        this.sub_off = sub_off;
        this.goals_saved = goals_saved;
    }

    public PlayerClass(String playerName) {
        this.player_name = playerName;

    }




    public String getTournament_name() {
        return tournament_name;
    }

    public void setTournament_name(String tournament_name) {
        this.tournament_name = tournament_name;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public int getGoals_scored() {
        return goals_scored;
    }

    public void setGoals_scored(int goals_scored) {
        this.goals_scored = goals_scored;
    }

    public int getAssist() {
        return assist;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }

    public int getYellow_card() {
        return yellow_card;
    }

    public void setYellow_card(int yellow_card) {
        this.yellow_card = yellow_card;
    }

    public int getRed_card() {
        return red_card;
    }

    public void setRed_card(int red_card) {
        this.red_card = red_card;
    }

    public int getStarting_11() {
        return starting_11;
    }

    public void setStarting_11(int starting_11) {
        this.starting_11 = starting_11;
    }

    public int getSub_on() {
        return sub_on;
    }

    public void setSub_on(int sub_on) {
        this.sub_on = sub_on;
    }

    public int getSub_off() {
        return sub_off;
    }

    public void setSub_off(int sub_off) {
        this.sub_off = sub_off;
    }

    public int getGoals_saved() {
        return goals_saved;
    }

    public void setGoals_saved(int goals_saved) {
        this.goals_saved = goals_saved;
    }
}
