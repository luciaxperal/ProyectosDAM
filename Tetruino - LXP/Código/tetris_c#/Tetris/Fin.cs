using System;
using System.IO.Ports;
using System.Windows.Forms;

namespace Tetris
{
    public partial class Fin : Form
    {
        public Fin()
        {
            InitializeComponent();
        }

        private void Fin_KeyUp(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.S)
                this.Close(); // Cierra esta ventana
            
            if (e.KeyCode == Keys.P)
                Application.Exit(); // Cierra la aplicación entera
            
        }
    }
}
