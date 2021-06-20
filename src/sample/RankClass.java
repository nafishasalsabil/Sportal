package sample;

public class RankClass {
    String player_name,team;
    int goal;

    public RankClass(String player_name, String team, int goal) {
        this.player_name = player_name;
        this.team = team;
        this.goal = goal;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }
}
