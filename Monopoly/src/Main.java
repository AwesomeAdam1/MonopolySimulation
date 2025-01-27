import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public Space[] board = {
            new Go(),
            new Property("Mediterranean Avenue", "Purple", 60, 50, new int[]{2, 10, 30, 90, 160, 250}, 30),
            new CommunityChest(),
            new Property("Baltic Avenue", "Purple", 60, 50, new int[]{4, 20, 60, 180, 320, 450}, 30),
            new Tax("Income Tax", true),
            new Railroad("Reading Railroad"),
            new Property("Oriental Avenue", "LightBlue", 100, 50, new int[]{6, 30, 90, 270, 400, 550}, 50),
            new Chance(),
            new Property("Vermont Avenue", "LightBlue", 100, 50, new int[]{6, 30, 90, 270, 400, 550}, 50),
            new Property("Connecticut Avenue", "LightBlue", 120, 50, new int[]{8, 40, 100, 300, 450, 600}, 60),
            new Jail(),
            new Property("St. Charles Place", "Pink", 140, 100, new int[]{10, 50, 150, 450, 625, 750}, 70),
            new Utility("Electric Company"),
            new Property("States Avenue", "Pink", 140, 100, new int[]{10, 50, 150, 450, 625, 750}, 70),
            new Property("Virginia Avenue", "Pink", 160, 100, new int[]{12, 60, 180, 500, 700, 900}, 80),
            new Railroad("Pennsylvania Railroad"),
            new Property("St. James Place", "Orange", 180, 100, new int[]{14, 70, 200, 550, 750, 950}, 90),
            new CommunityChest(),
            new Property("Tennessee Avenue", "Orange", 180, 100, new int[]{14, 70, 200, 550, 750, 950}, 90),
            new Property("New York Avenue", "Orange", 200, 100, new int[]{16, 80, 220, 600, 800, 1000}, 100),
            new FreeParking(),
            new Property("Kentucky Avenue", "Red", 220, 150, new int[]{18, 90, 250, 700, 875, 1050}, 110),
            new Chance(),
            new Property("Indiana Avenue", "Red", 220, 150, new int[]{18, 90, 250, 700, 875, 1050}, 110),
            new Property("Illinois Avenue", "Red", 240, 150, new int[]{20, 100, 300, 750, 925, 1100}, 120),
            new Railroad("B. & O. Railroad"),
            new Property("Atlantic Avenue", "Yellow", 260, 150, new int[]{22, 110, 330, 800, 975, 1150}, 130),
            new Property("Ventnor Avenue", "Yellow", 260, 150, new int[]{22, 110, 330, 800, 975, 1150}, 130),
            new Utility("Water Works"),
            new Property("Marvin Gardens", "Yellow", 280, 150, new int[]{24, 120, 360, 850, 1025, 1200}, 140),
            new GoToJail(),
            new Property("Pacific Avenue", "Green", 300, 200, new int[]{26, 130, 390, 900, 1100, 1275}, 150),
            new Property("North Carolina Avenue", "Green", 300, 200, new int[]{26, 130, 390, 900, 1100, 1275}, 150),
            new CommunityChest(),
            new Property("Pennsylvania Avenue", "Green", 320, 200, new int[]{28, 150, 450, 1000, 1200, 1400}, 160),
            new Railroad("Short Line"),
            new Chance(),
            new Property("Park Place", "Blue", 350, 200, new int[]{35, 175, 500, 1100, 1300, 1500}, 175),
            new Tax("Luxury Tax", false),
            new Property("Boardwalk", "Blue", 400, 200, new int[]{50, 200, 600, 1400, 1700, 2000}, 200),
    };
    public static ArrayList<Player> players;
    public String[] printTable = new String[4];

    public Main(ArrayList<Player> newPlayers) {
        int maxIterations = 1000;
        int playerIndex = 0;
        int iterations = 1;
        int player1Placement = -1;
        int player17Placement = -1;

        players = newPlayers;
        for (int i = 0; i < 4; i++) {
            printTable[i] = players.get(i).name;
        }
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                if (i != j) {
//                    Simulation.totalGames[Integer.parseInt(players.get(i).name.substring(6))][Integer.parseInt(players.get(j).name.substring(6))]++;
//                }
//            }
//        }

        int[] movesPerPlayer = new int[Simulation.playerCount];
        while (iterations <= maxIterations && players.size() >= 2) {
            Player currentPlayer = players.get(playerIndex);
            //System.out.printf("========== Iteration %d - %s =========\n", iterations, currentPlayer.name);
            //System.out.println("Current Money: " + currentPlayer.money);
            //System.out.printf("On %s\n", board[currentPlayer.positionIndex].toString());

            int doublesCount = 0;
            int roll1 = -1;
            int roll2 = -1;

            //Decides if player wants to stay in jail
            if (currentPlayer.inJail) {
                //System.out.printf("In Jail. %d turns left\n", --currentPlayer.jailTimeLeft);
                roll1 = Dice.roll();
                roll2 = Dice.roll();
                //System.out.printf("Rolled a %d and a %d\n", roll1, roll2);

                //Check doubles
                if (roll1 == roll2) {
                    //System.out.println("DOUBLES! Free get out of jail");
                    currentPlayer.positionIndex = (currentPlayer.positionIndex + roll1 + roll2) % 40;
                    //System.out.printf("Landed on %s\n", board[currentPlayer.positionIndex].toString());
                    currentPlayer.inJail = false;

                    //Do action on space
                    if (board[currentPlayer.positionIndex] instanceof Utility) {
                        ((Utility) board[currentPlayer.positionIndex]).doAction(currentPlayer, roll1 * 2);
                    } else {
                        board[currentPlayer.positionIndex].doAction(currentPlayer);
                    }
                }

                //Check if 3 turns have past, if so bail is forced
                if (currentPlayer.jailTimeLeft == 0 && currentPlayer.inJail) {
                    //System.out.println("3 turns have passed, player is forced to pay $50");
                    currentPlayer.payAmount(50);
                    currentPlayer.inJail = false;

                    if (currentPlayer.money >= 0) {
                        currentPlayer.positionIndex = (currentPlayer.positionIndex + roll1 + roll2) % 40;
                        if(board[currentPlayer.positionIndex] instanceof Property)
                        {
                            Property temp = (Property) board[currentPlayer.positionIndex];
                            //System.out.printf("Landed on %s\n", board[currentPlayer.positionIndex].toString() + " which has " +
                            //        temp.houses + " houses which costs $" + temp.rent[temp.houses] + " owned by player: " +
                            //        (temp.owner == null ? "no-one" : temp.owner.name) + " with $" + (temp.owner == null ? "0" : temp.owner.money));
                        }
                        else
                        {
                            //System.out.printf("Landed on %s\n", board[currentPlayer.positionIndex].toString());
                        }
                        //Do action on space
                        if (board[currentPlayer.positionIndex] instanceof Utility) {
                            ((Utility) board[currentPlayer.positionIndex]).doAction(currentPlayer, roll1 * 2);
                        } else {
                            board[currentPlayer.positionIndex].doAction(currentPlayer);
                        }
                    } else {
                        currentPlayer.setOwnersOfSpaces(null);
                    }
                }

                //Player attempts to pay bail based on risk appetite
                if (currentPlayer.inJail) {
                    boolean result = currentPlayer.payBail();
                    if (result) {
                        //System.out.println("Paid $50 to get out of jail");
                        roll1 = -1;
                        roll2 = -1;
                    }
                }
                currentPlayer.jailTimeLeft--;
            }

            //Won't run if player decides to stay in jail but will if they decide to pay (not forced) to get out
            if (!currentPlayer.inJail && roll1 == -1 && roll2 == -1 && currentPlayer.money >= 0) {
                do {
                    roll1 = Dice.roll();
                    roll2 = Dice.roll();
                    currentPlayer.positionIndex = ((currentPlayer.positionIndex + roll1 + roll2) % 40);
                    //System.out.printf("Rolled a %d and a %d\n", roll1, roll2);
                    if(board[currentPlayer.positionIndex] instanceof Property)
                    {
                        Property temp = (Property) board[currentPlayer.positionIndex];
                        //System.out.printf("Landed on %s\n", board[currentPlayer.positionIndex].toString() + " which has " +
                        //        temp.houses + " houses which costs $" + temp.rent[temp.houses] + " owned by player: " +
                        //        (temp.owner == null ? "no-one" : temp.owner.name) + " with $" + (temp.owner == null ? "0" : temp.owner.money));
                    }
                    else
                    {
                        //System.out.printf("Landed on %s\n", board[currentPlayer.positionIndex].toString());
                    }

                    //Passed Go
                    if (currentPlayer.positionIndex - roll1 - roll2 <= 0) {
                        currentPlayer.money += 200;
                    }

                    //Check doubles
                    if (roll1 == roll2) {
                        doublesCount++;
                        //System.out.printf("DOUBLES! Doubles Count: %d\n", doublesCount);
                    }


                    //Do action on space and make sure that last roll was not 3 doubles in a row
                    if (doublesCount < 3) {
                        if (board[currentPlayer.positionIndex] instanceof Utility) {
                            ((Utility) board[currentPlayer.positionIndex]).doAction(currentPlayer, roll1 * 2);
                        } else {
                            board[currentPlayer.positionIndex].doAction(currentPlayer);
                        }
                    }
                } while (roll1 == roll2 && doublesCount < 3);

                //3 doubles in a row -> jail
                if (doublesCount == 3) {
                    //System.out.println("Rolled 3 doubles in a row, going to jail.");
                    currentPlayer.inJail = true;
                    currentPlayer.jailTimeLeft = 3;
                    currentPlayer.positionIndex = 10;
                }
            }

            int buildHouseIteration = 0;
            for(Space space : currentPlayer.spaces)
            {
                if(space instanceof Property)
                    buildHouseIteration++;
            }

            if (currentPlayer.money >= 0) {
                currentPlayer.buildHouses(buildHouseIteration);
                currentPlayer.offerTrades();
            }

            //System.out.println("DEBUG: NEXT PLAYER");
            //System.out.println("Current player money: " + currentPlayer.money + "");
            //Checks bankruptcy

            if (currentPlayer.money <= 0) {
                //System.out.println("BANKRUPT! "+ currentPlayer.name + " is removed.");
                int playerArrayIndex = Integer.parseInt(currentPlayer.name.substring(6));
                Simulation.scores[playerArrayIndex] += 4 - players.size();
                // Space space = board[currentPlayer.positionIndex];
                // Player propOwner = null;
                // if (space instanceof Property) {
                //     Property property = (Property) space;
                //     propOwner = property.owner;
                // }
                // if (space instanceof Railroad) {
                //     Railroad railroad = (Railroad) space;
                //     propOwner = railroad.owner;
                // }
                // if (space instanceof Utility) {
                //     Utility utility = (Utility) space;
                //     propOwner = utility.owner;
                // }
                // if(space instanceof Tax)
                // {
                //     players.remove(playerIndex);
                //     playerIndex--;
                //     break;
                // }

                // System.out.println("The player was bankrupted by " + propOwner.toString());
                // for (int i = 0; i < currentPlayer.spaces.size(); i++) {
                //     propOwner.addSpace(currentPlayer.spaces.get(i));
                //     System.out.println(currentPlayer.spaces.get(i).toString() + " has been transferred to " + propOwner.name +
                //     " from " + currentPlayer.toString());
                // }

                if (playerArrayIndex == 1) {
                    player1Placement = 4 - players.size();
                }
                if (playerArrayIndex == 17) {
                    player17Placement = 4 - players.size();
                }

                players.remove(playerIndex);
                playerIndex--;

                for (int i = 0; i < players.size(); i++) {
                    Simulation.winsPerPlayerType[Integer.parseInt(players.get(i).name.substring(6))][playerArrayIndex]++;
                }
                for (int i = 0; i < 4; i++) {
                    if (!printTable[i].equals(currentPlayer.name)) {
                        Simulation.lifeExpectancyPerPlayer[playerArrayIndex][Integer.parseInt(printTable[i].substring(6))] += iterations;
                    }
                }
            }

            int playerArrayIndex = Integer.parseInt(currentPlayer.name.substring(6));
            movesPerPlayer[playerArrayIndex]++;
            for (int i = 1; i <= 15; i++) {
                if (movesPerPlayer[playerArrayIndex] <= i * Simulation.networthIntervals) {
                    Simulation.totalNetworth[playerArrayIndex][i] += currentPlayer.getNetWorth();
                    Simulation.totalGames[playerArrayIndex][i]++;
                }
            }

            // if (movesPerPlayer[playerArrayIndex] <= 50) {
            //     int totalMoves = Simulation.totalMoves50[playerArrayIndex];
            //     Simulation.avgNetworth50[playerArrayIndex]
            //             = (currentPlayer.getNetWorth() + totalMoves * Simulation.avgNetworth50[playerArrayIndex])/(totalMoves + 1);
            //     Simulation.totalMoves50[playerArrayIndex]++;
            // } else if (movesPerPlayer[playerArrayIndex] <= 100) {
            //     int totalMoves = Simulation.totalMoves100[playerArrayIndex];
            //     Simulation.avgNetworth100[playerArrayIndex]
            //             = (currentPlayer.getNetWorth() + totalMoves * Simulation.avgNetworth100[playerArrayIndex])/(totalMoves + 1);
            //     Simulation.totalMoves100[playerArrayIndex]++;
            // } else if (movesPerPlayer[playerArrayIndex] <= 150) {
            //     int totalMoves = Simulation.totalMoves150[playerArrayIndex];
            //     Simulation.avgNetworth150[playerArrayIndex]
            //             = (currentPlayer.getNetWorth() + totalMoves * Simulation.avgNetworth150[playerArrayIndex])/(totalMoves + 1);
            //     Simulation.totalMoves150[playerArrayIndex]++;
            // } else if (movesPerPlayer[playerArrayIndex] <= 200) {
            //     int totalMoves = Simulation.totalMoves200[playerArrayIndex];
            //     Simulation.avgNetworth200[playerArrayIndex]
            //             = (currentPlayer.getNetWorth() + totalMoves * Simulation.avgNetworth200[playerArrayIndex])/(totalMoves + 1);
            //     Simulation.totalMoves200[playerArrayIndex]++;
            // } else if (movesPerPlayer[playerArrayIndex] <= 250) {
            //     int totalMoves = Simulation.totalMoves250[playerArrayIndex];
            //     Simulation.avgNetworth250[playerArrayIndex]
            //             = (currentPlayer.getNetWorth() + totalMoves * Simulation.avgNetworth250[playerArrayIndex])/(totalMoves + 1);
            //     Simulation.totalMoves250[playerArrayIndex]++;
            // } else {
            //     //System.out.println("bum");
            //     int totalMoves = Simulation.totalMoves250more[playerArrayIndex];
            //     Simulation.avgNetworth250more[playerArrayIndex]
            //             = (currentPlayer.getNetWorth() + totalMoves * Simulation.avgNetworth250more[playerArrayIndex])/(totalMoves + 1);
            //     Simulation.totalMoves250more[playerArrayIndex]++;
            // }

            iterations++;
            playerIndex = (playerIndex + 1) % players.size();
        }

        if (players.size() == 1){
            int playerArrayIndex = Integer.parseInt(players.get(0).name.substring(6));
            Simulation.scores[playerArrayIndex] += 3;
            Simulation.lengthsaaa += iterations;
            if (playerArrayIndex == 1) {
                player1Placement = 4 - players.size();
            }
            if (playerArrayIndex == 17) {
                player17Placement = 4 - players.size();
            }
        } else {

            Collections.sort(players, (a, b) -> b.money - a.money);
            for (int i = 0; i < players.size(); i++) {
                int playerArrayIndex = Integer.parseInt(players.get(i).name.substring(6));
                Simulation.scores[playerArrayIndex] += 3 - i;
                if (playerArrayIndex == 1) {
                    player1Placement = 3 - i;
                }
                if (playerArrayIndex == 17) {
                    player17Placement = 3 - i;
                }
                for (int j = i + 1; j < players.size(); j++) {
                   Simulation.winsPerPlayerType[playerArrayIndex][Integer.parseInt(players.get(j).name.substring(6))]++;
                }
                for (int j = 0; j < 4; j++) {
                    if (!printTable[i].equals(players.get(i).name)) {
                        Simulation.lifeExpectancyPerPlayer[playerArrayIndex][Integer.parseInt(printTable[i].substring(6))] += iterations;
                    }
                }
            }
            Simulation.lengthsaaa += maxIterations;

        }

        boolean hasP1 = false;
        boolean hasP17 = false;
        for (int i = 0; i < printTable.length; i++) {
            if (Integer.parseInt(printTable[i].substring(6)) == 1) {
                hasP1 = true;
            }
            if (Integer.parseInt(printTable[i].substring(6)) == 17) {
                hasP17 = true;
            }
        }
        if (hasP1 && hasP17) {
            if (iterations > 50) {
                Simulation.player1PlacementsAfter.add(player1Placement);
                Simulation.player17PlacementsAfter.add(player17Placement);
            } else {
                Simulation.player1PlacementsBefore.add(player1Placement);
                Simulation.player17PlacementsBefore.add(player17Placement);
            }
        }
        

        //System.out.println("========== GAME END " + iterations + "=========");
        //System.out.println(players.size());
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            //System.out.println(player.toString());
            //System.out.println(player.money);
            for (Space s : player.spaces) {
                //System.out.println(s.toString());
            }
        }

    }

    public void printIteration(int iteration, boolean dontuse) {
        StringBuffer str = new StringBuffer();
        str.append("G:" + Simulation.game + ",I:" + iteration + ",");
        for (int i = 0; i < players.size(); i++) {
            str.append("P" + players.get(i).name.substring(6) + ":" + players.get(i).getNetWorth() + ",");
        }
        Simulation.filePrint.println(str);
    }

    public void printIteration(int iteration) {
        StringBuffer str = new StringBuffer();
        str.append(iteration + ",");
        int tableIndex = 0;

        for (int i = 0; i < printTable.length; i++) {
            if (tableIndex < players.size() && printTable[i].equals(players.get(tableIndex).name)) {
                str.append(players.get(tableIndex).getNetWorth() + ",");
                tableIndex++;
            } else {
                str.append("0,");
            }
        }
        Simulation.filePrint.println(str);
    }
}