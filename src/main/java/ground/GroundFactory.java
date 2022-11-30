package ground;

import app.Grid;
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

}
