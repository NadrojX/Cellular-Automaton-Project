package entities;

import app.Grid;
import app.Position;

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

    public List<Position> activateFire(Position position) {
        return null;
    }

    public Position activateFirefighter(Position position, Set<Position> fires) {
        return null;
    }

    public Position activateClouds(Position position, Set<Position> fires) {
        return null;
    }

    public  Position activateVirus() {
        return null;
    }

    public Position activatePeople(Position position) {
        return null;
    }
}
