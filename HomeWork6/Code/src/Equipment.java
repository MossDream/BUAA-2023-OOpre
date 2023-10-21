public class Equipment implements Commodity {
    private final int id;
    private final String name;
    private int star;
    private long price;

    public Equipment(int id, String name, int star, long price) {
        this.id = id;
        this.name = name;
        this.star = star;
        this.price = price;
    }

    @Override
    public long getPrice() {
        return price;
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    public void increaseStar() {
        star++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStar() {
        return star;
    }

    public int getLoseHp(int level, int attackedHp) {
        return this.star * level;
    }
}
