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
public abstract class Pieza {
   //Color Blanco o Negro obtenido del Enum Color.
    private Color color;
    //"Icono" en formato String. Aquí se almacena una cadena Unicode que muestra su icono del Ajedrez.
    private String icono;
    
//    Coordenadas que se asignan a la pieza al inicio de la partida y después de cada movimiento.
    private int x;
    private int y;
    

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setPosicion(int x, int y){
        this.x = x;
        this.y = y;
    }

  

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
    

    
    //El constructor añade el icono que corresponde según se crea usando el método agnadirIcono.
    public Pieza(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        
        this.agnadirIcono(color);
    }

    //Abstracto y desarrollado en las subclases individualmente, añade el icono adecuado a cada ficha,
    //teniendo en cuenta su color.
    public abstract void agnadirIcono(Color c);
    
    //Método que espera unas coordenadas y devuelve true si el movimiento es permitido por las reglas del ajedrez.
    //El comportamiento depende de la pieza y por tanto el código es único y adaptado a cada una de ellas por separado.
    public abstract boolean puedeMoverseA(int x, int y);





}
