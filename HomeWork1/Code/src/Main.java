package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int appleCount = 5;
        int bananaCount = 5;
        Seller seller = new Seller(appleCount, bananaCount);
        int initMoney = 20;
        Child child = new Child(initMoney);
        Scanner scanner = new Scanner(System.in);
        int opCount = Integer.parseInt(scanner.nextLine()); // 指令条数
        for (int i = 0; i < opCount; ++i) {
            String instr = scanner.nextLine();
            int beginIndex = 4;
            if (instr.startsWith("eat")) {
                child.eatOneFruit(instr.substring(beginIndex));
            } else {
                child.buyFromStore(instr.substring(beginIndex), seller);
            }
        }
        scanner.close(); // 关闭scanner
    }

}
