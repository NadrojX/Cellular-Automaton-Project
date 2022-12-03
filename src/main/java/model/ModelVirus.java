package model;

import app.Grid;
import app.Position;
import app.SingletonRandom;
import app.configuration.ModelConfigurationVirus;
import entities.EntitiesContext;

import java.util.*;

public class ModelVirus extends ModelFactory{

    Set<Position> virusSet = new HashSet<>();
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

    public void initialisationVirus(ModelConfigurationVirus modelConfiguration){
        for (int i = 0; i < modelConfiguration.getVirusNumber(); i++)
            virusList.add(positionInstance.randomPosition(rowCount, colCount));
        for (int i = 0; i < modelConfiguration.getPeopleNumber(); i++)
            peopleList.add(positionInstance.randomPosition(rowCount, colCount));
        for (int i = 0; i < modelConfiguration.getHealerNumber(); i++)
            healerList.add(positionInstance.randomPosition(rowCount, colCount));
    }

    @Override
    public void activation() {
        EntitiesContext virusEntities = new EntitiesContext(grid, "virus");
        EntitiesContext peopleEntities = new EntitiesContext(grid, "people");
        EntitiesContext healerEntities = new EntitiesContext(grid, "healer");
        EntitiesContext sicknessPeopleEntities = new EntitiesContext(grid, "sicknessPeople");

        newVirusPositionList = new ArrayList<>();
        newPeoplePositionList = new ArrayList<>();
        newHealerPositionList = new ArrayList<>();
        newSicknessPeoplePositionList = new ArrayList<>();

        virusMutation();

        if(virusList.isEmpty() && sicknessPeopleList.isEmpty()) return;

        for (Position virus : virusList) {
            infectionVirus(virusEntities, virus);
            Position newPosition = virusEntities.activate(virus, virusSet).get(0);
            grid.paint(virus.row(), virus.col());
            virusEntities.paint(newPosition.row(), newPosition.col());
            newVirusPositionList.add(newPosition);
        }

        virusList = newVirusPositionList;

        for(Position people : peopleList){
            Position newPosition = peopleEntities.activate(people, virusSet).get(0);
            grid.paint(people.row(), people.col());
            peopleEntities.paint(newPosition.row(), newPosition.col());
            newPeoplePositionList.add(newPosition);
        }

        peopleList = newPeoplePositionList;

        virusSet.clear();
        virusSet.addAll(sicknessPeopleList);

        for(Position sicknessPeople : sicknessPeopleList){
            Position newPosition = peopleEntities.activate(sicknessPeople, virusSet).get(0);
            grid.paint(sicknessPeople.row(), sicknessPeople.col());
            sicknessPeopleEntities.paint(newPosition.row(), newPosition.col());
            newSicknessPeoplePositionList.add(newPosition);
            naturalHeal(sicknessPeople);
        }

        sicknessPeopleList = newSicknessPeoplePositionList;

        for(Position healer : healerList){
            Position newPosition = healerEntities.activate(healer, virusSet).get(0);
            grid.paint(healer.row(), healer.col());
            healerEntities.paint(newPosition.row(), newPosition.col());
            newHealerPositionList.add(newPosition);
            healDoctor(healerEntities, healer);
        }

        healerList = newHealerPositionList;

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

    private void infectionVirus(EntitiesContext infecter, Position position){
        List<Position> neighbors = infecter.getNeighbor(position);
        int infectionChoice = random.getRandomNumber(5);

        if (infectionChoice == 2) {
            for (Position neighbor : neighbors) {
                if (peopleList.contains(neighbor)) {
                    grid.paint(neighbor.row(), neighbor.col());
                    peopleList.remove(neighbor);
                    sicknessPeopleList.add(neighbor);
                }
            }
        }

    }

    private void naturalHeal(Position position){
        if(step % 4 == 0 && step != 0){
            if(random.getRandomNumber(2) == 0) {
                grid.paint(position.col(), position.row());
                peopleList.add(position);
                newSicknessPeoplePositionList.remove(position);
            }
        }
    }

    private void healDoctor(EntitiesContext healer, Position position){
        List<Position> neighbors = healer.getNeighbor(position);

        for (Position neighbor : neighbors) {
            if (sicknessPeopleList.contains(neighbor)) {
                grid.paint(neighbor.row(), neighbor.col());
                sicknessPeopleList.remove(neighbor);
                newPeoplePositionList.add(neighbor);
            }
        }

    }

}
