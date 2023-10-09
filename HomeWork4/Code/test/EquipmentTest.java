import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EquipmentTest {
    private Equipment equipment;

    @Before
    public void setUp() throws Exception {
        equipment = new Equipment(1, "test", 1);
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
}