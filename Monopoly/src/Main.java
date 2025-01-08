import java.util.ArrayList;

public class Main {
    public static Space[] board = {
        new Go(),
        new Property("Mediterranean Avenue", "Purple", 60, 50, new int[]{2, 10, 30, 90, 160, 250}, 30),
        new CommunityChest(),
        new Property("Baltic Avenue", "Purple", 60, 50, new int[]{4, 20, 60, 180, 320, 450}, 30),
        new Tax("Income Tax", true),
        new RailRoad("Reading Railroad"),
        new Property("Oriental Avenue", "LightBlue", 100, 50, new int[]{6, 30, 90, 270, 400, 550}, 50),
        new Chance(),
        new Property("Vermont Avenue", "LightBlue", 100, 50, new int[]{6, 30, 90, 270, 400, 550}, 50),
        new Property("Connecticut Avenue", "LightBlue", 120, 50, new int[]{8, 40, 100, 300, 450, 600}, 60),
        new Jail(),
        new Property("St. Charles Place", "Pink", 140, 100, new int[]{10, 50, 150, 450, 625, 750}, 70),
        new Utility("Electric Company"),
        new Property("States Avenue", "Pink", 140, 100, new int[]{10, 50, 150, 450, 625, 750}, 70),
        new Property("Virginia Avenue", "Pink", 160, 100, new int[]{12, 60, 180, 500, 700, 900}, 80),
        new RailRoad("Pennsylvania Railroad"),
        new Property("St. James Place", "Orange", 180, 100, new int[]{14, 70, 200, 550, 750, 950}, 90),
        new CommunityChest(),
        new Property("Tennessee Avenue", "Orange", 180, 100, new int[]{14, 70, 200, 550, 750, 950}, 90),
        new Property("New York Avenue", "Orange", 200, 100, new int[]{16, 80, 220, 600, 800, 1000}, 100),
        new FreeParking(),
        new Property("Kentucky Avenue", "Red", 220, 150, new int[]{18, 90, 250, 700, 875, 1050}, 110),
        new Chance(),
        new Property("Indiana Avenue", "Red", 220, 150, new int[]{18, 90, 250, 700, 875, 1050}, 110),
        new Property("Illinois Avenue", "Red", 240, 150, new int[]{20, 100, 300, 750, 925, 1100}, 120),
        new RailRoad("B. & O. Railroad"),
        new Property("Atlantic Avenue", "Yellow", 260, 150, new int[]{22, 110, 330, 800, 975, 1150}, 130),
        new Property("Ventnor Avenue", "Yellow", 260, 150, new int[]{22, 110, 330, 800, 975, 1150}, 130),
        new Utility("Water Works"),
        new Property("Marvin Gardens", "Yellow", 280, 150, new int[]{24, 120, 360, 850, 1025, 1200}, 140),
        new GoToJail(),
        new Property("Pacific Avenue", "Green", 300, 200, new int[]{26, 130, 390, 900, 1100, 1275}, 150),
        new Property("North Carolina Avenue", "Green", 300, 200, new int[]{26, 130, 390, 900, 1100, 1275}, 150),
        new CommunityChest(),
        new Property("Pennsylvania Avenue", "Green", 320, 200, new int[]{28, 150, 450, 1000, 1200, 1400}, 160),
        new RailRoad("Short Line"),
        new Chance(),
        new Property("Park Place", "Blue", 350, 200, new int[]{35, 175, 500, 1100, 1300, 1500}, 175),
        new Tax("Luxury Tax", false),
        new Property("Boardwalk", "Blue", 400, 200, new int[]{50, 200, 600, 1400, 1700, 2000}, 200),
    };

    public static void main(String[] args) {
        int maxIterations = 1000;
        int playerIndex = 0;
        int iterations = 0;
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Player1", 0.5, 0.5, 0.5));
        players.add(new Player("Player2", 0.5, 0.5, 0.5));
        players.add(new Player("Player3", 0.5, 0.5, 0.5));
        players.add(new Player("Player4", 0.5, 0.5, 0.5));

        while (iterations <= maxIterations && players.size() >= 2) {
            Player currentPlayer = players.get(playerIndex);
            System.out.printf("========== Iteration %d - %s =========\n", iterations, currentPlayer.name);

            int doublesCount = 0;
            int roll1;
            int roll2;

            if (currentPlayer.inJail) {
                Jail jail = null;
                System.out.printf("In Jail. %d turns left\n", --currentPlayer.jailTimeLeft);
                roll1 = Dice.roll();
                roll2 = Dice.roll();
                System.out.printf("Rolled a %d and a %d\n", roll1, roll2);

                //Check doubles
                if (roll1 == roll2) {
                    System.out.printf("DOUBLES! Free get out of jail", doublesCount);
                    currentPlayer.positionIndex = (currentPlayer.positionIndex + roll1 + roll2 % 40);

                    //Do action on space
                    board[currentPlayer.positionIndex].doAction(currentPlayer);
                }
                //player attempts to pay bail based on risk appetite
                jail.payBail(currentPlayer);

            } 
                else {
                do {
                    roll1 = Dice.roll();
                    roll2 = Dice.roll();
                    currentPlayer.positionIndex = ((currentPlayer.positionIndex + roll1 + roll2) % 40);
                    System.out.printf("Rolled a %d and a %d\n", roll1, roll2);

                    //Passed Go
                    if (currentPlayer.positionIndex - roll1 - roll2 <= 0) {
                        currentPlayer.money += 200;
                    }

                    //Check doubles
                    if (roll1 == roll2) {
                        System.out.printf("DOUBLES! Doubles Count: %d\n", doublesCount);
                        doublesCount++;
                    }

                    //Do action on space and make sure that last roll was not 3 doubles in a row
                    if (doublesCount < 3) {
                            board[currentPlayer.positionIndex].doAction(currentPlayer);
                    }
                } while (roll1 != roll2 && doublesCount < 3);

                //3 doubles in a row -> jail
                if (doublesCount == 3) {
                    System.out.printf("Rolled 3 doubles in a row, going to jail.\n");
                    currentPlayer.inJail = true;
                    currentPlayer.jailTimeLeft = 3;
                    currentPlayer.positionIndex = 10;
                }


            }

            //Checks bankruptcy <SIMPLE>
            if (currentPlayer.money <= 0) {
                System.out.println("No more money! Player is removed.");
                players.remove(playerIndex);
                playerIndex--;
            }

            System.out.println("");
            iterations++;
            playerIndex = (playerIndex + 1) % players.size();
        }

    }
}