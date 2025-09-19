package homeworks.homework03;

public class Shop {
    private String shop; // Магазин
    private String city; // Город, в котором куплен телевизор

    public Shop(String shop, String city) {
        this.shop = shop;
        this.city = city;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shop='" + shop + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
