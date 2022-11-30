package app;

import model.ModelFactory;
import model.ModelFire;
import model.ModelVirus;

public class ModelUse {
  Grid grid;
  double colCount;
  double rowCount;

  ModelFactory modelFire;
  ModelFactory modelVirus;

  public ModelUse(Grid grid) {
    this.grid = grid;
    colCount = grid.colCount;
    rowCount = grid.rowCount;
    modelFire = new ModelFire(grid);
    modelVirus = new ModelVirus(grid);
  }

  public void initialisationFire(int fireNumber, int fireFighterNumber, int motorizedFireFighterNumber, int cloudsNumber, int mountainsNumber, int rockNumber) {
    modelFire.initialisation(fireNumber, fireFighterNumber, motorizedFireFighterNumber, cloudsNumber, mountainsNumber, rockNumber);
  }

  public void initialisationVirus(int virusNumber){
    modelVirus.initialisation(virusNumber);
  }

  public void activationFire() {
    modelFire.activation();
  }

  public void activationVirus() {
    modelVirus.activation();
  }

}