package homeworks.homework02;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Введите значение температуры по Фаренгейту: ");

        double fahrenheit  = input.nextDouble();
        double celsius = (fahrenheit - 32) * 5 / 9;

        System.out.println(fahrenheit + " градусов по Фаренгейту равна " + celsius + " по Цельсию");

    }
}
