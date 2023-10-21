import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EquipmentTest {
    private Equipment equipment;

    @Before
    public void setUp() throws Exception {
        equipment = new Equipment(1, "test", 1, 100);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Test finished.");
    }

    @Test
    public void increaseStar() {
        equipment.increaseStar();
        assertEquals(2, equipment.getStar());
    }

    @Test
    public void getId() {
        assertEquals(1, equipment.getId());
    }

    @Test
    public void getName() {
        assertEquals("test", equipment.getName());
    }

    @Test
    public void getStar() {
        assertEquals(1, equipment.getStar());
    }

    @Test
    public void getLoseHp() {
        equipment.getLoseHp(1, 1);
    }

    @Test
    public void getClassName() {
        equipment.getClassName();
    }

    @Test
    public void getPrice() {
        long price = equipment.getPrice();
    }
}