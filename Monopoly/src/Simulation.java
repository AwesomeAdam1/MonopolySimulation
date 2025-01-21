import java.util.*;
import java.io.*;

public class Simulation {

    public static PrintWriter filePrint = null;
    public static int game = 0;
    public static int divisionPerAttribute = 5;
    public static int playerCount = (int) Math.pow(divisionPerAttribute, 5);
    public static int[] scores = new int[playerCount];
    public static double[] avgNetworth50 = new double[playerCount];
    public static double[] avgNetworth100 = new double[playerCount];
    public static double[] avgNetworth150 = new double[playerCount];
    public static double[] avgNetworth200 = new double[playerCount];
    public static double[] avgNetworth250 = new double[playerCount];
    public static double[] avgNetworth250more = new double[playerCount];
    public static int[] totalMoves50 = new int[playerCount];
    public static int[] totalMoves100 = new int[playerCount];
    public static int[] totalMoves150 = new int[playerCount];
    public static int[] totalMoves200 = new int[playerCount];
    public static int[] totalMoves250 = new int[playerCount];
    public static int[] totalMoves250more = new int[playerCount];


    public static void main(String[] args) {
        double[][] playerWeights = new double[playerCount][5];
////
        int count = 0;
        for(int i = 0; i < divisionPerAttribute; i++) {
            for (int j = 0; j < divisionPerAttribute; j++) {
                for (int k = 0; k < divisionPerAttribute; k++) {
                    for (int l = 0; l < divisionPerAttribute; l++) {
                        for (int m = 0; m < divisionPerAttribute; m++) {
                            //0,0.25,0.5,0.75,1
                            playerWeights[count] = new double[]{i * 0.25, j * 0.25, k * 0.25, l * 0.25, m * 0.25};
                            count++;
                        }
                    }
                }
            }
        }

//        for (int i = 0; i < playerWeights.length; i++) {
//            System.out.println( Arrays.toString(playerWeights[i]));
//        }
//        System.out.println(playerWeights.length);
//
//        File fileOut = new File("Data.csv");
//        try {
//            filePrint = new PrintWriter(fileOut);
//            filePrint.println("I,P0,P1,P2,P3");
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//        boolean runSimulation = true;
//        boolean runOptimization = false;
//        int testSample = 100;
//        int iterations = 1000;
//        double bound = 0.1;
//        Random random = new Random();
//        if (runSimulation) {
//            for (int t = 0; t < 100; t++) {
//                game++;
//                System.out.println(game);
//                for (int i = 0; i < playerWeights.length; i++) {
//                    for (int j = i + 1; j < playerWeights.length; j++) {
//                        for (int k = j + 1; k < playerWeights.length; k++) {
//                            for (int l = k + 1; l < playerWeights.length; l++) {
//                                //System.out.println(game);
//                                ArrayList<Player> players = new ArrayList<>();
//                                players.add(new Player(
//                                        "Player" + i,
//                                        playerWeights[i][0],
//                                        playerWeights[i][1],
//                                        playerWeights[i][2],
//                                        playerWeights[i][3],
//                                        playerWeights[i][4]));
//                                players.add(new Player(
//                                        "Player" + j,
//                                        playerWeights[j][0],
//                                        playerWeights[j][1],
//                                        playerWeights[j][2],
//                                        playerWeights[j][3],
//                                        playerWeights[j][4]));
//                                players.add(new Player(
//                                        "Player" + k,
//                                        playerWeights[k][0],
//                                        playerWeights[k][1],
//                                        playerWeights[k][2],
//                                        playerWeights[k][3],
//                                        playerWeights[k][4]));
//                                players.add(new Player(
//                                        "Player" + l,
//                                        playerWeights[l][0],
//                                        playerWeights[l][1],
//                                        playerWeights[l][2],
//                                        playerWeights[l][3],
//                                        playerWeights[l][4]));
//
//                                new Main(players);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//
//        if (runOptimization) {
//            for (int ii = 0; ii < iterations; ii++) {
//                if (ii % 100 == 0)
//                    System.out.println(ii);
//                double[][] ideals = new double[4][5];
//                for (int t = 0; t < testSample; t++) {
//                    ArrayList<Player> players = new ArrayList<>();
//                    players.add(new Player("Player0",
//                            currentIdeal[0],
//                            currentIdeal[1],
//                            currentIdeal[2],
//                            currentIdeal[3],
//                            currentIdeal[4]));
//                    for (int i = 1; i <= 3; i++) {
//                        players.add(new Player("Player" + i,
//                                clamp(currentIdeal[0] + random.nextDouble(-bound, bound)),
//                                clamp(currentIdeal[1] + random.nextDouble(-bound, bound)),
//                                clamp(currentIdeal[2] + random.nextDouble(-bound, bound)),
//                                clamp(currentIdeal[3] + random.nextDouble(-bound, bound)),
//                                clamp(currentIdeal[4] + random.nextDouble(-bound, bound))));
//                    }
//                    for (int i = 0; i < 4; i++) {
//                        ideals[i] = players.get(i).getIdeals();
//                    }
//                    new Main(players);
//                }
//                //System.out.println(Arrays.toString(scores));
//
//                int max = -1;
//                int index = -1;
//                for (int i = 0; i < 4; i++) {
//                    if (scores[i] > max) {
//                        max = scores[i];
//                        index = i;
//                    }
//                }
//                currentIdeal = new double[]{
//                        (ideals[index][0] + currentIdeal[0])/2,
//                        (ideals[index][1] + currentIdeal[1])/2,
//                        (ideals[index][2] + currentIdeal[2])/2,
//                        (ideals[index][3] + currentIdeal[3])/2,
//                        (ideals[index][4] + currentIdeal[4])/2};
//                //System.out.println(Arrays.toString(currentIdeal));
//                Arrays.fill(scores,0);
//            }
//        }
//
//        //System.out.println(Arrays.toString(currentIdeal));
//
//

        for (int t = 1; t <= 100; t++) {
            System.out.println(game);
            for (int i = 0; i < playerWeights.length; i++) {
                for (int j = i + 1; j < playerWeights.length; j++) {
                    for (int k = j + 1; k < playerWeights.length; k++) {
                        for (int l = k + 1; l < playerWeights.length; l++) {
                            ArrayList<Player> players = new ArrayList<>();
                            players.add(new Player(
                                    "Player" + i,
                                    playerWeights[i][0],
                                    playerWeights[i][1],
                                    playerWeights[i][2],
                                    playerWeights[i][3],
                                    playerWeights[i][4]));
                            players.add(new Player(
                                    "Player" + j,
                                    playerWeights[j][0],
                                    playerWeights[j][1],
                                    playerWeights[j][2],
                                    playerWeights[j][3],
                                    playerWeights[j][4]));
                            players.add(new Player(
                                    "Player" + k,
                                    playerWeights[k][0],
                                    playerWeights[k][1],
                                    playerWeights[k][2],
                                    playerWeights[k][3],
                                    playerWeights[k][4]));
                            players.add(new Player(
                                    "Player" + l,
                                    playerWeights[l][0],
                                    playerWeights[l][1],
                                    playerWeights[l][2],
                                    playerWeights[l][3],
                                    playerWeights[l][4]));

                            new Main(players);
                        }
                    }
                }
            }
            game++;
        }

//        if (filePrint != null) {
//            filePrint.close();
//        }

        System.out.println(Arrays.toString(scores));
        System.out.println(Arrays.toString(avgNetworth50));
        System.out.println(Arrays.toString(avgNetworth100));
        System.out.println(Arrays.toString(avgNetworth150));
        System.out.println(Arrays.toString(avgNetworth200));
        System.out.println(Arrays.toString(avgNetworth250));
        System.out.println(Arrays.toString(avgNetworth250more));
    }

    public static double clamp(double input) {
        return Math.max(0, Math.min(1, input));
    }
}
