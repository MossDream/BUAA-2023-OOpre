import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Adventure implements Commodity, Employer, Employee {
    private final int id;
    private final String name;
    private int level = 1;
    private int hp = 500;
    private int fightBeforeHp = 500;
    private int maxBots = 1;
    private long price;
    private long money;
    private HashMap<Integer, Bottle> bottles = new HashMap<>();

    private HashMap<String, TreeMap<Integer, Bottle>> takenBottles = new HashMap<>();
    private HashMap<Integer, Equipment> equipments = new HashMap<>();

    private HashMap<String, Equipment> takenEquipments = new HashMap<>();

    private HashMap<Integer, Food> foods = new HashMap<>();

    private HashMap<String, TreeMap<Integer, Food>> takenFoods = new HashMap<>();

    private HashMap<Integer, Commodity> commodities = new HashMap<>();
    private HashMap<Integer, Employee> employees = new HashMap<>();

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
            boolean isEmpty = bottle.getIsEmpty();
            if (!isEmpty) {
                int increasedHp = bottle.getIncreasedHp(this.hp);
                this.hp += increasedHp;
                if (!isFight) {
                    this.fightBeforeHp = this.hp;
                }
                bottle.setIsEmpty(true);
            } else {
                takenBottles.get(bottleName).pollFirstEntry();
                this.price -= bottle.getPrice();
                bottles.remove(bottleId);
                commodities.remove(bottleId);
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
            this.price -= food.getPrice();
            commodities.remove(foodId);
            System.out.println(foodId + " " + this.level);
            if (takenFoods.get(foodName).isEmpty()) {
                takenFoods.remove(foodName);
            }
        } else {
            System.out.println("fail to eat " + foodName);
        }
    }

    public PrintInfo sellBottle(int bottleId) {
        Bottle bottle = bottles.get(bottleId);
        this.price -= bottle.getPrice();
        int capacity = bottle.getCapacity();
        String type = bottle.getClassName();
        Shop.getInstance().record(type, capacity, bottle.getPrice());
        this.money += bottle.getPrice();
        bottles.remove(bottleId); // 根据key删除元素
        String name = bottle.getName();
        if (takenBottles.containsKey(name)) {
            takenBottles.get(name).remove(bottleId);
        }
        commodities.remove(bottleId);
        return new PrintInfo(bottles.size(), name);
    }

    public PrintInfo sellEquipment(int equipmentId) {
        Equipment equipment = equipments.get(equipmentId);
        this.price -= equipment.getPrice();
        int star = equipment.getStar();
        String type = equipment.getClassName();
        Shop.getInstance().record(type, star, equipment.getPrice());
        this.money += equipment.getPrice();
        String name = equipment.getName();
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

    public PrintInfo sellFood(int foodId) {
        Food food = foods.get(foodId);
        this.price -= food.getPrice();
        int energy = food.getEnergy();
        String type = food.getClassName();
        Shop.getInstance().record(type, energy, food.getPrice());
        this.money += food.getPrice();
        String name = food.getName();
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

    public void sellAllItems() {
        long totalGotMoney = 0;
        ArrayList<Integer> ids = new ArrayList<>();
        if (!takenBottles.isEmpty()) {
            for (String name : takenBottles.keySet()) {
                TreeMap<Integer, Bottle> treeMap = takenBottles.get(name);
                if (treeMap.isEmpty()) {
                    continue;
                }
                for (Bottle bottle : treeMap.values()) {
                    ids.add(bottle.getId());
                    totalGotMoney += bottle.getPrice();
                }
            }
        }
        if (!ids.isEmpty()) {
            for (int id : ids) {
                sellBottle(id);
            }
            ids.clear();
        }
        if (!takenEquipments.isEmpty()) {
            for (Equipment equipment : takenEquipments.values()) {
                ids.add(equipment.getId());
                totalGotMoney += equipment.getPrice();
            }
        }
        if (!ids.isEmpty()) {
            for (int id : ids) {
                sellEquipment(id);
            }
            ids.clear();
        }
        if (!takenFoods.isEmpty()) {
            for (String name : takenFoods.keySet()) {
                TreeMap<Integer, Food> treeMap = takenFoods.get(name);
                if (treeMap.isEmpty()) {
                    continue;
                }
                for (Food food : treeMap.values()) {
                    ids.add(food.getId());
                    totalGotMoney += food.getPrice();
                }
            }
        }
        if (!ids.isEmpty()) {
            for (int id : ids) {
                sellFood(id);
            }
            ids.clear();
        }
        System.out.println(name + " emptied the backpack " + totalGotMoney);
    }

    public void buyABottle(int itemId, String itemName, String type, String others) {
        long price = Shop.getInstance().showPrice(type);
        if (this.money >= price) {
            this.money -= price;
            int capacity = Shop.getInstance().showAttribute(type);
            switch (type) {
                case "RegularBottle":
                    Bottle regularBottle = new RegularBottle(itemId, itemName,
                            capacity, price);
                    addBottle(regularBottle);
                    break;
                case "ReinforcedBottle":
                    double ratio1 = Double.parseDouble(others);
                    Bottle reinforcedBottle = new ReinforcedBottle(itemId, itemName,
                            capacity, price, ratio1);
                    addBottle(reinforcedBottle);
                    break;
                case "RecoverBottle":
                    double ratio2 = Double.parseDouble(others);
                    Bottle recoverBottle = new RecoverBottle(itemId, itemName,
                            capacity, price, ratio2);
                    addBottle(recoverBottle);
                    break;
                default:
                    break;
            }
            System.out.println("successfully buy " + itemName + " for " + price);
        } else {
            System.out.println("failed to buy " + itemName + " for " + price);
        }
    }

    public void buyAnEquipment(int itemId, String itemName, String type, String others) {
        long price = Shop.getInstance().showPrice(type);
        if (this.money >= price) {
            this.money -= price;
            int star = Shop.getInstance().showAttribute(type);
            switch (type) {
                case "RegularEquipment":
                    Equipment regularEquip = new RegularEquipment(itemId, itemName, star, price);
                    addEquipment(regularEquip);
                    break;
                case "EpicEquipment":
                    double ratio = Double.parseDouble(others);
                    Equipment epicEquip = new EpicEquipment(itemId, itemName, star, price, ratio);
                    addEquipment(epicEquip);
                    break;
                case "CritEquipment":
                    int critical = Integer.parseInt(others);
                    Equipment critEquip = new CritEquipment(itemId, itemName,
                            star, price, critical);
                    addEquipment(critEquip);
                    break;
                default:
                    break;
            }
            System.out.println("successfully buy " + itemName + " for " + price);
        } else {
            System.out.println("failed to buy " + itemName + " for " + price);
        }
    }

    public void buyAFood(int itemId, String itemName, String type, String others) {
        long price = Shop.getInstance().showPrice(type);
        if (this.money >= price) {
            this.money -= price;
            int energy = Shop.getInstance().showAttribute(type);
            Food food = new Food(itemId, itemName, energy, price);
            addFood(food);
            System.out.println("successfully buy " + itemName + " for " + price);
        } else {
            System.out.println("failed to buy " + itemName + " for " + price);
        }
    }

    public void buyAnItem(int itemId, String itemName, String type, String others) {
        if (type.contains("Bottle")) {
            buyABottle(itemId, itemName, type, others);
        } else if (type.contains("Equipment")) {
            buyAnEquipment(itemId, itemName, type, others);
        } else if (type.contains("Food")) {
            buyAFood(itemId, itemName, type, others);
        }
    }

    @Override
    public long getPrice() {
        long totalPrice = 0;
        for (Commodity commodity : commodities.values()) {
            totalPrice += commodity.getPrice();
        }
        this.price = totalPrice + this.money;
        return this.price;
    }

    @Override
    public String getClassName() {
        return "Adventurer";
    }

    @Override
    public void hire(int employeeId, Employee employee) {
        employees.put(employeeId, employee);
    }

    @Override
    public void notifyEmployees() {
        int half = this.fightBeforeHp / 2;
        int lostHp = this.fightBeforeHp - this.hp;
        if (this.hp <= half) {
            for (Employee employee : employees.values()) {
                long rescueMoney = employee.rescue(lostHp);
                this.money += rescueMoney;
            }
        }
    }

    @Override
    public long rescue(int lostHp) {
        long rescueMoney = (long) lostHp * (long) 10000;
        if (this.money >= rescueMoney) {
            this.money -= rescueMoney;
            return rescueMoney;
        } else {
            long leftMoney = this.money;
            this.money = 0;
            return leftMoney;
        }
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

    public int getHp() { return hp; }

    public void setHp(int hp) { this.hp = hp; }

    public void setFightBeforeHp(int hp) {
        this.fightBeforeHp = hp;
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
