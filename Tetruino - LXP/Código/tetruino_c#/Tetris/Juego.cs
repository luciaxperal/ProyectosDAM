using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO.Ports;
using System.Windows.Forms;

namespace Tetris
{
    public partial class Juego : Form
    {
        // La g es para dibujar los graficos del panel del "tablero"
        // La g2 es para dibujar los graficos del panel donde dibujara la siguiente figura
        Graphics g, g2;
        int x = 120, y = 120, block, nextBlock, laRotacion, numeroRot, numLineas, level, score;
        List<Casilla> tablero, almacen, siguienteFigura;
        Timer timer, timerArduino;
        bool puedeEmpezar, reanudar, isArduino, bajarMasVeloz, colisionIzq, colisionDcha;

        // Si no te lee la clase SerialPort, tienes que dar en mostrar posible correcciones, 
        // la opción de Buscar e intalar la última versión
        SerialPort serialPort;

        public Juego()
        {
            InitializeComponent();
            new Inicio().ShowDialog();
            iniciarTodo();
        }

        public void iniciarTodo()
        { 
            // Hago un try-catch, para saber si hay Arduino o no
            try
            {
                serialPort = new SerialPort("COM3");
                serialPort.Open();
                isArduino = true;
            }
            catch (System.IO.FileNotFoundException e) { isArduino = false; }

            // Inicializo todo
            g = panelTablero.CreateGraphics();
            g2 = panelFigura.CreateGraphics();

            tablero = new List<Casilla>();
            almacen = new List<Casilla>();
            siguienteFigura = new List<Casilla>();

            block = new Random().Next(0, 7);
            laRotacion = 0;

            score = 0;
            numLineas = 0;
            level = 1;
            lScore.Text = "" + score;
            lLines.Text = "" + numLineas;
            lLevel.Text = "" + level;

            puedeEmpezar = false;
            reanudar = false;
            bajarMasVeloz = false;
            colisionIzq = false;
            colisionDcha = false;

            timer = new Timer();
            timer.Interval = 300;
            timer.Tick += timer_Tick;

            timerArduino = new Timer();
            timerArduino.Interval = 300;
            timerArduino.Tick += timerArduino_Tick;
            timerArduino.Start();

            // Al comenzar la partida, se verá este mensaje
            lPaused.Text = "PRESS\nSTART";
            lPaused.Show();
            
            elTableroCero();
        }
        
        // Añade todas las casillas al tablero y las pone como vacías y de color blanco
        // Los límites los pone de color gris
        // El límite de arriba es invisible
        public void elTableroCero()
        {
            tablero.Clear();
            int laX = -30;
            for (int i = 0; i < 12; i++)
            {
                laX = laX + 30;
                int laY = -60;
                for (int j = 0; j < 20; j++)
                {
                    laY = laY + 30;
                    if (laX == 0 || laX == 330 || laY == 540)
                        tablero.Add(new Casilla() { ElColor = Brushes.Gray, CoorX = laX, CoorY = laY, Vacia = false });
                    else
                        tablero.Add(new Casilla() { ElColor = Brushes.White, CoorX = laX, CoorY = laY, Vacia = true });

                }
            }

            // "Traspaso" las figuras del almacen al tablero
            foreach (Casilla c in almacen)
                    recorre_guardaCasilla(c.CoorX, c.CoorY, c.ElColor, "RECORRER");
        }

        // ----------------------------  PANELES Y PINTAR ----------------------------------------
        private void panelTablero_Paint(object sender, PaintEventArgs e)
        {
            // Cuando está pausado el juego, para reanudar
            if (reanudar == true)
            {
                timer.Start();
                reanudar = false;
            }
            // Cuando ábre el juego y le da a empezar
            if (puedeEmpezar)
            {
                dibujar_guardar(block, "RECORRER");
                pintarCasillas("TABLERO");
            } else // Pinta las casillas, para que cuando está pausado o no ha comenzado, se vea el "tablero"
                pintarCasillas("TABLERO");
        }
        private void panelFigura_Paint(object sender, PaintEventArgs e)
        {
            pintarBordePanel(5, panelFigura, e);
            pintarCasillas("FIGURA");
        }
        private void panelScore_Paint(object sender, PaintEventArgs e) { pintarBordePanel(3, panelScore, e);}
        private void panelLines_Paint(object sender, PaintEventArgs e) { pintarBordePanel(3, panelLines, e);}
        private void panelLevel_Paint(object sender, PaintEventArgs e) { pintarBordePanel(3, panelLevel, e);}

        public void pintarBordePanel(int grosor, Panel panel, PaintEventArgs e)
        {
            // Pinta los bordes de los paneles
            ControlPaint.DrawBorder(e.Graphics, panel.ClientRectangle,
                     Color.Black, grosor, ButtonBorderStyle.Solid, // left
                     Color.Black, grosor, ButtonBorderStyle.Solid, // top
                     Color.Black, grosor, ButtonBorderStyle.Solid, // right
                     Color.Black, grosor, ButtonBorderStyle.Solid);// bottom
        }
        // Pinta las casillas guardadas en el list correspondiente. 
        // Este es el método que las pinta en el panel
        public void pintarCasillas(string panel)
        {
            if(panel == "TABLERO")
            {
                foreach (Casilla c in tablero)
                {
                    if (c.ElColor != Brushes.White)
                        g.FillRectangle(c.ElColor, c.CoorX + 1, c.CoorY + 1, 29, 29);
                    if(c.ElColor == Brushes.Gray)
                        g.FillRectangle(c.ElColor, c.CoorX + 1, c.CoorY + 1, 30, 30);
                }
            } else
            {
                foreach (Casilla c in siguienteFigura)
                {
                    
                    g2.DrawRectangle(new Pen(Color.Black, 3), c.CoorX, c.CoorY, 26, 26);
                    g2.FillRectangle(c.ElColor, c.CoorX + 1, c.CoorY + 1, 25, 25);
                }
            }
            
        }



        // Cada tick, mira si el bloque puede seguir bajando, si encuentra el final,
        // se guarda en el almacen y pinta la siguiente figura arriba. Inicia de nuevo algunos valores
        // Si se puede mover, se baja una casilla la figura en el eje Y
        // Cada tick, lee si hay alguna linea llena y la borra y repinta todo de nuevo
        public void timer_Tick(object sender, EventArgs e)
        {
            if (!puedeMoverse(block))
            {
                // Guardar el bloque cuando haya encontrado el final
                dibujar_guardar(block, "GUARDAR");

                bajarMasVeloz = false;

                y = 30;
                x = 120;


                timer.Interval = 300;

                block = nextBlock; // Coge el nextBlock anterior
                nextBlock = new Random().Next(0, 7); // Hace un nuevo nextblock para tener la siguiente figura en el panel
                // Muestra la siguiente figura en el panel de siguiente figura
                dibujar_guardar(nextBlock, "MOSTRAR");
                panelFigura.Refresh();

            }
            else
            {
                y = y + 30;

                // Cuando das a bajar la figura, este incrementa puntos por cada casilla caida veloz
                if (bajarMasVeloz)
                {
                    score = score + (2 * level);
                    lScore.Text = "" + score;
                    comprobarScore();
                }

                // Aqui hay algunos flags para cuando se usa en Arduino por motivos de excepciones con los procesos
                if (colisionIzq)
                    colisionesIzq();
                if (colisionDcha)
                    colisionesDcha();
                colisionIzq = false;
                colisionDcha = false; 
            }
            int laLinea = detectarLineaLlena();
            borrarLineas(laLinea);
            buscarAlmacen();
            borrarAlmacen();
            buscarLimiteFin(-30);
            panelTablero.Refresh();
        }


        public void timerArduino_Tick(object sender, EventArgs e)
        {
            if(isArduino)
            serialPort.DataReceived += serialPort_DataReceived;
        }

        
        // Eecorre en cada línea las x. Si están todas llenas, devuelve la línea llena
        public int detectarLineaLlena()
        {
            int cuentaTrue = 0;
            int laY = -30;
            for (int i = 0; i < 18; i++)
            {
                laY = laY + 30;
                int laX = 0;
                cuentaTrue = 0;
                for (int j = 0; j < 10; j++)
                {
                    if (buscarCasilla(laX, laY).ElColor != Brushes.White)
                    {
                        cuentaTrue++;
                        if (cuentaTrue > 9)
                            return laY;
                    }
                    else
                        break;
                    laX = laX + 30;
                }
            }
            return 3000;
        }

        // Cada casilla de la línea llena, la pone en blanco y obtiene el color de la
        // casilla de arriba y se lo pone a la eliminada
        public void borrarLineas(int laLinea)
        {
            if (laLinea != 3000)
            {
                int laX = 30;
                for (int j = 0; j < 10; j++)
                {
                    recorre_guardaCasilla(laX, laLinea, Brushes.White, "BORRAR");
                    if (buscarCasilla(laX, laLinea - 30).ElColor != Brushes.White)
                        recorre_guardaCasilla(laX, laLinea, buscarCasilla(laX, laLinea - 30).ElColor,"GUARDAR");
                    bajaLinea(laX,laLinea);

                    laX = laX + 30;
                }

                // Cuando se borra la linea, se incrementa el número de Lines
                // Si la linea es modulo de 10, es decir: 10,20,30... entonces se incrementa el nivel
                // Hay hasta 10 niveles, y en función de este, el score se incrementa más o menos
                numLineas = numLineas + 1;
                lLines.Text = "" + numLineas;
                if (numLineas%10 == 0)
                {
                    if (level < 9)
                    {
                        level = level + 1;
                        lLevel.Text = "" + level;
                    }
                }
                score = score + (100*level);
                lScore.Text = "" + score;
                comprobarScore();
            }
        }

        // Baja las casillas de todas las líneas superiores a la borrada
        public void bajaLinea(int laX, int laLinea)
        {
                while (laLinea > 30)
                {
                    recorre_guardaCasilla(laX, laLinea - 30, Brushes.White, "BORRAR");
                    recorre_guardaCasilla(laX, laLinea - 30, buscarCasilla(laX, laLinea - 60).ElColor, "GUARDAR");

                laLinea = laLinea - 30;
            }
        }

        // Cuando se llegan a los 200000 puntos, se "gana" y termina la partida
        public void comprobarScore()
        {
            if (score > 200000)
                terminarPartida();
        }

        // Este método se usa para saber si se están metiendo casillas de más en el almacén
        public void buscarAlmacen()
        {
            foreach (Casilla c in almacen)
            {
                if (c.ElColor == Brushes.White)
                    c.ElColor = Brushes.Pink;
            }
        }

        // Recorre el almacén y si una figura llega a la línea -30,
        // termina la partida porque ha perdido
        public void buscarLimiteFin(int y)
        {
            foreach (Casilla c in almacen)
            {
                if (c.CoorY == y)
                    terminarPartida();
            }
        }

        // LLeva a la pantalla de Game Over y reinicia todo
        // Cierra conexiones
        public void terminarPartida()
        {
            timer.Stop();
            timerArduino.Stop();
            if (isArduino)
                serialPort.Close();
            new Fin().ShowDialog();
            iniciarTodo();
        }

        // Borra casillas blancas residuales del almacén
        // IMPORTANTE: No se puede borrar el amacén con un Foreach porque da una 
        // excepción de: Collection was modified
        public void borrarAlmacen()
        {
            for (int i = almacen.Count - 1; i > -1; i--)
            {
                if (almacen[i].ElColor == Brushes.White || almacen[i].ElColor == Brushes.Pink)
                    almacen.Remove(almacen[i]);
            }
        }

        // Devuelve la casilla de esa coordenada
        public Casilla buscarCasilla(int x, int y)
        {
            foreach(Casilla c in tablero)
            {
                if (c.CoorX == x && c.CoorY == y)
                    return c;
            }
            return null;
        }

        // Este método tiene 4 funciones. Por una parte tiene recorrer, que recorre el tablero y
        // cambia de color las casillas para "simular" el movimiento de la figura.
        // Está la intención de guardar, que guarda en el almacén la figura
        // Mostrar sirve para mostrar la siguiente figura que va a caer
        // Y el else es Borrar. Este borra la casilla del almacén.
        public void recorre_guardaCasilla(int x, int y, Brush color, string intencion)
        {
            if (intencion == "RECORRER")
            {
                foreach (Casilla c in tablero)
                {
                    if (c.CoorX == x && c.CoorY == y)
                    {
                        if (c.Vacia == true && c.ElColor == Brushes.White)
                        {
                            c.ElColor = color;
                            c.Vacia = false;
                        }
                        else
                        {
                            c.ElColor = Brushes.White;
                            c.Vacia = true;
                        }
                    }
                }
            }
            else if (intencion == "MOSTRAR")
            {
                siguienteFigura.Add(new Casilla() { ElColor = color, CoorX = x, CoorY = y, Vacia = false });
            }
            else if (intencion == "GUARDAR")
            {
                almacen.Add(new Casilla() { ElColor = color, CoorX = x, CoorY = y, Vacia = false });
            }
            else
            {
                for (int i = almacen.Count - 1; i > -1; i--)
                {
                    if (almacen[i].CoorX == x && almacen[i].CoorY == y)
                        almacen.Remove(almacen[i]);
                }
            }   

        }

        // Cuando pasas las coordenadas, te dice si esa casilla es blanca
        // (si es blanca es que está vacía)
        // Recorre tanto el tablero como el almacén.
        public bool colision(int x, int y)
        {
            foreach (Casilla c in tablero)
            {
                    if (c.CoorX == x && c.CoorY == y)
                    {
                        if (c.ElColor == Brushes.White || c.ElColor == Brushes.Pink)
                                return true;
                        else
                                return false;
                    }
            }

            foreach (Casilla c in almacen)
            {
                if (c.CoorX == x && c.CoorY == y)
                {
                    if (c.ElColor == Brushes.White || c.ElColor == Brushes.Pink)
                        return true;
                    else
                        return false;
                    
                }
            }

            return true ;
        }

        // "Dibuja" (si es recorrer cambia las blancas por estas) o guarda una figura entera.
        // Le pasa las coordenadas X e Y para que los métodos de figura pueda colocar (es invisible el proceso)
        // las casillas de forma que haga esa figura
        public void dibujar_guardar(int b, string intencion)
        {
            elTableroCero();
            int laX = x, laY = y;

            if (intencion == "MOSTRAR")
            {
                laX = 60;
                laY = 120;
                siguienteFigura.Clear();
            }
            switch (b)
            {
                case 0: // I-BLOCK
                    numeroRot = 2;
                    figuraI(laX, laY, laRotacion, intencion);
                    break;
                case 1: // J-BLOCK
                    numeroRot = 4;
                    figuraJ(laX, laY, laRotacion, intencion);
                    break; 
                case 2: // L-BLOCK
                    numeroRot = 4;
                    figuraL(laX, laY, laRotacion, intencion);
                    break;
                case 3: // O-BLOCK
                    numeroRot = 0;
                    figuraO(laX, laY, laRotacion, intencion);
                    break;
                case 4: // S-BLOCK
                    numeroRot = 2;
                    figuraS(laX, laY, laRotacion, intencion);
                    break;
                case 5: // T-BLOCK
                    numeroRot = 4;
                    figuraT(laX, laY, laRotacion, intencion);
                    break;
                case 6: // Z-BLOCK
                    numeroRot = 2;
                    figuraZ(laX, laY, laRotacion, intencion);
                    break;                   
            }
        }

        //  ---------------- FIGURAS --------------------------

        
        public void figuraI(int x, int y, int rotacion, string intencion)
        {
            if (rotacion == 0)
            {
                if (intencion == "MOSTRAR")
                {
                    x = 75;
                    y = 135;
                    siguienteFigura.Clear();
                }
                recorre_guardaCasilla(x, y - 30, Brushes.LightBlue, intencion);
                recorre_guardaCasilla(x, y - 60, Brushes.LightBlue, intencion);
                recorre_guardaCasilla(x, y - 90, Brushes.LightBlue, intencion);
                recorre_guardaCasilla(x, y - 120, Brushes.LightBlue, intencion);
            }
            else
            {
                if (intencion == "MOSTRAR")
                {
                    x = 30;
                    y = 90;
                    siguienteFigura.Clear();
                }
                recorre_guardaCasilla(x, y-30, Brushes.LightBlue, intencion);
                recorre_guardaCasilla(x+30, y-30, Brushes.LightBlue, intencion);
                recorre_guardaCasilla(x+60, y-30, Brushes.LightBlue, intencion);
                recorre_guardaCasilla(x+90, y-30, Brushes.LightBlue, intencion);
            }
        }

        public void figuraJ(int x, int y, int rotacion, string intencion)
        {
            switch (rotacion)
            {
                case 0:
                    if (intencion == "MOSTRAR")
                    {
                        x = 50;
                        y = 100;
                        siguienteFigura.Clear();
                    }
                    recorre_guardaCasilla(x, y - 30, Brushes.Blue, intencion);
                    recorre_guardaCasilla(x, y - 60, Brushes.Blue, intencion);
                    recorre_guardaCasilla(x + 30, y - 30, Brushes.Blue, intencion);
                    recorre_guardaCasilla(x + 60, y - 30, Brushes.Blue, intencion);
                    break;
                case 1:
                    recorre_guardaCasilla(x, y - 30, Brushes.Blue, intencion);
                    recorre_guardaCasilla(x + 30, y - 90, Brushes.Blue, intencion);
                    recorre_guardaCasilla(x + 30, y - 60, Brushes.Blue, intencion);
                    recorre_guardaCasilla(x + 30, y - 30, Brushes.Blue, intencion);
                    break;
                case 2:
                    if (intencion == "MOSTRAR")
                    {
                        x = 50;
                        y = 100;
                        siguienteFigura.Clear();
                    }
                    recorre_guardaCasilla(x + 60, y - 30, Brushes.Blue, intencion);
                    recorre_guardaCasilla(x + 60, y - 60, Brushes.Blue, intencion);
                    recorre_guardaCasilla(x + 30, y - 60, Brushes.Blue, intencion);
                    recorre_guardaCasilla(x, y - 60, Brushes.Blue, intencion);
                    break;
                case 3:
                    recorre_guardaCasilla(x, y - 30, Brushes.Blue, intencion);
                    recorre_guardaCasilla(x, y - 60, Brushes.Blue, intencion);
                    recorre_guardaCasilla(x, y - 90, Brushes.Blue, intencion);
                    recorre_guardaCasilla(x + 30, y - 90, Brushes.Blue, intencion);
                    break;
            }
        }


        public void figuraL(int x, int y, int rotacion, string intencion)
        {
            switch (rotacion)
            {
                case 0:
                    if (intencion == "MOSTRAR")
                    {
                        x = 50;
                        y = 100;
                        siguienteFigura.Clear();
                    }
                    recorre_guardaCasilla(x + 60, y - 30, Brushes.Orange, intencion);
                    recorre_guardaCasilla(x, y - 30, Brushes.Orange, intencion);
                    recorre_guardaCasilla(x + 30, y - 30, Brushes.Orange, intencion);
                    recorre_guardaCasilla(x + 60, y - 60, Brushes.Orange, intencion);
                    break;
                case 1:
                    recorre_guardaCasilla(x, y - 90, Brushes.Orange, intencion);
                    recorre_guardaCasilla(x + 30, y - 30, Brushes.Orange, intencion);
                    recorre_guardaCasilla(x + 30, y - 60, Brushes.Orange, intencion);
                    recorre_guardaCasilla(x + 30, y - 90, Brushes.Orange, intencion);
                    break;
                case 2:
                    if (intencion == "MOSTRAR")
                    {
                        x = 50;
                        y = 100;
                        siguienteFigura.Clear();
                    }
                    recorre_guardaCasilla(x, y - 60, Brushes.Orange, intencion);
                    recorre_guardaCasilla(x + 30, y - 60, Brushes.Orange, intencion);
                    recorre_guardaCasilla(x + 60, y - 60, Brushes.Orange, intencion);
                    recorre_guardaCasilla(x, y - 30, Brushes.Orange,intencion);
                    break;
                case 3:
                    recorre_guardaCasilla(x, y - 30, Brushes.Orange, intencion);
                    recorre_guardaCasilla(x, y - 60, Brushes.Orange, intencion);
                    recorre_guardaCasilla(x, y - 90, Brushes.Orange, intencion);
                    recorre_guardaCasilla(x + 30, y - 30, Brushes.Orange,intencion);
                    break;
            }
        }

        public void figuraO(int x, int y, int rotacion, string intencion)
        {
            if (intencion == "MOSTRAR")
            {
                x = 60;
                y = 100;
                siguienteFigura.Clear();
            }
            recorre_guardaCasilla(x, y - 30, Brushes.Yellow,intencion);
            recorre_guardaCasilla(x, y - 60, Brushes.Yellow, intencion);
            recorre_guardaCasilla(x + 30, y - 30, Brushes.Yellow,intencion);
            recorre_guardaCasilla(x + 30, y - 60, Brushes.Yellow,intencion);
        }

        public void figuraS(int x, int y, int rotacion, string intencion)
        {
            if (rotacion == 0)
            {
                if (intencion == "MOSTRAR")
                {
                    x = 50;
                    y = 100;
                    siguienteFigura.Clear();
                }
                recorre_guardaCasilla(x, y - 30, Brushes.Green,intencion);
                recorre_guardaCasilla(x + 30, y - 60, Brushes.Green,  intencion);
                recorre_guardaCasilla(x + 30, y - 30, Brushes.Green, intencion);
                recorre_guardaCasilla(x + 60, y - 60, Brushes.Green, intencion);
            }
            else
            {
                recorre_guardaCasilla(x + 30, y - 30, Brushes.Green, intencion);
                recorre_guardaCasilla(x, y - 60, Brushes.Green,  intencion);
                recorre_guardaCasilla(x + 30, y - 60, Brushes.Green, intencion);
                recorre_guardaCasilla(x, y - 90, Brushes.Green,  intencion);
            }
        }

        public void figuraT(int x, int y, int rotacion, string intencion)
        {
            switch (rotacion)
            {
                case 0:
                    if (intencion == "MOSTRAR")
                    {
                        x = 50;
                        y = 100;
                        siguienteFigura.Clear();
                    }
                    recorre_guardaCasilla(x, y - 30, Brushes.Violet, intencion);
                    recorre_guardaCasilla(x + 30, y - 30, Brushes.Violet, intencion);
                    recorre_guardaCasilla(x + 60, y - 30, Brushes.Violet, intencion);
                    recorre_guardaCasilla(x + 30, y - 60, Brushes.Violet, intencion);
                    break;
                case 1:
                    recorre_guardaCasilla(x + 30, y - 30, Brushes.Violet, intencion);
                    recorre_guardaCasilla(x + 30, y - 60, Brushes.Violet, intencion);
                    recorre_guardaCasilla(x + 30, y - 90, Brushes.Violet, intencion);
                    recorre_guardaCasilla(x, y - 60, Brushes.Violet, intencion);
                    break;
                case 2:
                    if (intencion == "MOSTRAR")
                    {
                        x = 50;
                        y = 100;
                        siguienteFigura.Clear();
                    }
                    recorre_guardaCasilla(x + 30, y - 30, Brushes.Violet, intencion);
                    recorre_guardaCasilla(x, y - 60, Brushes.Violet, intencion);
                    recorre_guardaCasilla(x + 30, y - 60, Brushes.Violet, intencion);
                    recorre_guardaCasilla(x + 60, y - 60, Brushes.Violet, intencion);
                    break;
                case 3:
                    recorre_guardaCasilla(x, y - 30, Brushes.Violet, intencion);
                    recorre_guardaCasilla(x, y - 60, Brushes.Violet, intencion);
                    recorre_guardaCasilla(x, y - 90, Brushes.Violet, intencion);
                    recorre_guardaCasilla(x + 30, y - 60, Brushes.Violet, intencion);
                    break;
            }
        }

        public void figuraZ(int x, int y, int rotacion, string intencion)
        {
            if (rotacion == 0)
            {
                if (intencion == "MOSTRAR")
                {
                    x = 50;
                    y = 100;
                    siguienteFigura.Clear();
                }
                recorre_guardaCasilla(x + 30, y - 30, Brushes.Red, intencion);
                recorre_guardaCasilla(x + 30, y - 60, Brushes.Red, intencion);
                recorre_guardaCasilla(x + 60, y - 30, Brushes.Red,intencion);
                recorre_guardaCasilla(x, y - 60, Brushes.Red, intencion);
            }
            else
            {
                recorre_guardaCasilla(x, y - 30, Brushes.Red, intencion);
                recorre_guardaCasilla(x, y - 60, Brushes.Red, intencion);
                recorre_guardaCasilla(x + 30, y - 60, Brushes.Red, intencion);
                recorre_guardaCasilla(x + 30, y - 90, Brushes.Red, intencion);
            }
        }

        // ------------------------ COLISIONES ---------------------------------------

        public bool puedeMoverse(int block)
        {
            switch (block)
            {
                case 0:
                    if (laRotacion == 1)
                    {
                        if (!colision(x, y) || !colision(x + 30, y) || !colision(x + 60, y) || !colision(x + 90, y))
                            return false;
                    }
                    else
                    {
                        if (!colision(x, y))
                            return false;
                    }
                    return true;
                case 1:
                    switch (laRotacion)
                    {
                        case 0:
                            if (!colision(x, y) || !colision(x + 30, y) || !colision(x + 60, y))
                                return false;
                            return true;
                        case 1:
                            if (!colision(x, y) || !colision(x + 30, y))
                                return false;
                            return true;
                        case 2:
                            if (!colision(x + 60, y) || !colision(x, y - 30) || !colision(x + 30, y - 30))
                                return false;
                            return true;
                        case 3:
                            if (!colision(x, y) || !colision(x + 30, y - 60))
                                return false;
                            return true;
                    }
                    return true;

                case 2:
                    switch (laRotacion)
                    {
                        case 0:
                            if (!colision(x, y) || !colision(x + 30, y) || !colision(x + 60, y))
                                return false;
                            return true;
                        case 1:
                            if (!colision(x, y - 60) || !colision(x + 30, y))
                                return false;
                            return true;
                        case 2:
                            if (!colision(x + 60, y - 30) || !colision(x, y) || !colision(x + 30, y - 30))
                                return false;
                            return true;
                        case 3:
                            if (!colision(x, y) || !colision(x + 30, y))
                                return false;
                            return true;
                    }
                    return true;

                case 3:
                    if (!colision(x, y) || !colision(x + 30, y))
                        return false;
                    return true;
                case 4:
                    if (laRotacion == 1)
                    {
                        if (!colision(x, y - 30) || !colision(x + 30, y))
                            return false;
                        else
                            return true;
                    }
                    else
                    {
                        if (!colision(x, y) || !colision(x + 30, y) || !colision(x + 60, y - 30))
                            return false;
                    }
                    return true;
                case 5:
                    switch (laRotacion)
                    {
                        case 0:
                            if (!colision(x, y) || !colision(x + 30, y) || !colision(x + 60, y))
                                return false;
                            return true;
                        case 1:
                            if (!colision(x, y - 30) || !colision(x + 30, y))
                                return false;
                            return true;
                        case 2:
                            if (!colision(x + 60, y - 30) || !colision(x, y - 30) || !colision(x + 30, y))
                                return false;
                            return true;
                        case 3:
                            if (!colision(x, y) || !colision(x + 30, y - 30))
                                return false;
                            return true;
                    }
                    return true;
                case 6:
                    if (laRotacion == 1)
                    {
                        if (!colision(x, y) || !colision(x + 30, y - 30))
                            return false;
                    }
                    else {
                        if (!colision(x, y - 30) || !colision(x + 30, y) || !colision(x + 60, y))
                            return false;
                    }
                    return true;
            }
            return true;
        }

        public void colisionesIzq()
        {
            switch (block)
            {
                case 0:
                    if (laRotacion == 1)
                    {
                        if (colision(x - 30, y - 30))
                            x = x - 30;
                        break;
                    }
                    else
                    {
                        if ((colision(x - 30, y - 30)) || (colision(x - 30, y - 120))
                            || (colision(x - 30, y - 60)) || (colision(x - 30, y - 90)))
                            x = x - 30;
                        break;
                    }
                case 1:
                    switch (laRotacion)
                    {
                        case 0:
                            if (colision(x - 30, y - 30) || colision(x - 30, y - 60))
                                x = x - 30;
                            break;
                        case 1:
                            if (colision(x - 30, y-30))
                                x = x - 30;
                            break;
                        case 2:
                            if (colision(x - 30, y - 60))
                                x = x - 30;
                            break;
                        case 3:
                            if (colision(x - 30, y - 30) || colision(x - 30, y - 60) || colision(x - 30, y - 90))
                                x = x - 30;
                            break;
                    }
                    break;
                case 2:
                    switch (laRotacion)
                    {
                        case 0:
                            if (colision(x - 30, y - 30) || colision(x + 60, y - 60))
                                x = x - 30;
                            break;
                        case 1:
                            if (colision(x-30, y-90))
                                x = x - 30;
                            break;
                        case 2:
                            if (colision(x - 30, y - 30) || colision(x - 30, y - 60))
                                x = x - 30;
                            break;
                        case 3:
                            if (colision(x - 30, y - 30) || colision(x - 30, y - 60) || colision(x - 30, y - 90))
                                x = x - 30;
                            break;
                    }
                    break;

                case 3:
                    if (colision(x-30, y - 30) || colision(x - 30, y - 60))
                        x = x - 30;
                    break;
                case 4:
                    if (laRotacion == 0)
                    {
                        if (colision(x - 30, y - 30) || colision(x - 30, y - 60))
                            x = x - 30;
                        break;
                    }
                    else
                    {
                        if (colision(x - 30, y - 90) || colision(x - 30, y - 60))
                            x = x - 30;
                        break;
                    }
                    
                case 5:
                    switch (laRotacion)
                    {
                        case 0:
                            if (colision(x - 30, y - 30))
                                x = x - 30;
                            break;
                        case 1:

                            if (colision(x - 30, y - 60))
                                x = x - 30;
                            break;
                        case 2:

                            if (colision(x - 30, y - 60))
                                x = x - 30;
                            break;
                        case 3:

                            if (colision(x - 30, y - 30) || colision(x - 30, y - 60) || colision(x - 30, y - 90))
                                x = x - 30;
                            break;
                    }
                    break;
                case 6:
                    if (laRotacion == 0)
                    {
                        if (colision(x - 30, y - 60))
                            x = x - 30;
                        break;
                    }
                    else
                    {
                        if (colision(x- 30, y - 30) || colision(x - 30, y - 60) || colision(x - 30, y - 90))
                            x = x - 30;
                        break;
                    }
            }
        }

        public void colisionesDcha()
        {
            switch (block)
            {
                case 0:
                    if (laRotacion == 1)
                    {
                        if (colision(x + 120, y - 30))
                            x = x + 30;
                        break;
                    }
                    else
                    {
                        if ((colision(x + 30, y - 30)) || (colision(x + 30, y - 120))
                            || (colision(x + 30, y - 60)) || (colision(x + 30, y - 90)))
                            x = x + 30;
                        break;
                    }
                case 1:
                    switch (laRotacion)
                    {
                        case 0:
                            if (colision(x + 90, y - 30))
                                x = x + 30;
                            break;
                        case 1:
                            if (colision(x + 60, y - 30) || colision(x + 60, y - 60) || colision(x + 60, y - 90))
                                x = x + 30;
                            break;
                        case 2:
                            if (colision(x + 90, y - 30) || colision(x + 90, y - 60))
                                x = x + 30;
                            break;
                        case 3:
                            if (colision(x + 60, y - 90))
                                x = x + 30;
                            break;
                    }
                    break;
                case 2:
                    switch (laRotacion)
                    {
                        case 0:
                            if (colision(x + 90, y - 30) || colision(x + 90, y - 60))
                                x = x+30;
                            break;
                        case 1:
                            if (colision(x + 60, y - 30) || colision(x + 60, y - 60) || colision(x + 60, y - 90))
                                x = x + 30;
                            break;
                        case 2:
                            if (colision(x + 90, y - 60))
                                x = x +30;
                            break;
                        case 3:
                            if (colision(x + 60, y - 30))
                                x = x + 30;
                            break;
                    }
                    break;

                case 3:
                    if (colision(x + 60, y - 30) || colision(x + 60, y - 60))
                        x = x + 30;
                    break;
                case 4:
                    if (laRotacion == 0)
                    {
                        if (colision(x +90, y - 60))
                            x = x + 30;
                        break;
                    }
                    else
                    {
                        if (colision(x  + 60, y - 30) || colision(x + 60, y - 60))
                            x = x + 30;
                        break;
                    }

                case 5:
                    switch (laRotacion)
                    {
                        case 0:
                            if (colision(x + 90, y - 30))
                                x = x + 30;
                            break;
                        case 1:
                            if (colision(x + 60, y - 30) || colision(x + 60, y - 60) || colision(x + 60, y - 90))
                                x = x + 30;
                            break;
                        case 2:
                            if (colision(x + 90, y - 60))
                                x = x + 30;
                            break;
                        case 3:
                            if (colision(x + 60, y - 60))
                                x = x + 30;
                            break;
                    }
                    break;
                case 6:
                    if (laRotacion == 0)
                    {
                        if (colision(x + 90, y - 30))
                            x = x + 30;
                        break;
                    }
                    else
                    {
                        if (colision(x + 60, y - 90) || colision(x +60, y - 60))
                            x = x + 30;
                        break;
                    }
            }
        }

        // ----------------------- ACCIONES BOTONES --------------

        // Como algunas no pueden rotar,
        // o rotan menos veces, este método lo calcula
        public void rotar()
        {
            if (laRotacion < numeroRot - 1)
                laRotacion += 1;
            else
                laRotacion = 0;

        }

        public void bajarRapido()
        {
            timer.Interval = 100;
            timer.Stop();
            puedeEmpezar = true;
            reanudar = true;
            bajarMasVeloz = true;
        }

        private void Juego_KeyUp(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Right)
                colisionesDcha();
            if (e.KeyCode == Keys.Left)
                colisionesIzq();
            if (e.KeyCode == Keys.Down)
                bajarRapido();
            if (e.KeyCode == Keys.Up)
                rotar();
            if (e.KeyCode == Keys.S)
            {
                puedeEmpezar = true;
                timer.Start();
                lPaused.Hide();
            }
            if (e.KeyCode == Keys.P)
            {
                timer.Stop();
                lPaused.Text = "PAUSED";
                lPaused.Show();
            }

            // Aqui puse un refresh, porque no refrescaba al rotar o moverse
            panelTablero.Refresh();
        }

        // Este evento obtiene el dato mandado por el Arduino, y
        // dependiendo de cuál sea, hace una acción u otra
        private void serialPort_DataReceived(object sender, SerialDataReceivedEventArgs e)
        {
            serialPort = (SerialPort)sender;
            string input = serialPort.ReadLine();

            if (input.Contains("L"))
                colisionIzq = true;
            if (input.Contains("R"))
                colisionDcha = true;
            if (input.Contains("U"))
                rotar();
            if (input.Contains("D"))
                bajarRapido();
            if (input.Contains("P"))
            {
                timer.Stop();
                lPaused.Text = "PAUSED";
                lPaused.Show();
            }
            if (input.Contains("S"))
            {
                puedeEmpezar = true;
                reanudar = true;
                lPaused.Hide();
            }
            

            // Aqui puse un refresh, porque no refrescaba al rotar o moverse
            panelTablero.Refresh();
        }
    }

   

    public class Casilla
    {
        public Brush ElColor { get; set; }
        public int CoorX { get; set; }
        public int CoorY { get; set; }
        public bool Vacia { get; set; }
    }
}