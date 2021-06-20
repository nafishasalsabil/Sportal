package sample;

public class VenueClass {
    String place;
    int matches_played,tournaments,goals;

    public VenueClass(String place, int matches_played, int tournaments, int goals) {
        this.place = place;
        this.matches_played = matches_played;
        this.tournaments = tournaments;
        this.goals = goals;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getMatches_played() {
        return matches_played;
    }

    public void setMatches_played(int matches_played) {
        this.matches_played = matches_played;
    }

    public int getTournaments() {
        return tournaments;
    }

    public void setTournaments(int tournaments) {
        this.tournaments = tournaments;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }
}
