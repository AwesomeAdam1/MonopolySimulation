import java.util.Random;

public class Jail extends Space {

    private int bailCost;
    private Random random;

    public Jail(Player currentPlayer) {

        this.bailCost = 50;
        this.random = new Random(); // initialize random number generator.
    }

    public int getBailCost() {
        return bailCost;
    }

    public void setBailCost(int bailCost) {
        this.bailCost = bailCost;
    }

    // Method to handle a player landing on Jail
    public void landOn(Player player) {
        System.out.println(player.getName() + " has landed on Jail!");

        // Check if player is going to jail or just visiting
        if (!player.isInJail()) {
            // player has actually landed on jail, so set the player to go to jail
            player.setInJail(true);
            System.out.println(player.getName() + " is now in Jail!");

            player.setPositionIndex(10); // Set player location to Jail space, using hardcoded value
        }
        else{
           // player was just visiting, so we will allow visiting the jail
            // we do not need to tell player that they are visiting the jail
            System.out.println(player.getName() + "is visiting jail!");
        }

    }

    // Method to allow a player to attempt to pay bail and leave jail, based on risk appetite
    public void payBail(Player player) {
        if (player.isInJail()) {
            double riskAppetite = player.getRiskAppetite();
            // Generate a random double between 0 and 1.
            double randomValue = random.nextDouble();

            // If the random value is less than the risk appetite, pay the bail.
            if (randomValue < riskAppetite) {
                if(player.getMoney() >= bailCost) {
                    player.setMoney(player.getMoney() - bailCost);
                    player.setInJail(false);
                    System.out.println(player.getName() + " paid $" + bailCost + " to get out of Jail.");
                } else {
                    System.out.println(player.getName() + " does not have enough money to pay bail!");
                }

            } else {
                System.out.println(player.getName() + " decided to stay in Jail.");
            }
        } else {
            System.out.println(player.getName() + " is not in Jail!");
        }

    }
    @Override
    public String toString(){
        return super.toString();
    }
}