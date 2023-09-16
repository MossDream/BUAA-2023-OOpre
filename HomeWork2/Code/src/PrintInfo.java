public class PrintInfo {
    private final int numOrStar;
    private final String name;

    public PrintInfo(int numOrStar, String name) {
        this.numOrStar = numOrStar;
        this.name = name;
    }

    public void printIntFirst() {
        System.out.println(numOrStar + " " + name);
    }

    public void printStringFirst() {
        System.out.println(name + " " + numOrStar);
    }
}
