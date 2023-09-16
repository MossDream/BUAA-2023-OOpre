import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n;
        int capacity;
        int star;
        int advId;
        int botId;
        int equipId;
        String name;
        int type;
        HashMap<Integer, Adventure> adventures = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            type = scanner.nextInt();
            switch (type) {
                case 1:
                    advId = scanner.nextInt();
                    name = scanner.next();
                    adventures.put(advId, new Adventure(advId, name));
                    break;
                case 2:
                    advId = scanner.nextInt();
                    botId = scanner.nextInt();
                    name = scanner.next();
                    capacity = scanner.nextInt();
                    adventures.get(advId).addBottle(new Bottle(botId, name, capacity));
                    break;
                case 3:
                    advId = scanner.nextInt();
                    botId = scanner.nextInt();
                    adventures.get(advId).removeBottle(botId).printIntFirst();
                    break;
                case 4:
                    advId = scanner.nextInt();
                    equipId = scanner.nextInt();
                    name = scanner.next();
                    star = scanner.nextInt();
                    adventures.get(advId).addEquipment(new Equipment(equipId, name, star));
                    break;
                case 5:
                    advId = scanner.nextInt();
                    equipId = scanner.nextInt();
                    adventures.get(advId).removeEquipment(equipId).printIntFirst();
                    break;
                case 6:
                    advId = scanner.nextInt();
                    equipId = scanner.nextInt();
                    adventures.get(advId).increaseStar(equipId).printStringFirst();
                    break;
                default:
                    break;
            }
        }
    }
}
