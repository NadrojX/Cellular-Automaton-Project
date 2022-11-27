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
    FiresFightersEntities ffs = new FiresFightersEntities(grid);
    FiresEntities fs = new FiresEntities(grid);

    ffNewPositions = new ArrayList<>();
    for (Position ff : firefighters) {
      Position newPosition = ffs.activateFirefighter(ff, fires);
      grid.paint(ff.row(), ff.col());
      ffs.paint(newPosition.row(), newPosition.col());
      ffNewPositions.add(newPosition);
    }
    firefighters = ffNewPositions;
    if (step % 2 == 0) {
      List<Position> newFires = new ArrayList<>();
      for (Position fire : fires) {
        newFires.addAll(fs.activateFire(fire));
      }
      for (Position newFire : newFires)
        fs.paint(newFire.row(), newFire.col());
      fires.addAll(newFires);
    }
    step++;
  }

}