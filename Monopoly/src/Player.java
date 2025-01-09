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
    

//test

    public void buildHouses() {

    }

    public boolean bid() {
        return false;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public int getPositionIndex() {
        return positionIndex;
    }

    public void setPositionIndex(int positionIndex) {
        this.positionIndex = positionIndex;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public int getJailTimeLeft() {
        return jailTimeLeft;
    }

    public void setJailTimeLeft(int jailTimeLeft) {
        this.jailTimeLeft = jailTimeLeft;
    }

    public double getHouseManagement() {
        return houseManagement;
    }

    public void setHouseManagement(double houseManagement) {
        this.houseManagement = houseManagement;
    }

    public double getPropertyManagement() {
        return propertyManagement;
    }

    public void setPropertyManagement(double propertyManagement) {
        this.propertyManagement = propertyManagement;
    }

    public double getRiskAppetite() {
        return riskAppetite;
    }

    public void setRiskAppetite(double riskAppetite) {
        this.riskAppetite = riskAppetite;
    }

    //public
}
