public class Utility extends Space {
    public Player owner;
    public String name;
    public boolean isMortgaged = false;
    public int cost = 150;
    public int mortgageValue = 75;

    public Utility(String name) {
        this.name = name;
    }

    public void doAction(Player player) {

    }
}
