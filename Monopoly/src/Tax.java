public class Tax extends Space {
    public boolean isIncomeTax;
    public String name;

    public Tax(String name, boolean isIncomeTax) {
        this.name = name;
        this.isIncomeTax = isIncomeTax;
    }

    public void doAction(Player player) {

    }
}
