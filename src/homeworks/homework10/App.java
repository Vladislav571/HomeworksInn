package homeworks.homework10;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {


        int[] array = {10, 7, 2, 4};

        int[] result = Sequence.filter(array, (number -> sumDigits(number) % 2 == 0));

        System.out.println(Arrays.toString(result));
    }
    private static int sumDigits(int num) {
        int sum = 0;
        while(num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
