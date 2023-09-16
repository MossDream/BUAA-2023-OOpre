import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Bottle bottle = new Bottle(2, "bottle", 100);
        adventure.addBottle(bottle);
        assertEquals(1, adventure.getBottles().size());
        assertEquals(2, adventure.getBottles().get(2).getId());
        assertEquals("bottle", adventure.getBottles().get(2).getName());
        assertEquals(100, adventure.getBottles().get(2).getCapacity());
    }

    @Test
    public void addEquipment() {
        Equipment equipment = new Equipment(3, "equipment", 1);
        adventure.addEquipment(equipment);
        assertEquals(1, adventure.getEquipments().size());
        assertEquals(3, adventure.getEquipments().get(3).getId());
        assertEquals("equipment", adventure.getEquipments().get(3).getName());
        assertEquals(1, adventure.getEquipments().get(3).getStar());

    }

    @Test
    public void removeBottle() {
        adventure.addBottle(new Bottle(2, "bottle", 100));
        PrintInfo printInfo = adventure.removeBottle(2);
        assertEquals(0, adventure.getBottles().size());
        printInfo.printIntFirst();
        printInfo.printStringFirst();
    }

    @Test
    public void removeEquipment() {
        adventure.addEquipment(new Equipment(3, "equipment", 1));
        PrintInfo printInfo = adventure.removeEquipment(3);
        assertEquals(0, adventure.getEquipments().size());
        printInfo.printIntFirst();
        printInfo.printStringFirst();
    }

    @Test
    public void increaseStar() {
        adventure.addEquipment(new Equipment(3, "equipment", 1));
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
}