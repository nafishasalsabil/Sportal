package sample;

public class CricketerClass {
   String competition;
   int innings, runs, batting_average,strike_rate,fifty,century,sixes,fours,wickets,bowling_average ,economy;

    public CricketerClass(String competition, int innings, int runs, int batting_average, int strike_rate, int fifty, int century, int sixes, int fours, int wickets, int bowling_average, int economy) {
        this.competition = competition;
        this.innings = innings;
        this.runs = runs;
        this.batting_average = batting_average;
        this.strike_rate = strike_rate;
        this.fifty = fifty;
        this.century = century;
        this.sixes = sixes;
        this.fours = fours;
        this.wickets = wickets;
        this.bowling_average = bowling_average;
        this.economy = economy;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public int getInnings() {
        return innings;
    }

    public void setInnings(int innings) {
        this.innings = innings;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getBatting_average() {
        return batting_average;
    }

    public void setBatting_average(int batting_average) {
        this.batting_average = batting_average;
    }

    public int getStrike_rate() {
        return strike_rate;
    }

    public void setStrike_rate(int strike_rate) {
        this.strike_rate = strike_rate;
    }

    public int getFifty() {
        return fifty;
    }

    public void setFifty(int fifty) {
        this.fifty = fifty;
    }

    public int getCentury() {
        return century;
    }

    public void setCentury(int century) {
        this.century = century;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getBowling_average() {
        return bowling_average;
    }

    public void setBowling_average(int bowling_average) {
        this.bowling_average = bowling_average;
    }

    public int getEconomy() {
        return economy;
    }

    public void setEconomy(int economy) {
        this.economy = economy;
    }
}
