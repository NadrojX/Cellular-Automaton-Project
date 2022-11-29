package Ground;

import App.Grid;
import App.Position;

public abstract class GroundFactory {
    Grid grid;

    Position positionInstance = new Position(0, 0);
    double rowCount, colCount, height, width;

    public GroundFactory(Grid grid){
        this.grid = grid;
        this.rowCount = grid.getRowCount();
        this.colCount = grid.getColCount();
        this.height = grid.getHeight();
        this.width = grid.getWidth();
    }

}
