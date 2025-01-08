public class Property extends Space {
    public int houses = 0;
    public int houseCost;
    public int[] rent;
    public Player owner;
    public String color;
    public String name;
    public boolean isMortgaged = false;
    public int cost;
    public int mortgageValue;

    public Property(String name, String color, int cost, int houseCost, int[] rent, int mortgageValue) {
        this.name = name;
        this.color = color;
        this.cost = cost;
        this.houseCost = houseCost;
        this.rent = rent;
        this.mortgageValue = mortgageValue;
    }

    public void doAction(Player player) {

    }
}
