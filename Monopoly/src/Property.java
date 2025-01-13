import java.util.ArrayList;

public class Property extends Space {
    public int houses = 0;
    public int houseCost;
    public int[] rent;
    public Player owner;
    public String color;
    public String name;
    public boolean isMortgaged = false;
    public int price;
    public int mortgageValue;
    public boolean owned = false;

    public Property(String name, String color, int price, int houseCost, int[] rent, int mortgageValue) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.houseCost = houseCost;
        this.rent = rent;
        this.mortgageValue = mortgageValue;
    }

    public void doAction(Player player) {
        if (owner != null && owner.equals(player)) {
            System.out.println("Player owned!");
            return;
        }

        if (owned) {
            //Pay player
            int payAmount = player.payAmount(rent[houses]);
            owner.money += payAmount;
            System.out.printf("%s has been paid $%d by %s\n", owner.name, payAmount, player.name);
            System.out.printf("%s now has $%d\n", owner.name, owner.money);
            System.out.printf("%s now has $%d\n", player.name, player.money);
        } else {
            if (player.offerToBuySpace(this)) {
                //Buys property
                owner = player;
                owned = true;
                player.addSpace(this);
                System.out.printf("%s bought %s\n", player.name, name);
                System.out.println("Property purchased");
            } else {
                //Rejects property -> put for auction
                System.out.println("Player rejected direct purchase!");
                ArrayList<Player> playersInAuction = (ArrayList<Player>) Main.players.clone();
                playersInAuction.remove(player);

                int biddingPrice = price;
                int index = 0;
                while (playersInAuction.size() >= 2) {
                    int bid = playersInAuction.get(index).bid(biddingPrice, this);
                    if (bid == 0) {
                        //Does not want to bid
                        System.out.printf("%s dropped out of bidding.\n", playersInAuction.get(index).name);
                        playersInAuction.remove(index);
                        index %= playersInAuction.size();
                        continue;
                    } else {
                        //Overbids
                        biddingPrice = bid;
                        System.out.printf("%s bid %d\n", playersInAuction.get(index).name, biddingPrice);
                    }
                    index = (index + 1) % playersInAuction.size();
                }

                //Handle where no one bids
                if (biddingPrice == price) {
                    //No willing bid occurs
                    int bid = playersInAuction.get(0).bid(biddingPrice, this);
                    if (bid == 0) {
                        //No one bids
                        System.out.printf("No one bid on %s", name);
                    } else {
                        System.out.printf("%s won %s for %d\n", playersInAuction.get(0).name, name, biddingPrice);
                        Player winner = playersInAuction.get(0);
                        winner.money -= biddingPrice;
                        owner = winner;
                        owned = true;
                        winner.addSpace(this);
                    }
                } else {
                    //Has a winner where someone willingly bids
                    System.out.printf("%s won %s for %d\n", playersInAuction.get(0).name, name, biddingPrice);
                    Player winner = playersInAuction.get(0);
                    winner.money -= biddingPrice;
                    owner = winner;
                    owned = true;
                    winner.addSpace(this);
                }
            }
        }
    }

    public String toString() {
        return name;
    }
}
