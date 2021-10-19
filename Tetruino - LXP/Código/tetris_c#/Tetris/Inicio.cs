using System;
using System.IO.Ports;
using System.Windows.Forms;

namespace Tetris
{
    public partial class Inicio : Form
    {
        public Inicio()
        {
            InitializeComponent();
        }


        private void Inicio_KeyUp(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.S)
                this.Close(); // Cierra esta ventana
            
        }
    }
}
