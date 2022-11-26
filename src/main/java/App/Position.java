package App;

public record Position(int row, int col) {

    public Position randomPosition(){
        return new Position((int) (Math.random() * row), (int) (Math.random() * col));
    }

}
