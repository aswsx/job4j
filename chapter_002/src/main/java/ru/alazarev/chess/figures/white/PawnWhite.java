package ru.alazarev.chess.figures.white;

import ru.alazarev.chess.figures.Cell;
import ru.alazarev.chess.figures.FigureInterface;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnWhite implements FigureInterface {
    private final Cell position;

    public PawnWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        return new Cell[] { dest };
    }

    @Override
    public FigureInterface copy(Cell dest) {
        return new PawnWhite(dest);
    }
}
