import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class AdventureTest {
    private Adventure adventure;

    @Before
    public void setUp() throws Exception {
        adventure = new Adventure(1, "adventure");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Test finished.");
    }

    @Test
    public void addBottle() {
        Bottle bottle = new Bottle(2, "bottle", 100, 100);
        adventure.addBottle(bottle);
        assertEquals(1, adventure.getBottles().size());
        assertEquals(2, adventure.getBottles().get(2).getId());
        assertEquals("bottle", adventure.getBottles().get(2).getName());
        assertEquals(100, adventure.getBottles().get(2).getCapacity());
    }

    @Test
    public void addEquipment() {
        Equipment equipment = new Equipment(3, "equipment", 1, 100);
        adventure.addEquipment(equipment);
        assertEquals(1, adventure.getEquipments().size());
        assertEquals(3, adventure.getEquipments().get(3).getId());
        assertEquals("equipment", adventure.getEquipments().get(3).getName());
        assertEquals(1, adventure.getEquipments().get(3).getStar());

    }

    @Test
    public void addFood() {
        Food food = new Food(4, "food", 100, 100);
        adventure.addFood(food);
        assertEquals(1, adventure.getFoods().size());
        assertEquals(4, adventure.getFoods().get(4).getId());
        assertEquals("food", adventure.getFoods().get(4).getName());
        assertEquals(100, adventure.getFoods().get(4).getEnergy());
    }

    @Test
    public void sellBottle() {
        adventure.addBottle(new Bottle(2, "bottle", 100, 100));
        PrintInfo printInfo = adventure.sellBottle(2);
        assertEquals(0, adventure.getBottles().size());
        printInfo.printIntFirst();
        printInfo.printStringFirst();
    }

    @Test
    public void sellEquipment() {
        adventure.addEquipment(new Equipment(3, "equipment", 1, 100));
        PrintInfo printInfo = adventure.sellEquipment(3);
        assertEquals(0, adventure.getEquipments().size());
        printInfo.printIntFirst();
        printInfo.printStringFirst();
    }

    @Test
    public void sellFood() {
        adventure.addFood(new Food(4, "food", 100, 100));
        PrintInfo printInfo = adventure.sellFood(4);
        assertEquals(0, adventure.getFoods().size());
        printInfo.printIntFirst();
        printInfo.printStringFirst();
    }

    @Test
    public void increaseStar() {
        adventure.addEquipment(new Equipment(3, "equipment", 1, 100));
        PrintInfo printInfo = adventure.increaseStar(3);
        assertEquals(2, adventure.getEquipments().get(3).getStar());
        printInfo.printIntFirst();
        printInfo.printStringFirst();
    }

    @Test
    public void getBottles() {
        // 上面的方法已经测试过
        System.out.println("Tested.");
    }

    @Test
    public void getEquipments() {
        // 上面的方法已经测试过
        System.out.println("Tested.");
    }

    public void getFoods() {
        // 上面的方法已经测试过
        System.out.println("Tested.");
    }

    @Test
    public void takeBottle() {
        adventure.addBottle(new Bottle(2, "bottle", 100, 100));
        adventure.takeBottle(2);
        assertEquals(1, adventure.getTakenBottles().size());
        assertEquals(1, adventure.getTakenBottles().get("bottle").size());
        assertEquals(2, adventure.getTakenBottles().get("bottle").get(2).getId());
        assertEquals("bottle", adventure.getTakenBottles().get("bottle").get(2).getName());
        assertEquals(100, adventure.getTakenBottles().get("bottle").get(2).getCapacity());
        adventure.addBottle(new Bottle(3, "bottle", 100, 100));
        adventure.takeBottle(3);
        assertEquals(1, adventure.getTakenBottles().size());
        assertEquals(1, adventure.getTakenBottles().get("bottle").size());
        adventure.addFood(new Food(4, "food", 100, 100));
        adventure.takeFood(4);
        adventure.eatFood("food");
        adventure.addBottle(new Bottle(5, "bottle", 100, 100));
        adventure.takeBottle(5);
        assertEquals(1, adventure.getTakenBottles().size());
        assertEquals(2, adventure.getTakenBottles().get("bottle").size());
        assertEquals(5, adventure.getTakenBottles().get("bottle").get(5).getId());
        assertEquals("bottle", adventure.getTakenBottles().get("bottle").get(5).getName());
        assertEquals(100, adventure.getTakenBottles().get("bottle").get(5).getCapacity());
    }

    @Test
    public void takeEquipment() {
        adventure.addEquipment(new Equipment(3, "equipment", 1, 100));
        adventure.takeEquipment(3);
        assertEquals(1, adventure.getTakenEquipments().size());
        assertEquals(3, adventure.getTakenEquipments().get("equipment").getId());
        assertEquals("equipment", adventure.getTakenEquipments().get("equipment").getName());
        assertEquals(1, adventure.getTakenEquipments().get("equipment").getStar());
        adventure.addEquipment(new Equipment(4, "equipment", 2, 100));
        adventure.takeEquipment(4);
        assertEquals(1, adventure.getTakenEquipments().size());
        assertEquals(4, adventure.getTakenEquipments().get("equipment").getId());
        assertEquals("equipment", adventure.getTakenEquipments().get("equipment").getName());
        assertEquals(2, adventure.getTakenEquipments().get("equipment").getStar());
    }

    @Test
    public void takeFood() {
        adventure.addFood(new Food(5, "food", 100, 100));
        adventure.takeFood(5);
        assertEquals(1, adventure.getTakenFoods().size());
        assertEquals(1, adventure.getTakenFoods().get("food").size());
        assertEquals(5, adventure.getTakenFoods().get("food").get(5).getId());
        assertEquals("food", adventure.getTakenFoods().get("food").get(5).getName());
        assertEquals(100, adventure.getTakenFoods().get("food").get(5).getEnergy());
        adventure.addFood(new Food(6, "food", 100, 100));
        adventure.takeFood(6);
        assertEquals(1, adventure.getTakenFoods().size());
        assertEquals(2, adventure.getTakenFoods().get("food").size());
        assertEquals(6, adventure.getTakenFoods().get("food").get(6).getId());
        assertEquals("food", adventure.getTakenFoods().get("food").get(6).getName());
        assertEquals(100, adventure.getTakenFoods().get("food").get(6).getEnergy());
    }

    @Test
    public void useBottle() {
        adventure.addBottle(new Bottle(2, "bottle", 100, 100));
        adventure.takeBottle(2);
        adventure.useBottle(false, "battle");
        assertEquals(1, adventure.getTakenBottles().size());
        assertEquals(500, adventure.getHp());
        adventure.useBottle(false, "bottle");
        assertEquals(1, adventure.getTakenBottles().size());
        assertEquals(1, adventure.getTakenBottles().get("bottle").size());
        assertEquals(2, adventure.getTakenBottles().get("bottle").get(2).getId());
        assertEquals("bottle", adventure.getTakenBottles().get("bottle").get(2).getName());
        assertEquals(600, adventure.getHp());
        adventure.useBottle(false, "bottle");
        adventure.useBottle(true, "bottle");
        assertEquals(0, adventure.getTakenBottles().size());
        assertEquals(600, adventure.getHp());
    }

    @Test
    public void eatFood() {
        adventure.addFood(new Food(5, "food", 4, 100));
        adventure.takeFood(5);
        adventure.eatFood("foot");
        assertEquals(1, adventure.getTakenFoods().size());
        assertEquals(1, adventure.getLevel());
        adventure.eatFood("food");
        assertEquals(0, adventure.getTakenFoods().size());
        assertEquals(5, adventure.getLevel());
        assertEquals(2, adventure.getMaxBots());
    }

    @Test
    public void attackAoe() {
        HashMap<Integer, Adventure> advs = new HashMap<>();
        advs.put(1, adventure);
        advs.put(2, new Adventure(2, "adventure2"));
        HashMap<String, Integer> advNameToId = new HashMap<>();
        advNameToId.put("adventure", 1);
        advNameToId.put("adventure2", 2);
        ArrayList<String> fight = new ArrayList<>();
        fight.add("adventure");
        fight.add("adventure2");
        adventure.addEquipment(new Equipment(3, "equipment", 1, 100));
        adventure.attackAoe(fight, advs, advNameToId, "equipment");
        adventure.takeEquipment(3);
        adventure.attackAoe(fight, advs, advNameToId, "equipment");
    }

    @Test
    public void attack() {
        HashMap<Integer, Adventure> advs = new HashMap<>();
        advs.put(1, adventure);
        advs.put(2, new Adventure(2, "adventure2"));
        HashMap<String, Integer> advNameToId = new HashMap<>();
        advNameToId.put("adventure", 1);
        advNameToId.put("adventure2", 2);
        ArrayList<String> fight = new ArrayList<>();
        fight.add("adventure");
        fight.add("adventure2");
        adventure.addEquipment(new Equipment(3, "equipment", 1, 100));
        adventure.attack(fight, advs, advNameToId, "adventure2", "equipment");
        adventure.takeEquipment(3);
        adventure.attack(fight, advs, advNameToId, "adventure2", "equipment");
        assertEquals(499, advs.get(2).getHp());
    }

    @Test
    public void getHp() {
        assertEquals(500, adventure.getHp());
    }

    @Test
    public void setHp() {
        adventure.setHp(100);
        assertEquals(100, adventure.getHp());
    }

    @Test
    public void setFightBeforeHp() {
        adventure.setFightBeforeHp(500);
    }

    @Test
    public void getMaxBots() {
        assertEquals(1, adventure.getMaxBots());
    }

    @Test
    public void getLevel() {
        assertEquals(1, adventure.getLevel());
    }

    @Test
    public void getName() {
        assertEquals("adventure", adventure.getName());
    }

    @Test
    public void getId() {
        assertEquals(1, adventure.getId());
    }

    @Test
    public void getTakenBottles() {
        // 上面的方法已经测试过
        System.out.println("Tested.");
    }

    @Test
    public void getTakenEquipments() {
        // 上面的方法已经测试过
        System.out.println("Tested.");
    }

    @Test
    public void getTakenFoods() {
        // 上面的方法已经测试过
        System.out.println("Tested.");
    }

    @Test
    public void getClassName() {
        assertEquals("Adventurer", adventure.getClassName());
    }

    @Test
    public void getPrice() {
        assertEquals(0, adventure.getPrice());
    }

    @Test
    public void hireAdventure() {
        Adventure adventure2 = new Adventure(2, "adventure2");
        adventure.hireAdventure(2, adventure2);
    }

    @Test
    public void searchAllCommodities() {
        Adventure adventure2 = new Adventure(2, "adventure2");
        adventure.hireAdventure(2, adventure2);
        adventure.searchAllCommodities();
    }

    @Test
    public void searchMaxPrice() {
        adventure.searchMaxPrice();
        Adventure adventure2 = new Adventure(2, "adventure2");
        adventure.hireAdventure(2, adventure2);
        adventure.searchMaxPrice();
    }

    @Test
    public void searchCommodityClass() {
        Adventure adventure2 = new Adventure(2, "adventure2");
        adventure.hireAdventure(2, adventure2);
        adventure.searchCommodityClass(2);
    }

    @Test
    public void sellAllItems() {
        adventure.addBottle(new RegularBottle(2, "bottle", 100, 100));
        adventure.takeBottle(2);
        adventure.addEquipment(new RegularEquipment(3, "equipment", 1, 100));
        adventure.takeEquipment(3);
        adventure.addFood(new Food(4, "food", 100, 100));
        adventure.takeFood(4);
        adventure.sellAllItems();
    }

    @Test
    public void buyAnItem() {
        adventure.addBottle(new RegularBottle(2, "bottle", 100, 100));
        adventure.takeBottle(2);
        adventure.addEquipment(new RegularEquipment(3, "equipment", 1, 100));
        adventure.takeEquipment(3);
        adventure.addFood(new Food(4, "food", 100, 100));
        adventure.takeFood(4);
        adventure.sellAllItems();
        adventure.buyAnItem(2, "bottle", "RegularBottle", "100");
        adventure.buyAnItem(3, "equipment", "RegularEquipment", "100");
        adventure.buyAnItem(4, "food", "Food", "100");
    }

    @Test
    public void hire() {
        Adventure adventure2 = new Adventure(2, "adventure2");
        adventure.hire(2, adventure2);
    }

    @Test
    public void notifyEmployees() {
        Adventure adventure2 = new Adventure(2, "adventure2");
        adventure.hire(2, adventure2);
        adventure.setHp(10);
        adventure.notifyEmployees();
    }

    @Test
    public void rescue() {
        adventure.rescue(1);
        adventure.rescue(0);
    }
}