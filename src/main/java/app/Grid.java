package app;

import app.configuration.ModelChoose;
import app.configuration.ModelConfigurationFire;
import app.configuration.ModelConfigurationVirus;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Grid extends Canvas{
    double width;
    double height;
    double colCount;
    double rowCount;
    ModelUse modelUse;

    ModelChoose modelChoose = new ModelChoose();

    public Grid(int width, int height, int colCount, int rowCount) {
        super(width,height);
        this.width = width;
        this.height = height;
        this.colCount = colCount;
        this.rowCount = rowCount;
        setFocusTraversable(true);
        setOnMousePressed(this::mousePressed);
        modelUse = new ModelUse(this);
        switch (modelChoose.getModelChoose()) {
            case 0 -> modelUse.initialisationFire(new ModelConfigurationFire());
            case 1 -> modelUse.initialisationVirus(new ModelConfigurationVirus());
        }
    }

    public void restart(MouseEvent mouseEvent){
        modelUse = new ModelUse(this);
        switch (modelChoose.getModelChoose()) {
            case 0 -> modelUse.initialisationFire(new ModelConfigurationFire());
            case 1 -> modelUse.initialisationVirus(new ModelConfigurationVirus());
        }
        getGraphicsContext2D().clearRect(0,0,width, height);
        repaint();
    }
    private void mousePressed(MouseEvent mouseEvent) {
        switch (modelChoose.getModelChoose()) {
            case 0 -> modelUse.activationFire();
            case 1 -> modelUse.activationVirus();
        }
        repaint();
    }

    void repaint(){
        for(int col=0; col<colCount; col++)
            getGraphicsContext2D().strokeLine(0, col*width/colCount, height, col*width/colCount);
        for(int row=0; row<rowCount;row++)
            getGraphicsContext2D().strokeLine(row*height/rowCount,0,row*height/rowCount, width);
    }

    public void paint(int row, int col){
        getGraphicsContext2D().setFill(Color.WHITE);
        getGraphicsContext2D().fillRect(row*height/rowCount,col*width/colCount,height/rowCount,width/colCount);
    }

    public double getRowCount() {
        return rowCount;
    }

    public double getColCount() {
        return colCount;
    }

}