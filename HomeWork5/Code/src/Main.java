import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> getOrders(String line) {
        String[] strings = line.trim().split(" ");
        return new ArrayList<>(Arrays.asList(strings));
    }

    public static void makeChoice(ArrayList<String> orders, Demon demon) {
        int type = Integer.parseInt(orders.remove(0));
        switch (type) {
            case 1:
                demon.makeSoldier(orders);
                break;
            case 2:
                demon.formSoldier(orders);
                break;
            case 3:
                demon.copyTeam(orders);
                break;
            case 4:
                demon.screenTeam(orders);
                break;
            case 5:
                demon.mergeId2Id(orders);
                break;
            case 6:
                demon.teamAddStr(orders);
                break;
            case 7:
                demon.teamInterceptStr(orders);
                break;
            case 8:
                demon.getTeamSize(orders);
                break;
            case 9:
                demon.getTeamSizeWithStr(orders);
                break;
            case 10:
                demon.getUnformedSoldierNum();
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Demon demon = new Demon();
        int num = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < num; i++) {
            String nextLine = scanner.nextLine();
            makeChoice(getOrders(nextLine), demon);
        }
    }
}
