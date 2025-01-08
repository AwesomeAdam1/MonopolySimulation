import java.util.ArrayList;

public class Player {
    public ArrayList<Property> properties = new ArrayList<>();
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

    public int payRent() {
        return 0;
    }

    public boolean offerToBuyProperty(Space property) {
        return false;
    }

    public void buildHouses() {

    }

    public boolean bid() {
        return false;
    }

    public
}
