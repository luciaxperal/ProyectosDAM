using System;
using System.IO.Ports;
using System.Windows.Forms;

namespace Tetris
{
    public partial class Inicio : Form
    {
        SerialPort serialPort;
        Timer timer;
        bool isArduino;

        public Inicio()
        {
            InitializeComponent();
            try
            {
                serialPort = new SerialPort("COM3");
                serialPort.Open();
                isArduino = true;
            } catch(System.IO.FileNotFoundException e) { isArduino = false; }

            timer = new Timer();
            timer.Interval = 300;
            timer.Tick += timer_Tick;
            timer.Start();
        }

        private void timer_Tick(object sencder, EventArgs e)
        {
            if(isArduino)
            serialPort.DataReceived += serialPort_DataReceived;
        }

        private void Inicio_KeyUp(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.S)
            {
                serialPort.Close();
                this.Close(); // Cierra esta ventana
            }
        }

        private void serialPort_DataReceived(object sender, SerialDataReceivedEventArgs e)
        {
            serialPort = (SerialPort)sender;
            string input = serialPort.ReadLine(); // Lee el dato mandado de Arduino

            if (input.Contains("S"))
            {
                serialPort.Close();
                this.Close(); // Cierra esta ventana
            }
        }
    }
}
