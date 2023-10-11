import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    private static HashMap<Integer, Adventure> advs = new HashMap<>();
    private static ArrayList<String> fight = new ArrayList<>();
    private static HashMap<String, Integer> advNameToId = new HashMap<>();
    private static HashMap<String, ArrayList<String>> logByDate = new HashMap<>();
    private static HashMap<Integer, ArrayList<String>> logByAttacker = new HashMap<>();
    private static HashMap<Integer, ArrayList<String>> logByAttacked = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    private static HashMap<Integer, Consumer<ArrayList<String>>> actionsMap = new HashMap<>();

    public static void addAction() {
        actionsMap.put(1, Main::addAdventure);
        actionsMap.put(2, Main::addBottle);
        actionsMap.put(3, Main::removeBottle);
        actionsMap.put(4, Main::addEquipment);
        actionsMap.put(5, Main::removeEquipment);
        actionsMap.put(6, Main::increaseStar);
        actionsMap.put(7, Main::addFood);
        actionsMap.put(8, Main::removeFood);
        actionsMap.put(9, Main::takeEquipment);
        actionsMap.put(10, Main::takeBottle);
        actionsMap.put(11, Main::takeFood);
        actionsMap.put(12, Main::useBottle);
        actionsMap.put(13, Main::eatFood);
        actionsMap.put(14, Main::enterFightMode);
        actionsMap.put(15, Main::searchByDate);
        actionsMap.put(16, Main::searchByAttacker);
        actionsMap.put(17, Main::searchByAttacked);
    }

    public static void addAdventure(ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        String name = orders.get(1);
        advs.put(advId, new Adventure(advId, name));
        advNameToId.put(name, advId);
    }

    public static void addBottle(ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int botId = Integer.parseInt(orders.get(1));
        String name = orders.get(2);
        int capacity = Integer.parseInt(orders.get(3));
        advs.get(advId).addBottle(new Bottle(botId, name, capacity));
    }

    public static void removeBottle(ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int botId = Integer.parseInt(orders.get(1));
        advs.get(advId).removeBottle(botId).printIntFirst();
    }

    public static void addEquipment(ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int equipId = Integer.parseInt(orders.get(1));
        String name = orders.get(2);
        int star = Integer.parseInt(orders.get(3));
        Adventure adventure = advs.get(advId);
        adventure.addEquipment(new Equipment(equipId, name, star));
    }

    public static void removeEquipment(ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int equipId = Integer.parseInt(orders.get(1));
        advs.get(advId).removeEquipment(equipId).printIntFirst();
    }

    public static void increaseStar(ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int equipId = Integer.parseInt(orders.get(1));
        advs.get(advId).increaseStar(equipId).printStringFirst();
    }

    public static void addFood(ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int foodId = Integer.parseInt(orders.get(1));
        String name = orders.get(2);
        int energy = Integer.parseInt(orders.get(3));
        advs.get(advId).addFood(new Food(foodId, name, energy));
    }

    public static void removeFood(ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int foodId = Integer.parseInt(orders.get(1));
        advs.get(advId).removeFood(foodId).printIntFirst();
    }

    public static void takeEquipment(ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int equipId = Integer.parseInt(orders.get(1));
        advs.get(advId).takeEquipment(equipId);
    }

    public static void takeBottle(ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int botId = Integer.parseInt(orders.get(1));
        advs.get(advId).takeBottle(botId);
    }

    public static void takeFood(ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        int foodId = Integer.parseInt(orders.get(1));
        advs.get(advId).takeFood(foodId);
    }

    public static void useBottle(ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        String name = orders.get(1);
        advs.get(advId).useBottle(false, name);
    }

    public static void eatFood(ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        String name = orders.get(1);
        advs.get(advId).eatFood(name);
    }

    public static void enterFightMode(ArrayList<String> orders) {
        int m = Integer.parseInt(orders.get(0));
        int k = Integer.parseInt(orders.get(1));
        for (int i = 0; i < m; i++) {
            String name = orders.get(i + 2);
            fight.add(name);
        }
        System.out.println("Enter Fight Mode");
        for (int i = 0; i < k; i++) {
            String log = scanner.nextLine();
            parseFightLog(log);
        }
        fight.clear();
    }

    public static void writeDateLog(int type, String date,
                                    String userName, String botName,
                                    String attackerName, String attackedName, String equName) {
        if (type == 1) {
            //战斗日志类型是：某人使用药水
            if (logByDate.containsKey(date)) {
                logByDate.get(date).add(userName + " used " + botName);
            } else {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(userName + " used " + botName);
                logByDate.put(date, arrayList);
            }
        } else if (type == 2) {
            //战斗日志类型是：某人进行单个攻击
            if (logByDate.containsKey(date)) {
                logByDate.get(date).add(attackerName +
                        " attacked " + attackedName + " with " + equName);
            } else {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(attackerName + " attacked " + attackedName + " with " + equName);
                logByDate.put(date, arrayList);
            }
        } else if (type == 3) {
            //战斗日志类型是：某人进行AOE攻击
            if (logByDate.containsKey(date)) {
                logByDate.get(date).add(attackerName + " AOE-attacked with " + equName);
            } else {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(attackerName + " AOE-attacked with " + equName);
                logByDate.put(date, arrayList);
            }
        }
    }

    public static void writeAttack(HashMap<Integer, ArrayList<String>> log, int key,
                                   String date,
                                   String attackerName, String attackedName, String equName) {
        if (log.containsKey(key)) {
            log.get(key).add(date + " "
                    + attackerName + " attacked " + attackedName + " with " + equName);
        } else {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(date + " "
                    + attackerName + " attacked " + attackedName + " with " + equName);
            log.put(key, arrayList);
        }
    }

    public static void writeAttackAoe(HashMap<Integer, ArrayList<String>> log, int key,
                                      String date, String attackerName, String equName) {
        if (log.containsKey(key)) {
            log.get(key).add(date + " "
                    + attackerName + " AOE-attacked with " + equName);
        } else {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(date + " "
                    + attackerName + " AOE-attacked with " + equName);
            log.put(key, arrayList);
        }
    }

    public static void writeAttackerLog(int type, String date,
                                        String attackerName, String attackedName, String equName) {
        int attackerId = advNameToId.get(attackerName);
        if (type == 1) {
            //战斗类型是，某人进行单个攻击
            writeAttack(logByAttacker, attackerId, date, attackerName, attackedName, equName);
        } else if (type == 2) {
            //战斗类型是，某人进行AOE攻击
            writeAttackAoe(logByAttacker, attackerId, date, attackerName, equName);
        }
    }

    public static void writeAttackedLog(int type, String date,
                                        String attackerName, String attackedName, String equName) {
        if (type == 1) {
            //战斗类型是，某人进行单个攻击
            int attackedId = advNameToId.get(attackedName);
            writeAttack(logByAttacked, attackedId, date, attackerName, attackedName, equName);
        } else if (type == 2) {
            //战斗类型是，某人进行AOE攻击:
            for (String name : fight) {
                if (!name.equals(attackerName)) {
                    int attackedId = advNameToId.get(name);
                    writeAttackAoe(logByAttacked, attackedId, date, attackerName, equName);
                }
            }
        }
    }

    public static void parseFightLog(String log) {
        String[] strings = log.trim().split("-");
        if (strings[1].contains("@#")) {
            String[] strings1 = strings[1].split("@#");
            String date = strings[0].trim();
            String attackerName = strings1[0].trim();
            String equName = strings[2].trim();
            int attackerId = advNameToId.get(attackerName);
            boolean isSuccess = advs.get(attackerId).attackAoe(fight, advs, advNameToId, equName);
            if (isSuccess) {
                writeDateLog(3, date, null, null, attackerName, null, equName);
                writeAttackerLog(2, date, attackerName, null, equName);
                writeAttackedLog(2, date, attackerName, null, equName);
            }
        } else if (strings[1].contains("@")) {
            String[] strings1 = strings[1].split("@");
            String equName = strings[2].trim();
            String date = strings[0].trim();
            String attackedName = strings1[1].trim();
            String attackerName = strings1[0].trim();
            int attackerId = advNameToId.get(attackerName);
            boolean isSuccess = advs.get(attackerId).attack(fight, advs, advNameToId,
                    attackedName, equName);
            if (isSuccess) {
                writeDateLog(2, date, null, null, attackerName, attackedName, equName);
                writeAttackerLog(1, date, attackerName, attackedName, equName);
                writeAttackedLog(1, date, attackerName, attackedName, equName);
            }
        } else {
            String date = strings[0].trim();
            String userName = strings[1].trim();
            String botName = strings[2].trim();
            if (fight.contains(userName)) {
                int userId = advNameToId.get(userName);
                boolean isSuccess = advs.get(userId).useBottle(true, botName);
                if (isSuccess) {
                    writeDateLog(1, date, userName, botName, null, null, null);
                }
            } else {
                System.out.println("Fight log error");
            }
        }
    }

    public static void searchByDate(ArrayList<String> orders) {
        String date = orders.get(0);
        if (logByDate.containsKey(date)) {
            for (String log : logByDate.get(date)) {
                System.out.println(date + " " + log);
            }
        } else {
            System.out.println("No Matched Log");
        }
    }

    public static void searchByAttacker(ArrayList<String> orders) {
        int advId = Integer.parseInt(orders.get(0));
        if (logByAttacker.containsKey(advId)) {
            for (String log : logByAttacker.get(advId)) {
                System.out.println(log);
            }
        } else {
            System.out.println("No Matched Log");
        }
    }

    public static void searchByAttacked(ArrayList<String> orders) {
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

    public static void makeChoice(ArrayList<String> orders) {
        int type = Integer.parseInt(orders.remove(0));
        actionsMap.get(type).accept(orders);
    }

    public static void main(String[] args) {
        addAction();
        int n;
        n = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < n; i++) {
            String nextLine = scanner.nextLine();
            makeChoice(getOrders(nextLine));
        }
    }
}
