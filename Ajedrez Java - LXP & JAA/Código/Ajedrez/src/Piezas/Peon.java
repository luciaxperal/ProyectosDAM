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
public class Peon extends Pieza{
//    Indica si el peón se ha movido para permitir que se desplace 2 casillas en vez de 1.
    private boolean seHaMovido;

    public boolean isSeHaMovido() {
        return seHaMovido;
    }

    public void setSeHaMovido(boolean seHaMovido) {
        this.seHaMovido = seHaMovido;
    }
    
    
    

    public Peon(Color color, int x, int y) {
        super(color, x, y);
        this.seHaMovido =false;
    }

    
    
    @Override
    public boolean puedeMoverseA(int x, int y) {
        
        if(this.getColor().equals(Color.BLANCA)){
        
        int diffY = y - super.getY();

        
        if(this.seHaMovido==false){
            
        if (super.getY() != y && super.getX() == x && diffY==2) {
             this.setSeHaMovido(true);
            return true;
           
        } 
        if (super.getY() != y && super.getX() == x && diffY==1) {
             this.setSeHaMovido(true);
            return true;
        } 
      }
        else if (this.seHaMovido==true) {
           if (super.getY() != y && super.getX() == x && diffY==1) {
             this.setSeHaMovido(true);
               return true;
        }
        
        
        
        
        
      }
        return false;
        }
          if(this.getColor().equals(Color.NEGRA)){
           
        int diffY = y - super.getY();

        
        if(this.seHaMovido==false){
            
        if (super.getY() != y && super.getX() == x && diffY==-2) {
             this.setSeHaMovido(true);
            return true;
           
        } 
        if (super.getY() != y && super.getX() == x && diffY==-1) {
             this.setSeHaMovido(true);
            return true;
        } 
      }
        else if (this.seHaMovido==true) {
           if (super.getY() != y && super.getX() == x && diffY==-1) {
             this.setSeHaMovido(true);
               return true;
        }
        
        
        
        
        
      }
        return false;
        }
        return false;
    }

  
     @Override
    public void agnadirIcono(Color c) {
        if(c.equals(Color.BLANCA)){
            this.setIcono("\u2659 ");
        }
        else{
            this.setIcono("\u265F " );
        }
    }
    
}
