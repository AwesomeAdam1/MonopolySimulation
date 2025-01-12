import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    public final String[] colorOrder = {"Purple", "LightBlue", "Pink", "Orange", "Red", "Yellow", "Green", "Blue"};

    public ArrayList<Space> spaces = new ArrayList<>();
    public int positionIndex = 0;
    public int money = 1500;
    public String name;
    public boolean inJail = false;
    public int jailTimeLeft = 0;
    public int biddingIncrement = 10;
    public double houseManagement;
    public double propertyManagement;
    public double riskAppetite;
    public double tradingStrategy;
    public double auctionStrategy;

    /*
        House Management
            - How much of their money they invest into houses
        Property Management
            - How likely they are to buy a property depending on value
        Risk Appetite
            - How much money want as in security for issues
        Trading Strategy
            - Frequency they trade
        Auction Strategy
            - How much they are willing to bid on an auction
     */

    public Player(String name, double houseManagement, double propertyManagement, double riskAppetite, double tradingStrategy, double auctionStrategy) {
        this.name = name;
        this.houseManagement = houseManagement;
        this.propertyManagement = propertyManagement;
        this.riskAppetite = riskAppetite;
        this.tradingStrategy = tradingStrategy;
        this.auctionStrategy = auctionStrategy;
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

    public void analysis() {
        buildHouses();
    }

    public boolean offerToBuySpace(Space space) {
        if (space instanceof Property) {
            if (Math.random() > propertyManagement) {
                return false;
            }
            Property property = (Property) space;
            return true;
        }
        if (space instanceof Railroad) {
            if (Math.random() > propertyManagement) {
                return false;
            }
            Railroad railroad = (Railroad) space;
            return true;
        }
        if (space instanceof Utility) {
            if (Math.random() > propertyManagement) {
                return false;
            }
            Utility utility = (Utility) space;
            return true;
        }
        return false;
    }

    public void buildHouses() {
        HashMap<String, ArrayList<Property>> propertyAmounts = new HashMap<>();
        for (Space s : spaces) {
            if (s instanceof Property) {
                Property property = (Property) s;
                if (propertyAmounts.containsKey(property.color)) {
                    propertyAmounts.get(property.color).add(property);
                } else {
                    propertyAmounts.put(property.color, new ArrayList<>());
                    propertyAmounts.get(property.color).add(property);
                }
            }
        }

        for (Map.Entry<String, ArrayList<Property>> entry
                : propertyAmounts.entrySet()) {
            if (entry.getValue().size() != 3) {
                //Checks if all houses are built already
                boolean valid = false;
                for (Property p : entry.getValue()) {
                    if (p.houses < 5) {
                        valid = true;
                    }
                }
                if (!valid) {
                    continue;
                }

                //Spends on most expensive if houseManagement >= 0.5 and vise versa
                if (houseManagement >= 0.5) {

                } else {

                }
            }
        }


        int maxAmountToSpend = (int) (money * houseManagement);

        //Spends on most expensive if houseManagement >= 0.5 and vise versa
        String color = "";

    }

    public int bid(int currentBid, Space space) {
        // auctionStrategy ∈ [0,1) : where every .1 represents max bid will be up to (price * auctionStrategy/0.1)
        // auctionStrategy = 1 : means always overbid when possible
        if (auctionStrategy < 1) {
            if (space instanceof Property) {
                Property property = (Property) space;
                int maxBid = (int) (property.price * auctionStrategy / 0.1);
                if (currentBid < maxBid) {
                    if (currentBid + biddingIncrement <= money) {
                        //Bid only when have enough money
                        return currentBid + biddingIncrement;
                    } else {
                        //No money to bid more
                        return 0;
                    }
                } else {
                    //Not willing to bid more
                    return 0;
                }
            }
            if (space instanceof Railroad) {
                Railroad railroad = (Railroad) space;
                int maxBid = (int) (railroad.price * auctionStrategy / 0.1);
                if (currentBid < maxBid) {
                    if (currentBid + biddingIncrement <= money) {
                        //Bid only when have enough money
                        return currentBid + biddingIncrement;
                    } else {
                        //No money to bid more
                        return 0;
                    }
                } else {
                    //Not willing to bid more
                    return 0;
                }
            }
            if (space instanceof Utility) {
                Utility utility = (Utility) space;
                int maxBid = (int) (utility.price * auctionStrategy / 0.1);
                if (currentBid < maxBid) {
                    if (currentBid + biddingIncrement <= money) {
                        //Bid only when have enough money
                        return currentBid + biddingIncrement;
                    } else {
                        //No money to bid more
                        return 0;
                    }
                } else {
                    //Not willing to bid more
                    return 0;
                }
            }
        } else {
            // auctionStrategy is 1
            if (currentBid + biddingIncrement <= money) {
                //Bid only when have enough money
                return currentBid + biddingIncrement;
            } else {
                //No money to bid more
                return 0;
            }
        }
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
