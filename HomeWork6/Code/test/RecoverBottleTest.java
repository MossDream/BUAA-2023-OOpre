import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecoverBottleTest {
    private RecoverBottle recoverBottle;

    @Before
    public void setUp() throws Exception {
        recoverBottle = new RecoverBottle(1, "recoverBottle", 100, 100, 0.5);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("RecoverBottleTest finished");
    }

    @Test
    public void getIncreasedHp() {
        recoverBottle.getIncreasedHp(100);
    }
}