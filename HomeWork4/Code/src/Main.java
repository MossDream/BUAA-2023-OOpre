import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void addAdventure(HashMap<Integer, Adventure> advs,
                                    HashMap<String, Integer> advNameToId,
                                    ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        String name = orders.get(1);
        advs.put(advId, new Adventure(advId, name));
        advNameToId.put(name, advId);
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
        Adventure adventure = advs.get(advId);
        adventure.addEquipment(new Equipment(equipId, name, star));
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
        advs.get(advId).useBottle(false, name);
    }

    public static void eatFood(HashMap<Integer, Adventure> advs, ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        String name = orders.get(1);
        advs.get(advId).eatFood(name);
    }

    public static void enterFightMode(HashMap<Integer, Adventure> advs,
                                      HashMap<String, Integer> advNameToId,
                                      ArrayList<String> fight,
                                      HashMap<String, ArrayList<String>> logByDate,
                                      HashMap<Integer, ArrayList<String>> logByAttacker,
                                      HashMap<Integer, ArrayList<String>> logByAttacked,
                                      ArrayList<String> orders) {
        int m = Integer.parseInt(orders.get(0));
        int k = Integer.parseInt(orders.get(1));
        for (int i = 0; i < m; i++) {
            String name = orders.get(i + 2);
            fight.add(name);
        }
        System.out.println("Enter Fight Mode");
        Scanner logScanner = new Scanner(System.in);
        for (int i = 0; i < k; i++) {
            String log = logScanner.nextLine();
            parseFightLog(advs, advNameToId,
                    fight, log, logByDate, logByAttacker, logByAttacked);
        }
        fight.clear();
    }

    public static void writeAttackLog(HashMap<Integer, ArrayList<String>> log,
                                      String date,
                                      int key,
                                      String attackerName,
                                      String attackedName,
                                      String equName) {
        if (log.containsKey(key)) {
            log.get(key).add(date + " " + attackerName +
                    " attacked " + attackedName
                    + " with " + equName);
        } else {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(date + " " + attackerName +
                    " attacked " + attackedName
                    + " with " + equName);
            log.put(key, arrayList);
        }
    }

    public static void parseFightLog(HashMap<Integer, Adventure> advs,
                                     HashMap<String, Integer> advNameToId,
                                     ArrayList<String> fight,
                                     String log,
                                     HashMap<String, ArrayList<String>> logByDate,
                                     HashMap<Integer, ArrayList<String>> logByAttacker,
                                     HashMap<Integer, ArrayList<String>> logByAttacked) {
        String[] strings = log.trim().split("-");
        if (strings[1].contains("@#")) {
            String[] strings1 = strings[1].split("@#");
            String equName = strings[2].trim();
            int attackerId = advNameToId.get(strings1[0].trim());
            //写日志的动作包含在attackAoe中
            advs.get(attackerId).attackAoe(fight,
                    advs, advNameToId,
                    equName, strings[0].trim(),
                    logByDate, logByAttacker, logByAttacked);
        } else if (strings[1].contains("@")) {
            String[] strings1 = strings[1].split("@");
            String equName = strings[2].trim();
            String date = strings[0].trim();
            String attackedName = strings1[1].trim();
            String attackerName = strings1[0].trim();
            int attackerId = advNameToId.get(attackerName);
            boolean isSuccess = advs.get(attackerId).attack(fight, advs, advNameToId,
                    attackedName, equName);
            //单独写日志，写日期日志、攻击者日志和被攻击者日志
            if (isSuccess) {
                if (logByDate.containsKey(date)) {
                    logByDate.get(date).add(attackerName +
                            " attacked " + attackedName
                            + " with " + equName);
                } else {
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add(attackerName + " attacked " + attackedName + " with " + equName);
                    logByDate.put(date, arrayList);
                }
                writeAttackLog(logByAttacker,
                        date, attackerId, attackerName, attackedName, equName);
                int attackedId = advNameToId.get(attackedName);
                writeAttackLog(logByAttacked,
                        date, attackedId, attackerName, attackedName, equName);
            }
        } else {
            String botName = strings[2].trim();
            if (fight.contains(strings[1].trim())) {
                int userId = advNameToId.get(strings[1].trim());
                boolean isSuccess = advs.get(userId).useBottle(true, botName);
                if (isSuccess) {
                    //单独写日志,只写日期日志
                    if (logByDate.containsKey(strings[0].trim())) {
                        logByDate.get(strings[0].trim()).add(strings[1].trim()
                                + " used " + botName);
                    } else {
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(strings[1].trim() + " used " + botName);
                        logByDate.put(strings[0].trim(), arrayList);
                    }
                }
            } else {
                System.out.println("Fight log error");
            }
        }
    }

    public static void searchByDate(HashMap<String, ArrayList<String>> logByDate,
                                    ArrayList<String> orders) {
        String date = orders.get(0);
        if (logByDate.containsKey(date)) {
            for (String log : logByDate.get(date)) {
                System.out.println(date + " " + log);
            }
        } else {
            System.out.println("No Matched Log");
        }
    }

    public static void searchByAttacker(HashMap<Integer, ArrayList<String>> logByAttacker,
                                        ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        if (logByAttacker.containsKey(advId)) {
            for (String log : logByAttacker.get(advId)) {
                System.out.println(log);
            }
        } else {
            System.out.println("No Matched Log");
        }
    }

    public static void searchByAttacked(HashMap<Integer, ArrayList<String>> logByAttacked,
                                        ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        if (logByAttacked.containsKey(advId)) {
            for (String log : logByAttacked.get(advId)) {
                System.out.println(log);
            }
        } else {
            System.out.println("No Matched Log");
        }
    }

    public static ArrayList<String> getOrders(String line) {
        String[] strings = line.trim().split(" ");
        return new ArrayList<>(Arrays.asList(strings));
    }

    public static void makeChoice(HashMap<Integer, Adventure> advs,
                                  HashMap<String, Integer> advNameToId,
                                  ArrayList<String> fight,
                                  HashMap<String, ArrayList<String>> logByDate,
                                  HashMap<Integer, ArrayList<String>> logByAttacker,
                                  HashMap<Integer, ArrayList<String>> logByAttacked,
                                  ArrayList<String> orders) {
        int type = Integer.parseInt(orders.remove(0));
        switch (type) {
            case 1:
                addAdventure(advs, advNameToId, orders);
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
            case 14:
                enterFightMode(advs, advNameToId,
                        fight, logByDate, logByAttacker, logByAttacked,
                        orders);
                break;
            case 15:
                searchByDate(logByDate, orders);
                break;
            case 16:
                searchByAttacker(logByAttacker, orders);
                break;
            case 17:
                searchByAttacked(logByAttacked, orders);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        int n;
        HashMap<Integer, Adventure> advs = new HashMap<>();
        ArrayList<String> fight = new ArrayList<>();
        HashMap<String, Integer> advNameToId = new HashMap<>();
        HashMap<String, ArrayList<String>> logByDate = new HashMap<>();
        HashMap<Integer, ArrayList<String>> logByAttacker = new HashMap<>();
        HashMap<Integer, ArrayList<String>> logByAttacked = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        n = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < n; i++) {
            String nextLine = scanner.nextLine();
            makeChoice(advs, advNameToId,
                    fight,
                    logByDate, logByAttacker, logByAttacked,
                    getOrders(nextLine));
        }
    }
}
