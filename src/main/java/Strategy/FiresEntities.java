package Strategy;

import App.Grid;
import App.Position;
import javafx.scene.paint.Color;

import java.util.List;

public class FiresEntities implements Entities{
    Grid grid;
    Position positionInstance = new Position(0, 0);

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

    public List<Position> activateFire(Position position) {
        return positionInstance.nextPosition(position, rowCount, colCount);
    }
}
