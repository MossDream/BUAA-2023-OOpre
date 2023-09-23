import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrintInfoTest {
    private PrintInfo printInfo;

    @Before
    public void setUp() throws Exception {
        printInfo = new PrintInfo(1, "bottle");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Test finished.");
    }

    @Test
    public void printIntFirst() {
        printInfo.printIntFirst();
    }

    @Test
    public void printStringFirst() {
        printInfo.printStringFirst();
    }
}