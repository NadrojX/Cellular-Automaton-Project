package Ground;

import App.Grid;
public abstract class GroundFactory {
    Grid grid;
    double rowCount, colCount, height, width;

    public GroundFactory(Grid grid){
        this.grid = grid;
        this.rowCount = grid.getRowCount();
        this.colCount = grid.getColCount();
        this.height = grid.getHeight();
        this.width = grid.getWidth();
    }

}
