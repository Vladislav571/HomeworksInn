package attestation01;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.orel.java.attestation01.model.User;
import ru.orel.java.attestation01.repositories.UsersRepository;
import ru.orel.java.attestation01.repositories.UsersRepositoryFileImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private UsersRepository repository;

    @BeforeEach
    void setup() {
        repository = new UsersRepositoryFileImpl();
    }

    @AfterEach
    void cleanup() {
        repository.deleteAll();
    }

    //ПОЗИТИВНЫЙ ТЕСТ №1: создание и поиск пользователя
    @Test
    void testCreateAndFindById() {
        User user = new User(UUID.randomUUID().toString(), LocalDateTime.now(), "test_login",
                "352pass", "Тестов", "Игнат", "Тестович", 1, true);
        repository.create(user);
        User retrievedUser = repository.findById(user.getId());
        assertNotNull(retrievedUser);
        assertEquals(user, retrievedUser);
    }

    // ОТРИЦАТЕЛЬНЫЙ ТЕСТ №1: попытка создать пользователя с пустым логином
    @Test
    void testCreateWithInvalidLogin() {
        assertThrows(IllegalArgumentException.class, () -> {
            User invalidUser = new User(UUID.randomUUID().toString(), LocalDateTime.now(), "",
                    "352password", "Некорректный", "Некорректный", "", 30, true);
            repository.create(invalidUser);
        });
    }

    //ПОЗИТИВНЫЙ ТЕСТ №2: обновление пользователя
    @Test
    void testUpdateUser() {
        User user = new User(UUID.randomUUID().toString(), LocalDateTime.now(), "update_test",
                "352pass", "Новиков", "Николай", "Николаевич", 35, true);
        repository.create(user);
        user.setIsWorker(false);
        repository.update(user);
        User updatedUser = repository.findById(user.getId());
        assertFalse(updatedUser.isWorker());
    }

    // ОТРИЦАТЕЛЬНЫЙ ТЕСТ №2: попытка удалить несуществующего пользователя
    @Test
    void testDeleteNonExistingUser() {
        assertThrows(RuntimeException.class, () -> {
            repository.deleteById("non-existing-id");
        });
    }

    // ПОЗИТИВНЫЙ ТЕСТ №3: получение всех пользователей
    @Test
    void testGetAllUsers() {
        User user1 = new User(UUID.randomUUID().toString(), LocalDateTime.now(), "first_user",
                "pass1", "Иванов", "Иван", "Иванович", 30, true);
        User user2 = new User(UUID.randomUUID().toString(), LocalDateTime.now(), "second_user",
                "pass2", "Петрова", "Анна", "Александровна", 25, false);
        repository.create(user1);
        repository.create(user2);
        List<User> users = repository.findAll();
        assertEquals(2, users.size());
    }

    // ОТРИЦАТЕЛЬНЫЙ ТЕСТ №3: попытка обновить несуществующего пользователя
    @Test
    void testUpdateNonExistingUser() {
        User nonExistentUser = new User("non-existing-uuid", LocalDateTime.now(), "fake_user",
                "pass3", "Петров", "Петро", "Петрович", 30, true);
        assertThrows(RuntimeException.class, () -> {
            repository.update(nonExistentUser);
        });
    }
}
