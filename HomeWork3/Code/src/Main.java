import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void addAdventure(HashMap<Integer, Adventure> advs, ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        String name = orders.get(1);
        advs.put(advId, new Adventure(advId, name));
    }

    public static void addBottle(HashMap<Integer, Adventure> advs, ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int botId = Integer.parseInt(orders.get(1));
        String name = orders.get(2);
        int capacity = Integer.parseInt(orders.get(3));
        advs.get(advId).addBottle(new Bottle(botId, name, capacity));
    }

    public static void removeBottle(HashMap<Integer, Adventure> advs, ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int botId = Integer.parseInt(orders.get(1));
        advs.get(advId).removeBottle(botId).printIntFirst();
    }

    public static void addEquipment(HashMap<Integer, Adventure> advs, ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int equipId = Integer.parseInt(orders.get(1));
        String name = orders.get(2);
        int star = Integer.parseInt(orders.get(3));
        advs.get(advId).addEquipment(new Equipment(equipId, name, star));
    }

    public static void removeEquipment(HashMap<Integer, Adventure> advs, ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int equipId = Integer.parseInt(orders.get(1));
        advs.get(advId).removeEquipment(equipId).printIntFirst();
    }

    public static void increaseStar(HashMap<Integer, Adventure> advs, ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int equipId = Integer.parseInt(orders.get(1));
        advs.get(advId).increaseStar(equipId).printStringFirst();
    }

    public static void addFood(HashMap<Integer, Adventure> advs, ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int foodId = Integer.parseInt(orders.get(1));
        String name = orders.get(2);
        int energy = Integer.parseInt(orders.get(3));
        advs.get(advId).addFood(new Food(foodId, name, energy));
    }

    public static void removeFood(HashMap<Integer, Adventure> advs, ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int foodId = Integer.parseInt(orders.get(1));
        advs.get(advId).removeFood(foodId).printIntFirst();
    }

    public static void takeEquipment(HashMap<Integer, Adventure> advs, ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int equipId = Integer.parseInt(orders.get(1));
        advs.get(advId).takeEquipment(equipId);
    }

    public static void takeBottle(HashMap<Integer, Adventure> advs, ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int botId = Integer.parseInt(orders.get(1));
        advs.get(advId).takeBottle(botId);
    }

    public static void takeFood(HashMap<Integer, Adventure> advs, ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int foodId = Integer.parseInt(orders.get(1));
        advs.get(advId).takeFood(foodId);
    }

    public static void useBottle(HashMap<Integer, Adventure> advs, ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        String name = orders.get(1);
        advs.get(advId).useBottle(name);
    }

    public static void eatFood(HashMap<Integer, Adventure> advs, ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        String name = orders.get(1);
        advs.get(advId).eatFood(name);
    }

    public static ArrayList<String> getOrders(String line) {
        String[] strings = line.trim().split(" ");
        return new ArrayList<>(Arrays.asList(strings));
    }

    public static void makeChoice(HashMap<Integer, Adventure> advs, ArrayList<String> orders) {
        int type = Integer.parseInt(orders.remove(0));
        switch (type) {
            case 1:
                addAdventure(advs, orders);
                break;
            case 2:
                addBottle(advs, orders);
                break;
            case 3:
                removeBottle(advs, orders);
                break;
            case 4:
                addEquipment(advs, orders);
                break;
            case 5:
                removeEquipment(advs, orders);
                break;
            case 6:
                increaseStar(advs, orders);
                break;
            case 7:
                addFood(advs, orders);
                break;
            case 8:
                removeFood(advs, orders);
                break;
            case 9:
                takeEquipment(advs, orders);
                break;
            case 10:
                takeBottle(advs, orders);
                break;
            case 11:
                takeFood(advs, orders);
                break;
            case 12:
                useBottle(advs, orders);
                break;
            case 13:
                eatFood(advs, orders);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        int n;
        HashMap<Integer, Adventure> advs = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        n = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < n; i++) {
            String nextLine = scanner.nextLine();
            makeChoice(advs, getOrders(nextLine));
        }
    }
}
