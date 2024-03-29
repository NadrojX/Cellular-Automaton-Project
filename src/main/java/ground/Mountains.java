package ground;

import app.Grid;
import app.Position;
import javafx.scene.paint.Color;

public class Mountains extends GroundFactory{

    public Mountains(Grid grid){
        super(grid);
    }

    @Override
    public void paint(int row, int col) {
        grid.getGraphicsContext2D().setFill(Color.BROWN);
        grid.getGraphicsContext2D().fillOval(row*height/rowCount,col*width/colCount,height/rowCount,width/colCount);
    }

    @Override
    public Position activate(Position position) {
        return position;
    }

}
