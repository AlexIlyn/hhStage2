package com.zaakjin.tropicalIsland;

//Класс ячейка которая хранит в себе высоту height, ссылки на соседние ячейки neighbors и максимальный уровень задерживаемой воды воде maxWaterLevel.
public class Cell {
    int maxWaterLevel;
    Cell[] neighbors;
    int height;


    Cell(int height) {
        maxWaterLevel = 1000;
        this.height = height;
        neighbors = new Cell[4];
    }

    //Проверяем может ли вода стечь в соседние ячейки параметры reverseHorizontal и reverseVertical определяют с какой стороны проверять
    void checkNeighbors(boolean reverseHorizontal, boolean reverseVertical) {
        for (int i = 0; i < neighbors.length; i++) {
            if (reverseHorizontal ? (reverseVertical ? (i > 1) : (i == 1 || i == 2)) : (reverseVertical ? (i == 0 || i == 3) : (i < 2))) {
                //если соседняя ячейка пустая устанавливаем макси
                if (neighbors[i] == null) {
                    maxWaterLevel = height;
                    return;
                }
                //Если высота соседней ячейки меньше
                if (neighbors[i].height < height) {
                    //то если максимальный уровень воды соседней ячейки меньше
                    if (neighbors[i].maxWaterLevel < maxWaterLevel) {
                        //устанавливаем максимальный уровень воды равным высоте текущей ячейки
                        maxWaterLevel = height;
                    } else
                    //то если максимальный уровень воды соседней ячейки больше
                    if (neighbors[i].maxWaterLevel > maxWaterLevel) {
                        //устанавливаем максимальный уровень воды равным максимальному уровню воды соседней ячейки
                        maxWaterLevel = neighbors[i].maxWaterLevel;
                    }
                }
                //Если высота соседней ячейки больше или равна высоте текущей ячейки
                if (neighbors[i].height >= height) {
                    if (neighbors[i].maxWaterLevel < maxWaterLevel) {
                        maxWaterLevel = neighbors[i].maxWaterLevel;
                    }
                }
            }
        }
    }
}