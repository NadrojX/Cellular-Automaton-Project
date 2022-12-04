package entities;

import app.Grid;
import app.Position;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class EntitiesContext {

    EntitiesManager entitiesManager;
    String strategy;

    public EntitiesContext(Grid grid, String strategy){
        switch (strategy) {
            case "fireFighter" -> {
                entitiesManager = new FiresFightersEntities(grid);
                this.strategy = strategy;
            }
            case "fires" -> {
                entitiesManager = new FiresEntities(grid);
                this.strategy = strategy;
            }
            case "motorizedFireFighter" -> {
                entitiesManager = new MotorizedFFEntities(grid);
                this.strategy = strategy;
            }
            case "clouds" -> {
                entitiesManager = new CloudsEntity(grid);
                this.strategy = strategy;
            }
            case "virus" -> {
                entitiesManager = new VirusEntities(grid);
                this.strategy = strategy;
            }
            case "people" -> {
                entitiesManager = new PeoplesEntities(grid);
                this.strategy = strategy;
            }
            case "healer" -> {
                entitiesManager = new HealerEntities(grid);
                this.strategy = strategy;
            }
            case "sicknessPeople" -> {
                entitiesManager = new SicknessPeopleEntities(grid);
                this.strategy = strategy;
            }
        }
    }

    public List<Position> activate(Position position, Set<Position> setUsages){
        switch (strategy){
            case "fires" -> {
                return entitiesManager.activateEntities(position);
            }
            case "fireFighter", "healer", "clouds" -> {
                return Collections.singletonList(entitiesManager.activateEntitiesNeedSet(position, setUsages));
            }
            case "motorizedFireFighter" -> {
                Position position1 = entitiesManager.activateEntitiesNeedSet(position, setUsages);
                return Collections.singletonList(entitiesManager.activateEntitiesNeedSet(position1, setUsages));
            }
            case "virus" -> {
                return Collections.singletonList(entitiesManager.activateVirus());
            }
            case "people", "sicknessPeople" -> {
                return Collections.singletonList(entitiesManager.activatePeople(position));
            }
        }
        return null;
    }

    public void paint(int row, int col){
        entitiesManager.paint(row, col);
    }

    public List<Position> getNeighbor(Position position){
        return entitiesManager.getNeighbor(position);
    }

}
