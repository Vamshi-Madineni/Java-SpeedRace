package sample;

public class Data {
    private String username;
    private int gamesWon;

    public Data(String username, int gamesWon) {
        this.username = username;
        this.gamesWon = gamesWon;
    }

    public String getUsername() {
        return username;
    }

    public int getGamesWon() {
        return gamesWon;
    }
}
