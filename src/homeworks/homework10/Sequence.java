package homeworks.homework10;

import java.util.Arrays;

public class Sequence {

    public static int[] filter(int[] array, ByCondition condition){
        int[] result = new int[]{};
        int counter = 0;
        for (int element : array){
            if (condition.isOk(element)){
                result = Arrays.copyOf(result, result.length +1);
                result[counter] = element;
                counter ++;
            }
        }
        return result;
    }
}
