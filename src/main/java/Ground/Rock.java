package Ground;

import App.Grid;
import App.Position;
import javafx.scene.paint.Color;

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
}
