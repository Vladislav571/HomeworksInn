package homeworks.homework06;

import java.util.Arrays;
import java.util.Objects;

public class Person {
    private String name;
    private Double money;

    private Product[] products = new Product[0];

    private int arrayIndex = 0;

    public Person(String name, Double money){
        this.setMoney(money);
        this.setName(name);
    }

    public Person(String params){
        String[] paramArray = params.split("=");
        this.name = paramArray[0];
        this.money = Double.valueOf(paramArray[1].trim());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        if (money >= 0){
            this.money = money;
        }
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }
    public void addProduct(Product product){
        if (this.money >= product.getPrice()){
            products = Arrays.copyOf(products, products.length + 1);
            products[products.length - 1] = product;
            this.money = this.money - product.getPrice();
            arrayIndex++;
        } else {
            System.out.println(this.name + " не можеть себе позволить купить " + product.getName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(money, person.money) && Objects.deepEquals(products, person.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money, Arrays.hashCode(products));
    }

    @Override
    public String toString() {
        if (products.length == 0) {
            return name + " ничего не куплено!";
        }
        else {
            return name + " - " + Arrays.toString(products);
        }
    }
}
