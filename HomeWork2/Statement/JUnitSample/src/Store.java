public class Store {
    private int appleCount; // 3 元 1 个
    private int bananaCount; // 2 元 1 个

    public Store(int appleCount, int bananaCount) {
        this.appleCount = appleCount;
        this.bananaCount = bananaCount;
    }

    public int getAppleCount() {
        return appleCount;
    }

    public int getBananaCount()
    {
        return bananaCount;
    }

    public void trySellOut(Child child, String goal) {
        if (goal.equals("apple") && appleCount > 0) {
            appleCount--;
            child.addOneFruit(goal);
            child.subMoney(3);
            System.out.println("buy " + goal + " ok!");
        } else if (goal.equals("banana") && bananaCount > 0) {
            bananaCount--;
            child.addOneFruit(goal);
            child.subMoney(2);
            System.out.println("buy " + goal + " ok!");
        } else {
            System.out.println("buy " + goal + " failed!");
        }
    }
}
