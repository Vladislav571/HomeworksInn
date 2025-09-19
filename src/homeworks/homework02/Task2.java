package homeworks.homework02;

public class Task2 {
    public static void main(String[] args) {

        int first_number = 25;
        int second_number = 5;
        double average_of_two = ((first_number + second_number) / 2);

        System.out.println("Сумма первого и второго числа: " + (first_number + second_number));
        System.out.println("Разница первого и второго числа: " + (first_number - second_number));
        System.out.println("Произведение из двух целых чисел: " + (first_number * second_number));
        System.out.println("Среднее из двух целых чисел: " + average_of_two);
        System.out.println("Максимальное целое число: " + Math.max(first_number, second_number));
        System.out.println("Минимальное целое число: " + Math.min(first_number, second_number));
        System.out.println("Расстояние между числами: " + (Math.abs(first_number - second_number)));
    }
}

