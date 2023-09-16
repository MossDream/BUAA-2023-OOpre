import org.junit.Test;
public class StoreTest {
    @Test
    public void trySellOut() {
        Store store = new Store(1, 1);
        Child child = new Child(100);
        store.trySellOut(child, "banana");
        assert (store.getBananaCount() == 0);
        store.trySellOut(child, "apple");
        assert (store.getAppleCount() == 0);
        store.trySellOut(child, "banana");
        assert (store.getBananaCount() == 0);
        assert (child.getBananaCount() == 1);
    }
}