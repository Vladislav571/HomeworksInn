package homeworks.homework07;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> regularProducts = new ArrayList<>(); // Список обычных продуктов
        List<Product> discountedProducts = new ArrayList<>(); // Список акционных продуктов

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("END")) break;

            // Пытаемся прочитать продукт
            try {
                Product product = parseProduct(line);

                // Определяем, является ли продукт обычным или акционным
                if (product instanceof DiscountProduct) {
                    discountedProducts.add((DiscountProduct) product);
                } else {
                    regularProducts.add(product);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Выводим списки продуктов
        printProducts(regularProducts, discountedProducts, "Обычные продукты:", "Акционные продукты:");
    }

    // Парсер строки для создания продукта
    private static Product parseProduct(String data) {
        String[] parts = data.split("=");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Неверный формат строки: " + data);
        }

        String name = parts[0].trim();
        String priceStr = parts[1].trim();

        // Проверка имени продукта
        if (name.length() < 3 || name.matches("^\\d+$")) {
            throw new IllegalArgumentException("Недопустимое имя продукта! Название должно быть длиной минимум 3 символа и не содержать только цифры.");
        }

        // Парсим цену и скидку
        Double basePrice;
        Double discountPercent = null;

        // Отделяем цену от скидки, если есть акция
        if (priceStr.contains(",")) {
            String[] priceSplit = priceStr.split(",", 2);
            basePrice = Double.parseDouble(priceSplit[0].trim());
            discountPercent = Double.parseDouble(priceSplit[1].replace("%", "").trim());
        } else {
            basePrice = Double.parseDouble(priceStr);
        }

        // Проверка базовой цены
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Недопустимая стоимость продукта! Цена должна быть положительным числом.");
        }

        // Если есть скидка, рассчитываем конечную цену с учетом скидки
        if (discountPercent != null) {
            double actualPrice = basePrice * (1 - discountPercent / 100);
            return new DiscountProduct(name, actualPrice, discountPercent);
        } else {
            return new RegularProduct(name, basePrice);
        }
    }

    // Печать списков продуктов
    private static void printProducts(List<Product> regularProducts,List<Product> discountedProducts, String titleRegular, String titleDiscount) {
        System.out.println(titleRegular + ": " + extractNames(regularProducts));
        System.out.println(titleDiscount + ": " + extractNames(discountedProducts));
    }

    // Извлекаем только имена продуктов
    private static String extractNames(List<Product> products) {
        StringBuilder sb = new StringBuilder();
        for (Product product : products) {
            sb.append(product.getName()).append(", ");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
    }
}