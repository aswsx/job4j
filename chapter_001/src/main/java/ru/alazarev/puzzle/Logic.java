package ru.alazarev.puzzle;

import ru.alazarev.puzzle.firuges.Cell;
import ru.alazarev.puzzle.firuges.Figure;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final int size;
    private final Figure[] figures;
    private int index = 0;

    public Logic(int size) {
        this.size = size;
        this.figures = new Figure[size * size];
    }

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            if (this.isFree(steps)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        }
        return rst;
    }

    public boolean isFree(Cell... cells) {
        boolean result = cells.length > 0;
        for (Cell cell : cells) {
            if (this.findBy(cell) != -1) {
                result = false;
                break;
            }
        }
        return result;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    public boolean isWin() {
        int[][] table = this.convert();
        boolean result = false;
        int rowCount = 1;
        int columnCount = 1;
        int countOfBubbles = 5;
        for (int row = 1; row < table.length; row++) {
            for (int column = 1; column < table.length; column++) {
                if (table[row][column] == 1 && table[row][column] == table[row][column - 1]) {
                    columnCount++;
                } else if (table[row][column] == 1 && table[row][column] == table[row - 1][column]) {
                    rowCount++;
                }
            }
            if (columnCount == countOfBubbles || rowCount == countOfBubbles) {
                result = true;
                break;
            }
        }
        return result;
    }

    public int[][] convert() {
        int[][] table = new int[this.size][this.size];
        for (int row = 0; row != table.length; row++) {
            for (int cell = 0; cell != table.length; cell++) {
                int position = this.findBy(new Cell(row, cell));
                if (position != -1 && this.figures[position].movable()) {
                    table[row][cell] = 1;
                }
            }
        }
        return table;
    }
}
