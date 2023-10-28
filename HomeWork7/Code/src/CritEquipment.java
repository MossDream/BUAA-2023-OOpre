public class CritEquipment extends Equipment {
    private int critical;

    public CritEquipment(int id, String name, int star, long price, int critical) {
        super(id, name, star, price);
        this.critical = critical;
    }

    @Override
    public int getLoseHp(int level, int attackedHp) {
        return this.getStar() * level + this.critical;
    }
}
