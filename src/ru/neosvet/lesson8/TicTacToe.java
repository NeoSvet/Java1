package ru.neosvet.lesson8;

import ru.neosvet.lesson4.Coords;
import ru.neosvet.lesson4.Direction;
import ru.neosvet.lesson4.Steps;

import java.util.Random;

public class TicTacToe {
    enum Line {ROW, COLUMN, DIAGONAL_ONE, DIAGONAL_TWO}

    private final int SIZE = 5, LINE_FOR_WIN = 3;
    private final char DOT_EMPTY = ' ', DOT_NULL = 'n', DOT_FIRST = 'X', DOT_SECOND = 'O';

    private final char[][] map = new char[SIZE][SIZE];
    private final Random random = new Random();
    private String msg_finish;
    private boolean human_first, finish;

    public TicTacToe(boolean human_first) {
        this.human_first = human_first;
        finish = false;
        initMap();
        if(!human_first)
            getCoordsAiTurn();
    }

    private void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public char[][] getMap() {
        return map;
    }

    public boolean isFinish() {
        return finish;
    }

    public void goHumanTurn(Coords coords) {
        map[coords.getX()][coords.getY()] = human_first ? DOT_FIRST : DOT_SECOND;
        finish = checkEnd(coords, true);
    }

    public Coords getCoordsAiTurn() {
        Coords coords;
        do {
            coords = getCoordsForAiStep();
        } while (isBusyCell(coords));

        map[coords.getX()][coords.getY()] = human_first ? DOT_SECOND : DOT_FIRST;
        finish = checkEnd(coords, false);
        return coords;
    }

    public String getText(boolean forHuman) {
        char c;
        if (forHuman)
            c = human_first ? DOT_FIRST : DOT_SECOND;
        else
            c = human_first ? DOT_SECOND : DOT_FIRST;
        return String.valueOf(c);
    }

    private Coords getCoordsForAiStep() {
        Coords coords;

        char ai = human_first ? DOT_SECOND : DOT_FIRST;
        coords = getCoordsFromCheckChance(ai);
        if (coords.exists())
            return coords;

        char human = human_first ? DOT_FIRST : DOT_SECOND;
        coords = getCoordsFromCheckChance(human);
        if (coords.exists())
            return coords;

        return new Coords(random.nextInt(SIZE), random.nextInt(SIZE));
    }

    private Coords getCoordsFromCheckChance(char symbol) {
        Coords coords = new Coords();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (map[x][y] == symbol) {
                    coords = getCoordsFromCheckChance(x, y, symbol);
                    if (coords.exists())
                        return coords;
                }
            }
        }
        return coords;
    }

    private Coords getCoordsFromCheckChance(int x, int y, char symbol) {
        Coords result, coords = new Coords(x, y);
        result = getCoordsFromCheckChanceLine(symbol, coords, Line.ROW);
        if (result.exists())
            return result;
        result = getCoordsFromCheckChanceLine(symbol, coords, Line.COLUMN);
        if (result.exists())
            return result;
        result = getCoordsFromCheckChanceLine(symbol, coords, Line.DIAGONAL_ONE);
        if (result.exists())
            return result;
        return getCoordsFromCheckChanceLine(symbol, coords, Line.DIAGONAL_TWO);
    }

    private Coords getCoordsFromCheckChanceLine(char symbol, Coords source, Line line) {
        char cell;
        boolean firstSide = true;
        boolean hasEmpty = false;
        Coords result = new Coords();
        Coords coords = source.clone();
        int k = 1;

        Steps steps = getSteps(line);
        while (true) {
            coords.applySteps(steps);
            cell = getCell(coords);
            if (cell == symbol) {
                if (++k == LINE_FOR_WIN)
                    return result;
            } else if (firstSide && (cell != DOT_EMPTY || hasEmpty)) {
                firstSide = false;
                steps.invert();
                coords = source.clone();
            } else if (cell == DOT_EMPTY) {
                if (hasEmpty)
                    break;
                result = coords.clone();
                if (++k == LINE_FOR_WIN)
                    return result;
                hasEmpty = true;
            } else
                break;
        }
        return new Coords();
    }

    public boolean isBusyCell(Coords coords) {
        return map[coords.getX()][coords.getY()] != DOT_EMPTY;
    }

    private boolean checkEnd(Coords coords, boolean isHuman) {
        if (checkWinOnCell(coords)) {
            msg_finish = isHuman ? "УРА! Вы победили!"
                    : "Восстание близко! AI победил";
            return true;
        }

        if (isMapFull()) {
            msg_finish = "Ничья!";
            return true;
        }

        return false;
    }

    public String getMsgFinish() {
        return msg_finish;
    }

    private boolean isMapFull() {
        for (char[] chars : map) {
            for (char aChar : chars) {
                if (aChar == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWinOnCell(Coords coords) {
        if (checkLine(coords, Line.ROW))
            return true;
        if (checkLine(coords, Line.COLUMN))
            return true;
        if (checkLine(coords, Line.DIAGONAL_ONE))
            return true;
        return checkLine(coords, Line.DIAGONAL_TWO);
    }

    private boolean checkLine(Coords source, Line line) {
        char symbol = getCell(source);
        Coords coords = source.clone();
        boolean firstSide = true;
        int k = 1;

        Steps steps = getSteps(line);
        while (true) {
            coords.applySteps(steps);
            if (getCell(coords) == symbol) {
                if (++k == LINE_FOR_WIN)
                    return true;
            } else if (firstSide) {
                firstSide = false;
                steps.invert();
                coords = source.clone();
            } else
                return false;
        }
    }

    private Steps getSteps(Line line) {
        switch (line) {
            case ROW:
                return new Steps(Direction.UP, Direction.NONE);
            case COLUMN:
                return new Steps(Direction.NONE, Direction.UP);
            case DIAGONAL_ONE:
                return new Steps(Direction.UP, Direction.UP);
            default: //case DIAGONAL_TWO:
                return new Steps(Direction.UP, Direction.DOWN);
        }
    }

    private char getCell(Coords coords) {
        if (coords.isValid(SIZE - 1))
            return map[coords.getX()][coords.getY()];
        return DOT_NULL;
    }

}
