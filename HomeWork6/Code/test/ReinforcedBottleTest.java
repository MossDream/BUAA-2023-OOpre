import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReinforcedBottleTest {
    private ReinforcedBottle reinforcedBottle;

    @Before
    public void setUp() throws Exception {
        reinforcedBottle = new ReinforcedBottle(1, "ReinforcedBottle", 100, 100, 0.5);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Test ReinforcedBottle finished.");
    }

    @Test
    public void getIncreasedHp() {
        reinforcedBottle.getIncreasedHp(100);
    }
}