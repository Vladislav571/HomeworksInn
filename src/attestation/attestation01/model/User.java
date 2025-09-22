package attestation.attestation01.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private final String id;
    private final LocalDateTime dateTime;
    private final String login;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String middleName;
    private Integer age;
    private boolean isWorker = false;

    public User(
            String id,
            LocalDateTime dateTime,
            String login,
            String password,
            String firstName,
            String lastName,
            String middleName,
            Integer age,
            boolean isWorker
    ) throws IllegalArgumentException {
        validate(id);
        this.id = id;
        this.dateTime = dateTime != null ? dateTime : LocalDateTime.now();
        this.login = validateLogin(login);
        this.password = validatePassword(password);
        this.firstName = validateString(firstName, true);
        this.lastName = validateString(lastName, true);
        this.middleName = validateString(middleName, false);
        if (age != null && (age <= 0 || age > 150)) throw new IllegalArgumentException("Возраст некорректен");
        this.age = age;
        this.isWorker = isWorker;
    }


    // Методы для проверок данных
    private static void validate(String value) {
        if (value == null || value.trim().isEmpty()) throw new IllegalArgumentException("ID обязателен");
    }

    private static String validateLogin(String login) {
        if (!login.matches("[A-Za-z0-9_]{1,19}")) throw new IllegalArgumentException("Логин неверен");
        return login;
    }

    private static String validatePassword(String password) {
        if (!password.matches("(?=.*\\D)(?=.*\\d)[A-Za-z0-9_]{1,19}"))
            throw new IllegalArgumentException("Пароль неверен");
        return password;
    }

    private static String validateString(String string, boolean required) {
        if ((string == null || string.trim().isEmpty()) && required)
            throw new IllegalArgumentException("Фамилия и Имя обязательны");
        else if (string != null && !string.matches("[А-ЯЁа-яё]+"))
            throw new IllegalArgumentException("Строка должна содержать только русские буквы");
        return string;
    }

    public String getId() {
        return id;
    }

    public boolean isWorker() {
        return isWorker;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setIsWorker(boolean workerStatus) {
        this.isWorker = workerStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isWorker == user.isWorker && Objects.equals(id, user.id) && Objects.equals(dateTime, user.dateTime) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(middleName, user.middleName) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, login, password, firstName, lastName, middleName, age, isWorker);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", dateTime=" + dateTime +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", age=" + age +
                ", isWorker=" + isWorker +
                '}';
    }
}
