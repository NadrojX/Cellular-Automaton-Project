package Strategy;

import App.Grid;
import javafx.scene.paint.Color;

public class FiresEntities implements Entities{
    Grid grid;
    double rowCount, colCount, height, width;

    public FiresEntities(Grid grid){
        this.grid = grid;
        this.rowCount = grid.getRowCount();
        this.colCount = grid.getColCount();
        this.height = grid.getHeight();
        this.width = grid.getWidth();
    }

    @Override
    public void paint(int row, int col) {
        grid.getGraphicsContext2D().setFill(Color.RED);
        grid.getGraphicsContext2D().fillRect(row*height/rowCount,col*width/colCount,height/rowCount,width/colCount);
    }
}
