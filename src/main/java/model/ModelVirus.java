package model;

import app.Grid;
import app.Position;
import entities.EntitiesContext;

import java.util.HashSet;
import java.util.Set;

public class ModelVirus extends ModelFactory{

    Set<Position> virusSet = new HashSet<>();

    Set<Position> peopleSet = new HashSet<>();

    Position positionInstance = new Position(0, 0);

    int step = 0;

    public ModelVirus(Grid grid) {
        super(grid);
    }

    public void initialisation(int virusNumber, int peopleNumber){
        for (int i = 0; i < virusNumber; i++)
            virusSet.add(positionInstance.randomPosition(rowCount, colCount));
        for (int i = 0; i < peopleNumber; i++)
            peopleSet.add(positionInstance.randomPosition(rowCount, colCount));
    }

    @Override
    public void activation() {
        EntitiesContext virusEntities = new EntitiesContext(grid, "virus");
        EntitiesContext peopleEntities = new EntitiesContext(grid, "people");

        if(step % 2 == 0){
            for (Position virus : virusSet) {
                Position newPosition = virusEntities.activate(virus, virusSet).get(0);
                grid.paint(virus.row(), virus.col());
                virusEntities.paint(newPosition.row(), newPosition.col());
            }
        }

        for(Position people : peopleSet){
            Position newPosition = peopleEntities.activate(people, virusSet).get(0);
            grid.paint(people.row(), people.col());
            peopleEntities.paint(newPosition.row(), newPosition.col());
        }

        step++;
    }
}
