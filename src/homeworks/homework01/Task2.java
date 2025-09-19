package homeworks.homework01;

import java.util.Random;

public class Task2 {
    public static void main(String[] args){

        Random random = new Random();
        int petya = random.nextInt( 3);
        int vasya = random.nextInt(  3);

        System.out.println("камень-0, ножницы-1, бумага-2");

        System.out.println("Петя выбрал:" + petya);
        System.out.println("Вася выбрал:" + vasya);

        if (petya == vasya){
            System.out.println("Ничья");
        }

        else if ((petya == 0 && vasya == 1)|| (petya ==1 && vasya == 2)||(petya == 2 && vasya == 0)){
            System.out.println("Выиграл Петя");
        }
        else{
            System.out.println("Выиграл Вася");
        }

    }
}
