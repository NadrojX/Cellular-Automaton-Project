package entities;

import app.Grid;
import app.Position;
import javafx.scene.paint.Color;

import java.util.*;

public class HealerEntities extends EntitiesManager{

    public HealerEntities(Grid grid) {
        super(grid);
    }

    @Override
    public void paint(int row, int col) {
        grid.getGraphicsContext2D().setFill(Color.LIMEGREEN);
        grid.getGraphicsContext2D().fillRect(row * height / rowCount, col * width / colCount, height / rowCount, width / colCount);
    }

    public Position activateHealer(Position position, Set<Position> virus){
        return aStepTowardFire(position, virus);
    }
    private Position aStepTowardFire(Position position, Set<Position> virus) {
        Set<Position> seen = new HashSet<>();
        HashMap<Position, Position> firstMove = new HashMap<>();
        Queue<Position> toVisit = new LinkedList<>(positionInstance.nextPosition(position, colCount, rowCount));
        for (Position initialMove : toVisit)
            firstMove.put(initialMove, initialMove);
        while (!toVisit.isEmpty()) {
            Position current = toVisit.poll();
            if (virus.contains(current))
                return firstMove.get(current);
            for (Position adjacent : positionInstance.nextPosition(current, colCount, rowCount)) {
                if (seen.contains(adjacent)) continue;
                toVisit.add(adjacent);
                seen.add(adjacent);
                firstMove.put(adjacent, firstMove.get(current));
            }
        }
        return position;
    }

}
