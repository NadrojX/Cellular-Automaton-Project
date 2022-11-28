package Strategy;

import App.Grid;
import App.Position;
import javafx.scene.paint.Color;

import java.util.*;

public class FiresFightersEntities extends EntitiesManager{

    public FiresFightersEntities(Grid grid){
        super(grid);
    }

    @Override
    public void paint(int row, int col) {
        grid.getGraphicsContext2D().setFill(Color.BLUE);
        grid.getGraphicsContext2D().fillOval(row*height/rowCount,col*width/colCount,height/rowCount,width/colCount);
    }

    public Position activateFirefighter(Position position, Set<Position> fires) {
        Position randomPosition = aStepTowardFire(position, fires);
        List<Position> nextFires = positionInstance.nextPosition(randomPosition, colCount, rowCount).stream().filter(fires::contains).toList();
        extinguish(randomPosition, fires);
        for (Position fire : nextFires)
            extinguish(fire, fires);
        return randomPosition;
    }

    public Position aStepTowardFire(Position position, Set<Position> fires) {
        Set<Position> seen = new HashSet<>();
        HashMap<Position, Position> firstMove = new HashMap<>();
        Queue<Position> toVisit = new LinkedList<>(positionInstance.nextPosition(position, colCount, rowCount));
        for (Position initialMove : toVisit)
            firstMove.put(initialMove, initialMove);
        while (!toVisit.isEmpty()) {
            Position current = toVisit.poll();
            if (fires.contains(current))
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

    public void extinguish(Position position, Set<Position> fires) {
        fires.remove(position);
        grid.paint(position.row(), position.col());
    }
}
