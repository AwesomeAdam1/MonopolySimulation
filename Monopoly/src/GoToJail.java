public class GoToJail extends Space {
    public void doAction(Player player) {
        player.inJail = true;
        player.jailTimeLeft = 3;
        player.positionIndex = 10;
        System.out.println("Going to Jail!");
    }

    public String toString() {
        return "Go to Jail";
    }
}
