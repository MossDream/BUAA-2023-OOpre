import org.junit.Test;
public class ChildTest {

    @Test
    public void subMoney() {
        Child child = new Child(20);
        child.subMoney(5);
        assert (child.getMoney() == 15);
        // 看看是不是确实的减去了那么多钱？，比如敲错了写成=而不是-=了
    }

    @Test
    public void addOneFruit() {
        Child child = new Child(20);
        child.addOneFruit("apple");
        assert (child.getAppleCount() == 1);
        //是否加上了？
        child.addOneFruit("banana");
        assert (child.getBananaCount() == 1);
        //是否加上了？
    }

    @Test
    public void eat() {
        Child child = new Child(20);
        child.eat("banana");
        assert (child.getBananaCount() == 0);
        //是否没有判断就直接减了？
        child.addOneFruit("banana");
        //为了保证覆盖性，我们可能要测试一下这个意外条件
        child.eat("something");
        //这里的add方法在上面需要已经测试过正确性
        child.eat("banana");
        child.addOneFruit("apple");
        assert (child.getBananaCount() == 0);
        child.eat("apple");
        assert (child.getAppleCount() == 0);
    }

    @Test
    public void buyFromStore() {
        Store store = new Store(5, 5);
        Child child1 = new Child(2);
        child1.buyFromStore("banana", store);
        assert (child1.getBananaCount() == 1);
        assert (child1.getMoney() == 0);
        Child child2 = new Child(3);
        child2.buyFromStore("apple", store);
        assert (child2.getAppleCount() == 1);
        assert (child2.getMoney() == 0);
        Child child3 = new Child(1);
        child3.buyFromStore("apple", store);
        child3.buyFromStore("banana", store);
        assert (child3.getMoney() == 1);
        assert (child3.getAppleCount() == 0);
        assert (child3.getBananaCount() == 0);
    }
}