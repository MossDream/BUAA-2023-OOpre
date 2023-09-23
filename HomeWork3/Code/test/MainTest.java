import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class MainTest {
    private HashMap<Integer, Adventure> adventures;

    @Before
    public void setUp() throws Exception {
        adventures = new HashMap<>();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Test finished");
    }

    @Test
    public void addAdventure() {
        ArrayList<String> orders = new ArrayList<>();
        orders.add("123");
        orders.add("test");
        Main.addAdventure(adventures, orders);
        assertEquals("test", adventures.get(123).getName());
        assertEquals(123, adventures.get(123).getId());
        assertEquals(1, adventures.get(123).getLevel());
        assertEquals(500, adventures.get(123).getHp());
        assertEquals(1, adventures.get(123).getMaxBots());
    }

    @Test
    public void addBottle() {
        ArrayList<String> orders1 = new ArrayList<>();
        ArrayList<String> orders2 = new ArrayList<>();
        orders1.add("123");
        orders1.add("test");
        orders2.add("123");
        orders2.add("1");
        orders2.add("test");
        orders2.add("100");
        Main.addAdventure(adventures, orders1);
        Main.addBottle(adventures, orders2);
        assertEquals("test", adventures.get(123).getBottles().get(1).getName());
        assertEquals(1, adventures.get(123).getBottles().get(1).getId());
        assertEquals(100, adventures.get(123).getBottles().get(1).getCapacity());

    }

    @Test
    public void removeBottle() {
        ArrayList<String> orders1 = new ArrayList<>();
        ArrayList<String> orders2 = new ArrayList<>();
        orders1.add("123");
        orders1.add("test");
        orders2.add("123");
        orders2.add("1");
        orders2.add("test");
        orders2.add("100");
        Main.addAdventure(adventures, orders1);
        Main.addBottle(adventures, orders2);
        Main.removeBottle(adventures, orders2);
        assertEquals(0, adventures.get(123).getBottles().size());
    }

    @Test
    public void addEquipment() {
        ArrayList<String> orders1 = new ArrayList<>();
        ArrayList<String> orders2 = new ArrayList<>();
        orders1.add("123");
        orders1.add("test");
        orders2.add("123");
        orders2.add("1");
        orders2.add("test");
        orders2.add("100");
        Main.addAdventure(adventures, orders1);
        Main.addEquipment(adventures, orders2);
        assertEquals("test", adventures.get(123).getEquipments().get(1).getName());
        assertEquals(1, adventures.get(123).getEquipments().get(1).getId());
        assertEquals(100, adventures.get(123).getEquipments().get(1).getStar());
    }

    @Test
    public void removeEquipment() {
        ArrayList<String> orders1 = new ArrayList<>();
        ArrayList<String> orders2 = new ArrayList<>();
        orders1.add("123");
        orders1.add("test");
        orders2.add("123");
        orders2.add("1");
        orders2.add("test");
        orders2.add("100");
        Main.addAdventure(adventures, orders1);
        Main.addEquipment(adventures, orders2);
        Main.removeEquipment(adventures, orders2);
        assertEquals(0, adventures.get(123).getEquipments().size());
    }

    @Test
    public void increaseStar() {
        ArrayList<String> orders1 = new ArrayList<>();
        ArrayList<String> orders2 = new ArrayList<>();
        orders1.add("123");
        orders1.add("test");
        orders2.add("123");
        orders2.add("1");
        orders2.add("test");
        orders2.add("100");
        ArrayList<String> orders3 = new ArrayList<>();
        orders3.add("123");
        orders3.add("1");
        Main.addAdventure(adventures, orders1);
        Main.addEquipment(adventures, orders2);
        Main.increaseStar(adventures, orders3);
        assertEquals(101, adventures.get(123).getEquipments().get(1).getStar());
    }

    @Test
    public void addFood() {
        ArrayList<String> orders1 = new ArrayList<>();
        ArrayList<String> orders2 = new ArrayList<>();
        orders1.add("123");
        orders1.add("test");
        orders2.add("123");
        orders2.add("1");
        orders2.add("test");
        orders2.add("100");
        Main.addAdventure(adventures, orders1);
        Main.addFood(adventures, orders2);
        assertEquals("test", adventures.get(123).getFoods().get(1).getName());
        assertEquals(1, adventures.get(123).getFoods().get(1).getId());
        assertEquals(100, adventures.get(123).getFoods().get(1).getEnergy());
    }

    @Test
    public void removeFood() {
        ArrayList<String> orders1 = new ArrayList<>();
        ArrayList<String> orders2 = new ArrayList<>();
        orders1.add("123");
        orders1.add("test");
        orders2.add("123");
        orders2.add("1");
        orders2.add("test");
        orders2.add("100");
        Main.addAdventure(adventures, orders1);
        Main.addFood(adventures, orders2);
        Main.removeFood(adventures, orders2);
        assertEquals(0, adventures.get(123).getFoods().size());
    }

    @Test
    public void takeEquipment() {
        ArrayList<String> orders1 = new ArrayList<>();
        ArrayList<String> orders2 = new ArrayList<>();
        orders1.add("123");
        orders1.add("test");
        orders2.add("123");
        orders2.add("1");
        orders2.add("test");
        orders2.add("100");
        ArrayList<String> orders3 = new ArrayList<>();
        orders3.add("123");
        orders3.add("1");
        Main.addAdventure(adventures, orders1);
        Main.addEquipment(adventures, orders2);
        Main.takeEquipment(adventures, orders3);
        assertEquals(1, adventures.get(123).getTakenEquipments().size());
    }

    @Test
    public void takeBottle() {
        ArrayList<String> orders1 = new ArrayList<>();
        ArrayList<String> orders2 = new ArrayList<>();
        orders1.add("123");
        orders1.add("test");
        orders2.add("123");
        orders2.add("1");
        orders2.add("test");
        orders2.add("100");
        ArrayList<String> orders3 = new ArrayList<>();
        orders3.add("123");
        orders3.add("1");
        Main.addAdventure(adventures, orders1);
        Main.addBottle(adventures, orders2);
        Main.takeBottle(adventures, orders3);
        assertEquals(1, adventures.get(123).getTakenBottles().size());
    }

    @Test
    public void takeFood() {
        ArrayList<String> orders1 = new ArrayList<>();
        ArrayList<String> orders2 = new ArrayList<>();
        orders1.add("123");
        orders1.add("test");
        orders2.add("123");
        orders2.add("1");
        orders2.add("test");
        orders2.add("100");
        ArrayList<String> orders3 = new ArrayList<>();
        orders3.add("123");
        orders3.add("1");
        Main.addAdventure(adventures, orders1);
        Main.addFood(adventures, orders2);
        Main.takeFood(adventures, orders3);
        assertEquals(1, adventures.get(123).getTakenFoods().size());
    }

    @Test
    public void useBottle() {
        ArrayList<String> orders1 = new ArrayList<>();
        ArrayList<String> orders2 = new ArrayList<>();
        orders1.add("123");
        orders1.add("test");
        orders2.add("123");
        orders2.add("1");
        orders2.add("test");
        orders2.add("100");
        ArrayList<String> orders3 = new ArrayList<>();
        orders3.add("123");
        orders3.add("1");
        ArrayList<String> orders4 = new ArrayList<>();
        orders4.add("123");
        orders4.add("test");
        Main.addAdventure(adventures, orders1);
        Main.addBottle(adventures, orders2);
        Main.takeBottle(adventures, orders3);
        Main.useBottle(adventures, orders4);
        assertEquals(0, adventures.get(123).getTakenBottles().get("test").get(1).getCapacity());
    }

    @Test
    public void eatFood() {
        ArrayList<String> orders1 = new ArrayList<>();
        ArrayList<String> orders2 = new ArrayList<>();
        orders1.add("123");
        orders1.add("test");
        orders2.add("123");
        orders2.add("1");
        orders2.add("test");
        orders2.add("100");
        ArrayList<String> orders3 = new ArrayList<>();
        orders3.add("123");
        orders3.add("1");
        ArrayList<String> orders4 = new ArrayList<>();
        orders4.add("123");
        orders4.add("test");
        Main.addAdventure(adventures, orders1);
        Main.addFood(adventures, orders2);
        Main.takeFood(adventures, orders3);
        Main.eatFood(adventures, orders4);
        assertEquals(0, adventures.get(123).getTakenFoods().size());
    }

    @Test
    public void getOrders() {
        String line = "1 123 test";
        ArrayList<String> orders = Main.getOrders(line);
        assertEquals("1", orders.get(0));
        assertEquals("123", orders.get(1));
        assertEquals("test", orders.get(2));
    }

    @Test
    public void makeChoice() {
        String line = "1 123 test";
        ArrayList<String> orders = Main.getOrders(line);
        Main.makeChoice(adventures, orders);
        assertEquals("test", adventures.get(123).getName());
        line = "2 123 1 test 100";
        orders = Main.getOrders(line);
        Main.makeChoice(adventures, orders);
        line = "10 123 1";
        orders = Main.getOrders(line);
        Main.makeChoice(adventures, orders);
        line = "12 123 test";
        orders = Main.getOrders(line);
        Main.makeChoice(adventures, orders);
        line = "3 123 1";
        orders = Main.getOrders(line);
        Main.makeChoice(adventures, orders);
        line = "4 123 1 test 100";
        orders = Main.getOrders(line);
        Main.makeChoice(adventures, orders);
        line = "6 123 1";
        orders = Main.getOrders(line);
        Main.makeChoice(adventures, orders);
        line = "9 123 1";
        orders = Main.getOrders(line);
        Main.makeChoice(adventures, orders);
        line = "5 123 1";
        orders = Main.getOrders(line);
        Main.makeChoice(adventures, orders);
        line = "7 123 1 test 100";
        orders = Main.getOrders(line);
        Main.makeChoice(adventures, orders);
        line = "11 123 1";
        orders = Main.getOrders(line);
        Main.makeChoice(adventures, orders);
        line = "13 123 test";
        orders = Main.getOrders(line);
        Main.makeChoice(adventures, orders);
        line = "7 123 1 test 100";
        orders = Main.getOrders(line);
        Main.makeChoice(adventures, orders);
        line = "8 123 1";
        orders = Main.getOrders(line);
        Main.makeChoice(adventures, orders);
    }
}