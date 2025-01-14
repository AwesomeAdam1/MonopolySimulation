public class Tax extends Space {
    public boolean isIncomeTax;
    public String name;

    public Tax(String name, boolean isIncomeTax) {
        this.name = name;
        this.isIncomeTax = isIncomeTax;
    }

    public void doAction(Player player) {
        if (isIncomeTax) {
            //Income Tax Space
            //System.out.printf("Paid $%d in %s\n", (int) Math.min(200, player.money * 0.1), name);
            player.money -= Math.min(200, player.money * 0.1);
        } else {
            //Luxury Tax Space
            //System.out.println("Paid $75 in Luxury Tax");
            player.money -= 75;
        }
    }

    public String toString() {
        return name;
    }
}
