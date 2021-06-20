package sample;

public class TournamentPlayerClass {
    int points,set_won;

    public TournamentPlayerClass(int points, int set_won) {
        this.points = points;
        this.set_won = set_won;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getSet_won() {
        return set_won;
    }

    public void setSet_won(int set_won) {
        this.set_won = set_won;
    }
}
