package App;

import Strategy.Entities;
import Strategy.FiresEntities;
import Strategy.FiresFightersEntities;

import java.util.*;


public class Model {
  Grid grid;
  double colCount;
  double rowCount;
  List<Position> firefighters = new ArrayList<>();
  Set<Position> fires = new HashSet<>();
  List<Position> ffNewPositions;
  Position positionInstance = new Position(0, 0);
  int step = 0;

  public Model(Grid grid) {
    this.grid = grid;
    colCount = grid.colCount;
    rowCount = grid.rowCount;
  }

  public void initialisation(int fireNumber, int fireFighterNumber) {
    for (int index = 0; index < fireNumber; index++)
      fires.add(positionInstance.randomPosition(rowCount, colCount));
    for (int index = 0; index < fireFighterNumber; index++)
      firefighters.add(positionInstance.randomPosition(rowCount, colCount));
  }

  public void activation() {
    Entities ffs = new FiresFightersEntities(grid);
    Entities fs = new FiresEntities(grid);

    ffNewPositions = new ArrayList<>();
    for (Position ff : firefighters) {
      Position newPosition = activateFirefighter(ff);
      grid.paint(ff.row(), ff.col());
      ffs.paint(newPosition.row(), newPosition.col());
      ffNewPositions.add(newPosition);
    }
    firefighters = ffNewPositions;
    if (step % 2 == 0) {
      List<Position> newFires = new ArrayList<>();
      for (Position fire : fires) {
        newFires.addAll(activateFire(fire));
      }
      for (Position newFire : newFires)
        fs.paint(newFire.row(), newFire.col());
      fires.addAll(newFires);
    }
    step++;

  }

  private List<Position> activateFire(Position position) {
    return positionInstance.nextPosition(position, rowCount, colCount);
  }

  private Position activateFirefighter(Position position) {
    Position randomPosition = aStepTowardFire(position);
    List<Position> nextFires = positionInstance.nextPosition(randomPosition, colCount, rowCount).stream().filter(fires::contains).toList();
    extinguish(randomPosition);
    for (Position fire : nextFires)
      extinguish(fire);
    return randomPosition;
  }

  private void extinguish(Position position) {
    fires.remove(position);
    grid.paint(position.row(), position.col());
  }


  private Position aStepTowardFire(Position position) {
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

}