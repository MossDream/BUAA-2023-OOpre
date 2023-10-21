public class RecoverBottle extends Bottle {
    private double ratio;

    public RecoverBottle(int id, String name, int capacity, long price, double ratio) {
        super(id, name, capacity, price);
        this.ratio = ratio;
    }

    @Override
    public int getIncreasedHp(int userHp) {
        return (int) (userHp * ratio);
    }
}
