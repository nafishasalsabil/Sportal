package sample;

public class RefereeClass {
    String name;
    int matches,booking,yellow,red;

    public RefereeClass(String name, int matches, int booking, int yellow, int red) {
        this.name = name;
        this.matches = matches;
        this.booking = booking;
        this.yellow = yellow;
        this.red = red;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getBooking() {
        return booking;
    }

    public void setBooking(int booking) {
        this.booking = booking;
    }

    public int getYellow() {
        return yellow;
    }

    public void setYellow(int yellow) {
        this.yellow = yellow;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }
}
