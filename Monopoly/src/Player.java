import java.util.ArrayList;

public class Player {
    public ArrayList<Space> spaces = new ArrayList<>();
    public int positionIndex = 0;
    public int money = 1500;
    public String name;
    public boolean inJail = false;
    public int jailTimeLeft = 0;
    public double houseManagement;
    public double propertyManagement;
    public double riskAppetite;

    public Player(String name, double houseManagement, double propertyManagement, double riskAppetite) {
        this.name = name;
        this.houseManagement = houseManagement;
        this.propertyManagement = propertyManagement;
        this.riskAppetite = riskAppetite;
    }

    public void addSpace(Space space){
        spaces.add(space);
    }

    public int payAmount(int amount) {
        //===== Handle Getting More Money Action Later =====/


        if (amount > money) {
            money -= amount;
            return money;
        } else {
            money -= amount;
            return amount;
        }
    }

    public boolean offerToBuySpace(Space space) {
        if (space instanceof Property) {
            Property property = (Property) space;
        }
        if (space instanceof Railroad) {
            Railroad railroad = (Railroad) space;
        }
        if (space instanceof Utility) {
            Utility utility = (Utility) space;
        }
        return false;
    }

    public void buildHouses() {

    }

    public int bid(int currentBid, Space space) {
        return 0;
    }

    public boolean payBail() {
        // If the random value is less than the risk appetite, pay the bail.
        if (Math.random() < riskAppetite) {
            if (money >= 50) {
                money -= 50;
                inJail = false;
                return true;
            }
        }
        return false;
    }

    public boolean equals(Player player) {
        return player.name.equals(name);
    }
}
