package ru.orel.java.homework09.repositories;

import lombok.*;
import ru.orel.java.homework09.car.Car;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor

    public class CarRepositoryFileImpl implements CarRepository {

        private final File filePath;

        @Override
        public void create(Car car) throws IOException {
            try (BufferedWriter writer = Files.newBufferedWriter(filePath.toPath(), StandardOpenOption.APPEND)) {
                writer.write(car.toString());
                writer.newLine();
            }
        }

        @Override
        public Car findById(String id) throws IOException {
            try (BufferedReader reader = Files.newBufferedReader(filePath.toPath())) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 0 && parts[0].equals(id)) {
                        return parseFromString(line);
                    }
                }
            }
            return null;
        }

        @Override
        public List<Car> findAll() throws IOException {
            List<Car> cars = new ArrayList<>();
            try (BufferedReader reader = Files.newBufferedReader(filePath.toPath())) {
                String line;
                while ((line = reader.readLine()) != null) {
                    cars.add(parseFromString(line));
                }
            }
            return cars;
        }

        @Override
        public void update(Car car) throws IOException {
            List<String> lines = readAllLines();
            List<String> updatedLines = new ArrayList<>();
            boolean updated = false;
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].trim().equalsIgnoreCase(car.getBrand()) &&
                        parts[1].trim().equalsIgnoreCase(car.getModel())) {
                    updatedLines.add(car.toString());
                    updated = true;
                } else {
                    updatedLines.add(line);
                }
            }
            if (!updated) {
                throw new IllegalArgumentException("Автомобиль не найден для обновления: "
                        + "brand=" + car.getBrand() + ", model=" + car.getModel());
            }
            writeToFile(updatedLines);
        }

        @Override
        public void deleteById(String id) throws IOException {
            List<String> lines = readAllLines();
            List<String> filteredLines = new ArrayList<>();
            for (String line : lines) {
                String[] parts = line.split(",");
                if (!(parts.length > 0 && parts[0].equals(id))) {
                    filteredLines.add(line);
                }
            }
            writeToFile(filteredLines);
        }

        @Override
        public void deleteAll() throws IOException {
            try (BufferedWriter writer = Files.newBufferedWriter(filePath.toPath())) {
                writer.write("");
            }
        }

        private List<String> readAllLines() throws IOException {
            return Files.readAllLines(filePath.toPath());
        }

        private void writeToFile(List<String> data) throws IOException {
            try (BufferedWriter writer = Files.newBufferedWriter(filePath.toPath())) {
                for (String str : data) {
                    writer.write(str);
                    writer.newLine();
                }
            }
        }

    private Car parseFromString(String line) {
        String[] parts = line.split(",");
        return new Car(
                parts[0].trim(),              // brand
                parts[1].trim(),              // model
                Integer.parseInt(parts[2].trim()), // yearOfRelease
                Integer.parseInt(parts[3].trim()), // power
                Integer.parseInt(parts[4].trim()), // boost
                Integer.parseInt(parts[5].trim()), // pendant
                Integer.parseInt(parts[6].trim()));// durability
    }
    }
