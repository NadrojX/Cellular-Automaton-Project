package entities;

import app.Grid;
import app.Position;
import javafx.scene.paint.Color;

import java.util.*;


public class VirusEntities extends EntitiesManager {

    public VirusEntities(Grid grid) {
        super(grid);
    }

    @Override
    public void paint(int row, int col) {
        grid.getGraphicsContext2D().setFill(Color.INDIANRED);
        grid.getGraphicsContext2D().fillOval(row * height / rowCount, col * width / colCount, height / rowCount, width / colCount);
    }

    @Override
    public Position activateVirus(Position position) {
        return position;
    }

}
