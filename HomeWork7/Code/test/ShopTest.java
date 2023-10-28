import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShopTest {

    @Before
    public void setUp() throws Exception {
        Shop shop = Shop.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Test finished.");
    }

    @Test
    public void getInstance() {
        Shop shop = Shop.getInstance();
    }

    @Test
    public void record() {
        Shop.getInstance().record("RegularBottle", 100, 100);
        Shop.getInstance().record("RegularEquipment", 100, 100);
        Shop.getInstance().record("Food", 100, 100);
    }

    @Test
    public void showPrice() {
        Shop.getInstance().showPrice("RegularBottle");
        Shop.getInstance().showPrice("RegularEquipment");
        Shop.getInstance().showPrice("Food");
    }

    @Test
    public void showAttribute() {
        Shop.getInstance().showAttribute("RegularBottle");
        Shop.getInstance().showAttribute("RegularEquipment");
        Shop.getInstance().showAttribute("Food");
    }
}