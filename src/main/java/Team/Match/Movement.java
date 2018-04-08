package Team.Match;

public class Movement {
    private String matchId;

    public Movement (){}

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Movement (String matchId){
        this.matchId = matchId;
    }
}
