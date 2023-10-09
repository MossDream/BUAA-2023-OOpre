public class Equipment {
    private final int id;
    private final String name;
    private int star;

    public Equipment(int id, String name, int star) {
        this.id = id;
        this.name = name;
        this.star = star;
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
}
