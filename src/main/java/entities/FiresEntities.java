package entities;

import app.Grid;
import app.Position;
import javafx.scene.paint.Color;

import java.util.List;

public class FiresEntities extends EntitiesManager{

    public FiresEntities(Grid grid){
        super(grid);
    }
    @Override
    public void paint(int row, int col) {
        grid.getGraphicsContext2D().setFill(Color.RED);
        grid.getGraphicsContext2D().fillRect(row*height/rowCount,col*width/colCount,height/rowCount,width/colCount);
    }

    public List<Position> activateEntities(Position position) {
        return positionInstance.nextPosition(position, rowCount, colCount);
    }

}
