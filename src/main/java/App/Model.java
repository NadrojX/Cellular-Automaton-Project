package App;

import Entities.EntitiesContext;
import Ground.Grounds;
import Ground.Mountains;
import Ground.Road;
import Ground.Rock;

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
  Set<Position> roads = new HashSet<>();
  Set<Position> rocks = new HashSet<>();
  List<Position> ffNewPositions;
  List<Position> mffNewPositions;
  List<Position> cloudsNewPositions;
  List<Position> rocksBlock;
  Position positionInstance = new Position(0, 0);

  Random random = new Random();
  int step = 0;

  public Model(Grid grid) {
    this.grid = grid;
    colCount = grid.colCount;
    rowCount = grid.rowCount;
  }

  public void initialisation(int fireNumber, int fireFighterNumber) {
    for (int index = 0; index < 10; index++)
      fires.add(positionInstance.randomPosition(rowCount, colCount));
    for (int index = 0; index < 6; index++)
      firefighters.add(positionInstance.randomPosition(rowCount, colCount));
    for (int index = 0; index < 0; index++)
      motorizedFirefighters.add(positionInstance.randomPosition(rowCount, colCount));
    for (int index = 0; index < 3; index++)
      clouds.add(positionInstance.randomPosition(rowCount, colCount));
    for (int index = 0; index < 6; index++)
      mountains.add(positionInstance.randomPosition(rowCount, colCount));

    for (int index = 0; index < 2; index++) {
      roads.add(positionInstance.randomPosition(rowCount, colCount));
      int choose = random.nextInt(2);
      switch (choose) {
        case 0 -> {
          int sizeRoad = random.nextInt(6);
          for (int i = 0; i < sizeRoad; i++) {
            for (int j = 0; j < roads.size(); j++) {
              if(j > grid.rowCount) continue;
              roads.add(new Position((roads.stream().toList().get(j).row() + i), roads.stream().toList().get(j).col()));
            }
          }
        }
        case 1 -> {
          int sizeRoad2 = random.nextInt(6);
          for (int k = 0; k < sizeRoad2; k++) {
            for (int l = 0; l < roads.size(); l++) {
              if(l > grid.colCount) continue;
              roads.add(new Position((roads.stream().toList().get(l).row()), roads.stream().toList().get(l).col() - l));
            }
          }
        }
      }
    }

    for (int index = 0; index < 3; index++)
      rocks.add(positionInstance.randomPosition(rowCount, colCount));
  }

  public void activation() {
    EntitiesContext ffs = new EntitiesContext(grid, "ffs");
    EntitiesContext fs = new EntitiesContext(grid, "fs");
    EntitiesContext mffs = new EntitiesContext(grid, "mffs");
    EntitiesContext cl = new EntitiesContext(grid, "clouds");

    Grounds mount = new Mountains(grid);
    Grounds road = new Road(grid);
    Grounds rock = new Rock(grid);

    ffNewPositions = new ArrayList<>();
    mffNewPositions = new ArrayList<>();
    cloudsNewPositions = new ArrayList<>();

    for (Position mountain : mountains) {
      Position newPosition = mount.activate(mountain);
      grid.paint(mountain.row(), mountain.col());
      mount.paint(newPosition.row(), newPosition.col());
    }

    for (Position roa : roads) {
      Position newPosition = road.activate(roa);
      grid.paint(roa.row(), roa.col());
      road.paint(newPosition.row(), newPosition.col());
    }

    for (Position rockS : rocks) {
      Position newPosition = mount.activate(rockS);
      grid.paint(rockS.row(), rockS.col());
      rock.paint(newPosition.row(), newPosition.col());
    }

    for (Position ff : firefighters) {
      Position newPosition = ffs.activate(ff, fires).get(0);
      if(mountains.contains(newPosition)){
        ffs.paint(ff.row(), ff.col());
      } else {
        grid.paint(ff.row(), ff.col());
        ffs.paint(newPosition.row(), newPosition.col());
        ffNewPositions.add(newPosition);
      }
    }

    firefighters = ffNewPositions;

    for (Position mff : motorizedFirefighters) {
      Position newPosition = mffs.activate(mff, fires).get(0);
      if(mountains.contains(newPosition)){
        mffs.paint(mff.row(), mff.col());
      } else {
        grid.paint(mff.row(), mff.col());
        mffs.paint(newPosition.row(), newPosition.col());
        mffNewPositions.add(newPosition);
      }
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
          if (mountains.contains(newFires.get(i)) || roads.contains(newFires.get(i))) {
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