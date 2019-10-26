package RegExr;

class Match {
    private String homeTeam;
    private String awayTeam;
    private String date;
    private String score;


    Match(String homeTeam, String awayTeam, String date, String score) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.score = score;
    }

    String getAwayTeam() {
        return awayTeam;
    }

    String getDate() {
        return date;
    }

    String getScore() {
        return score;
    }

    String getHomeTeam() {
        return this.homeTeam;
    }
}
