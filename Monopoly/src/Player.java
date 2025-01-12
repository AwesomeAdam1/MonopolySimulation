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
            if(Math.random() > propertyManagement)
                return false;
            Property property = (Property) space;
            System.out.println("True evaluation");

            return true;
        }
        if (space instanceof Railroad) {
            if(Math.random() > propertyManagement)
                return false;
            Railroad railroad = (Railroad) space;
            System.out.println("True evaluation");

            return true;
        }
        if (space instanceof Utility) {
            if(Math.random() > propertyManagement)
            return false;
            Utility utility = (Utility) space;
            System.out.println("True evaluation");

            return true;
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



    //Trading logic and code begins here. Idk what im doing tbh adam pls revise later

    //Use this class to check for trade success. (@AwesomeAdam1 do you want to assume that if the 
    // initate trade player passes the check the trade will always succeed? Or do both players,
    // on both ends of the trade, both have to pask the riskCheck?)
    private boolean tradeRiskCheck(double riskAppetite){
        //(0.5 + (this.riskAppetite/2))) accounts for probability of success
        return (Math.random() <=  (0.5 + (this.riskAppetite/2)));
    }




    //Checks if player owns specificed color group for house building
    public boolean ownsColorGroup(String colorGroup) {
        int totalRequired;
        int hasColor = 0;
        if(colorGroup.equals("Purple") || colorGroup.equals("Blue"))
            totalRequired = 2;
        else
            totalRequired = 3;
            
        System.out.println("Debug totalRequired for Colorgroup: " + totalRequired);    
        for (int i = 0; i < spaces.size(); i++) {
            if(spaces.get(i) instanceof Property)
            {
                Property property = (Property) spaces.get(i);
                System.out.println("Debug color current property " + property.color);
                if(property.color.equals(colorGroup))
                {
                    hasColor++;    
                    System.out.println("Debug: property equals " + property.color + colorGroup);
                    System.out.println("HasColor count " + hasColor);

                }    
            }        
        }
        if((colorGroup.equals("Purple") || colorGroup.equals("Blue")) && hasColor == 2)
            return true;
        else if(hasColor == 3)
            return true;
        else
            return false;        
    }
}


