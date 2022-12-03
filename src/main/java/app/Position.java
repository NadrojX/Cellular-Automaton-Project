package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public record Position(int row, int col) {

    public Position randomPosition(double rowCount, double colCount){
        return new Position((int) (Math.random() * rowCount), (int) (Math.random() * colCount));
    }

    public Position nextStep(Position position){
        SingletonRandom random = SingletonRandom.getInstance();

        switch (random.getRandomNumber(4)) {
            case 0 -> {
                return new Position(position.row() - 1, position.col());
            }
            case 1 -> {
                return new Position(position.row(), position.col() - 1);
            }
            case 2 -> {
                return new Position(position.row() + 1, position.col());
            }
            case 3 -> {
                return new Position(position.row(), position.col() + 1);
            }
        }
        return position;
    }

    public List<Position> nextPosition(Position position, double rowCount, double colCount) {
        List<Position> list = new ArrayList<>();
        if (position.row() > 0) list.add(new Position(position.row() - 1, position.col()));
        if (position.col() > 0) list.add(new Position(position.row(), position.col() - 1));
        if (position.row() < rowCount - 1) list.add(new Position(position.row() + 1, position.col()));
        if (position.col() < colCount - 1) list.add(new Position(position.row(), position.col() + 1));
        return list;
    }

    public List<Position> nextRandomPosition(Position position) {
        List<Position> list = new ArrayList<>();
        Random random = new Random();
        int number = random.nextInt(8);
        switch (number) {
            case 0 -> list.add(new Position(position.row - 1, position.col + 1));
            case 1 -> list.add(new Position(position.row + 1, position.col - 1));
            case 2 -> list.add(new Position(position.row + 1, position.col + 1));
            case 3 -> list.add(new Position(position.row - 1, position.col - 1));
            case 4 -> list.add(new Position(position.row - 1, position.col));
            case 5 -> list.add(new Position(position.row, position.col - 1));
            case 6 -> list.add(new Position(position.row + 1, position.col));
            case 7 -> list.add(new Position(position.row, position.col + 1));
        }
        return list;
    }
}
