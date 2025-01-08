public class RailRoad extends Space {
    public Player owner;
    public String name;
    public boolean isMortgaged = false;
    public int cost = 200;
    public int mortgageValue = 100;

    public RailRoad(String name) {
        this.name = name;
    }

    public void doAction(Player player) {

    }
}
