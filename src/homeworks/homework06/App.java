package homeworks.homework06;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {


//        Product product = new Product("Кофе", 120.0);
//        Product milk = new Product("Молоко", 80.0);
//        Person person = new Person("Игорь", 1000.0);
//
//        person.addProduct(product);
//        person.addProduct(milk);
//
//        System.out.println(person.toString().replace("[", "").replace("]", ""));

        Scanner scanner = new Scanner(System.in);

        String personsString = scanner.nextLine();
        String productsString = scanner.nextLine();

        String[] personStringArray = personsString.split(";");
        String[] productStringArray = productsString.split(";");

        Person[] persons = new Person[personStringArray.length];
        Product[] products = new Product[productStringArray.length];

        for (int i = 0; i < personStringArray.length; i++){
            Person p = new Person(personStringArray[i]);
            persons[i] = p;
        }


        while (true){
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("end"))
                break;

            String[] input = line.split(";");
            String personName = input[0];
            String productName = input[1];

            for (int i = 0; i < persons.length; i++){
                if (persons[i].getName().equals(personName)){
                    for (int j = 0; j < products.length; j++){
                        if (products[j].getName().equals(productName)){
                            persons[i].addProduct(products[j]);
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(persons));
    }

}
