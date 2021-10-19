
namespace Tetris
{
    partial class Juego
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Juego));
            this.panelTablero = new System.Windows.Forms.Panel();
            this.lPaused = new System.Windows.Forms.Label();
            this.panelFigura = new System.Windows.Forms.Panel();
            this.label1 = new System.Windows.Forms.Label();
            this.lScore = new System.Windows.Forms.Label();
            this.panelScore = new System.Windows.Forms.Panel();
            this.panelLines = new System.Windows.Forms.Panel();
            this.label2 = new System.Windows.Forms.Label();
            this.lLines = new System.Windows.Forms.Label();
            this.panelLevel = new System.Windows.Forms.Panel();
            this.label4 = new System.Windows.Forms.Label();
            this.lLevel = new System.Windows.Forms.Label();
            this.panelTablero.SuspendLayout();
            this.panelScore.SuspendLayout();
            this.panelLines.SuspendLayout();
            this.panelLevel.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelTablero
            // 
            this.panelTablero.BackColor = System.Drawing.Color.Black;
            this.panelTablero.Controls.Add(this.lPaused);
            this.panelTablero.Location = new System.Drawing.Point(32, 32);
            this.panelTablero.Name = "panelTablero";
            this.panelTablero.Size = new System.Drawing.Size(362, 572);
            this.panelTablero.TabIndex = 0;
            this.panelTablero.Paint += new System.Windows.Forms.PaintEventHandler(this.panelTablero_Paint);
            // 
            // lPaused
            // 
            this.lPaused.AutoSize = true;
            this.lPaused.BackColor = System.Drawing.Color.Transparent;
            this.lPaused.Font = new System.Drawing.Font("Consolas", 26F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.lPaused.ForeColor = System.Drawing.Color.White;
            this.lPaused.Location = new System.Drawing.Point(79, 184);
            this.lPaused.Name = "lPaused";
            this.lPaused.Size = new System.Drawing.Size(0, 61);
            this.lPaused.TabIndex = 1;
            this.lPaused.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // panelFigura
            // 
            this.panelFigura.AutoScroll = true;
            this.panelFigura.BackColor = System.Drawing.Color.White;
            this.panelFigura.ForeColor = System.Drawing.Color.Gray;
            this.panelFigura.Location = new System.Drawing.Point(468, 432);
            this.panelFigura.Name = "panelFigura";
            this.panelFigura.Size = new System.Drawing.Size(183, 151);
            this.panelFigura.TabIndex = 3;
            this.panelFigura.Paint += new System.Windows.Forms.PaintEventHandler(this.panelFigura_Paint);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.BackColor = System.Drawing.Color.Transparent;
            this.label1.Font = new System.Drawing.Font("Consolas", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.label1.ForeColor = System.Drawing.Color.Black;
            this.label1.Location = new System.Drawing.Point(42, 10);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(90, 33);
            this.label1.TabIndex = 4;
            this.label1.Text = "SCORE";
            // 
            // lScore
            // 
            this.lScore.AutoSize = true;
            this.lScore.BackColor = System.Drawing.Color.Black;
            this.lScore.Font = new System.Drawing.Font("Consolas", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.lScore.Location = new System.Drawing.Point(72, 54);
            this.lScore.Name = "lScore";
            this.lScore.RightToLeft = System.Windows.Forms.RightToLeft.Yes;
            this.lScore.Size = new System.Drawing.Size(60, 33);
            this.lScore.TabIndex = 6;
            this.lScore.Text = "000";
            // 
            // panelScore
            // 
            this.panelScore.AutoScroll = true;
            this.panelScore.BackColor = System.Drawing.Color.White;
            this.panelScore.Controls.Add(this.label1);
            this.panelScore.Controls.Add(this.lScore);
            this.panelScore.ForeColor = System.Drawing.Color.Transparent;
            this.panelScore.Location = new System.Drawing.Point(457, 32);
            this.panelScore.Name = "panelScore";
            this.panelScore.Size = new System.Drawing.Size(194, 104);
            this.panelScore.TabIndex = 10;
            this.panelScore.Paint += new System.Windows.Forms.PaintEventHandler(this.panelScore_Paint);
            // 
            // panelLines
            // 
            this.panelLines.AutoScroll = true;
            this.panelLines.BackColor = System.Drawing.Color.White;
            this.panelLines.Controls.Add(this.label2);
            this.panelLines.Controls.Add(this.lLines);
            this.panelLines.ForeColor = System.Drawing.Color.Transparent;
            this.panelLines.Location = new System.Drawing.Point(457, 294);
            this.panelLines.Name = "panelLines";
            this.panelLines.Size = new System.Drawing.Size(194, 104);
            this.panelLines.TabIndex = 11;
            this.panelLines.Paint += new System.Windows.Forms.PaintEventHandler(this.panelLines_Paint);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.BackColor = System.Drawing.Color.Transparent;
            this.label2.Font = new System.Drawing.Font("Consolas", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.label2.ForeColor = System.Drawing.Color.Black;
            this.label2.Location = new System.Drawing.Point(42, 10);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(90, 33);
            this.label2.TabIndex = 4;
            this.label2.Text = "LINES";
            // 
            // lLines
            // 
            this.lLines.AutoSize = true;
            this.lLines.BackColor = System.Drawing.Color.Black;
            this.lLines.Font = new System.Drawing.Font("Consolas", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.lLines.Location = new System.Drawing.Point(102, 53);
            this.lLines.Name = "lLines";
            this.lLines.RightToLeft = System.Windows.Forms.RightToLeft.Yes;
            this.lLines.Size = new System.Drawing.Size(30, 33);
            this.lLines.TabIndex = 6;
            this.lLines.Text = "0";
            // 
            // panelLevel
            // 
            this.panelLevel.AutoScroll = true;
            this.panelLevel.BackColor = System.Drawing.Color.White;
            this.panelLevel.Controls.Add(this.label4);
            this.panelLevel.Controls.Add(this.lLevel);
            this.panelLevel.ForeColor = System.Drawing.Color.Transparent;
            this.panelLevel.Location = new System.Drawing.Point(457, 163);
            this.panelLevel.Name = "panelLevel";
            this.panelLevel.Size = new System.Drawing.Size(194, 104);
            this.panelLevel.TabIndex = 11;
            this.panelLevel.Paint += new System.Windows.Forms.PaintEventHandler(this.panelLevel_Paint);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Consolas", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.label4.ForeColor = System.Drawing.Color.Black;
            this.label4.Location = new System.Drawing.Point(42, 10);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(90, 33);
            this.label4.TabIndex = 4;
            this.label4.Text = "LEVEL";
            // 
            // lLevel
            // 
            this.lLevel.AutoSize = true;
            this.lLevel.BackColor = System.Drawing.Color.Black;
            this.lLevel.Font = new System.Drawing.Font("Consolas", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.lLevel.Location = new System.Drawing.Point(102, 53);
            this.lLevel.Name = "lLevel";
            this.lLevel.RightToLeft = System.Windows.Forms.RightToLeft.Yes;
            this.lLevel.Size = new System.Drawing.Size(30, 33);
            this.lLevel.TabIndex = 6;
            this.lLevel.Text = "1";
            // 
            // Juego
            // 
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.None;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(18)))), ((int)(((byte)(157)))), ((int)(((byte)(162)))));
            this.ClientSize = new System.Drawing.Size(755, 619);
            this.Controls.Add(this.panelLevel);
            this.Controls.Add(this.panelLines);
            this.Controls.Add(this.panelScore);
            this.Controls.Add(this.panelFigura);
            this.Controls.Add(this.panelTablero);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "Juego";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Tetruino";
            this.KeyUp += new System.Windows.Forms.KeyEventHandler(this.Juego_KeyUp);
            this.panelTablero.ResumeLayout(false);
            this.panelTablero.PerformLayout();
            this.panelScore.ResumeLayout(false);
            this.panelScore.PerformLayout();
            this.panelLines.ResumeLayout(false);
            this.panelLines.PerformLayout();
            this.panelLevel.ResumeLayout(false);
            this.panelLevel.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.Panel panelTablero;
        private System.Windows.Forms.Panel panelFigura;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label lScore;
        private System.Windows.Forms.Panel panelScore;
        private System.Windows.Forms.Panel panelLines;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label lLines;
        private System.Windows.Forms.Panel panelLevel;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label lLevel;
        private System.Windows.Forms.Label lPaused;
    }
}

