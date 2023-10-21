import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Adventure implements Commodity {
    private final int id;
    private final String name;
    private int level = 1;
    private int hp = 500;
    private int maxBots = 1;
    private long price;
    private HashMap<Integer, Bottle> bottles = new HashMap<>();

    private HashMap<String, TreeMap<Integer, Bottle>> takenBottles = new HashMap<>();
    private HashMap<Integer, Equipment> equipments = new HashMap<>();

    private HashMap<String, Equipment> takenEquipments = new HashMap<>();

    private HashMap<Integer, Food> foods = new HashMap<>();

    private HashMap<String, TreeMap<Integer, Food>> takenFoods = new HashMap<>();

    private HashMap<Integer, Commodity> commodities = new HashMap<>();

    public Adventure(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addBottle(Bottle bottle) {
        bottles.put(bottle.getId(), bottle);
        commodities.put(bottle.getId(), bottle);
        this.price += bottle.getPrice();
    }

    public void addEquipment(Equipment equipment) {
        equipments.put(equipment.getId(), equipment);
        commodities.put(equipment.getId(), equipment);
        this.price += equipment.getPrice();
    }

    public void addFood(Food food) {
        foods.put(food.getId(), food);
        commodities.put(food.getId(), food);
        this.price += food.getPrice();
    }

    public void takeBottle(int bottleId) {
        Bottle bottle = bottles.get(bottleId);
        if (takenBottles.containsKey(bottle.getName())) {
            if (takenBottles.get(bottle.getName()).size() < maxBots) {
                takenBottles.get(bottle.getName()).put(bottleId, bottle);
            }
        } else {
            TreeMap<Integer, Bottle> treeMap = new TreeMap<>();
            treeMap.put(bottleId, bottle);
            takenBottles.put(bottle.getName(), treeMap);
        }
    }

    public void takeEquipment(int equipmentId) {
        Equipment equipment = equipments.get(equipmentId);
        takenEquipments.put(equipment.getName(), equipment);
    }

    public void takeFood(int foodId) {
        Food food = foods.get(foodId);
        if (takenFoods.containsKey(food.getName())) {
            takenFoods.get(food.getName()).put(foodId, food);
        } else {
            TreeMap<Integer, Food> treeMap = new TreeMap<>();
            treeMap.put(foodId, food);
            takenFoods.put(food.getName(), treeMap);
        }
    }

    public boolean useBottle(boolean isFight, String bottleName) {
        if (takenBottles.containsKey(bottleName) && !takenBottles.get(bottleName).isEmpty()) {
            Map.Entry<Integer, Bottle> entry = takenBottles.get(bottleName).firstEntry();
            int bottleId = entry.getKey();
            Bottle bottle = entry.getValue();
            if (bottle.getCapacity() > 0) {
                int increasedHp = bottle.getIncreasedHp(this.hp);
                this.hp += increasedHp;
                bottle.setCapacity(0);
            } else {
                takenBottles.get(bottleName).pollFirstEntry();
                bottles.remove(bottleId);
            }
            System.out.println(bottleId + " " + this.hp);
            if (takenBottles.get(bottleName).isEmpty()) {
                takenBottles.remove(bottleName);
            }
            return true;
        } else {
            if (isFight) {
                System.out.println("Fight log error");
            } else {
                System.out.println("fail to use " + bottleName);
            }
            return false;
        }
    }

    public void eatFood(String foodName) {
        if (takenFoods.containsKey(foodName) && !takenFoods.get(foodName).isEmpty()) {
            Map.Entry<Integer, Food> entry = takenFoods.get(foodName).pollFirstEntry();
            int foodId = entry.getKey();
            Food food = entry.getValue();
            this.level += food.getEnergy();
            this.maxBots = this.level / 5 + 1;
            foods.remove(foodId);
            System.out.println(foodId + " " + this.level);
            if (takenFoods.get(foodName).isEmpty()) {
                takenFoods.remove(foodName);
            }
        } else {
            System.out.println("fail to eat " + foodName);
        }
    }

    public PrintInfo removeBottle(int bottleId) {
        String name = bottles.get(bottleId).getName();
        this.price -= bottles.get(bottleId).getPrice();
        bottles.remove(bottleId); // 根据key删除元素
        if (takenBottles.containsKey(name)) {
            takenBottles.get(name).remove(bottleId);
        }
        commodities.remove(bottleId);
        return new PrintInfo(bottles.size(), name);
    }

    public PrintInfo removeEquipment(int equipmentId) {
        this.price -= equipments.get(equipmentId).getPrice();
        String name = equipments.get(equipmentId).getName();
        equipments.remove(equipmentId);
        commodities.remove(equipmentId);
        if (takenEquipments.containsKey(name)) {
            int id = takenEquipments.get(name).getId();
            if (id == equipmentId) {
                takenEquipments.remove(name);
            }
        }
        return new PrintInfo(equipments.size(), name);
    }

    public PrintInfo removeFood(int foodId) {
        this.price -= foods.get(foodId).getPrice();
        String name = foods.get(foodId).getName();
        commodities.remove(foodId);
        foods.remove(foodId);
        if (takenFoods.containsKey(name)) {
            takenFoods.get(name).remove(foodId);
        }
        return new PrintInfo(foods.size(), name);
    }

    public PrintInfo increaseStar(int equipmentId) {
        Equipment equipment = equipments.get(equipmentId);
        equipment.increaseStar();
        return new PrintInfo(equipment.getStar(), equipment.getName());
    }

    public boolean attackAoe(ArrayList<String> fight,
                             HashMap<Integer, Adventure> advs,
                             HashMap<String, Integer> advNameToId,
                             String equName) {
        if (fight.contains(name) && takenEquipments.containsKey(equName)) {
            Equipment equipment = takenEquipments.get(equName);
            for (String name : fight) {
                if (!name.equals(this.name)) {
                    int attackedId = advNameToId.get(name);
                    Adventure attacked = advs.get(attackedId);
                    int loseHp = equipment.getLoseHp(level, attacked.getHp());
                    attacked.setHp(attacked.getHp() - loseHp);
                    System.out.print(attacked.getHp() + " ");
                }
            }
            System.out.println();
            return true;
        } else {
            System.out.println("Fight log error");
            return false;
        }
    }

    public boolean attack(ArrayList<String> fight,
                          HashMap<Integer, Adventure> advs,
                          HashMap<String, Integer> advNameToId,
                          String attackedName,
                          String equName) {
        if (fight.contains(name) && fight.contains(attackedName) &&
                takenEquipments.containsKey(equName)) {
            Equipment equipment = takenEquipments.get(equName);
            int attackedId = advNameToId.get(attackedName);
            Adventure attacked = advs.get(attackedId);
            int loseHp = equipment.getLoseHp(level, attacked.getHp());
            attacked.setHp(attacked.getHp() - loseHp);
            System.out.println(attacked.getId() + " " + attacked.getHp());
            return true;
        } else {
            System.out.println("Fight log error");
            return false;
        }
    }

    public void hireAdventure(int adventureId, Adventure adventure) {
        commodities.put(adventureId, adventure);
        this.price += adventure.getPrice();
    }

    public void searchAllCommodities() {
        int totalNum = commodities.size();
        long totalPrice = 0;
        for (Commodity commodity : commodities.values()) {
            totalPrice += commodity.getPrice();
        }
        System.out.println(totalNum + " " + totalPrice);
    }

    public void searchMaxPrice() {
        if (commodities.isEmpty()) {
            System.out.println("0");
        } else {
            long maxPrice = 0;
            for (Commodity commodity : commodities.values()) {
                if (commodity.getPrice() > maxPrice) {
                    maxPrice = commodity.getPrice();
                }
            }
            System.out.println(maxPrice);
        }

    }

    public void searchCommodityClass(int commodityId) {
        String name = commodities.get(commodityId).getClassName();
        System.out.println("Commodity whose id is " +
                commodityId + " belongs to " + name);
    }

    @Override
    public long getPrice() {
        return this.price;
    }

    @Override
    public String getClassName() {
        return "Adventurer";
    }

    public HashMap<Integer, Bottle> getBottles() {
        return bottles;
    }

    public HashMap<Integer, Equipment> getEquipments() {
        return equipments;
    }

    public HashMap<String, TreeMap<Integer, Bottle>> getTakenBottles() {
        return takenBottles;
    }

    public HashMap<String, Equipment> getTakenEquipments() {
        return takenEquipments;
    }

    public HashMap<Integer, Food> getFoods() {
        return foods;
    }

    public HashMap<String, TreeMap<Integer, Food>> getTakenFoods() {
        return takenFoods;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxBots() {
        return maxBots;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
