package sample;

public class MatchScheduleClass {
    String teamA,teamB,match_date,match_time,venue,facilitator,referee;
    int match_id;

    public MatchScheduleClass(String teamA, String teamB, String match_date, String match_time, String venue, String facilitator, String referee, int match_id) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.match_date = match_date;
        this.match_time = match_time;
        this.venue = venue;
        this.facilitator = facilitator;
        this.referee = referee;
        this.match_id = match_id;
    }

    public MatchScheduleClass(String text, String text1) {

        this.teamA = text;
        this.teamB = text1;

    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

    public String getMatch_time() {
        return match_time;
    }

    public void setMatch_time(String match_time) {
        this.match_time = match_time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getFacilitator() {
        return facilitator;
    }

    public void setFacilitator(String facilitator) {
        this.facilitator = facilitator;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }
}
