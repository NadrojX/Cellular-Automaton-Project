package ground;

import app.Grid;
import app.Position;
import javafx.scene.paint.Color;

public class Road extends GroundFactory{

    public Road(Grid grid) {
        super(grid);
    }

    @Override
    public void paint(int row, int col) {
        grid.getGraphicsContext2D().setFill(Color.DARKGOLDENROD);
        grid.getGraphicsContext2D().fillRect(row*height/rowCount,col*width/colCount,height/rowCount,width/colCount);
    }

    @Override
    public Position activate(Position position) {
        return position;
    }
}
