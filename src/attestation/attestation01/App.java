package attestation.attestation01;

import attestation.attestation01.model.User;
import attestation.attestation01.repositories.UsersRepository;
import attestation.attestation01.repositories.UsersRepositoryFileImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class App {

    public static void main(String[] args) {
        UsersRepository repository = new UsersRepositoryFileImpl();

        // Создание нового пользователя
        User newUser = new User(UUID.randomUUID().toString(), LocalDateTime.now(), "test_user",
                "52fhr", "Иванов", "Иван", "Иванович", 30, true);
        repository.create(newUser);

        // Поиск пользователя по ID
        User foundUser = repository.findById(newUser.getId());
        System.out.println("Найденный пользователь: " + foundUser.toString());

        // Обновление пользователя
        foundUser.setIsWorker(false);
        repository.update(foundUser);

        // Повторный поиск после обновления
        foundUser = repository.findById(newUser.getId());
        System.out.println("Обновленный пользователь: " + foundUser.toString());

        // Получение всех пользователей
        List<User> allUsers = repository.findAll();
        System.out.println("Все пользователи: ");
        for (User user : allUsers) {
            System.out.println(user.toString());
        }

        // Удаление пользователя
        repository.deleteById(newUser.getId());

        // Попытка найти удаленного пользователя
        try {
            repository.findById(newUser.getId());
        } catch (RuntimeException e) {
            System.out.println("Удаленный пользователь не найден: " + e.getMessage());
        }

        // Полная очистка базы данных
        repository.deleteAll();
        System.out.println("База очищена!");
    }
}
