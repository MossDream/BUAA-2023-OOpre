import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BottleTest {
    private Bottle bottle;

    @Before
    public void setUp() throws Exception {
        bottle = new Bottle(1, "bottle", 100, 100);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Test finished.");
    }

    @Test
    public void getId() {
        assertEquals(1, bottle.getId());
    }

    @Test
    public void getName() {
        assertEquals("bottle", bottle.getName());
    }

    @Test
    public void getCapacity() {
        assertEquals(100, bottle.getCapacity());
    }

    @Test
    public void setCapacity() {
        bottle.setCapacity(200);
        assertEquals(200, bottle.getCapacity());
    }

    @Test
    public void getIsEmpty() {
        bottle.getIsEmpty();
    }

    @Test
    public void setIsEmpty() {
        bottle.setIsEmpty(true);
        assertEquals(true, bottle.getIsEmpty());
    }

    @Test
    public void getIncreasedHp() {
        bottle.getIncreasedHp(0);
    }

    @Test
    public void getPrice() {
        long price = bottle.getPrice();
    }

    @Test
    public void getClassName() {
        bottle.getClassName();
    }

}