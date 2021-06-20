package sample;

public class TennisStandindClass {
    String team;
    int played,won,lost,pts;

    public TennisStandindClass(String team, int played, int won, int lost, int pts) {
        this.team = team;
        this.played = played;
        this.won = won;
        this.lost = lost;
        this.pts = pts;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }
}
