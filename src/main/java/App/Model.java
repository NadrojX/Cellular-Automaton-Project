package App;

import Entities.EntitiesContext;
import Ground.Grounds;
import Ground.Mountains;

import java.util.*;

public class Model {
  Grid grid;
  double colCount;
  double rowCount;
  List<Position> firefighters = new ArrayList<>();
  List<Position> motorizedFirefighters = new ArrayList<>();
  Set<Position> fires = new HashSet<>();
  List<Position> clouds = new ArrayList<>();
  Set<Position> mountains = new HashSet<>();
  List<Position> ffNewPositions;
  List<Position> mffNewPositions;
  List<Position> cloudsNewPositions;
  Position positionInstance = new Position(0, 0);
  int step = 0;

  public Model(Grid grid) {
    this.grid = grid;
    colCount = grid.colCount;
    rowCount = grid.rowCount;
  }

  public void initialisation(int fireNumber, int fireFighterNumber) {
    for (int index = 0; index < 16; index++)
      fires.add(positionInstance.randomPosition(rowCount, colCount));
    for (int index = 0; index < 0; index++)
      firefighters.add(positionInstance.randomPosition(rowCount, colCount));
    for (int index = 0; index < 10; index++)
      motorizedFirefighters.add(positionInstance.randomPosition(rowCount, colCount));
    for (int index = 0; index < 2; index++)
      clouds.add(positionInstance.randomPosition(rowCount, colCount));
    for (int index = 0; index < 6; index++)
      mountains.add(positionInstance.randomPosition(rowCount, colCount));
  }

  public void activation() {
    EntitiesContext ffs = new EntitiesContext(grid, "ffs");
    EntitiesContext fs = new EntitiesContext(grid, "fs");
    EntitiesContext mffs = new EntitiesContext(grid, "mffs");
    EntitiesContext cl = new EntitiesContext(grid, "clouds");

    Grounds mount = new Mountains(grid);

    ffNewPositions = new ArrayList<>();
    mffNewPositions = new ArrayList<>();
    cloudsNewPositions = new ArrayList<>();

    for (Position mountain : mountains) {
      Position newPosition = mount.activate(mountain);
      grid.paint(mountain.row(), mountain.col());
      mount.paint(newPosition.row(), newPosition.col());
    }

    for (Position ff : firefighters) {
      Position newPosition = ffs.activate(ff, fires).get(0);
      grid.paint(ff.row(), ff.col());
      ffs.paint(newPosition.row(), newPosition.col());
      ffNewPositions.add(newPosition);
    }

    firefighters = ffNewPositions;

    for (Position mff : motorizedFirefighters) {
      Position newPosition = mffs.activate(mff, fires).get(0);
      grid.paint(mff.row(), mff.col());
      mffs.paint(newPosition.row(), newPosition.col());
      mffNewPositions.add(newPosition);
    }

    motorizedFirefighters = mffNewPositions;

    for (Position cloud : clouds) {
      Position newPosition = cl.activate(cloud, fires).get(0);
      grid.paint(cloud.row(), cloud.col());
      cl.paint(newPosition.row(), newPosition.col());
      cloudsNewPositions.add(newPosition);
    }

    clouds = cloudsNewPositions;

    if (step % 2 == 0) {
      List<Position> newFires = new ArrayList<>();
      for (Position fire : fires) {
        newFires.addAll(fs.activate(fire,fires));
        for (int i = 0; i < newFires.size(); i++) {
            if (mountains.contains(newFires.get(i))) {
              newFires.remove(i);
            }
        }
      }
      for (Position newFire : newFires)
        fs.paint(newFire.row(), newFire.col());
      fires.addAll(newFires);
    }

    step++;
  }

}