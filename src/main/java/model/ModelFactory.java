package model;

import app.Grid;
import app.configuration.ModelConfigurationFire;
import app.configuration.ModelConfigurationVirus;

public abstract class ModelFactory implements Model{

    Grid grid;
    double colCount, rowCount;

    public ModelFactory(Grid grid){
        this.grid = grid;
        this.rowCount = grid.getRowCount();
        this.colCount = grid.getColCount();
    }

    public void initialisationFire(ModelConfigurationFire modelConfiguration){
    }

    public void initialisationVirus(ModelConfigurationVirus modelConfigurationVirus){

    }

}
