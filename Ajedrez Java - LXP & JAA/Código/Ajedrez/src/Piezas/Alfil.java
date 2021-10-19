/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Piezas;

/**
 *
 * @author Jesús y Lucía
 */
public class Alfil extends Pieza {

    public Alfil(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean puedeMoverseA(int x, int y) {
        int diffX = Math.abs(x - super.getX());
        int diffY = Math.abs(y - super.getY());

        if (diffX == diffY) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void agnadirIcono(Color c) {
        if (c.equals(Color.BLANCA)) {
            this.setIcono("\u2657 ");
        } else {
            this.setIcono("\u265D ");
        }
    }

}
