import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

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
    public double tradingStrategy;

    public Player(String name, double houseManagement, double propertyManagement, double riskAppetite, double tradingStrategy) {
        this.name = name;
        this.houseManagement = houseManagement;
        this.propertyManagement = propertyManagement;
        this.riskAppetite = riskAppetite;
        this.tradingStrategy = tradingStrategy;
    }

    public void addSpace(Space space){
        spaces.add(space);
    }

    public int payAmount(int amount) {
        //===== Handle Getting More Money Action Later =====/


        if (amount > money) {
            if(totalNumberOfHouses() > 0)
            {
                sortCheapest();
                while(money <= 0 && totalNumberOfHouses() > 0 )
                {
                    sellHouse();
                }
                if(money > 0)
                    return money;
                else
                    System.out.println(name + " is still broke after selling all houses. BANKRUPT!");
            }          
        }

        if (money >= amount) {
            money -= amount;
            return amount;
        } else {
            money -= amount;
            return money;
        }
    }

    public boolean offerToBuySpace(Space space) {
        if (space instanceof Property) {
            if(Math.random() > propertyManagement)
                return false;
            Property property = (Property) space;
            return true;
        }
        if (space instanceof Railroad) {
            if(Math.random() > propertyManagement)
                return false;
            Railroad railroad = (Railroad) space;
            return true;
        }
        if (space instanceof Utility) {
            if(Math.random() > propertyManagement)
                return false;
            Utility utility = (Utility) space;
            return true;
        }
        return false;
    }


    private ArrayList<Property> getBuildableProperties(ArrayList<Space> spaces) {
        ArrayList<Property> buildableProperties = new ArrayList<>();
        for (int i = 0; i < spaces.size(); i++) {
            if (spaces.get(i) instanceof Property) {
                Property property = (Property) spaces.get(i);
                if(property.owner.name.equals(name) && ownsColorGroup(property.color) && property.houses < 4 && money > property.houseCost)
                    buildableProperties.add(property);
             }
        }
        return buildableProperties;
    }

    private void sortPropertiesByHouseCost(ArrayList<Property> properties) {
        int n = properties.size();
        for (int i = 0; i < n - 1; i++) {
            int minMaxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (this.houseManagement < 0.5)
                    if (properties.get(j).houseCost < properties.get(minMaxIndex).houseCost)
                        minMaxIndex = j;
                else 
                    if (properties.get(j).houseCost > properties.get(minMaxIndex).houseCost)
                        minMaxIndex = j;
            }
            Property temp = properties.get(minMaxIndex);
            properties.set(minMaxIndex, properties.get(i));
            properties.set(i, temp);
        }
    }    

    public void buildHouses() {
    if(Math.random() < houseManagement){
        ArrayList<Space> spaces = this.spaces;
        ArrayList<Property> buildableProperties = getBuildableProperties(spaces);
        ArrayList<ArrayList<Property>> propertiesByColor = new ArrayList<>();

        for (Property property : buildableProperties) {
            boolean hasGroup = false;
            for(ArrayList<Property> group : propertiesByColor)
            {
                if(group.get(0).color.equals(property.color))
                {
                    group.add(property);
                    hasGroup = true;
                    break;
                }
            }
            if(!hasGroup){
                propertiesByColor.add(new ArrayList<>(Arrays.asList(property)));
            }
        }

       if (buildableProperties.size() > 0) {
            for (int houseLevel = 1; houseLevel <= 4; houseLevel++) { // Check for every house level.
                 for(ArrayList<Property> group : propertiesByColor){ //Iterate through every group.
                    sortPropertiesByHouseCost(group);
                   for(Property property : group){ //Try to build on every property
                        if(property.houses == houseLevel -1 &&  money > property.houseCost)
                       {
                           property.houses += 1;
                            money -= property.houseCost;
                           System.out.println(name + " bought a house on " + property.name + " for $" + property.houseCost + "! This makes its rent now: $" + property.rent[property.houses] +
                               " instead of $" + property.rent[property.houses - 1]);
                           return;
                        }
                   }
               }
           }
        }
       }
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

    
    public int totalNumberOfHouses()
    {
        int rtn = 0;
        for (int i = 0; i < spaces.size(); i++) 
        {
            if(spaces.get(i) instanceof Property)
            {
                Property property = (Property) spaces.get(i);
                rtn += property.houses;
            }
        }
        return rtn;
    }
    public ArrayList<Property> sortCheapest() // ONLY CALL AFTER CHECKING totalNumberOfHouses() FOR PLAYER > 0
    {
        ArrayList<Property> housedProperties = new ArrayList<>();
        for (int i = 0; i < spaces.size(); i++) 
        {
            if(spaces.get(i) instanceof Property)
            {
                Property property = (Property) spaces.get(i);
                if(property.houses > 0)
                    housedProperties.add(property);
            }
        }
        if(housedProperties.size() == 1)
            return housedProperties;
        for (int i = 0; i < housedProperties.size() - 1; i++)  //I think this was called a bubble sort idfk
        {
            for (int j = 0; j < housedProperties.size() - 1 - i; j++) 
            {
                if (housedProperties.get(j).houseCost > housedProperties.get(j + 1).houseCost) 
                {
                    Property temp = housedProperties.get(j);
                    housedProperties.set(j, housedProperties.get(j + 1));
                    housedProperties.set(j + 1, temp);
                }
            }
        }
        return housedProperties; 
    }
    public void sellHouse()
    {
        ArrayList<Property> propertiesToSell = sortCheapest();
        Property byeBye = propertiesToSell.get(0);
        
        int indexOfSellHouse = 0;
        for (int i = 0; i < spaces.size(); i++) {
            if(spaces.get(i) instanceof Property)
            {
                Property property = (Property) spaces.get(i);
                if(property.name.equals(byeBye.name))    
                {
                    indexOfSellHouse = i; 
                    break;
                }
            }
        }
        Property property = (Property) spaces.get(indexOfSellHouse);
        System.out.println("Player before house sell balance: " + money);
        property.houses--;
        money += property.houseCost;
        System.out.println(name + " sold a house on " + property.name + " for " + " $" + property.houseCost);
        System.out.println(name + "'s balance is now " + money);
    }
    //Checks if player owns specificed color group for house building
    public boolean ownsColorGroup(String colorGroup) {
        int hasColor = 0;
        for (int i = 0; i < spaces.size(); i++) {
            if(spaces.get(i) instanceof Property)
            {
                Property property = (Property) spaces.get(i);
//                System.out.println("Debug color current property " + property.color);
                if(property.color.equals(colorGroup))
                {
                    hasColor++;    
//                    System.out.println("Debug: property equals " + property.color + colorGroup);
//                    System.out.println("HasColor count " + hasColor);

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

    public void offerTrades() {
        //Gets possible properties that player wants
        HashSet<String> wantedProperties = new HashSet<>();
        for (Space s : spaces) {
            if (s instanceof Property property) {
                wantedProperties.add(property.color);
            }
            if (s instanceof Railroad) {
                wantedProperties.add("Railroad");
            }
            if (s instanceof Utility) {
                wantedProperties.add("Utility");
            }
        }

        System.out.println("DEBUG: FLAG AFTER SET");

        for (Player p : Main.players) {
            for (int i = 0; i < p.spaces.size(); i++) {
                Space s = p.spaces.get(i);
                if (s instanceof Property) {
                    Property property = (Property) s;
                    if (wantedProperties.contains(property.color)) {
                        //Make a tradeOffer
                        Space toGiveSpace = findClosestValueSpace(property.price, property.color);
                        if (toGiveSpace != null) {
                            ArrayList<Space> player1Spaces = new ArrayList<>();
                            player1Spaces.add(toGiveSpace);
                            ArrayList<Space> player2Spaces = new ArrayList<>();
                            player2Spaces.add(s);
                            TradeOffer tradeOffer = new TradeOffer(player1Spaces, 0, player2Spaces, 0, this, p);
                            p.considerIncomingTradeOffer(tradeOffer);
                            break;
                        }
                    }
                }
                if (s instanceof Railroad) {
                    Railroad railroad = (Railroad) s;
                    if (wantedProperties.contains("Railroad")) {
                        //Make a tradeOffer
                        Space toGiveSpace = findClosestValueSpace(railroad.price, "Railroad");
                        if (toGiveSpace != null) {
                            ArrayList<Space> player1Spaces = new ArrayList<>();
                            player1Spaces.add(toGiveSpace);
                            ArrayList<Space> player2Spaces = new ArrayList<>();
                            player2Spaces.add(s);
                            TradeOffer tradeOffer = new TradeOffer(player1Spaces, 0, player2Spaces, 0, this, p);
                            p.considerIncomingTradeOffer(tradeOffer);
                        }
                    }
                }
                if (s instanceof Utility) {
                    Utility utility = (Utility) s;
                    if (wantedProperties.contains("Utility")) {
                        //Make a tradeOffer
                        Space toGiveSpace = findClosestValueSpace(utility.price, "Utility");
                        if (toGiveSpace != null) {
                            ArrayList<Space> player1Spaces = new ArrayList<>();
                            player1Spaces.add(toGiveSpace);
                            ArrayList<Space> player2Spaces = new ArrayList<>();
                            player2Spaces.add(s);
                            TradeOffer tradeOffer = new TradeOffer(player1Spaces, 0, player2Spaces, 0, this, p);
                            p.considerIncomingTradeOffer(tradeOffer);
                        }
                    }
                }
            }
        }
        System.out.println("FLAG FINISH TRADES");
    }

    public Space findClosestValueSpace(int targetValue, String dontTradeTag) {
        int distanceFromTarget = 99999;
        Space toGive = null;
        for (Space s : spaces) {
            if (s instanceof Property) {
                Property property = (Property) s;
                if (!property.color.equals(dontTradeTag)) {
                    if (Math.abs(property.price - targetValue) <= distanceFromTarget) {
                        distanceFromTarget = Math.abs(property.price - targetValue);
                        toGive = s;
                    }
                }
            }
            if (s instanceof Railroad) {
                Railroad railroad = (Railroad) s;
                if (!dontTradeTag.equals("Railroad")) {
                    if (Math.abs(railroad.price - targetValue) <= distanceFromTarget) {
                        distanceFromTarget = Math.abs(railroad.price - targetValue);
                        toGive = s;
                    }
                }
            }
            if (s instanceof Utility) {
                Utility utility = (Utility) s;
                if (!dontTradeTag.equals("Utility")) {
                    if (Math.abs(utility.price - targetValue) <= distanceFromTarget) {
                        distanceFromTarget = Math.abs(utility.price - targetValue);
                        toGive = s;
                    }
                }
            }
        }
        return toGive;
    }

    public void considerIncomingTradeOffer(TradeOffer tradeOffer) {
        if (Math.random() < tradingStrategy) {
            tradeOffer.trade();
        }
    }
}


