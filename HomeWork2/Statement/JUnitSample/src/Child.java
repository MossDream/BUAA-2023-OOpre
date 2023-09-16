public class Child {
    private int money;
    private int appleCount;
    private int bananaCount;

    public Child(int money) {
        this.money = money;
        this.appleCount = 0;
        this.bananaCount = 0;
    }

    public void subMoney(int count) {
        money -= count;
    }

    public void addOneFruit(String goal) {
        if (goal.equals("apple")) {
            appleCount++;
        } else if (goal.equals("banana")) {
            bananaCount++;
        }
    }

    public void eat(String goal) {
        if (goal.equals("apple") && appleCount > 0) {
            System.out.println("eat " + goal + " ok!");
            appleCount--;
        } else if (goal.equals("banana") && bananaCount > 0) {
            System.out.println("eat " + goal + " ok!");
            bananaCount--;
        } else {
            System.out.println("eat " + goal + " failed!");
        }
    }

    public void buyFromStore(String goal, Store store) {
        if (goal.equals("apple") && money >= 3) {
            store.trySellOut(this, "apple");
        } else if (goal.equals("banana") && money >= 2) {
            store.trySellOut(this, "banana");
        } else {
            System.out.println("buy " + goal + " failed!");
        }
    }
    //我们需要一些保证正确的简单内容获取方法，这是测试其内容的基础

    public int getMoney() {
        return money;
    }

    public int getAppleCount() {
        return appleCount;
    }

    public int getBananaCount() {
        return bananaCount;
    }
}

