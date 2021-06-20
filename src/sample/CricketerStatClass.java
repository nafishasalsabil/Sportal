package sample;

public class CricketerStatClass {
    String nameC;
    int  innings, not_out, century, half_century, total_run, total_run_given, total_sixes, total_fours, wickets, extra_run,total_ball_played,total_ball_delivered;

    public CricketerStatClass(String nameC, int innings, int not_out, int century, int half_century, int total_run, int total_run_given, int total_sixes, int total_fours, int wickets, int extra_run, int total_ball_played, int total_ball_delivered) {
        this.nameC = nameC;
        this.innings = innings;
        this.not_out = not_out;
        this.century = century;
        this.half_century = half_century;
        this.total_run = total_run;
        this.total_run_given = total_run_given;
        this.total_sixes = total_sixes;
        this.total_fours = total_fours;
        this.wickets = wickets;
        this.extra_run = extra_run;
        this.total_ball_played = total_ball_played;
        this.total_ball_delivered = total_ball_delivered;
    }

    public CricketerStatClass(String playerName) {
        this.nameC = playerName;
    }


    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public int getInnings() {
        return innings;
    }

    public void setInnings(int innings) {
        this.innings = innings;
    }

    public int getNot_out() {
        return not_out;
    }

    public void setNot_out(int not_out) {
        this.not_out = not_out;
    }

    public int getCentury() {
        return century;
    }

    public void setCentury(int century) {
        this.century = century;
    }

    public int getHalf_century() {
        return half_century;
    }

    public void setHalf_century(int half_century) {
        this.half_century = half_century;
    }

    public int getTotal_run() {
        return total_run;
    }

    public void setTotal_run(int total_run) {
        this.total_run = total_run;
    }

    public int getTotal_run_given() {
        return total_run_given;
    }

    public void setTotal_run_given(int total_run_given) {
        this.total_run_given = total_run_given;
    }

    public int getTotal_sixes() {
        return total_sixes;
    }

    public void setTotal_sixes(int total_sixes) {
        this.total_sixes = total_sixes;
    }

    public int getTotal_fours() {
        return total_fours;
    }

    public void setTotal_fours(int total_fours) {
        this.total_fours = total_fours;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getExtra_run() {
        return extra_run;
    }

    public void setExtra_run(int extra_run) {
        this.extra_run = extra_run;
    }

    public int getTotal_ball_played() {
        return total_ball_played;
    }

    public void setTotal_ball_played(int total_ball_played) {
        this.total_ball_played = total_ball_played;
    }

    public int getTotal_ball_delivered() {
        return total_ball_delivered;
    }

    public void setTotal_ball_delivered(int total_ball_delivered) {
        this.total_ball_delivered = total_ball_delivered;
    }
}
