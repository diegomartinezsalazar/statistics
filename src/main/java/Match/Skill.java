package Match;

public class Skill extends Movement{
    private Player player;
    private String value;

    public Skill (String matchId, Player player, String value){
        super(matchId);
        this.setPlayer(player);
        this.setValue(value);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
