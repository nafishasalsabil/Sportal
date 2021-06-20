package sample;

public class InstitutionClass {
    String name;
    int team,player;

    public InstitutionClass(String name, int team, int player) {
        this.name = name;
        this.team = team;
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}
