package App;

public record Position(int row, int col) {

    public Position randomPosition(double rowCount, double colCount){
        return new Position((int) (Math.random() * rowCount), (int) (Math.random() * colCount));
    }

}
