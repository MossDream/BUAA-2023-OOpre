public class EpicEquipment extends Equipment {
    private double ratio;

    public EpicEquipment(int id, String name, int star, long price, double ratio) {
        super(id, name, star, price);
        this.ratio = ratio;
    }

    @Override
    public int getLoseHp(int level, int attackedHp) {
        return (int) (attackedHp * ratio);
    }
}
