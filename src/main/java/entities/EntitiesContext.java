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
            case "ffs" -> {
                entitiesManager = new FiresFightersEntities(grid);
                this.strategy = strategy;
            }
            case "fs" -> {
                entitiesManager = new FiresEntities(grid);
                this.strategy = strategy;
            }
            case "mffs" -> {
                entitiesManager = new MotorizedFFEntities(grid);
                this.strategy = strategy;
            }
            case "clouds" -> {
                entitiesManager = new CloudsEntity(grid);
                this.strategy = strategy;
            }
        }
    }

    public void paint(int row, int col){
        entitiesManager.paint(row, col);
    }

    public List<Position> activate(Position position, Set<Position> fires){
        switch (strategy){
            case "fs" -> {
                return entitiesManager.activateFire(position);
            }
            case "ffs" -> {
                return Collections.singletonList(entitiesManager.activateFirefighter(position, fires));
            }
            case "mffs" -> {
                Position position1 = entitiesManager.activateFirefighter(position, fires);
                return Collections.singletonList(entitiesManager.activateFirefighter(position1, fires));
            }
            case "clouds" -> {
                return Collections.singletonList(entitiesManager.activateClouds(position, fires));
            }
        }
        return null;
    }
}
