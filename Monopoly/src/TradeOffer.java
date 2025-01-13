import java.util.ArrayList;

public class TradeOffer {
    public ArrayList<Space> playerProperties1;
    public int playerCash1;
    public ArrayList<Space> playerProperties2;
    public int playerCash2;
    public Player player1;
    public Player player2;

    //Player1 is the one that offers the trade and Player2 is the one that recieves Player1 stuff and vise versa
    public TradeOffer(ArrayList<Space> playerProperties1, int playerCash1, ArrayList<Space> playerProperties2, int playerCash2, Player player1, Player player2) {
        this.playerProperties1 = playerProperties1;
        this.playerCash1 = playerCash1;
        this.playerProperties2 = playerProperties2;
        this.playerCash2 = playerCash2;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void trade() {
        playerCash1 += playerCash2;
        playerCash2 -= playerCash1;

        player2.spaces.addAll(playerProperties1);
        player1.spaces.removeAll(playerProperties1);

        player1.spaces.addAll(playerProperties2);
        player2.spaces.removeAll(playerProperties2);
    }
}
