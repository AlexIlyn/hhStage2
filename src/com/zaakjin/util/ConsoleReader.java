package com.zaakjin.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleReader {
    Scanner scanner;
    String[] string;

    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    public Integer readInt(int min, int max) {
        Integer i = null;
        string = scanner.nextLine().split(" ");
        if (!(string.length > 1)) {
            try {
                i = Integer.parseInt(string[0]);
                if (i < min || i > max) {
                    System.err.printf("[ERROR]: Введенное не верное значение. Введенное число должно быть больше %s и меньше %s.\n", min, max);
                    return null;
                }
            } catch (NumberFormatException e) {
                System.err.println("[ERROR]: Неверный формат введенной строки.");
            }
        } else {
            System.err.println("[ERROR]: Неверный формат введенной строки.");
        }
        return i;
    }

    public Integer readInt() {
        return readInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public Integer[] readIntArray(int length, int min, int max) {
        Integer[] integerArray = new Integer[length];
        string = scanner.nextLine().split(" ");
        if (string.length == length) {
            for (int i = 0; i < string.length; i++) {
                try {
                    integerArray[i] = Integer.parseInt(string[i]);
                    if (integerArray[i] < min || integerArray[i] > max) {
                        System.err.printf("[ERROR]: Введенное не верное значение. Введенное число должно быть больше %s и меньше %s.\n", min, max);
                        return null;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("[ERROR]: Неверный формат введенной строки.");
                    return null;
                }
            }
        } else {
            System.err.println("[ERROR]: Неверный формат введенной строки.");
        }
        return integerArray;
    }

    public Integer[] readIntArray(int length) {
        return readIntArray(length, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public ArrayList<BigInteger> readListOfIntegersOLD() {
        ArrayList<BigInteger> integerArrayList = new ArrayList<>();
        String temp;
        while (scanner.hasNextLine()) {
            temp = scanner.next();

            if (temp.length() < 50) {
                integerArrayList.add(new BigInteger(temp));
            } else {
                try {
                    throw new NumberFormatException("[ERROR]: Вы ввели число длиннее 50 символов.");
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
                    return null;
                }
            }
        }
        return integerArrayList;
    }

    public ArrayList<BigInteger> readListOfBigIntegers() {
        ArrayList<BigInteger> integerArrayList = new ArrayList<>();
        String temp;
        while (scanner.hasNextLine()) {
            temp = scanner.nextLine();
            if (temp.isEmpty()) break;
            if (temp.length() < 50) {
                try {
                    integerArrayList.add(new BigInteger(temp));
                } catch (NumberFormatException e) {
                    System.err.println("[ERROR]: Неверный формат введенной строки.");
                    return null;
                }
            } else {
                try {
                    throw new NumberFormatException("[ERROR]: Вы ввели число длиннее 50 символов.");
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
                    return null;
                }
            }
        }
        return integerArrayList;
    }
}
