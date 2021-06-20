package sample;

public class StandingInfoClass {
    String nameP;
    int matches_played,won,draw,lost,goal_differ,points;

    public StandingInfoClass(String name, int matches_played, int won, int draw, int lost, int goal_differ, int points) {
        this.nameP = name;
        this.matches_played = matches_played;
        this.won = won;
        this.draw = draw;
        this.lost = lost;
        this.goal_differ = goal_differ;
        this.points = points;
    }

    public StandingInfoClass(String teamN, int matches, int won, int lost, int points) {
        this.nameP = teamN;
        this.matches_played = matches;
        this.won = won;

        this.lost = lost;

        this.points = points;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public int getMatches_played() {
        return matches_played;
    }

    public void setMatches_played(int matches_played) {
        this.matches_played = matches_played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getGoal_differ() {
        return goal_differ;
    }

    public void setGoal_differ(int goal_differ) {
        this.goal_differ = goal_differ;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
