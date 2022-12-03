package app;

import app.configuration.ModelConfigurationFire;
import app.configuration.ModelConfigurationVirus;
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

  public void initialisationFire(ModelConfigurationFire modelConfiguration) {
    modelFire.initialisationFire(modelConfiguration);
  }

  public void initialisationVirus(ModelConfigurationVirus modelConfiguration){
    modelVirus.initialisationVirus(modelConfiguration);
  }

  public void activationFire() {
    modelFire.activation();
  }

  public void activationVirus() {
    modelVirus.activation();
  }

}