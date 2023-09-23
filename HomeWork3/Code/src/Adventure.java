import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Adventure {
    private final int id;
    private final String name;
    private int level = 1;
    private int hp = 500;

    private int maxBots = 1;
    private HashMap<Integer, Bottle> bottles = new HashMap<>();

    private HashMap<String, TreeMap<Integer, Bottle>> takenBottles = new HashMap<>();
    private HashMap<Integer, Equipment> equipments = new HashMap<>();

    private HashMap<String, Equipment> takenEquipments = new HashMap<>();

    private HashMap<Integer, Food> foods = new HashMap<>();

    private HashMap<String, TreeMap<Integer, Food>> takenFoods = new HashMap<>();

    public Adventure(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addBottle(Bottle bottle) {
        bottles.put(bottle.getId(), bottle);
    }

    public void addEquipment(Equipment equipment) {
        equipments.put(equipment.getId(), equipment);
    }

    public void addFood(Food food) {
        foods.put(food.getId(), food);
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

    public void useBottle(String bottleName) {
        if (takenBottles.containsKey((bottleName))) {
            Map.Entry<Integer, Bottle> entry = takenBottles.get(bottleName).firstEntry();
            int bottleId = entry.getKey();
            Bottle bottle = entry.getValue();
            if (bottle.getCapacity() > 0) {
                this.hp += bottle.getCapacity();
                bottle.setCapacity(0);
            } else {
                takenBottles.get(bottleName).pollFirstEntry();
                bottles.remove(bottleId);
            }
            System.out.println(bottleId + " " + this.hp);
            if (takenBottles.get(bottleName).isEmpty()) {
                takenBottles.remove(bottleName);
            }

        } else {
            System.out.println("fail to use " + bottleName);
        }
    }

    public void eatFood(String foodName) {
        if (takenFoods.containsKey(foodName)) {
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
        bottles.remove(bottleId); // 根据key删除元素
        return new PrintInfo(bottles.size(), name);
    }

    public PrintInfo removeEquipment(int equipmentId) {
        String name = equipments.get(equipmentId).getName();
        equipments.remove(equipmentId);
        return new PrintInfo(equipments.size(), name);
    }

    public PrintInfo removeFood(int foodId) {
        String name = foods.get(foodId).getName();
        foods.remove(foodId);
        return new PrintInfo(foods.size(), name);
    }

    public PrintInfo increaseStar(int equipmentId) {
        Equipment equipment = equipments.get(equipmentId);
        equipment.increaseStar();
        return new PrintInfo(equipment.getStar(), equipment.getName());
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
