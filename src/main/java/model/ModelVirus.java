package model;

import app.Grid;
import app.Position;
import entities.EntitiesContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelVirus extends ModelFactory{

    Set<Position> t = new HashSet<>();
    List<Position> virusSet = new ArrayList<>();

    List<Position> virusNewPosition = new ArrayList<>();

    Position positionInstance = new Position(0, 0);


    public ModelVirus(Grid grid) {
        super(grid);
    }

    public void initialisation(int virusNumber){
        for (int i = 0; i < virusNumber; i++)
            virusSet.add(positionInstance.randomPosition(rowCount, colCount));
    }

    @Override
    public void activation() {
        EntitiesContext virusEntities = new EntitiesContext(grid, "virus");

        for(Position virus : virusSet){
            Position newPosition = virusEntities.activate(virus, t).get(0);
            grid.paint(virus.row(), virus.col());
            virusEntities.paint(newPosition.row(), newPosition.col());
            virusNewPosition.add(newPosition);
        }

        virusSet = virusNewPosition;

    }
}
