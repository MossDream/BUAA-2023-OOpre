import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EpicEquipmentTest {
    private EpicEquipment epicEquipment;

    @Before
    public void setUp() throws Exception {
        epicEquipment = new EpicEquipment(1, "Epic", 1, 100, 0.5);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Test finished");
    }

    @Test
    public void getLoseHp() {
        epicEquipment.getLoseHp(1, 100);
    }
}