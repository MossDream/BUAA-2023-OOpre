import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FoodTest {
    private Food food;

    @Before
    public void setUp() throws Exception {
        food = new Food(1, "test", 1, 100);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Test finished.");
    }

    @Test
    public void getId() {
        assertEquals(1, food.getId());
    }

    @Test
    public void getName() {
        assertEquals("test", food.getName());
    }

    @Test
    public void getEnergy() {
        assertEquals(1, food.getEnergy());
    }

    @Test
    public void getPrice() {
        assertEquals(100, food.getPrice());
    }

    @Test
    public void getClassName() {
        assertEquals("Food", food.getClassName());
    }
}