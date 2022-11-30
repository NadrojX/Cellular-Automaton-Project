package entities;

import app.Position;

import java.util.Set;

public interface ExtinguishEntities {

    Position aStepTowardFire(Position position, Set<Position> fires);
    void extinguish(Position position, Set<Position> fires);

}
