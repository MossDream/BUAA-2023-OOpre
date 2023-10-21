public class Food implements Commodity {
    private final int id;
    private final String name;
    private int energy;
    private long price;

    public Food(int id, String name, int energy, long price) {
        this.id = id;
        this.name = name;
        this.energy = energy;
        this.price = price;
    }

    @Override
    public long getPrice() {
        return this.price;
    }

    @Override
    public String getClassName() {
        return "Food";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }
}
