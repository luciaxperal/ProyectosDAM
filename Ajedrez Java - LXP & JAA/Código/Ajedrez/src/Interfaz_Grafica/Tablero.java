/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz_Grafica;

import Nucleo.Cerebro;
import Piezas.Color;
import Piezas.Pieza;
import java.awt.Image;

import java.util.HashMap;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesús y Lucía
 */
public class Tablero extends javax.swing.JFrame {

    private Cerebro c;
    //Coordenadas en string de la pieza seleccionada y en uso.
    private String piezaEnUso;
    //Color que corresponde al jugador que posee el turno en este momento.
    private Color turnoDe;
    //Int que determina el número de turnos que han pasado.
    private int turnos;

    //HashMap que contiene todos los botones que se usan como tablero.
    //A la hora de añadirlos, como clave se les dará sus "Coordenadas" en string.
    private HashMap<String, JButton> botones;

    //En el constructor tenemos el código para añadir los botones al HashMap.
    //También refresca el tablero y mediante un método explicado más adelante, 
    //configura el turno inicial.
    public Tablero(Cerebro c) {
        this.c = c;
        initComponents();

        //Aqui estamos poniendo una imagen de fondo para que quede bonito
        ImageIcon portada = new ImageIcon(getClass().getResource("ajedrezdesenfoque.jpg"));
        Image img = portada.getImage();
        img = img.getScaledInstance(jLabelFondo.getWidth(), jLabelFondo.getHeight(), java.awt.Image.SCALE_SMOOTH);
        portada.setImage(img);
        jLabelFondo.setIcon(portada);

        piezaEnUso = null;
        this.setTurnoDe(Color.NEGRA);
        this.setTurnos(0);
        this.botones = new HashMap<>();

        botones.put("00", jButton00);
        botones.put("01", jButton01);
        botones.put("02", jButton02);
        botones.put("03", jButton03);
        botones.put("04", jButton04);
        botones.put("05", jButton05);
        botones.put("06", jButton06);
        botones.put("07", jButton07);
        botones.put("10", jButton10);
        botones.put("11", jButton11);
        botones.put("12", jButton12);
        botones.put("13", jButton13);
        botones.put("14", jButton14);
        botones.put("15", jButton15);
        botones.put("16", jButton16);
        botones.put("17", jButton17);
        botones.put("20", jButton20);
        botones.put("21", jButton21);
        botones.put("22", jButton22);
        botones.put("23", jButton23);
        botones.put("24", jButton24);
        botones.put("25", jButton25);
        botones.put("26", jButton26);
        botones.put("27", jButton27);
        botones.put("30", jButton30);
        botones.put("31", jButton31);
        botones.put("32", jButton32);
        botones.put("33", jButton33);
        botones.put("34", jButton34);
        botones.put("35", jButton35);
        botones.put("36", jButton36);
        botones.put("37", jButton37);
        botones.put("40", jButton40);
        botones.put("41", jButton41);
        botones.put("42", jButton42);
        botones.put("43", jButton43);
        botones.put("44", jButton44);
        botones.put("45", jButton45);
        botones.put("46", jButton46);
        botones.put("47", jButton47);
        botones.put("50", jButton50);
        botones.put("51", jButton51);
        botones.put("52", jButton52);
        botones.put("53", jButton53);
        botones.put("54", jButton54);
        botones.put("55", jButton55);
        botones.put("56", jButton56);
        botones.put("57", jButton57);
        botones.put("60", jButton60);
        botones.put("61", jButton61);
        botones.put("62", jButton62);
        botones.put("63", jButton63);
        botones.put("64", jButton64);
        botones.put("65", jButton65);
        botones.put("66", jButton66);
        botones.put("67", jButton67);
        botones.put("70", jButton70);
        botones.put("71", jButton71);
        botones.put("72", jButton72);
        botones.put("73", jButton73);
        botones.put("74", jButton74);
        botones.put("75", jButton75);
        botones.put("76", jButton76);
        botones.put("77", jButton77);

        refrescarTablero();
        this.turnos = 0;
        actualizarTurno(turnoDe);
        refrescarContadorTurno();
    }

    public Cerebro getC() {
        return c;
    }

    public void setC(Cerebro c) {
        this.c = c;
    }

    public HashMap<String, JButton> getBotones() {
        return botones;
    }

    public void setBotones(HashMap<String, JButton> botones) {
        this.botones = botones;
    }

    public String getPiezaEnUso() {
        return piezaEnUso;
    }

    public void setPiezaEnUso(String piezaEnUso) {
        this.piezaEnUso = piezaEnUso;
    }

    public Color getTurnoDe() {
        return turnoDe;
    }

    public void setTurnoDe(Color turnoDe) {
        this.turnoDe = turnoDe;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    //Método que refresca el GUI de los turnos.
    public void refrescarContadorTurno() {

        jLabelRonda.setText("Ronda: " + this.getTurnos());
    }

    //Método que cambia el turno activo a el jugador opuesto según el color que le pases
    //del jugador anterior. También suma 1 al contador de turnos.
    public void actualizarTurno(Color ultimaPieza) {
        if (ultimaPieza.toString().equals("BLANCA")) {
            this.setTurnoDe(Color.NEGRA);
            jLabelTurnoDe.setText("TURNO DE: " + this.turnoDe.toString());

        } else {
            this.setTurnoDe(Color.BLANCA);
            jLabelTurnoDe.setText("TURNO DE: " + this.turnoDe.toString());

        }
        this.turnos++;
    }

    //Método que actualiza el estado visual del tablero. 
    //Dos bucles realizan 2 barridos a todos los botones, 
    //recorriendo según su string de coordenada y hacen dos cosas:
    //1 Poner el icono de la ficha en los botones que correspondan.
    //2 Quitar el icono de la ficha en los botones que correspondan.
    public void refrescarTablero() {
        for (String s : this.getBotones().keySet()) {

            int x = Integer.parseInt(Character.toString(s.charAt(0)));
            int y = Integer.parseInt(Character.toString(s.charAt(1)));

            for (Pieza p : c.getFichas()) {
                if (p.getX() == x && p.getY() == y) {
                    botones.get(s).setText(p.getIcono());

                }

            }
        }
        for (String s : this.getBotones().keySet()) {
            int x = Integer.parseInt(Character.toString(s.charAt(0)));
            int y = Integer.parseInt(Character.toString(s.charAt(1)));

            if (!c.getFichas().contains(c.encuentraFicha(x, y))) {
                botones.get(s).setText("");
            }

        }
        String losMuertos = "";
        for (Pieza dead : c.getCementerio()) {
            losMuertos = losMuertos + " " + dead.getIcono();
        }

        jTextAreaCementerio.setText(losMuertos);
    }

    //Refresca el GUI que muestra la pieza en uso.
    public void refrescarGUI(Pieza p) {
        jLabelFichaEnUso.setText("PIEZA EN USO: " + p.getIcono());

    }

    //Método que se encarga de plasmar los movimientos de las fichas en la interfaz.
    //Hace uso del "mueveFicha" que se encuentra en cerebro, y dependiendo de si hay una ficha en uso o no, 
    //permite dos comportamientos para cada botón:
    //Caso 1: te deja elegir la ficha del botón como ficha en uso
    //Caso 2: mueve la ficha en uso a este botón del tablero
    //También hace la comprobación de si la partida se ha acabado e informa al usuario de esto.
    //Este método se asigna a TODOS LOS BOTONES DEL TABLERO y cada uno de ellos le pasa la 
    //coordenada en String apropiada para su funcionamiento.
    public void accion(String coord) {

        if (this.piezaEnUso == null) {

            for (Pieza p : c.getFichas()) {
                String clave = "" + p.getX() + p.getY();
                if (clave.equals(coord)) {

                    if (p.getColor().toString().equals(this.turnoDe.toString())) {
                        this.setPiezaEnUso(clave);
                        refrescarGUI(p);
                    } else {
                        JOptionPane.showMessageDialog(this, "El turno es de las fichas " + this.turnoDe + "S, seleccione una ficha de su turno.");
                    }

                }
            }

        } else {

            Pieza quesemueve;
            quesemueve = c.encuentraFicha(Integer.parseInt(Character.toString(this.piezaEnUso.charAt(0))), Integer.parseInt(Character.toString(this.piezaEnUso.charAt(1))));

            if (c.mueveFicha(quesemueve,
                    Integer.parseInt(Character.toString(coord.charAt(0))), Integer.parseInt(Character.toString(coord.charAt(1))))) {
                actualizarTurno(quesemueve.getColor());
            }
            refrescarContadorTurno();
            refrescarTablero();
            this.piezaEnUso = null;
            jLabelFichaEnUso.setText("PIEZA EN USO: ");
            if (c.winState() || this.turnos >= 50) {

                if (this.turnos >= 50) {
                    JOptionPane.showMessageDialog(this, "¡Partida terminada! La partida llegó a 50 Turnos: EMPATE");
                } else {
                    JOptionPane.showMessageDialog(this, "¡Partida terminada! Ha ganado: " + this.turnoDe + "S");

                }
                this.reiniciarPartida();
            }

        }

    }

    //Se libra de la ventana actual y llama al reset del cerebro para iniciar otra partida.
    public void reiniciarPartida() {
        this.dispose();
        Cerebro.reset();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton07 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton57 = new javax.swing.JButton();
        jButton67 = new javax.swing.JButton();
        jButton77 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton06 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton76 = new javax.swing.JButton();
        jButton66 = new javax.swing.JButton();
        jButton65 = new javax.swing.JButton();
        jButton74 = new javax.swing.JButton();
        jButton75 = new javax.swing.JButton();
        jButton05 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton64 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton04 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton03 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();
        jButton70 = new javax.swing.JButton();
        jButton71 = new javax.swing.JButton();
        jButton01 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton02 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        jButton72 = new javax.swing.JButton();
        jButton73 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton00 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton62 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jLabelFichaEnUso = new javax.swing.JLabel();
        jLabelTurnoDe = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaCementerio = new javax.swing.JTextArea();
        jButtonReiniciar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabelRonda = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1014, 750));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jButton07.setBackground(new java.awt.Color(255, 255, 255));
        jButton07.setFont(jButton07.getFont().deriveFont(jButton07.getFont().getSize()+17f));
        jButton07.setBorderPainted(false);
        jButton07.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton07.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton07.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton07.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton07ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton07);
        jButton07.setBounds(325, 33, 73, 73);

        jButton17.setBackground(new java.awt.Color(153, 153, 153));
        jButton17.setFont(jButton17.getFont().deriveFont(jButton17.getFont().getSize()+17f));
        jButton17.setBorderPainted(false);
        jButton17.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton17.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton17.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton17);
        jButton17.setBounds(407, 33, 73, 73);

        jButton27.setBackground(new java.awt.Color(255, 255, 255));
        jButton27.setFont(jButton27.getFont().deriveFont(jButton27.getFont().getSize()+17f));
        jButton27.setBorderPainted(false);
        jButton27.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton27.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton27.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton27);
        jButton27.setBounds(489, 33, 73, 73);

        jButton37.setBackground(new java.awt.Color(153, 153, 153));
        jButton37.setFont(jButton37.getFont().deriveFont(jButton37.getFont().getSize()+17f));
        jButton37.setBorderPainted(false);
        jButton37.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton37.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton37.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton37);
        jButton37.setBounds(571, 33, 73, 73);

        jButton47.setBackground(new java.awt.Color(255, 255, 255));
        jButton47.setFont(jButton47.getFont().deriveFont(jButton47.getFont().getSize()+17f));
        jButton47.setBorderPainted(false);
        jButton47.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton47.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton47.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton47);
        jButton47.setBounds(653, 33, 73, 73);

        jButton57.setBackground(new java.awt.Color(153, 153, 153));
        jButton57.setFont(jButton57.getFont().deriveFont(jButton57.getFont().getSize()+17f));
        jButton57.setBorderPainted(false);
        jButton57.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton57.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton57.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton57ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton57);
        jButton57.setBounds(735, 33, 73, 73);

        jButton67.setBackground(new java.awt.Color(255, 255, 255));
        jButton67.setFont(jButton67.getFont().deriveFont(jButton67.getFont().getSize()+17f));
        jButton67.setBorderPainted(false);
        jButton67.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton67.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton67.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton67ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton67);
        jButton67.setBounds(817, 33, 73, 73);

        jButton77.setBackground(new java.awt.Color(153, 153, 153));
        jButton77.setFont(jButton77.getFont().deriveFont(jButton77.getFont().getSize()+17f));
        jButton77.setBorderPainted(false);
        jButton77.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton77.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton77.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton77ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton77);
        jButton77.setBounds(899, 33, 73, 73);

        jButton16.setBackground(new java.awt.Color(255, 255, 255));
        jButton16.setFont(jButton16.getFont().deriveFont(jButton16.getFont().getSize()+17f));
        jButton16.setBorderPainted(false);
        jButton16.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton16.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton16.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton16);
        jButton16.setBounds(407, 115, 73, 73);

        jButton06.setBackground(new java.awt.Color(153, 153, 153));
        jButton06.setFont(jButton06.getFont().deriveFont(jButton06.getFont().getSize()+17f));
        jButton06.setBorderPainted(false);
        jButton06.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton06.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton06.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton06ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton06);
        jButton06.setBounds(325, 115, 73, 73);

        jButton36.setBackground(new java.awt.Color(255, 255, 255));
        jButton36.setFont(jButton36.getFont().deriveFont(jButton36.getFont().getSize()+17f));
        jButton36.setBorderPainted(false);
        jButton36.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton36.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton36.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton36);
        jButton36.setBounds(571, 115, 73, 73);

        jButton26.setBackground(new java.awt.Color(153, 153, 153));
        jButton26.setFont(jButton26.getFont().deriveFont(jButton26.getFont().getSize()+17f));
        jButton26.setBorderPainted(false);
        jButton26.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton26.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton26.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton26);
        jButton26.setBounds(489, 115, 73, 73);

        jButton56.setBackground(new java.awt.Color(255, 255, 255));
        jButton56.setFont(jButton56.getFont().deriveFont(jButton56.getFont().getSize()+17f));
        jButton56.setBorderPainted(false);
        jButton56.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton56.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton56.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton56);
        jButton56.setBounds(735, 115, 73, 73);

        jButton46.setBackground(new java.awt.Color(153, 153, 153));
        jButton46.setFont(jButton46.getFont().deriveFont(jButton46.getFont().getSize()+17f));
        jButton46.setBorderPainted(false);
        jButton46.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton46.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton46.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton46);
        jButton46.setBounds(653, 115, 73, 73);

        jButton76.setBackground(new java.awt.Color(255, 255, 255));
        jButton76.setFont(jButton76.getFont().deriveFont(jButton76.getFont().getSize()+17f));
        jButton76.setBorderPainted(false);
        jButton76.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton76.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton76.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton76ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton76);
        jButton76.setBounds(899, 115, 73, 73);

        jButton66.setBackground(new java.awt.Color(153, 153, 153));
        jButton66.setFont(jButton66.getFont().deriveFont(jButton66.getFont().getSize()+17f));
        jButton66.setBorderPainted(false);
        jButton66.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton66.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton66.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton66ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton66);
        jButton66.setBounds(817, 115, 73, 73);

        jButton65.setBackground(new java.awt.Color(255, 255, 255));
        jButton65.setFont(jButton65.getFont().deriveFont(jButton65.getFont().getSize()+17f));
        jButton65.setBorderPainted(false);
        jButton65.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton65.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton65.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton65ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton65);
        jButton65.setBounds(817, 192, 73, 74);

        jButton74.setBackground(new java.awt.Color(255, 255, 255));
        jButton74.setFont(jButton74.getFont().deriveFont(jButton74.getFont().getSize()+17f));
        jButton74.setBorderPainted(false);
        jButton74.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton74.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton74.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton74ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton74);
        jButton74.setBounds(899, 275, 73, 73);

        jButton75.setBackground(new java.awt.Color(153, 153, 153));
        jButton75.setFont(jButton75.getFont().deriveFont(jButton75.getFont().getSize()+17f));
        jButton75.setBorderPainted(false);
        jButton75.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton75.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton75.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton75ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton75);
        jButton75.setBounds(899, 192, 73, 74);

        jButton05.setBackground(new java.awt.Color(255, 255, 255));
        jButton05.setFont(jButton05.getFont().deriveFont(jButton05.getFont().getSize()+17f));
        jButton05.setBorderPainted(false);
        jButton05.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton05.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton05.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton05ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton05);
        jButton05.setBounds(325, 192, 73, 74);

        jButton15.setBackground(new java.awt.Color(153, 153, 153));
        jButton15.setFont(jButton15.getFont().deriveFont(jButton15.getFont().getSize()+17f));
        jButton15.setBorderPainted(false);
        jButton15.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton15.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton15.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton15);
        jButton15.setBounds(407, 192, 73, 73);

        jButton25.setBackground(new java.awt.Color(255, 255, 255));
        jButton25.setFont(jButton25.getFont().deriveFont(jButton25.getFont().getSize()+17f));
        jButton25.setBorderPainted(false);
        jButton25.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton25.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton25.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton25);
        jButton25.setBounds(489, 192, 73, 74);

        jButton64.setBackground(new java.awt.Color(153, 153, 153));
        jButton64.setFont(jButton64.getFont().deriveFont(jButton64.getFont().getSize()+17f));
        jButton64.setBorderPainted(false);
        jButton64.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton64.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton64.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton64ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton64);
        jButton64.setBounds(817, 275, 73, 73);

        jButton14.setBackground(new java.awt.Color(255, 255, 255));
        jButton14.setFont(jButton14.getFont().deriveFont(jButton14.getFont().getSize()+17f));
        jButton14.setBorderPainted(false);
        jButton14.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton14.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton14.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14);
        jButton14.setBounds(407, 275, 73, 73);

        jButton04.setBackground(new java.awt.Color(153, 153, 153));
        jButton04.setFont(jButton04.getFont().deriveFont(jButton04.getFont().getSize()+17f));
        jButton04.setBorderPainted(false);
        jButton04.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton04.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton04.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton04ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton04);
        jButton04.setBounds(325, 275, 73, 73);

        jButton34.setBackground(new java.awt.Color(255, 255, 255));
        jButton34.setFont(jButton34.getFont().deriveFont(jButton34.getFont().getSize()+17f));
        jButton34.setBorderPainted(false);
        jButton34.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton34.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton34.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton34);
        jButton34.setBounds(571, 275, 73, 73);

        jButton35.setBackground(new java.awt.Color(153, 153, 153));
        jButton35.setFont(jButton35.getFont().deriveFont(jButton35.getFont().getSize()+17f));
        jButton35.setBorderPainted(false);
        jButton35.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton35.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton35.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton35);
        jButton35.setBounds(571, 192, 73, 74);

        jButton24.setBackground(new java.awt.Color(153, 153, 153));
        jButton24.setFont(jButton24.getFont().deriveFont(jButton24.getFont().getSize()+17f));
        jButton24.setBorderPainted(false);
        jButton24.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton24.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton24.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton24);
        jButton24.setBounds(489, 275, 73, 73);

        jButton45.setBackground(new java.awt.Color(255, 255, 255));
        jButton45.setFont(jButton45.getFont().deriveFont(jButton45.getFont().getSize()+17f));
        jButton45.setBorderPainted(false);
        jButton45.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton45.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton45.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton45);
        jButton45.setBounds(653, 192, 73, 74);

        jButton54.setBackground(new java.awt.Color(255, 255, 255));
        jButton54.setFont(jButton54.getFont().deriveFont(jButton54.getFont().getSize()+17f));
        jButton54.setBorderPainted(false);
        jButton54.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton54.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton54.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton54);
        jButton54.setBounds(735, 275, 73, 73);

        jButton55.setBackground(new java.awt.Color(153, 153, 153));
        jButton55.setFont(jButton55.getFont().deriveFont(jButton55.getFont().getSize()+17f));
        jButton55.setBorderPainted(false);
        jButton55.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton55.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton55.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton55);
        jButton55.setBounds(735, 192, 73, 74);

        jButton44.setBackground(new java.awt.Color(153, 153, 153));
        jButton44.setFont(jButton44.getFont().deriveFont(jButton44.getFont().getSize()+17f));
        jButton44.setBorderPainted(false);
        jButton44.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton44.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton44.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton44);
        jButton44.setBounds(653, 275, 73, 73);

        jButton03.setBackground(new java.awt.Color(255, 255, 255));
        jButton03.setFont(jButton03.getFont().deriveFont(jButton03.getFont().getSize()+17f));
        jButton03.setBorderPainted(false);
        jButton03.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton03.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton03.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton03ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton03);
        jButton03.setBounds(325, 357, 73, 73);

        jButton13.setBackground(new java.awt.Color(153, 153, 153));
        jButton13.setFont(jButton13.getFont().deriveFont(jButton13.getFont().getSize()+17f));
        jButton13.setBorderPainted(false);
        jButton13.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton13.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton13.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13);
        jButton13.setBounds(407, 357, 73, 73);

        jButton23.setBackground(new java.awt.Color(255, 255, 255));
        jButton23.setFont(jButton23.getFont().deriveFont(jButton23.getFont().getSize()+17f));
        jButton23.setBorderPainted(false);
        jButton23.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton23.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton23.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton23);
        jButton23.setBounds(489, 357, 73, 73);

        jButton61.setBackground(new java.awt.Color(255, 255, 255));
        jButton61.setFont(jButton61.getFont().deriveFont(jButton61.getFont().getSize()+17f));
        jButton61.setBorderPainted(false);
        jButton61.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton61.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton61.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton61);
        jButton61.setBounds(817, 521, 73, 74);

        jButton70.setBackground(new java.awt.Color(255, 255, 255));
        jButton70.setFont(jButton70.getFont().deriveFont(jButton70.getFont().getSize()+17f));
        jButton70.setBorderPainted(false);
        jButton70.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton70.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton70.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton70ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton70);
        jButton70.setBounds(899, 604, 73, 73);

        jButton71.setBackground(new java.awt.Color(153, 153, 153));
        jButton71.setFont(jButton71.getFont().deriveFont(jButton71.getFont().getSize()+17f));
        jButton71.setBorderPainted(false);
        jButton71.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton71.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton71.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton71ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton71);
        jButton71.setBounds(899, 521, 73, 74);

        jButton01.setBackground(new java.awt.Color(255, 255, 255));
        jButton01.setFont(jButton01.getFont().deriveFont(jButton01.getFont().getSize()+17f));
        jButton01.setBorderPainted(false);
        jButton01.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton01.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton01.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton01ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton01);
        jButton01.setBounds(325, 521, 73, 74);

        jButton11.setBackground(new java.awt.Color(153, 153, 153));
        jButton11.setFont(jButton11.getFont().deriveFont(jButton11.getFont().getSize()+17f));
        jButton11.setBorderPainted(false);
        jButton11.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton11.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton11.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11);
        jButton11.setBounds(407, 521, 73, 73);

        jButton21.setBackground(new java.awt.Color(255, 255, 255));
        jButton21.setFont(jButton21.getFont().deriveFont(jButton21.getFont().getSize()+17f));
        jButton21.setBorderPainted(false);
        jButton21.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton21.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton21.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton21);
        jButton21.setBounds(489, 521, 73, 74);

        jButton60.setBackground(new java.awt.Color(153, 153, 153));
        jButton60.setFont(jButton60.getFont().deriveFont(jButton60.getFont().getSize()+17f));
        jButton60.setBorderPainted(false);
        jButton60.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton60.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton60.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton60ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton60);
        jButton60.setBounds(817, 604, 73, 73);

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setFont(jButton12.getFont().deriveFont(jButton12.getFont().getSize()+17f));
        jButton12.setBorderPainted(false);
        jButton12.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton12.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton12.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12);
        jButton12.setBounds(407, 439, 73, 73);

        jButton02.setBackground(new java.awt.Color(153, 153, 153));
        jButton02.setFont(jButton02.getFont().deriveFont(jButton02.getFont().getSize()+17f));
        jButton02.setBorderPainted(false);
        jButton02.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton02.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton02.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton02ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton02);
        jButton02.setBounds(325, 439, 73, 73);

        jButton32.setBackground(new java.awt.Color(255, 255, 255));
        jButton32.setFont(jButton32.getFont().deriveFont(jButton32.getFont().getSize()+17f));
        jButton32.setBorderPainted(false);
        jButton32.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton32.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton32.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton32);
        jButton32.setBounds(571, 439, 73, 73);

        jButton33.setBackground(new java.awt.Color(153, 153, 153));
        jButton33.setFont(jButton33.getFont().deriveFont(jButton33.getFont().getSize()+17f));
        jButton33.setBorderPainted(false);
        jButton33.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton33.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton33.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton33);
        jButton33.setBounds(571, 357, 73, 73);

        jButton22.setBackground(new java.awt.Color(153, 153, 153));
        jButton22.setFont(jButton22.getFont().deriveFont(jButton22.getFont().getSize()+17f));
        jButton22.setBorderPainted(false);
        jButton22.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton22.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton22.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton22);
        jButton22.setBounds(489, 439, 73, 73);

        jButton43.setBackground(new java.awt.Color(255, 255, 255));
        jButton43.setFont(jButton43.getFont().deriveFont(jButton43.getFont().getSize()+17f));
        jButton43.setBorderPainted(false);
        jButton43.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton43.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton43.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton43);
        jButton43.setBounds(653, 357, 73, 73);

        jButton52.setBackground(new java.awt.Color(255, 255, 255));
        jButton52.setFont(jButton52.getFont().deriveFont(jButton52.getFont().getSize()+17f));
        jButton52.setBorderPainted(false);
        jButton52.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton52.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton52.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton52);
        jButton52.setBounds(735, 439, 73, 73);

        jButton53.setBackground(new java.awt.Color(153, 153, 153));
        jButton53.setFont(jButton53.getFont().deriveFont(jButton53.getFont().getSize()+17f));
        jButton53.setBorderPainted(false);
        jButton53.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton53.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton53.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton53);
        jButton53.setBounds(735, 357, 73, 73);

        jButton42.setBackground(new java.awt.Color(153, 153, 153));
        jButton42.setFont(jButton42.getFont().deriveFont(jButton42.getFont().getSize()+17f));
        jButton42.setBorderPainted(false);
        jButton42.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton42.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton42.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton42);
        jButton42.setBounds(653, 439, 73, 73);

        jButton63.setBackground(new java.awt.Color(255, 255, 255));
        jButton63.setFont(jButton63.getFont().deriveFont(jButton63.getFont().getSize()+17f));
        jButton63.setBorderPainted(false);
        jButton63.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton63.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton63.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton63ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton63);
        jButton63.setBounds(817, 357, 73, 73);

        jButton72.setBackground(new java.awt.Color(255, 255, 255));
        jButton72.setFont(jButton72.getFont().deriveFont(jButton72.getFont().getSize()+17f));
        jButton72.setBorderPainted(false);
        jButton72.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton72.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton72.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton72ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton72);
        jButton72.setBounds(899, 439, 73, 73);

        jButton73.setBackground(new java.awt.Color(153, 153, 153));
        jButton73.setFont(jButton73.getFont().deriveFont(jButton73.getFont().getSize()+17f));
        jButton73.setBorderPainted(false);
        jButton73.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton73.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton73.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton73ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton73);
        jButton73.setBounds(899, 357, 73, 73);

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setFont(jButton10.getFont().deriveFont(jButton10.getFont().getSize()+17f));
        jButton10.setBorderPainted(false);
        jButton10.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton10.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton10.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10);
        jButton10.setBounds(407, 604, 73, 73);

        jButton00.setBackground(new java.awt.Color(153, 153, 153));
        jButton00.setFont(jButton00.getFont().deriveFont(jButton00.getFont().getSize()+17f));
        jButton00.setBorderPainted(false);
        jButton00.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton00.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton00.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton00.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton00ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton00);
        jButton00.setBounds(325, 604, 73, 73);

        jButton30.setBackground(new java.awt.Color(255, 255, 255));
        jButton30.setFont(jButton30.getFont().deriveFont(jButton30.getFont().getSize()+17f));
        jButton30.setBorderPainted(false);
        jButton30.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton30.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton30.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton30);
        jButton30.setBounds(571, 604, 73, 73);

        jButton31.setBackground(new java.awt.Color(153, 153, 153));
        jButton31.setFont(jButton31.getFont().deriveFont(jButton31.getFont().getSize()+17f));
        jButton31.setBorderPainted(false);
        jButton31.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton31.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton31.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton31);
        jButton31.setBounds(571, 521, 73, 74);

        jButton62.setBackground(new java.awt.Color(153, 153, 153));
        jButton62.setFont(jButton62.getFont().deriveFont(jButton62.getFont().getSize()+17f));
        jButton62.setBorderPainted(false);
        jButton62.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton62.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton62.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton62ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton62);
        jButton62.setBounds(817, 439, 73, 73);

        jButton20.setBackground(new java.awt.Color(153, 153, 153));
        jButton20.setFont(jButton20.getFont().deriveFont(jButton20.getFont().getSize()+17f));
        jButton20.setBorderPainted(false);
        jButton20.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton20.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton20.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton20);
        jButton20.setBounds(489, 604, 73, 73);

        jButton41.setBackground(new java.awt.Color(255, 255, 255));
        jButton41.setFont(jButton41.getFont().deriveFont(jButton41.getFont().getSize()+17f));
        jButton41.setBorderPainted(false);
        jButton41.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton41.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton41.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton41);
        jButton41.setBounds(653, 521, 73, 74);

        jButton50.setBackground(new java.awt.Color(255, 255, 255));
        jButton50.setFont(jButton50.getFont().deriveFont(jButton50.getFont().getSize()+17f));
        jButton50.setBorderPainted(false);
        jButton50.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton50.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton50.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton50);
        jButton50.setBounds(735, 604, 73, 73);

        jButton51.setBackground(new java.awt.Color(153, 153, 153));
        jButton51.setFont(jButton51.getFont().deriveFont(jButton51.getFont().getSize()+17f));
        jButton51.setBorderPainted(false);
        jButton51.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton51.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton51.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton51);
        jButton51.setBounds(735, 521, 73, 74);

        jButton40.setBackground(new java.awt.Color(153, 153, 153));
        jButton40.setFont(jButton40.getFont().deriveFont(jButton40.getFont().getSize()+17f));
        jButton40.setBorderPainted(false);
        jButton40.setMaximumSize(new java.awt.Dimension(73, 73));
        jButton40.setMinimumSize(new java.awt.Dimension(73, 73));
        jButton40.setPreferredSize(new java.awt.Dimension(73, 73));
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton40);
        jButton40.setBounds(653, 604, 73, 73);

        jLabelFichaEnUso.setBackground(new java.awt.Color(255, 255, 255));
        jLabelFichaEnUso.setFont(jLabelFichaEnUso.getFont().deriveFont(jLabelFichaEnUso.getFont().getSize()+7f));
        jLabelFichaEnUso.setText("FICHA EN USO: ");
        getContentPane().add(jLabelFichaEnUso);
        jLabelFichaEnUso.setBounds(50, 180, 210, 70);

        jLabelTurnoDe.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        jLabelTurnoDe.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTurnoDe.setText("TURNO DE:");
        getContentPane().add(jLabelTurnoDe);
        jLabelTurnoDe.setBounds(20, 30, 221, 31);

        jTextAreaCementerio.setEditable(false);
        jTextAreaCementerio.setColumns(20);
        jTextAreaCementerio.setFont(jTextAreaCementerio.getFont().deriveFont(jTextAreaCementerio.getFont().getSize()+17f));
        jTextAreaCementerio.setRows(5);
        jScrollPane1.setViewportView(jTextAreaCementerio);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 310, 251, 120);

        jButtonReiniciar.setText("REINICIAR PARTIDA");
        jButtonReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReiniciarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonReiniciar);
        jButtonReiniciar.setBounds(30, 613, 170, 40);

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CEMENTERIO");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(60, 280, 140, 23);

        jLabelRonda.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabelRonda.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabelRonda);
        jLabelRonda.setBounds(20, 60, 210, 40);
        getContentPane().add(jLabelFondo);
        jLabelFondo.setBounds(0, 0, 1020, 710);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Todos los botones tienen un evento de acción en el que llaman al método "acción" y le pasan su coordenada.
    //Para facilidad de uso y programación, los nombres de cada botón han sido cambiados para corresponder con dichas coordenadas.
    private void jButton00ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton00ActionPerformed

        accion("00");

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton00ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        accion("14");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        accion("16");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        accion("10");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        accion("20");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        accion("30");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        accion("40");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        accion("50");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton60ActionPerformed
        accion("60");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton60ActionPerformed

    private void jButton70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton70ActionPerformed
        accion("70");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton70ActionPerformed

    private void jButton01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton01ActionPerformed
        accion("01");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton01ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        accion("11");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        accion("21");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        accion("31");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        accion("41");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        accion("51");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton61ActionPerformed
        accion("61");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton61ActionPerformed

    private void jButton71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton71ActionPerformed
        accion("71");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton71ActionPerformed

    private void jButton02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton02ActionPerformed
        accion("02");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton02ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        accion("12");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        accion("22");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        accion("32");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        accion("42");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        accion("52");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton62ActionPerformed
        accion("62");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton62ActionPerformed

    private void jButton72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton72ActionPerformed
        accion("72");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton72ActionPerformed

    private void jButton03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton03ActionPerformed
        accion("03");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton03ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        accion("13");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        accion("23");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        accion("33");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        accion("43");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        accion("53");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton63ActionPerformed
        accion("63");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton63ActionPerformed

    private void jButton73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton73ActionPerformed
        accion("73");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton73ActionPerformed

    private void jButton04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton04ActionPerformed
        accion("04");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton04ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        accion("24");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        accion("34");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        accion("44");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        accion("54");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton64ActionPerformed
        accion("64");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton64ActionPerformed

    private void jButton74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton74ActionPerformed
        accion("74");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton74ActionPerformed

    private void jButton05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton05ActionPerformed
        accion("05");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton05ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        accion("15");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        accion("25");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        accion("35");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        accion("45");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        accion("55");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton55ActionPerformed

    private void jButton65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton65ActionPerformed
        accion("65");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton65ActionPerformed

    private void jButton75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton75ActionPerformed
        accion("75");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton75ActionPerformed

    private void jButton06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton06ActionPerformed
        accion("06");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton06ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        accion("26");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        accion("36");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        accion("46");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        accion("56");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton56ActionPerformed

    private void jButton66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton66ActionPerformed
        accion("66");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton66ActionPerformed

    private void jButton76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton76ActionPerformed
        accion("76");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton76ActionPerformed

    private void jButton07ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton07ActionPerformed
        accion("07");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton07ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        accion("17");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        accion("27");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        accion("37");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        accion("47");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton57ActionPerformed
        accion("57");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton57ActionPerformed

    private void jButton67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton67ActionPerformed
        accion("67");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton67ActionPerformed

    private void jButton77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton77ActionPerformed
        accion("77");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton77ActionPerformed

    private void jButtonReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReiniciarActionPerformed
        this.reiniciarPartida();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonReiniciarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
JOptionPane.showMessageDialog(this,"¡GRACIAS POR JUGAR! - Jesús y Lucía");
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton00;
    private javax.swing.JButton jButton01;
    private javax.swing.JButton jButton02;
    private javax.swing.JButton jButton03;
    private javax.swing.JButton jButton04;
    private javax.swing.JButton jButton05;
    private javax.swing.JButton jButton06;
    private javax.swing.JButton jButton07;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton70;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton73;
    private javax.swing.JButton jButton74;
    private javax.swing.JButton jButton75;
    private javax.swing.JButton jButton76;
    private javax.swing.JButton jButton77;
    private javax.swing.JButton jButtonReiniciar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelFichaEnUso;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelRonda;
    private javax.swing.JLabel jLabelTurnoDe;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaCementerio;
    // End of variables declaration//GEN-END:variables
}
