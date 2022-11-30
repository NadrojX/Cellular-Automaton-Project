package model;

import app.Grid;

public abstract class ModelFactory implements Model{

    Grid grid;
    double colCount, rowCount;

    public ModelFactory(Grid grid){
        this.grid = grid;
        this.rowCount = grid.getRowCount();
        this.colCount = grid.getColCount();
    }

    public void initialisation(int fireNumber, int fireFighterNumber, int motorizedFireFighterNumber, int cloudsNumber, int mountainsNumber, int rockNumber){

    }

}
