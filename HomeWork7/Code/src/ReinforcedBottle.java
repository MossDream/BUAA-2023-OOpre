public class ReinforcedBottle extends Bottle {
    private double ratio;

    public ReinforcedBottle(int id, String name, int capacity, long price, double ratio) {
        super(id, name, capacity, price);
        this.ratio = ratio;
    }

    @Override
    public int getIncreasedHp(int userHp) {
        return (int) (this.getCapacity() * (1 + ratio));
    }
}
