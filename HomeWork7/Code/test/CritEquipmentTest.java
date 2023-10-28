import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CritEquipmentTest {
    private CritEquipment critEquipment;

    @Before
    public void setUp() throws Exception {
        critEquipment = new CritEquipment(1, "test", 1, 100, 10);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Test finished.");
    }

    @Test
    public void getLoseHp() {
        critEquipment.getLoseHp(1, 1);
    }
}