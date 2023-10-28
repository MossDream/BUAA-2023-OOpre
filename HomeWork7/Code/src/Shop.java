public class Shop {
    private static Shop shop = new Shop();

    private Shop() {
    }

    private int totalCapacity;
    private long totalBotPrice;
    private int totalBotNum;
    private int totalStar;
    private long totalEquipPrice;
    private int totalEquipNum;
    private int totalEnergy;
    private long totalFoodPrice;
    private int totalFoodNum;

    public static Shop getInstance() {
        return shop;
    }

    public void record(String goodsType, int attribute, long price) {
        if (goodsType.contains("Bottle")) {
            totalBotNum++;
            totalBotPrice += price;
            totalCapacity += attribute;
        } else if (goodsType.contains("Equipment")) {
            totalEquipNum++;
            totalEquipPrice += price;
            totalStar += attribute;
        } else if (goodsType.contains("Food")) {
            totalFoodNum++;
            totalFoodPrice += price;
            totalEnergy += attribute;
        }
    }

    public long showPrice(String goodsType) {
        if (goodsType.contains("Bottle")) {
            return totalBotPrice / totalBotNum;
        } else if (goodsType.contains("Equipment")) {
            return totalEquipPrice / totalEquipNum;
        } else if (goodsType.contains("Food")) {
            return totalFoodPrice / totalFoodNum;
        }
        return 0;
    }

    public int showAttribute(String goodsType) {
        if (goodsType.contains("Bottle")) {
            return totalCapacity / totalBotNum;
        } else if (goodsType.contains("Equipment")) {
            return totalStar / totalEquipNum;
        } else if (goodsType.contains("Food")) {
            return totalEnergy / totalFoodNum;
        }
        return 0;
    }
}
