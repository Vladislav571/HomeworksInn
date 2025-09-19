package homeworks.homework12;

import homeworks.homework12.exception.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Person {

    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final LocalDate birthDay;
    private final long phoneNumber;
    private final char gender;
    private final Integer age;

    public Person(String... data) throws Exception {
        if(data.length != 6) { // проверяем длину входящего массива
            throw new InvalidInputDataException("Количество полей не соответствует требованиям.");
        }

        // Проверяем каждое значение и присваиваем полю соответствующие переменные
        this.firstName = validateAndGetString(data[0], "Фамилия");
        this.lastName = validateAndGetString(data[1], "Имя");
        this.middleName = validateAndGetString(data[2], "Отчество");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            this.birthDay = LocalDate.parse(data[3], formatter); // проверка формата даты
        } catch(DateTimeParseException ex) {
            throw new InvalidBirthDateException("Некорректный формат даты рождения: " + data[3]);
        }

        try {
            this.phoneNumber = Long.parseLong(data[4]); // проверка номера телефона
        } catch(NumberFormatException ex) {
            throw new PhoneNumberFormatException("Некорректный номер телефона: " + data[4]);
        }

        // Проверка пола
        if(!data[5].equalsIgnoreCase("f") && !data[5].equalsIgnoreCase("m")) {
            throw new GenderException("Пол указан неправильно. Должен быть 'f' или 'm'.");
        }
        this.gender = data[5].charAt(0);

        // Возраст не нужен при создании экземпляра, поскольку вычисляется автоматически
        this.age = calculateAge(birthDay);
    }

    // Метод расчета возраста
    private int calculateAge(LocalDate birthDay) {
        return Period.between(birthDay, LocalDate.now()).getYears(); // Используем Java Time API для подсчета возраста
    }

    // Методы проверки корректности вводимых значений
    private String validateAndGetString(String value, String fieldName) throws EmptyFieldException {
        if(value.isEmpty()) {
            throw new EmptyFieldException(fieldName + " не может быть пустым.");
        }
        return value.trim();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + middleName + ", Дата Рождения: " +
                birthDay.toString() + ", Телефон: " + phoneNumber + ", Пол: " + gender + ", Возраст: " + age;
    }

    // Получение имени файла (фамилия)
    public String getFileName() {
        return firstName + ".txt";
    }

}
