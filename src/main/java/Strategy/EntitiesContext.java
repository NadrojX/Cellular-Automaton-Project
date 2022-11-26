package Strategy;

public class EntitiesContext {

    Entities entities;

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public void entitiesPaint(int row, int col){
        entities.paint(row, col);
    }
}
