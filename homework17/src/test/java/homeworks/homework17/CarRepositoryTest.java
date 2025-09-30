package homeworks.homework17;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.orel.java.homework09.car.Car;
import ru.orel.java.homework09.repositories.CarRepository;
import ru.orel.java.homework09.repositories.CarRepositoryFileImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarRepositoryTest {

    private CarRepository repository;
    private File testFile;

    @BeforeEach
    void setup() throws Exception {
        testFile = new File("src/test/resources/cars_test.txt");
        repository = new CarRepositoryFileImpl(testFile);
    }

    @AfterEach
    void cleanup() throws Exception {
        testFile.delete();
    }

    @Test
    void testCreateAndFindById() throws IOException {
        Car car = new Car("TestBrand", "TestModel", 2023, 250, 50, 100, 88);
        repository.create(car);

        Car foundCar = repository.findById("TestBrand");
        assertNotNull(foundCar);
        assertEquals(car, foundCar);
    }

    @Test
    void testFindAll() throws IOException {
        Car car1 = new Car("TestBrand1", "TestModel1", 2021, 200, 40, 90, 77);
        Car car2 = new Car("TestBrand2", "TestModel2",2022, 300, 60, 110, 88);
        repository.create(car1);
        repository.create(car2);

        List<Car> allCars = repository.findAll();
        assertEquals(2, allCars.size());
    }

    @Test
    void testUpdate() throws IOException {
        Car initialCar = new Car("TestBrand", "TestModel", 2023, 250, 50, 100, 88);
        repository.create(initialCar);

        Car updatedCar = new Car("TestBrand", "TestModel", 2024, 300, 60, 110, 99);
        repository.update(updatedCar);

        Car retrievedCar = repository.findById("TestBrand");
        assertEquals(updatedCar, retrievedCar);
    }

    @Test
    void testDeleteById() throws IOException {
        Car car = new Car("TestBrand", "TestModel", 2023, 250, 50, 100, 88);
        repository.create(car);

        repository.deleteById("TestBrand");
        Car deletedCar = repository.findById("TestBrand");
        assertNull(deletedCar);
    }

    @Test
    void testDeleteAll() throws IOException {
        Car car1 = new Car("TestBrand1", "TestModel1", 2021, 200, 40, 90, 77);
        Car car2 = new Car("TestBrand2", "TestModel2", 2022, 300, 60, 110, 88);
        repository.create(car1);
        repository.create(car2);

        repository.deleteAll();
        List<Car> allCars = repository.findAll();
        assertTrue(allCars.isEmpty());
    }
}

