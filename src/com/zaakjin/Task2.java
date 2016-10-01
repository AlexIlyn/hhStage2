package com.zaakjin;

import com.zaakjin.infiniteSequence.InfiniteSequence;
import com.zaakjin.util.ConsoleReader;

import java.math.BigInteger;

public class Task2 {

    public static void main(String[] args) {
        ConsoleReader consoleReader = new ConsoleReader();
        System.out.println("Введите список чисел индекс которых вы хотите найти в бесконечной последовательности:");
        for (BigInteger j : consoleReader.readListOfBigIntegers()) {
            System.out.printf("Индекс числа %s в бесконечной последовательности: %s \n", j, InfiniteSequence.calculatePosition(j));
        }
    }


}
