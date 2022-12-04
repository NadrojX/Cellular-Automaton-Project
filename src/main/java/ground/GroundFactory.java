package ground;

import app.Grid;
import app.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class GroundFactory implements Grounds{
    Grid grid;
    double rowCount, colCount, height, width;

    public GroundFactory(Grid grid){
        this.grid = grid;
        this.rowCount = grid.getRowCount();
        this.colCount = grid.getColCount();
        this.height = grid.getHeight();
        this.width = grid.getWidth();
    }

    public List<Position> getNeighbor(Position position){
        List<Position> neighbor = new ArrayList<>();
        neighbor.add(new Position(position.row() + 1, position.col()));
        neighbor.add(new Position(position.row() - 1, position.col()));
        neighbor.add(new Position(position.row(), position.col() + 1));
        neighbor.add(new Position(position.row(), position.col() - 1));
        return neighbor;
    }

}
