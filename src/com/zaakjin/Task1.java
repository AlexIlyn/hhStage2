package com.zaakjin;

import com.zaakjin.tropicalIsland.Island;
import com.zaakjin.util.ConsoleReader;

public class Task1 {
    public static void main(String[] args) {
        int numOfIslands;
        Integer[] size;
        Integer[][] currentIslandHeightMap;
        Island[] islands;
        ConsoleReader consoleReader = new ConsoleReader();

        System.out.println("Ввведите количество островов:");
        numOfIslands = consoleReader.readInt();
        islands = new Island[numOfIslands];

        for (int i = 0; i < numOfIslands; i++) {
            System.out.println("Ввведите размер острова:");
            size = consoleReader.readIntArray(2, 1, 50);
            try {
                currentIslandHeightMap = new Integer[size[0]][size[1]];
            } catch (NullPointerException e) {
                e.printStackTrace();
                return;
            }
            System.out.println("Введите матрицу высот острова:");
            for (int j = 0; j < size[0]; j++) {
                currentIslandHeightMap[j] = consoleReader.readIntArray(size[1], 1, 1000);
            }
            islands[i] = new Island(currentIslandHeightMap);
        }
        for (int i = 0; i < numOfIslands; i++) {
            System.out.printf("Объем воды которая задержится на острове №%s: %s едениц(а) \n", i+1, islands[i].calculateHoldVolume());
        }


    }
}
