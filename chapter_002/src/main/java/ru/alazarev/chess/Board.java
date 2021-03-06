package ru.alazarev.chess;

import ru.alazarev.chess.exception.FigureNotFoundException;
import ru.alazarev.chess.exception.ImposibleMoveException;
import ru.alazarev.chess.exception.OccupiedWayException;
import ru.alazarev.chess.figures.Cell;
import ru.alazarev.chess.figures.Figure;

public class Board {
    private Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean result = false;
        int index = this.findBy(source);
        if (this.findBy(source) == -1) {
            throw new FigureNotFoundException("Figure not found");
        }
        Cell[] steps = this.figures[index].way(source, dest);
        if (steps.length == 0 || !(steps[steps.length - 1].equals(dest))) {
            throw new ImposibleMoveException("Impossible move");
        }
        if (!this.figures[index].getClass().toString().contains("Knight")) {
            for (Cell cell : steps) {
                if (findBy(cell) != -1) {
                    throw new OccupiedWayException("Figure on way " + cell);
                }
            }
        } else if (findBy(dest) != -1) {
            throw new OccupiedWayException("Figure on way " + dest);
        }
        this.figures[index] = this.figures[index].copy(dest);
        result = true;
        return result;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position.equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }
}
