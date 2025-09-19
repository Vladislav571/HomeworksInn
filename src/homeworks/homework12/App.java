package homeworks.homework12;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите данные (ФИО, Дату рождения, Номер телефона, Пол, Возраст):\n");

        while(true) {
            try {
                String input = scanner.nextLine().trim();

                // Разбиваем строку на массив строк
                String[] fields = input.split("\\s+"); // Пробелы используются как разделители

                // Создаем экземпляр Person
                Person p = new Person(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);

                writeToFile(p); // Записываем в файл
                break; // Завершаем цикл ввода, если успешно обработали запись

            } catch(Exception e) {
                System.err.println("Ошибка обработки данных: " + e.getMessage()); // Сообщаем пользователю об ошибке
            }
        }
    }

    // Метод записи данных в файл
    private static void writeToFile(Person person) throws IOException {
        File file = new File(person.getFileName());

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file, true)); // Открываем файл в режиме добавления
            writer.write(person.toString()); // Записываем данные в файл
            writer.newLine(); // Добавляем новую строку
        } finally {
            if(writer != null) {
                writer.close(); // Обязательно закрываем поток
            }
        }
    }
}
