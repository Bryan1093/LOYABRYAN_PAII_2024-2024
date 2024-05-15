package ec.uce.edu.basebeans.models;

import org.springframework.stereotype.Component;
public abstract class Role implements Drawable{
    private int [] coordX;
    private int [] coordY;
    public Role(){

    }
    public Role(int size) {
        this.coordX = new int[size];
        this.coordY = new int[size];
    }

    public int[] getCoordX() {
        return coordX;
    }

    public void setCoordX(int[] coordX) {
        this.coordX = coordX;
    }

    public int[] getCoordY() {
        return coordY;
    }

    public void setCoordY(int[] coordY) {
        this.coordY = coordY;
    }
}