package entities;

import app.Grid;
import app.Position;
import javafx.scene.paint.Color;

public class HealerEntities extends EntitiesManager{

    public HealerEntities(Grid grid) {
        super(grid);
    }

    @Override
    public void paint(int row, int col) {
        grid.getGraphicsContext2D().setFill(Color.LIMEGREEN);
        grid.getGraphicsContext2D().fillRect(row * height / rowCount, col * width / colCount, height / rowCount, width / colCount);
    }

    public Position activatePeople(Position position){
        return position.randomPosition(rowCount, colCount);
    }
}
