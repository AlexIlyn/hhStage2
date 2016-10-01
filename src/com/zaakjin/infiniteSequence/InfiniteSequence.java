package com.zaakjin.infiniteSequence;

import java.math.BigInteger;
import java.util.ArrayList;

public class InfiniteSequence {

    //Метод раскладывает введенное число input на минимальные числа идущие подряд и возвращает ArrayList с этими числами
    public static ArrayList<BigInteger> decompositionBigInt(BigInteger input) {
        final String inStr = input.toString();
        ArrayList<BigInteger> numbers = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        //пороверяем можно ли разложить input на числа короче чем input
        for (int curLength = 1; curLength < inStr.length(); curLength++) {
            for (int curShift = 0; curShift < curLength && curShift + curLength <= inStr.length(); curShift++) {
                BigInteger i = new BigInteger(inStr.substring(curShift, curShift + curLength));
                if (curShift > 0) {
                    numbers.add(i.subtract(BigInteger.ONE));
                    temp.append(i.subtract(BigInteger.ONE));
                }
                while (temp.length() < inStr.length()) {
                    temp.append(i);
                    numbers.add(i);
                    i = i.add(BigInteger.ONE);
                }
                if (temp.indexOf(inStr) > -1) return numbers;
                numbers.clear();
                temp.setLength(0);
            }
        }
        //проверям возможно ли разложить input на числа равные по длинне input
        int i = inStr.length() - 1;
        BigInteger currNumber = new BigInteger(inStr.substring(i) + new String(new char[i]).replace("\0", "0"));
        BigInteger nextNumber;
        BigInteger someNumber = new BigInteger("0");
        for (; i > 1; i--) {
            nextNumber = new BigInteger(inStr.substring(i - 1) + new String(new char[i - 1]).replace("\0", "0"));
            if (currNumber.toString().length() < inStr.length()) {
                currNumber = nextNumber;
                if (currNumber.toString().length() < inStr.length()) {
                    continue;
                }
                i--;
            }
            someNumber = new BigInteger(inStr.substring(0, i));
            if (!someNumber.toString().matches("9+")) {
                someNumber = someNumber.add(BigInteger.ONE);
            } else {
                someNumber = BigInteger.ZERO;
            }
            if (nextNumber.compareTo(currNumber.add(someNumber)) < 0) {
                currNumber = nextNumber;
            }
        }
        currNumber = currNumber.add(someNumber);
        if (currNumber.toString().length() == inStr.length()) {
            numbers.add(currNumber.subtract(BigInteger.ONE));
            numbers.add(currNumber);
        } else {
            //если не получилось разложить input то возвращаем input
            numbers.add(input);
        }
        return numbers;
    }


    //метод получает на вход число input, раскладывает его с помощью метода decompositionBigInt и возвращает позицию числа input в бесконечной последовательности
    public static BigInteger calculatePosition(BigInteger input) {
        ArrayList<BigInteger> decomposedValue = decompositionBigInt(input);
        BigInteger position = decomposedValue.get(0).multiply(BigInteger.valueOf(decomposedValue.get(0).toString().length()));
        for (int i = 1; i <= decomposedValue.get(0).toString().length() - 1; i++) {
            position = position.subtract(BigInteger.valueOf(new Double(Math.pow(10, i)).longValue()));
        }
        if (!input.equals(decomposedValue.get(0))) {
            StringBuilder stringBuilder = new StringBuilder();
            decomposedValue.forEach(stringBuilder::append);
            position = position.add(BigInteger.valueOf(stringBuilder.indexOf(input.toString())));
        }
        return position;
    }
}
