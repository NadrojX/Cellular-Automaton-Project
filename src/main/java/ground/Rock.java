package ground;

import app.Grid;
import app.Position;
import javafx.scene.paint.Color;

import java.util.List;

public class Rock extends GroundFactory{

    public Rock(Grid grid) {
        super(grid);
    }

    @Override
    public void paint(int row, int col) {
        grid.getGraphicsContext2D().setFill(Color.DARKSLATEGREY);
        grid.getGraphicsContext2D().fillOval(row*height/rowCount,col*width/colCount,height/rowCount,width/colCount);
    }

    @Override
    public Position activate(Position position) {
        return position;
    }

    @Override
    public List<Position> getNeighbor(Position position) {
        return super.getNeighbor(position);
    }
}
