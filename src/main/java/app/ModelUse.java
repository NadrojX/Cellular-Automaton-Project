package app;

import model.ModelFires;

public class ModelUse {
  Grid grid;
  double colCount;
  double rowCount;

  ModelFires modelFires;

  public ModelUse(Grid grid) {
    this.grid = grid;
    colCount = grid.colCount;
    rowCount = grid.rowCount;
    modelFires = new ModelFires(grid);
  }

  public void initialisation(int fireNumber, int fireFighterNumber, int motorizedFireFighterNumber, int cloudsNumber, int mountainsNumber, int rockNumber) {
    modelFires.initialisation(fireNumber, fireFighterNumber, motorizedFireFighterNumber, cloudsNumber, mountainsNumber, rockNumber);
  }

  public void activation() {
    modelFires.activation();
  }

}