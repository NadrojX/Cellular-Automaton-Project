package model;

import app.Grid;
import app.Position;
import app.SingletonRandom;
import entities.EntitiesContext;
import ground.Grounds;
import ground.Mountains;
import ground.Road;
import ground.Rock;

import java.util.*;

public class ModelFire extends ModelFactory {

    List<Position> firefightersList = new ArrayList<>();
    List<Position> motorizedFirefightersList = new ArrayList<>();
    Set<Position> firesSet = new HashSet<>();
    List<Position> cloudsList = new ArrayList<>();
    Set<Position> mountainsSet = new HashSet<>();
    Set<Position> roadsSet = new HashSet<>();
    Set<Position> rocksSet = new HashSet<>();
    List<Position> ffNewPositions;
    List<Position> mffNewPositions;
    List<Position> cloudsNewPositions;
    Position positionInstance = new Position(0, 0);
    SingletonRandom random = SingletonRandom.getInstance();
    int step = 0;

    public ModelFire(Grid grid) {
        super(grid);
    }

    @Override
    public void initialisation(int fireNumber, int fireFighterNumber, int motorizedFireFighterNumber, int cloudsNumber, int mountainsNumber, int rockNumber) {
        for (int index = 0; index < fireNumber; index++)
            firesSet.add(positionInstance.randomPosition(rowCount, colCount));
        for (int index = 0; index < fireFighterNumber; index++)
            firefightersList.add(positionInstance.randomPosition(rowCount, colCount));
        for (int index = 0; index < motorizedFireFighterNumber; index++)
            motorizedFirefightersList.add(positionInstance.randomPosition(rowCount, colCount));
        for (int index = 0; index < cloudsNumber; index++)
            cloudsList.add(positionInstance.randomPosition(rowCount, colCount));
        for (int index = 0; index < mountainsNumber; index++)
            mountainsSet.add(positionInstance.randomPosition(rowCount, colCount));

        for (int index = 0; index < 2; index++) {
            roadsSet.add(positionInstance.randomPosition(rowCount, colCount));
            int choose = random.getRandomNumber(2);
            switch (choose) {
                case 0 -> {
                    int sizeRoad = random.getRandomNumber(6);
                    for (int i = 0; i < sizeRoad; i++) {
                        for (int j = 0; j < roadsSet.size(); j++) {
                            if(j > rowCount) continue;
                            roadsSet.add(new Position((roadsSet.stream().toList().get(j).row() + i), roadsSet.stream().toList().get(j).col()));
                        }
                    }
                }
                case 1 -> {
                    int sizeRoad2 = random.getRandomNumber(6);
                    for (int k = 0; k < sizeRoad2; k++) {
                        for (int l = 0; l < roadsSet.size(); l++) {
                            if(l > colCount) continue;
                            roadsSet.add(new Position((roadsSet.stream().toList().get(l).row()), roadsSet.stream().toList().get(l).col() - l));
                        }
                    }
                }
            }
        }

        for (int index = 0; index < rockNumber; index++)
            rocksSet.add(positionInstance.randomPosition(rowCount, colCount));
    }

    @Override
    public void activation() {
        EntitiesContext fireFightersEntities = new EntitiesContext(grid, "fireFighter");
        EntitiesContext firesEntities = new EntitiesContext(grid, "fires");
        EntitiesContext motorizedFireFighterEntities = new EntitiesContext(grid, "motorizedFireFighter");
        EntitiesContext cloudsEntities = new EntitiesContext(grid, "clouds");

        Grounds mountainsGround = new Mountains(grid);
        Grounds roadGround = new Road(grid);
        Grounds rockGround = new Rock(grid);

        ffNewPositions = new ArrayList<>();
        mffNewPositions = new ArrayList<>();
        cloudsNewPositions = new ArrayList<>();

        for (Position mountain : mountainsSet) {
            Position newPosition = mountainsGround.activate(mountain);
            grid.paint(mountain.row(), mountain.col());
            mountainsGround.paint(newPosition.row(), newPosition.col());
        }

        for (Position road : roadsSet) {
            Position newPosition = roadGround.activate(road);
            grid.paint(road.row(), road.col());
            roadGround.paint(newPosition.row(), newPosition.col());
        }

        for (Position rock : rocksSet) {
            Position newPosition = mountainsGround.activate(rock);
            grid.paint(rock.row(), rock.col());
            rockGround.paint(newPosition.row(), newPosition.col());
        }

        for (Position fireFighter : firefightersList) {
            Position newPosition = fireFightersEntities.activate(fireFighter, firesSet).get(0);
            if(mountainsSet.contains(newPosition)){
                fireFightersEntities.paint(fireFighter.row(), fireFighter.col());
            } else {
                grid.paint(fireFighter.row(), fireFighter.col());
                fireFightersEntities.paint(newPosition.row(), newPosition.col());
                ffNewPositions.add(newPosition);
            }
        }

        firefightersList = ffNewPositions;

        for (Position motorizedFireFighter : motorizedFirefightersList) {
            Position newPosition = motorizedFireFighterEntities.activate(motorizedFireFighter, firesSet).get(0);
            if(mountainsSet.contains(newPosition)){
                motorizedFireFighterEntities.paint(motorizedFireFighter.row(), motorizedFireFighter.col());
            } else {
                grid.paint(motorizedFireFighter.row(), motorizedFireFighter.col());
                motorizedFireFighterEntities.paint(newPosition.row(), newPosition.col());
                mffNewPositions.add(newPosition);
            }
        }

        motorizedFirefightersList = mffNewPositions;

        for (Position cloud : cloudsList) {
            Position newPosition = cloudsEntities.activate(cloud, firesSet).get(0);
            grid.paint(cloud.row(), cloud.col());
            cloudsEntities.paint(newPosition.row(), newPosition.col());
            cloudsNewPositions.add(newPosition);
        }

        cloudsList = cloudsNewPositions;

        if (step % 2 == 0) {
            List<Position> newFires = new ArrayList<>();
            for (Position fire : firesSet) {
                newFires.addAll(firesEntities.activate(fire, firesSet));
                for (int i = 0; i < newFires.size(); i++) {
                    if (mountainsSet.contains(newFires.get(i)) || roadsSet.contains(newFires.get(i))) {
                        newFires.remove(newFires.get(i));
                    }
                }
            }
            for (Position newFire : newFires)
                firesEntities.paint(newFire.row(), newFire.col());
            firesSet.addAll(newFires);
        }

        step++;
    }
}
