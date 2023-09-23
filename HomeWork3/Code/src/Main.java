import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void addAdventure(HashMap<Integer, Adventure> adventures, Scanner scanner) {
        int advId = scanner.nextInt();
        String name = scanner.next();
        adventures.put(advId, new Adventure(advId, name));
    }

    public static void addBottle(HashMap<Integer, Adventure> adventures, Scanner scanner) {
        int advId = scanner.nextInt();
        int botId = scanner.nextInt();
        String name = scanner.next();
        int capacity = scanner.nextInt();
        adventures.get(advId).addBottle(new Bottle(botId, name, capacity));
    }

    public static void removeBottle(HashMap<Integer, Adventure> adventures, Scanner scanner) {
        int advId = scanner.nextInt();
        int botId = scanner.nextInt();
        adventures.get(advId).removeBottle(botId).printIntFirst();
    }

    public static void addEquipment(HashMap<Integer, Adventure> adventures, Scanner scanner) {
        int advId = scanner.nextInt();
        int equipId = scanner.nextInt();
        String name = scanner.next();
        int star = scanner.nextInt();
        adventures.get(advId).addEquipment(new Equipment(equipId, name, star));
    }

    public static void removeEquipment(HashMap<Integer, Adventure> adventures, Scanner scanner) {
        int advId = scanner.nextInt();
        int equipId = scanner.nextInt();
        adventures.get(advId).removeEquipment(equipId).printIntFirst();
    }

    public static void increaseStar(HashMap<Integer, Adventure> adventures, Scanner scanner) {
        int advId = scanner.nextInt();
        int equipId = scanner.nextInt();
        adventures.get(advId).increaseStar(equipId).printStringFirst();
    }

    public static void addFood(HashMap<Integer, Adventure> adventures, Scanner scanner) {
        int advId = scanner.nextInt();
        int foodId = scanner.nextInt();
        String name = scanner.next();
        int energy = scanner.nextInt();
        adventures.get(advId).addFood(new Food(foodId, name, energy));
    }

    public static void removeFood(HashMap<Integer, Adventure> adventures, Scanner scanner) {
        int advId = scanner.nextInt();
        int foodId = scanner.nextInt();
        adventures.get(advId).removeFood(foodId).printIntFirst();
    }

    public static void takeEquipment(HashMap<Integer, Adventure> adventures, Scanner scanner) {
        int advId = scanner.nextInt();
        int equipId = scanner.nextInt();
        adventures.get(advId).takeEquipment(equipId);
    }

    public static void takeBottle(HashMap<Integer, Adventure> adventures, Scanner scanner) {
        int advId = scanner.nextInt();
        int botId = scanner.nextInt();
        adventures.get(advId).takeBottle(botId);
    }

    public static void takeFood(HashMap<Integer, Adventure> adventures, Scanner scanner) {
        int advId = scanner.nextInt();
        int foodId = scanner.nextInt();
        adventures.get(advId).takeFood(foodId);
    }

    public static void useBottle(HashMap<Integer, Adventure> adventures, Scanner scanner) {
        int advId = scanner.nextInt();
        String name = scanner.next();
        adventures.get(advId).useBottle(name);
    }

    public static void eatFood(HashMap<Integer, Adventure> adventures, Scanner scanner) {
        int advId = scanner.nextInt();
        String name = scanner.next();
        adventures.get(advId).eatFood(name);
    }

    public static void main(String[] args) {
        int n;
        int type;
        HashMap<Integer, Adventure> adventures = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            type = scanner.nextInt();
            switch (type) {
                case 1:
                    addAdventure(adventures, scanner);
                    break;
                case 2:
                    addBottle(adventures, scanner);
                    break;
                case 3:
                    removeBottle(adventures, scanner);
                    break;
                case 4:
                    addEquipment(adventures, scanner);
                    break;
                case 5:
                    removeEquipment(adventures, scanner);
                    break;
                case 6:
                    increaseStar(adventures, scanner);
                    break;
                case 7:
                    addFood(adventures, scanner);
                    break;
                case 8:
                    removeFood(adventures, scanner);
                    break;
                case 9:
                    takeEquipment(adventures, scanner);
                    break;
                case 10:
                    takeBottle(adventures, scanner);
                    break;
                case 11:
                    takeFood(adventures, scanner);
                    break;
                case 12:
                    useBottle(adventures, scanner);
                    break;
                case 13:
                    eatFood(adventures, scanner);
                    break;
                default:
                    break;
            }
        }
    }
}
