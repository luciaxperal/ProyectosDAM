/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nucleo;

import Interfaz_Grafica.Inicio;
import Interfaz_Grafica.Tablero;
import Piezas.Alfil;
import Piezas.Caballo;
import Piezas.Color;
import Piezas.Peon;
import Piezas.Pieza;
import Piezas.Reina;
import Piezas.Rey;
import Piezas.Torre;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesús y Lucía
 */
public class Cerebro {

    //Array que contiene todas las fichas al inicio de la partida.
    private ArrayList<Pieza> fichas;
    //Array que contiene las fichas que están eliminadas.
    private ArrayList<Pieza> cementerio;

    public Cerebro() {

        fichas = new ArrayList<>();
        cementerio = new ArrayList<>();

        //FICHAS BLANCAS 
        Peon bp1 = new Peon(Color.BLANCA, 0, 1);
        Peon bp2 = new Peon(Color.BLANCA, 1, 1);
        Peon bp3 = new Peon(Color.BLANCA, 2, 1);
        Peon bp4 = new Peon(Color.BLANCA, 3, 1);
        Peon bp5 = new Peon(Color.BLANCA, 4, 1);
        Peon bp6 = new Peon(Color.BLANCA, 5, 1);
        Peon bp7 = new Peon(Color.BLANCA, 6, 1);
        Peon bp8 = new Peon(Color.BLANCA, 7, 1);
        Torre bt1 = new Torre(Color.BLANCA, 0, 0);

        Torre bt2 = new Torre(Color.BLANCA, 7, 0);
        Caballo bc1 = new Caballo(Color.BLANCA, 1, 0);
        Caballo bc2 = new Caballo(Color.BLANCA, 6, 0);
        Alfil ba1 = new Alfil(Color.BLANCA, 2, 0);
        Alfil ba2 = new Alfil(Color.BLANCA, 5, 0);
        Rey bk = new Rey(Color.BLANCA, 4, 0);
        Reina bq = new Reina(Color.BLANCA, 3, 0);
        //FICHAS NEGRAS 
        Peon np1 = new Peon(Color.NEGRA, 0, 6);
        Peon np2 = new Peon(Color.NEGRA, 1, 6);
        Peon np3 = new Peon(Color.NEGRA, 2, 6);
        Peon np4 = new Peon(Color.NEGRA, 3, 6);
        Peon np5 = new Peon(Color.NEGRA, 4, 6);
        Peon np6 = new Peon(Color.NEGRA, 5, 6);
        Peon np7 = new Peon(Color.NEGRA, 6, 6);
        Peon np8 = new Peon(Color.NEGRA, 7, 6);
        Torre nt1 = new Torre(Color.NEGRA, 0, 7);
        Torre nt2 = new Torre(Color.NEGRA, 7, 7);
        Caballo nc1 = new Caballo(Color.NEGRA, 1, 7);
        Caballo nc2 = new Caballo(Color.NEGRA, 6, 7);
        Alfil na1 = new Alfil(Color.NEGRA, 2, 7);
        Alfil na2 = new Alfil(Color.NEGRA, 5, 7);
        Rey nk = new Rey(Color.NEGRA, 4, 7);
        Reina nq = new Reina(Color.NEGRA, 3, 7);

        this.fichas.add(bp1);
        this.fichas.add(bp2);
        this.fichas.add(bp3);
        this.fichas.add(bp4);
        this.fichas.add(bp5);
        this.fichas.add(bp6);
        this.fichas.add(bp7);
        this.fichas.add(bp8);
        this.fichas.add(bt1);
        this.fichas.add(bt2);
        this.fichas.add(bc1);
        this.fichas.add(bc2);
        this.fichas.add(ba1);
        this.fichas.add(ba2);
        this.fichas.add(bk);
        this.fichas.add(bq);

        this.fichas.add(np1);
        this.fichas.add(np2);
        this.fichas.add(np3);
        this.fichas.add(np4);
        this.fichas.add(np5);
        this.fichas.add(np6);
        this.fichas.add(np7);
        this.fichas.add(np8);
        this.fichas.add(nt1);
        this.fichas.add(nt2);
        this.fichas.add(nc1);
        this.fichas.add(nc2);
        this.fichas.add(na1);
        this.fichas.add(na2);
        this.fichas.add(nk);
        this.fichas.add(nq);

    }

    public ArrayList<Pieza> getFichas() {
        return fichas;
    }

    public void setFichas(ArrayList<Pieza> fichas) {
        this.fichas = fichas;
    }

    public ArrayList<Pieza> getCementerio() {
        return cementerio;
    }

    public void setCementerio(ArrayList<Pieza> cementerio) {
        this.cementerio = cementerio;
    }

//    Este método devuelve false si en las coordenadas indicadas o bien no hay un enemigo 
//    o si hay un aliado. True si hay un enemigo.
    public boolean enemigoEnCoordenadas(Color usuario, int x, int y) {

        for (Pieza p : this.getFichas()) {
            if (p.getX() == x && p.getY() == y) {

                if (p.getColor().toString().equals(usuario.toString())) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
//    Este método devuelve true solo si se encuentra un aliado en las coordenadas indicadas.
//    False si es un enemigo o si no hay nadie.

    public boolean aliadoEnCoordenadas(Color usuario, int x, int y) {

        for (Pieza p : this.getFichas()) {
            if (p.getX() == x && p.getY() == y) {

                if (p.getColor().toString().equals(usuario.toString())) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

//    Este método nace de la necesidad de comprobar el movimiento del peon
//    mientras se tiene en cuenta si hay enemigos en diagonal (ya que la clase Peon no tiene
//    acceso a la controladora). Utiliza el "enemigoEnCoordenadas" previamente explicado, y parte de 
//    la lógica de programación usada para encontrar movimientos en diagonal. Devuelve true si el peon
//    se mueve en diagonal Y hay un enemigo en dicha casilla. El limitante para la distancia a la que esto 
//    puede suceder se encuentra en la clase peon y su metodo "puedeMoverseA".
    public boolean hayEnemigoEnDiagonalPeon(Pieza peon, int x, int y) {
        int diffX = Math.abs(x - peon.getX());
        int diffY = Math.abs(y - peon.getY());

        if (diffX == diffY && diffX == 1 && enemigoEnCoordenadas(peon.getColor(), x, y)) {
            return true;
        }
        return false;
    }

    //El método mueveFicha es el corazón de todo el funcionamiento de este ajedrez. Implementa todo el resto de métodos
    //tanto de la clase cerebro como de las clases pieza, para , mediante una larga cadena de condiciones, averiguar si el movimiento:
    // 1 Es posible según las reglas del ajedrez
    // 2 Es "físicamente" posible (Sin colisiones, sin coger el espacio de un aliado, etc)
    // TLDR; Devuelve true si la pieza se puede mover/comer teniendo en cuenta las normas del ajedrez.
    public boolean mueveFicha(Pieza piezaAMover, int x, int y) {
        try {

            if (!outOfBounds(x, y)) {

                if (colisionador(piezaAMover, x, y) == false) {

                    if (piezaAMover instanceof Peon && hayEnemigoEnDiagonalPeon(piezaAMover, x, y)) {
                        if (enemigoEnCoordenadas(piezaAMover.getColor(), x, y)) {
                            JOptionPane.showMessageDialog(null, "SE COME AL ENEMIGO");

                            this.cementerio.add(encuentraFicha(x, y));
                            this.fichas.remove(encuentraFicha(x, y));
                            piezaAMover.setPosicion(x, y);
                            return true;

                        } else {
                            if (aliadoEnCoordenadas(piezaAMover.getColor(), x, y)) {
                                JOptionPane.showMessageDialog(null, "NO SE PUEDE MOVER, ES UN ALIADO");
                                return false;

                            } else {

                                piezaAMover.setPosicion(x, y);
                                return true;
                            }
                        }

                    }

                    if (piezaAMover.puedeMoverseA(x, y)) {
                        if (enemigoEnCoordenadas(piezaAMover.getColor(), x, y)) {
                            if (piezaAMover instanceof Peon) {
                                JOptionPane.showMessageDialog(null, "NO SE PUEDE MOVER");
                                return false;
                            } else {
                                JOptionPane.showMessageDialog(null, "SE COME AL ENEMIGO");

                                this.cementerio.add(encuentraFicha(x, y));
                                this.fichas.remove(encuentraFicha(x, y));
                                piezaAMover.setPosicion(x, y);
                                return true;
                            }
                        } else {
                            if (aliadoEnCoordenadas(piezaAMover.getColor(), x, y)) {

                                JOptionPane.showMessageDialog(null, "NO SE PUEDE MOVER, ES UN ALIADO");
                                return false;

                            } else {

                                piezaAMover.setPosicion(x, y);
                                return true;
                            }
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "NO SE PUEDE MOVER, HAY UNA FICHA EN EL CAMINO");

                    return false;
                }

            }
        } catch (NullPointerException ex) {
            System.out.print("");
        }
        return false;
    }

//    Método que devuelve el objeto Pieza que se encuentre en las coordenadas que se le pasan.
//    Devuelve null si no hay una ficha en dichas coordenadas.
    public Pieza encuentraFicha(int x, int y) {
        for (Pieza p : this.getFichas()) {
            if (p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        return null;
    }

//    Método que devuelve true si las coordenadas que se le dan se salen de los límites del tablero
//    (Cualquier coordenada que sea mayor que 7 o inferior a 0)
    public boolean outOfBounds(int x, int y) {
        boolean xOutOfBounds = (x < 0 || x > 7);
        boolean yOutOfBounds = (y < 0 || y > 7);

        if (yOutOfBounds || xOutOfBounds) {
            return true;
        }

        return false;
    }

    //Método que comprueba que haya un rey muerto en el cementerio, y devuelve true en ese caso.
    //Condición utilizada para acabar la partida.
    public boolean winState() {
        for (Pieza dead : this.cementerio) {
            if (dead instanceof Rey) {
                return true;
            }
        }
        return false;
    }

//    Método que simula el viaje de una ficha hasta su destino, y detecta si está colisionando contra otra,
//    sea esta aliada o enemiga. Sirve para limitar los movimientos que una ficha puede hacer, excluyendo al caballo, el cual
//    puede saltar fichas. 
    public boolean colisionador(Pieza piezaAMover, int x, int y) {
        int initX = piezaAMover.getX();
        int initY = piezaAMover.getY();
        boolean mueveX = (x != initX);
        boolean mueveY = (y != initY);
        int sumaOrestaX = 0;
        int sumaOrestaY = 0;
        if (x < initX) {
            sumaOrestaX = -1;
        }
        if (x > initX) {
            sumaOrestaX = 1;
        }

        if (y < initY) {
            sumaOrestaY = -1;
        }
        if (y > initY) {
            sumaOrestaY = 1;
        }

        if (piezaAMover instanceof Caballo) {
            return false;
        }

        if (mueveY == true && mueveX == true) {
            while (initY != (y - sumaOrestaY)) {
                initX = initX + sumaOrestaX;
                initY = initY + sumaOrestaY;
                if (encuentraFicha(initX, initY) != null) {
                    return true;
                }
            }

        } else if (mueveX == true && mueveY == false) {
            while (initX != (x - sumaOrestaX)) {
                initX = initX + sumaOrestaX;
                if (encuentraFicha(initX, initY) != null) {
                    return true;
                }
            }

        } else if (mueveY == true && mueveX == false) {
            while (initY != (y - sumaOrestaY)) {
                initY = initY + sumaOrestaY;
                if (encuentraFicha(initX, initY) != null) {
                    return true;
                }
            }

        }
        return false;
    }

    //Este método inicia (o reinicia si se usa de nuevo) las variables adecuadas para el correcto funcionamiento del ajedrez.
    //Se usa para reiniciar la partida cuando
    // esta acaba, o cuando el usuario decida y también al darle a jugar en la pantalla de inicio.
    public static void reset() {
        Cerebro c = new Cerebro();

        Tablero tabla = new Tablero(c);
        tabla.setVisible(true);
    }

    public static void main(String[] args) {

        //Aqui hacemos visible la pantalla de inicio
        Inicio ini = new Inicio();
        ini.setVisible(true);

    }

}
