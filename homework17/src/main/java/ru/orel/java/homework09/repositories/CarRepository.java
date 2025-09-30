package ru.orel.java.homework09.repositories;

import ru.orel.java.homework09.car.Car;

import java.io.IOException;
import java.util.List;

public interface CarRepository {

    void create(Car car) throws IOException;
    Car findById(String id) throws IOException;
    List<Car> findAll() throws IOException;
    void update(Car car) throws IOException;
    void deleteById(String id) throws IOException;
    void deleteAll()throws IOException;
}
