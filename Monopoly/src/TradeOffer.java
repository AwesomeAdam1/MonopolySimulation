import java.util.ArrayList;

public class TradeOffer {
    public Space playerProperties1;
    public Space playerProperties2;
    public Player player1;
    public Player player2;

    //Player1 is the one that offers the trade and Player2 is the one that recieves Player1 stuff and vise versa


    public TradeOffer(Space playerProperties1, Space playerProperties2, Player player1, Player player2) {
        this.playerProperties1 = playerProperties1;
        this.playerProperties2 = playerProperties2;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void trade() {
        player1.addSpace(playerProperties2);
        player2.addSpace(playerProperties1);
        for (int i = 0; i < player1.spaces.size(); i++) {
            if (player1.spaces.get(i) instanceof Property && playerProperties1 instanceof Property) {
                Property p1 = (Property) player1.spaces.get(i);
                Property p2 = (Property) playerProperties1;
                if (p1.name.equals(p2.name)) {
                    player1.spaces.remove(i);
                    break;
                }
            }
            if (player1.spaces.get(i) instanceof Railroad && playerProperties1 instanceof Railroad) {
                Railroad p1 = (Railroad) player1.spaces.get(i);
                Railroad p2 = (Railroad) playerProperties1;
                if (p1.name.equals(p2.name)) {
                    player1.spaces.remove(i);
                    break;
                }
            }
            if (player1.spaces.get(i) instanceof Utility && playerProperties1 instanceof Utility) {
                Utility p1 = (Utility) player1.spaces.get(i);
                Utility p2 = (Utility) playerProperties1;
                if (p1.name.equals(p2.name)) {
                    player1.spaces.remove(i);
                    break;
                }
            }
        }

        for (int i = 0; i < player2.spaces.size(); i++) {
            if (player2.spaces.get(i) instanceof Property && playerProperties2 instanceof Property) {
                Property p1 = (Property) player2.spaces.get(i);
                Property p2 = (Property) playerProperties2;
                if (p1.name.equals(p2.name)) {
                    player2.spaces.remove(i);
                    break;
                }
            }
            if (player2.spaces.get(i) instanceof Railroad && playerProperties2 instanceof Railroad) {
                Railroad p1 = (Railroad) player2.spaces.get(i);
                Railroad p2 = (Railroad) playerProperties2;
                if (p1.name.equals(p2.name)) {
                    player2.spaces.remove(i);
                    break;
                }
            }
            if (player2.spaces.get(i) instanceof Utility && playerProperties2 instanceof Utility) {
                Utility p1 = (Utility) player2.spaces.get(i);
                Utility p2 = (Utility) playerProperties2;
                if (p1.name.equals(p2.name)) {
                    player2.spaces.remove(i);
                    break;
                }
            }
        }
        //System.out.println("TRADE COMPLETETEETETETEDDDDD !!!!!!!!!");
    }
}
