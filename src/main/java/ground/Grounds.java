package ground;

import app.Position;

public interface Grounds {

    void paint(int row, int col);
    Position activate(Position position);
}
