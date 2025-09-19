package homeworks.homework02;

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Исходная строка: ");
        String inputString = scanner.next();

        int retry = 8;
        System.out.println("Количество повторений: " + retry);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < retry; i ++){
            stringBuilder.append(inputString);
        }
        System.out.println("Повторения = " + inputString.repeat(retry));
        System.out.println("StringBuilder = " + stringBuilder);
    }
}
