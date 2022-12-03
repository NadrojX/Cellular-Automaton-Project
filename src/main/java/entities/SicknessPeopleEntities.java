package entities;

import app.Grid;
import app.Position;
import javafx.scene.paint.Color;

public class SicknessPeopleEntities extends EntitiesManager{

    public SicknessPeopleEntities(Grid grid) {
        super(grid);
    }

    @Override
    public void paint(int row, int col) {
        grid.getGraphicsContext2D().setFill(Color.INDIANRED);
        grid.getGraphicsContext2D().fillRect(row * height / rowCount, col * width / colCount, height / rowCount, width / colCount);
    }

    @Override
    public Position activatePeople(Position position) {
        return position;
    }
}
