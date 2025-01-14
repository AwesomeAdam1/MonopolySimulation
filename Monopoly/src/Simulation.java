import java.util.*;
import java.io.*;

public class Simulation {
    public static PrintWriter filePrint = null;
    public static int game = 1;

    public static void main(String[] args) {
        double[][] playerWeights = {
                {0, 1, 0.526, 0, 0.263},
                {0.895, 0.842, 0.368, 0.947, 0.105},
                {0.789, 0.789, 0, 0.316, 0.526},
                {0.053, 0.263, 0.105, 0.421, 1},
                {0.421, 0.211, 0.263, 0.895, 0},
                {0.263, 0.632, 0.947, 0.579, 0.632},
                {0.579, 0.737, 0.842, 0.211, 0.474},
                {0.158, 0.368, 0.632, 0.474, 0.842},
                {0.947, 0.158, 0.211, 0.632, 0.053},
                {0.842, 0.316, 0.737, 0.526, 0.211},
                {0.684, 0.105, 0.789, 0.263, 0.895},
                {0.105, 0.474, 0.474, 1, 0.421},
                {0.474, 0.684, 0.053, 0.789, 0.579},
                {1, 0.526, 0.684, 0.368, 0.316},
                {0.211, 0.947, 0.579, 0.053, 0.947},
                {0.632, 0.421, 1, 0.158, 0.368},
                {0.368, 0.895, 0.158, 0.842, 0.737},
                {0.526, 0.579, 0.895, 0.684, 0.789},
                {0.737, 0, 0.316, 0.105, 0.684},
                {0.316, 0.053, 0.421, 0.737, 0.158}
        };

        File fileOut = new File("Data.csv");
        try {
            filePrint = new PrintWriter(fileOut);
            //filePrint.println("test");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < playerWeights.length; i++) {
            for (int j = i + 1; j < playerWeights.length; j++) {
                for (int k = j + 1; k < playerWeights.length; k++) {
                    for (int l = k + 1; l < playerWeights.length; l++) {
                        System.out.println(game);
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
                        game++;
                    }
                }
            }
        }

        if (filePrint != null) {
            filePrint.close();
        }
    }
}
