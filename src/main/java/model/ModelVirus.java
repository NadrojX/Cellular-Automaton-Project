package model;

import app.Grid;
import app.Position;
import app.SingletonRandom;
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
    List<Position> newSicknessPeoplePositionList;

    Position positionInstance = new Position(0, 0);

    SingletonRandom random = SingletonRandom.getInstance();

    int step = 0;

    public ModelVirus(Grid grid) {
        super(grid);
    }

    public void initialisation(int virusNumber, int peopleNumber, int healerNumber){
        for (int i = 0; i < virusNumber; i++)
            virusList.add(positionInstance.randomPosition(rowCount, colCount));
        for (int i = 0; i < peopleNumber; i++)
            peopleList.add(positionInstance.randomPosition(rowCount, colCount));
        for (int i = 0; i < healerNumber; i++)
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
        newSicknessPeoplePositionList = new ArrayList<>();

        virusMutation();

        for (Position virus : virusList) {
            infection(virusEntities, virus);
            Position newPosition = virusEntities.activate(virus, t).get(0);
            grid.paint(virus.row(), virus.col());
            virusEntities.paint(newPosition.row(), newPosition.col());
            newVirusPositionList.add(newPosition);
        }

        virusList = newVirusPositionList;

        for(Position people : peopleList){
            infection(peopleEntities, people);
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
            newSicknessPeoplePositionList.add(newPosition);
            heal(sicknessPeople);
        }

        sicknessPeopleList = newSicknessPeoplePositionList;

        step++;
    }

    private void virusMutation(){
        int virusMutation = random.getRandomNumber(2);
        switch (virusMutation) {
            case 0 -> {
                if(virusList.isEmpty()) return;
                if(step % 5 == 0 && step != 0){
                    int virusTarget = random.getRandomNumber(virusList.size());
                    grid.paint(virusList.get(virusTarget).row(), virusList.get(virusTarget).col());
                    virusList.remove(virusTarget);
                }
            }
            case 1 -> {
                if(virusList.isEmpty()) return;
                if(step % 5 == 0 && step != 0){
                    virusList.add(positionInstance.randomPosition(rowCount, colCount));
                }
            }
        }
    }

    private void infection(EntitiesContext infecter, Position position){
        List<Position> neighbors = infecter.getNeighbor(position);

        int infectionChoice = random.getRandomNumber(2);

        if (infectionChoice == 0) {
            for (Position neighbor : neighbors) {
                if (peopleList.contains(neighbor)) {
                    grid.paint(neighbor.row(), neighbor.col());
                    peopleList.remove(neighbor);
                    sicknessPeopleList.add(neighbor);
                }
            }
        }

    }

    private void heal(Position position){
        if(step % 9 == 0 && step != 0){
            grid.paint(position.col(), position.row());
            peopleList.add(position);
            newSicknessPeoplePositionList.remove(position);
        }
    }

}
