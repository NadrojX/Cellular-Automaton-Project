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
    List<Position> virusList = new ArrayList<>();

    List<Position> peopleList = new ArrayList<>();
    List<Position> healerList = new ArrayList<>();
    List<Position> sicknessPeopleList = new ArrayList<>();

    List<Position> newVirusPositionList;
    List<Position> newPeoplePositionList;
    List<Position> newHealerPositionList;

    Position positionInstance = new Position(0, 0);

    int step = 0;

    public ModelVirus(Grid grid) {
        super(grid);
    }

    public void initialisation(int virusNumber, int peopleNumber){
        for (int i = 0; i < virusNumber; i++)
            virusList.add(positionInstance.randomPosition(rowCount, colCount));
        for (int i = 0; i < peopleNumber; i++)
            peopleList.add(positionInstance.randomPosition(rowCount, colCount));
        for (int i = 0; i < peopleNumber; i++)
            healerList.add(positionInstance.randomPosition(rowCount, colCount));
    }

    @Override
    public void activation() {
        EntitiesContext virusEntities = new EntitiesContext(grid, "virus");
        EntitiesContext peopleEntities = new EntitiesContext(grid, "people");
        EntitiesContext healerEntities = new EntitiesContext(grid, "healer");
        EntitiesContext sicknessPeopleEntities = new EntitiesContext(grid, "sickness");

        newVirusPositionList = new ArrayList<>();
        newPeoplePositionList = new ArrayList<>();
        newHealerPositionList = new ArrayList<>();


        for (Position virus : virusList) {
            Position newPosition = virusEntities.activate(virus, t).get(0);
            grid.paint(virus.row(), virus.col());
            virusEntities.paint(newPosition.row(), newPosition.col());
            newVirusPositionList.add(newPosition);
        }

        virusList = newVirusPositionList;

        for(Position people : peopleList){
            Position newPosition = peopleEntities.activate(people, t).get(0);
            grid.paint(people.row(), people.col());
            peopleEntities.paint(newPosition.row(), newPosition.col());
            newPeoplePositionList.add(newPosition);
        }

        peopleList = newPeoplePositionList;

        for(Position healer : healerList){
            Position newPosition = peopleEntities.activate(healer, t).get(0);
            grid.paint(healer.row(), healer.col());
            healerEntities.paint(newPosition.row(), newPosition.col());
            newHealerPositionList.add(newPosition);
        }

        healerList = newHealerPositionList;

        for(Position sicknessPeople : sicknessPeopleList){
            Position newPosition = peopleEntities.activate(sicknessPeople, t).get(0);
            grid.paint(sicknessPeople.row(), sicknessPeople.col());
            sicknessPeopleEntities.paint(newPosition.row(), newPosition.col());
        }

        step++;
    }
}
