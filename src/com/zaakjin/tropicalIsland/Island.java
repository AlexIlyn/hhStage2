package com.zaakjin.tropicalIsland;

/**
 * Created by goldf on 30.09.2016.
 */
public class Island {
    Cell[][] island;
    int holdVolume;

    //класс острова
    public Island(Integer[][] inIsland) {
        this.island = new Cell[inIsland.length][inIsland[0].length];
        //заполняем остров ячейками
        for (int i = 0; i < inIsland.length; i++) {
            for (int j = 0; j < inIsland[i].length; j++) {
                island[i][j] = new Cell(inIsland[i][j]);
            }
        }
        //задаем ячейкам ссылки на соседей
        for (int i = 0; i < inIsland.length; i++) {
            for (int j = 0; j < inIsland[i].length; j++) {
                if (j != 0) island[i][j].neighbors[0] = island[i][j - 1];
                if (i != 0) island[i][j].neighbors[1] = island[i - 1][j];
                if (j != inIsland[i].length - 1) island[i][j].neighbors[2] = island[i][j + 1];
                if (i != inIsland.length - 1) island[i][j].neighbors[3] = island[i + 1][j];
            }
        }
        //получаем максимальные уровни воды в ячейка проверяя отсров в четырех направлениях
        for (int i = 0; i < inIsland.length; i++) {
            for (int j = 0; j < inIsland[i].length; j++) {
                island[i][j].checkNeighbors(false, false);
            }
            for (int j = inIsland[i].length - 1; j >= 0; j--) {
                island[i][j].checkNeighbors(true, false);
            }
        }
        for (int i = inIsland.length - 1; i >= 0; i--) {
            for (int j = inIsland[i].length - 1; j >= 0; j--) {
                island[i][j].checkNeighbors(true, true);
            }
            for (int j = 0; j < inIsland[i].length; j++) {
                island[i][j].checkNeighbors(false, true);
            }
        }
    }

    //подсчитываем уровень воды который задерживается на острове
    public int calculateHoldVolume() {
        holdVolume = 0;
        for (int i = 0; i < this.island.length; i++) {
            for (int j = 0; j < this.island[i].length; j++) {
                holdVolume += (this.island[i][j].maxWaterLevel - this.island[i][j].height);
            }
        }
        return holdVolume;
    }
}
