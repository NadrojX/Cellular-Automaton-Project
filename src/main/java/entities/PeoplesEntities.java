package entities;

import app.Grid;
import app.Position;
import javafx.scene.paint.Color;

public class PeoplesEntities extends EntitiesManager{

    public PeoplesEntities(Grid grid) {
        super(grid);
    }

    @Override
    public void paint(int row, int col) {
        grid.getGraphicsContext2D().setFill(Color.YELLOW);
        grid.getGraphicsContext2D().fillRect(row * height / rowCount, col * width / colCount, height / rowCount, width / colCount);
    }

    public Position activatePeople(Position position){
        return position.nextStep(position);
    }

}
