import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @Before
    public void setUp() throws Exception {
        System.out.println("Test start");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Test finished");
    }

    @Test
    public void addAction() {
        Main.addAction();
    }

    @Test
    public void addAdventure() {
        ArrayList<String> orders = new ArrayList<>();
        orders.add("123");
        orders.add("test");
        Main.addAdventure(orders);
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
        Main.addAdventure(orders1);
        Main.addBottle(orders2);
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
        Main.addAdventure(orders1);
        Main.addBottle(orders2);
        Main.removeBottle(orders2);
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
        Main.addAdventure(orders1);
        Main.addEquipment(orders2);
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
        Main.addAdventure(orders1);
        Main.addEquipment(orders2);
        Main.removeEquipment(orders2);
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
        Main.addAdventure(orders1);
        Main.addEquipment(orders2);
        Main.increaseStar(orders3);
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
        Main.addAdventure(orders1);
        Main.addFood(orders2);
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
        Main.addAdventure(orders1);
        Main.addFood(orders2);
        Main.removeFood(orders2);
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
        Main.addAdventure(orders1);
        Main.addEquipment(orders2);
        Main.takeEquipment(orders3);
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
        Main.addAdventure(orders1);
        Main.addBottle(orders2);
        Main.takeBottle(orders3);
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
        Main.addAdventure(orders1);
        Main.addFood(orders2);
        Main.takeFood(orders3);

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
        Main.addAdventure(orders1);
        Main.addBottle(orders2);
        Main.takeBottle(orders3);
        Main.useBottle(orders4);
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
        Main.addAdventure(orders1);
        Main.addFood(orders2);
        Main.takeFood(orders3);
        Main.eatFood(orders4);
    }

    @Test
    public void parseFightLog() {
        Main.addAdventure(new ArrayList<>(Arrays.asList("1", "test")));
        Main.addAdventure(new ArrayList<>(Arrays.asList("2", "test2")));
        String log1 = "2023/10-test-bottle";
        Main.addBottle(new ArrayList<>(Arrays.asList("1", "111", "bottle", "100")));
        Main.takeBottle(new ArrayList<>(Arrays.asList("1", "111")));
        Main.parseFightLog(log1);
        Main.addEquipment(new ArrayList<>(Arrays.asList("1", "222", "equipment", "100")));
        Main.takeEquipment(new ArrayList<>(Arrays.asList("1", "222")));
        String log2 = "2023/10-test@test2-equipment";
        Main.parseFightLog(log2);
        String log3 = "2023/10-test@#-equipment";
        Main.parseFightLog(log3);
    }

    @Test
    public void writeDateLog() {
        Main.writeDateLog(1, "2023/10", "test", "test2", "test3", "test4", "test5");
        Main.writeDateLog(2, "2023/10", "test", "test2", "test3", "test4", "test5");
        Main.writeDateLog(3, "2023/10", "test", "test2", "test3", "test4", "test5");
        Main.writeDateLog(1, "2023/10", "test", "test2", "test3", "test4", "test5");
        Main.writeDateLog(2, "2023/10", "test", "test2", "test3", "test4", "test5");
        Main.writeDateLog(3, "2023/10", "test", "test2", "test3", "test4", "test5");
    }

    @Test
    public void writeAttackerLog() {
        Main.writeAttackerLog(1, "test", "test2", "test3", "test4");
        Main.writeAttackerLog(2, "test", "test2", "test3", "test4");
        Main.writeAttackerLog(1, "test", "test2", "test3", "test4");
        Main.writeAttackerLog(2, "test", "test2", "test3", "test4");
    }

    @Test
    public void writeAttackedLog() {
        Main.addAdventure(new ArrayList<>(Arrays.asList("1", "test3")));
        Main.writeAttackedLog(1, "test", "test2", "test3", "test4");
        Main.writeAttackedLog(2, "test", "test2", "test3", "test4");
        Main.writeAttackedLog(1, "test", "test2", "test3", "test4");
        Main.writeAttackedLog(2, "test", "test2", "test3", "test4");
    }

    @Test
    public void searchByDate() {
        ArrayList<String> orders = new ArrayList<>();
        orders.add("2023/10");
        Main.searchByDate(orders);
        Main.writeDateLog(1, "2023/10", "test", "test2", "test3", "test4", "test5");
        Main.searchByDate(orders);
    }

    @Test
    public void searchByAttacker() {
        ArrayList<String> orders = new ArrayList<>();
        orders.add("1");
        Main.searchByAttacker(orders);
        Main.addAdventure(new ArrayList<>(Arrays.asList("1", "test2")));
        Main.writeAttackerLog(1, "test", "test2", "test3", "test4");
        Main.searchByAttacker(orders);
    }

    @Test
    public void searchByAttacked() {
        ArrayList<String> orders = new ArrayList<>();
        orders.add("1");
        Main.searchByAttacked(orders);
        Main.addAdventure(new ArrayList<>(Arrays.asList("1", "test2")));
        Main.writeAttackedLog(1, "test", "test2", "test3", "test4");
        Main.searchByAttacked(orders);
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
        Main.makeChoice(orders);
        line = "2 123 1 test 100";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
        line = "10 123 1";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
        line = "12 123 test";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
        line = "3 123 1";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
        line = "4 123 1 test 100";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
        line = "6 123 1";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
        line = "9 123 1";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
        line = "5 123 1";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
        line = "7 123 1 test 100";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
        line = "11 123 1";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
        line = "13 123 test";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
        line = "7 123 1 test 100";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
        line = "8 123 1";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
        line = "15 2023/10";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
        line = "16 1";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
        line = "17 1";
        orders = Main.getOrders(line);
        Main.makeChoice(orders);
    }
}