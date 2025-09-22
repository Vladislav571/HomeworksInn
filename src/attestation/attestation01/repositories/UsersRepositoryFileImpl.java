package attestation.attestation01.repositories;

import attestation.attestation01.model.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryFileImpl implements UsersRepository{

    private static final String FILE_NAME = "C:\\Users\\Владислав\\HomeworksInnopolis\\users.txt";
    private List<User> users = new ArrayList<>();

    public UsersRepositoryFileImpl() {
        loadFromFile(); // Загружаем пользователей
    }

    @Override
    public void create(User user) {
        users.add(user);
        saveToFile();
    }

    @Override
    public synchronized User findById(String id) {
        for (User u : users) {
            if (id.equals(u.getId())) return u;
        }
        throw new RuntimeException("Пользователь с указанным идентификатором не найден.");
    }

    @Override
    public synchronized List<User> findAll() {
        return new ArrayList<>(users);
    }

    private void saveToFile() {
        try (Writer writer = Files.newBufferedWriter(Paths.get(FILE_NAME))) {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter('|'));
            for (User user : users) {
                printer.printRecord(mapUserToRecord(user));
            }
            printer.flush();
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    @Override
    public synchronized void update(User updatedUser) {
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (updatedUser.getId().equals(users.get(i).getId())) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            users.set(index, updatedUser);
            saveToFile();
        } else {
            throw new RuntimeException("Пользователь с указанным идентификатором не найден.");
        }
    }

    @Override
    public synchronized void deleteById(String id) {
        boolean deleted = users.removeIf(user -> user.getId().equals(id));
        if (!deleted) {
            throw new RuntimeException("Пользователь с указанным идентификатором не найден.");
        }
        saveToFile();
    }

    @Override
    public synchronized void deleteAll() {
        users.clear();
        saveToFile();
    }

    // Вспомогательные методы для чтения и сохранения данных в файл
    private void loadFromFile() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_NAME))) {
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter('|'));

            for (CSVRecord record : parser) {
                try {
                    User user = mapRecordToUser(record);
                    users.add(user);
                } catch (IllegalArgumentException e) {
                    System.err.println("Ошибка при чтении пользователя с такими данными:");
                    System.err.println("ID: " + record.get(0));
                    System.err.println("Имя пользователя: " + record.get(2));
                    System.err.println("Пароль: " + record.get(3));
                    throw e;
                }
            }
        } catch (IOException | IndexOutOfBoundsException ex) {
            System.err.println("Ошибка загрузки из файла: " + ex.getMessage());
        }
    }



    // Преобразуем строки в объект User
    private User mapRecordToUser(CSVRecord record) {
        return new User(
                record.get(0),                     // id
                LocalDateTime.parse(record.get(1)),// dateTime
                record.get(2),                     // login
                record.get(3),                     // password
                record.get(4),                     // firstName
                record.get(5),                     // lastName
                record.get(6),                     // middleName
                Integer.valueOf(record.get(7)),   // age
                Boolean.parseBoolean(record.get(8))// isWorker
        );
    }

    // Преобразуем объект User обратно в строку для записи в файл
    private Object[] mapUserToRecord(User user) {
        return new Object[]{
                user.getId(),
                user.getDateTime(),
                user.getLogin(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getAge(),
                user.isWorker()
        };
    }
}
