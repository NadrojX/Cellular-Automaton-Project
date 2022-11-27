package App;

import java.util.ArrayList;
import java.util.List;

public record Position(int row, int col) {

    public Position randomPosition(double rowCount, double colCount){
        return new Position((int) (Math.random() * rowCount), (int) (Math.random() * colCount));
    }

    public List<Position> nextPosition(Position position, double rowCount, double colCount) {
        List<Position> list = new ArrayList<>();
        if (position.row() > 0) list.add(new Position(position.row() - 1, position.col()));
        if (position.col() > 0) list.add(new Position(position.row(), position.col() - 1));
        if (position.row() < rowCount - 1) list.add(new Position(position.row() + 1, position.col()));
        if (position.col() < colCount - 1) list.add(new Position(position.row(), position.col() + 1));
        return list;
    }
}
