package entities;

import app.Grid;
import app.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class EntitiesManager implements Entities{
    Grid grid;
    Position positionInstance = new Position(0, 0);
    double rowCount, colCount, height, width;

    public EntitiesManager(Grid grid){
        this.grid = grid;
        this.rowCount = grid.getRowCount();
        this.colCount = grid.getColCount();
        this.height = grid.getHeight();
        this.width = grid.getWidth();
    }

    public List<Position> activateEntities(Position position) {
        return null;
    }

    public Position activateEntitiesNeedSet(Position position, Set<Position> fires) {
        return null;
    }

    public  Position activateVirus() {return null;}

    public Position activatePeople(Position position) {return null;}

    public List<Position> getNeighbor(Position position){
        List<Position> neighbor = new ArrayList<>();
        neighbor.add(new Position(position.row() + 1, position.col()));
        neighbor.add(new Position(position.row() - 1, position.col()));
        neighbor.add(new Position(position.row(), position.col() + 1));
        neighbor.add(new Position(position.row(), position.col() - 1));
        return neighbor;
    }

}
