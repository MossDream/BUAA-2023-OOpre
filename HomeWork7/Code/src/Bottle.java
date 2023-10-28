public class Bottle implements Commodity {
    private final int id;
    private final String name;

    private int capacity;
    private long price;

    private boolean isEmpty = false;

    public Bottle(int id, String name, int capacity, long price) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getIncreasedHp(int userHp) {
        return this.capacity;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }
}
