import java.util.*;
import java.io.*;

public class Simulation {

    public static PrintWriter filePrint = null;
    public static int game = 0;
    public static int divisionPerAttribute = 5;
    public static int playerCount = 50; //(int) Math.pow(divisionPerAttribute, 5);
    public static int[] scores = new int[playerCount];
    public static double[][] lifeExpectancyPerPlayer = new double[playerCount][playerCount];
    public static double [][] winsPerPlayerType = new double[playerCount][playerCount];
    public static double [][] totalGames = new double[playerCount][playerCount];
    // public static double[] avgNetworth50 = new double[playerCount];
    // public static double[] avgNetworth100 = new double[playerCount];
    // public static double[] avgNetworth150 = new double[playerCount];
    // public static double[] avgNetworth200 = new double[playerCount];
    // public static double[] avgNetworth250 = new double[playerCount];
    // public static double[] avgNetworth250more = new double[playerCount];
    // public static int[] totalMoves50 = new int[playerCount];
    // public static int[] totalMoves100 = new int[playerCount];
    // public static int[] totalMoves150 = new int[playerCount];
    // public static int[] totalMoves200 = new int[playerCount];
    // public static int[] totalMoves250 = new int[playerCount];
    // public static int[] totalMoves250more = new int[playerCount];
    public static int lengthsaaa = 0;
    public static int networthIntervals = 15;
    public static int totalIntervals = 10;
    public static int[][] totalMoves = new int[playerCount][totalIntervals]; //[player][moves at interval]
    public static int[][] totalNetworth = new int[playerCount][totalIntervals]; //[player][total networth at interval]

    public static void main(String[] args) {
//        int gamesPerPlayerType = 153;
        int gamesPerPlayerType = 1128 * 100;
        double[][] playerWeights = {
                {0.462, 0.241, 0.586, 0.624, 0.600},
                {0.895, 0.842, 0.368, 0.947, 0.105},
                {0.008, 0.090, 0.874, 0.165, 0.131},
                {0.721, 0.174, 0.728, 0.697, 0.438},
                {0.102, 0.071, 0.322, 0.465, 0.950},
                {0.538, 0.675, 0.124, 0.347, 0.283},
                {0.312, 0.549, 0.253, 0.843, 0.716},
                {0.651, 0.888, 0.495, 0.231, 0.819},
                {0.427, 0.308, 0.642, 0.754, 0.092},
                {0.194, 0.482, 0.769, 0.516, 0.384},
                {0.845, 0.274, 0.399, 0.180, 0.607},
                {0.316, 0.723, 0.053, 0.441, 0.993},
                {0.579, 0.015, 0.314, 0.682, 0.770},
                {0.904, 0.612, 0.827, 0.239, 0.538},
                {0.392, 0.409, 0.678, 0.903, 0.156},
                {0.071, 0.821, 0.143, 0.375, 0.295},
                {0.232, 0.564, 0.481, 0.792, 0.642},
                {0.750, 0.928, 0.296, 0.068, 0.884},
                {0.680, 0.381, 0.751, 0.530, 0.198},
                {0.257, 0.690, 0.585, 0.320, 0.442},
                {0.480, 0.297, 0.711, 0.892, 0.103},
                {0.619, 0.432, 0.875, 0.701, 0.324},
                {0.148, 0.819, 0.369, 0.234, 0.758},
                {0.875, 0.532, 0.643, 0.082, 0.509},
                {0.397, 0.065, 0.147, 0.316, 0.910},
                {0.561, 0.769, 0.492, 0.678, 0.247},
                {0.804, 0.144, 0.231, 0.860, 0.359},
                {0.280, 0.601, 0.938, 0.401, 0.173},
                {0.645, 0.788, 0.083, 0.542, 0.824},
                {0.736, 0.317, 0.814, 0.159, 0.630},
                {0.256, 0.904, 0.458, 0.285, 0.752},
                {0.485, 0.562, 0.215, 0.917, 0.411},
                {0.678, 0.123, 0.792, 0.048, 0.337},
                {0.834, 0.470, 0.659, 0.708, 0.869},
                {0.504, 0.335, 0.370, 0.812, 0.290},
                {0.912, 0.656, 0.748, 0.574, 0.130},
                {0.079, 0.098, 0.953, 0.632, 0.786},
                {0.234, 0.881, 0.528, 0.097, 0.442},
                {0.614, 0.490, 0.295, 0.710, 0.320},
                {0.752, 0.780, 0.406, 0.285, 0.937},
                {0.091, 0.867, 0.801, 0.923, 0.151},
                {0.624, 0.379, 0.973, 0.460, 0.586},
                {0.335, 0.052, 0.745, 0.307, 0.178},
                {0.412, 0.274, 0.569, 0.811, 0.832},
                {0.578, 0.393, 0.125, 0.649, 0.246},
                {0.804, 0.128, 0.657, 0.072, 0.491},
                {0.290, 0.750, 0.348, 0.985, 0.612},
                {0.675, 0.839, 0.512, 0.448, 0.739},
                {0.082, 0.593, 0.109, 0.264, 0.194},
                {0.728, 0.481, 0.890, 0.740, 0.303}
        };

//        double[][] playerWeights = {
//                {0, 1, 0.526, 0, 0.263},
//                {0.895, 0.842, 0.368, 0.947, 0.105},
//                {0.789, 0.789, 0, 0.316, 0.526},
//                {0.053, 0.263, 0.105, 0.421, 1},
//                {0.421, 0.211, 0.263, 0.895, 0},
//                {0.263, 0.632, 0.947, 0.579, 0.632},
//                {0.579, 0.737, 0.842, 0.211, 0.474},
//                {0.158, 0.368, 0.632, 0.474, 0.842},
//                {0.947, 0.158, 0.211, 0.632, 0.053},
//                {0.842, 0.316, 0.737, 0.526, 0.211},
//                {0.684, 0.105, 0.789, 0.263, 0.895},
//                {0.105, 0.474, 0.474, 1, 0.421},
//                {0.474, 0.684, 0.053, 0.789, 0.579},
//                {1, 0.526, 0.684, 0.368, 0.316},
//                {0.211, 0.947, 0.579, 0.053, 0.947},
//                {0.632, 0.421, 1, 0.158, 0.368},
//                {0.368, 0.895, 0.158, 0.842, 0.737},
//                {0.526, 0.579, 0.895, 0.684, 0.789},
//                {0.737, 0, 0.316, 0.105, 0.684},
//                {0.316, 0.053, 0.421, 0.737, 0.158}
//        };

//        double[][] playerWeights = new double[playerCount][5];

//        int count = 0;
//        for(int i = 0; i < divisionPerAttribute; i++) {
//            for (int j = 0; j < divisionPerAttribute; j++) {
//                for (int k = 0; k < divisionPerAttribute; k++) {
//                    for (int l = 0; l < divisionPerAttribute; l++) {
//                        for (int m = 0; m < divisionPerAttribute; m++) {
//                            //0,0.25,0.5,0.75,1
//                            playerWeights[count] = new double[]{i * 0.25, j * 0.25, k * 0.25, l * 0.25, m * 0.25};
//                            count++;
//                        }
//                    }
//                }
//            }
//        }

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
////
        for (int t = 1; t <= 100; t++) {
            System.out.println(t);
            for (int i = 0; i < playerWeights.length; i++) {
                for (int j = i + 1; j < playerWeights.length; j++) {
                    for (int k = j + 1; k < playerWeights.length; k++) {
                        for (int l = k + 1; l < playerWeights.length; l++) {
                            if (game % 5000 == 0) {
                                System.out.println(game);
                            }
                            game++;
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
        }

//        if (filePrint != null) {
//            filePrint.close();
//        }
        // System.out.println(Arrays.toString(scores));
        // System.out.println(Arrays.toString(avgNetworth50));
        // System.out.println(Arrays.toString(avgNetworth100));
        // System.out.println(Arrays.toString(avgNetworth150));
        // System.out.println(Arrays.toString(avgNetworth200));
        // System.out.println(Arrays.toString(avgNetworth250));
        // System.out.println(Arrays.toString(avgNetworth250more));
//        for (int i = 0; i < playerCount; i++) {
//            System.out.println(Arrays.toString(winsPerPlayerType[i]));
//        }
//        for (int i = 0; i < playerCount; i++) {
//            System.out.println(Arrays.toString(lifeExpectancyPerPlayer[i]));
//        }
        for (int i = 0; i < playerCount; i++) {
            for (int j = 0; j < playerCount; j++) {
                winsPerPlayerType[i][j] /= gamesPerPlayerType;
                lifeExpectancyPerPlayer[i][j] /= gamesPerPlayerType;
                totalNetworth[i][j] /= totalMoves[i][j];
            }
        }
        System.out.println("Average Networth");
        for (int i = 0; i < playerCount; i++) {
            System.out.println(Arrays.toString(totalNetworth[i]));
        }
        System.out.println("Win Percentage");
        for (int i = 0; i < playerCount; i++) {
            System.out.println(Arrays.toString(winsPerPlayerType[i]));
        }
        System.out.println("Average Life Expectancy");
        for (int i = 0; i < playerCount; i++) {
            System.out.println(Arrays.toString(lifeExpectancyPerPlayer[i]));
        }
//        System.out.println(lengthsaaa);
//        for (int i = 0; i < totalGames.length; i++) {
//            System.out.println(Arrays.toString(totalGames[i]));
//        }
//        System.out.println(Arrays.toString(totalGames[0]));
//        System.out.println(totalGames[0].length);
    }
}
