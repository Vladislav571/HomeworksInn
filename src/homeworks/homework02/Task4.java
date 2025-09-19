package homeworks.homework02;

import java.util.Arrays;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        String wages[][] =
                {
                        {" - - - - - - - - - - "},
                        {" - - - - - - - - - - "},
                        {" - - - - - - - - - - "},
                        {" - - - - - - - - - - "},
                        {" - - - - - - - - - - "},
                        {" - - - - - - - - - - "},
                        {" - - - - - - - - - - "},
                        {" - - - - - - - - - - "},
                        {" - - - - - - - - - - "},
                        {" - - - - - - - - - - "},


                };

        Arrays.stream(wages).map(Arrays::toString).forEach(System.out::println);
    }
}
