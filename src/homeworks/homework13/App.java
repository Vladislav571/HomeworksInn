package homeworks.homework13;

import homeworks.homework13.utils.ParseUtils;

public class App {

    public static void main(String[] args) {

        Integer result1 = ParseUtils.parseCount("100");
        Integer result2 = ParseUtils.validateCount("52");
        Double result3 = ParseUtils.parseNumber("32.5");

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
