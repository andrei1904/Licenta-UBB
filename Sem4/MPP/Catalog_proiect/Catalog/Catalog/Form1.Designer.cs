namespace Catalog
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.tab1 = new System.Windows.Forms.TabControl();
            this.tabNote = new System.Windows.Forms.TabPage();
            this.buttonStergeNota = new System.Windows.Forms.Button();
            this.buttonModificaNota = new System.Windows.Forms.Button();
            this.buttonAdaugaNota = new System.Windows.Forms.Button();
            this.label10 = new System.Windows.Forms.Label();
            this.textBoxNota = new System.Windows.Forms.TextBox();
            this.dataGridViewNote = new System.Windows.Forms.DataGridView();
            this.label9 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.dataGridViewDisciplineNote = new System.Windows.Forms.DataGridView();
            this.dataGridViewStudentiNote = new System.Windows.Forms.DataGridView();
            this.tabStudenti = new System.Windows.Forms.TabPage();
            this.buttonDelete = new System.Windows.Forms.Button();
            this.buttonUpdate = new System.Windows.Forms.Button();
            this.buttonSave = new System.Windows.Forms.Button();
            this.textBoxPrenume = new System.Windows.Forms.TextBox();
            this.textBoxNume = new System.Windows.Forms.TextBox();
            this.textBoxNumar = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.dataGridViewStudents = new System.Windows.Forms.DataGridView();
            this.tabDiscipline = new System.Windows.Forms.TabPage();
            this.dataGridViewDiscipline = new System.Windows.Forms.DataGridView();
            this.buttonDeleteDisciplina = new System.Windows.Forms.Button();
            this.buttonUpdateDiscilina = new System.Windows.Forms.Button();
            this.buttonSaveDisciplina = new System.Windows.Forms.Button();
            this.textBoxNumarCrediteDisciplina = new System.Windows.Forms.TextBox();
            this.textBoxDenumireDisciplina = new System.Windows.Forms.TextBox();
            this.textBoxCodDisciplina = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.buttonGenereazaRaport = new System.Windows.Forms.Button();
            this.tab1.SuspendLayout();
            this.tabNote.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewNote)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewDisciplineNote)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewStudentiNote)).BeginInit();
            this.tabStudenti.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewStudents)).BeginInit();
            this.tabDiscipline.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewDiscipline)).BeginInit();
            this.SuspendLayout();
            // 
            // tab1
            // 
            this.tab1.Controls.Add(this.tabNote);
            this.tab1.Controls.Add(this.tabStudenti);
            this.tab1.Controls.Add(this.tabDiscipline);
            this.tab1.Location = new System.Drawing.Point(0, -1);
            this.tab1.Name = "tab1";
            this.tab1.SelectedIndex = 0;
            this.tab1.Size = new System.Drawing.Size(912, 566);
            this.tab1.TabIndex = 0;
            // 
            // tabNote
            // 
            this.tabNote.Controls.Add(this.buttonGenereazaRaport);
            this.tabNote.Controls.Add(this.buttonStergeNota);
            this.tabNote.Controls.Add(this.buttonModificaNota);
            this.tabNote.Controls.Add(this.buttonAdaugaNota);
            this.tabNote.Controls.Add(this.label10);
            this.tabNote.Controls.Add(this.textBoxNota);
            this.tabNote.Controls.Add(this.dataGridViewNote);
            this.tabNote.Controls.Add(this.label9);
            this.tabNote.Controls.Add(this.label8);
            this.tabNote.Controls.Add(this.label7);
            this.tabNote.Controls.Add(this.dataGridViewDisciplineNote);
            this.tabNote.Controls.Add(this.dataGridViewStudentiNote);
            this.tabNote.Location = new System.Drawing.Point(4, 29);
            this.tabNote.Name = "tabNote";
            this.tabNote.Padding = new System.Windows.Forms.Padding(3);
            this.tabNote.Size = new System.Drawing.Size(904, 533);
            this.tabNote.TabIndex = 0;
            this.tabNote.Text = "Note";
            this.tabNote.UseVisualStyleBackColor = true;
            // 
            // buttonStergeNota
            // 
            this.buttonStergeNota.Location = new System.Drawing.Point(780, 238);
            this.buttonStergeNota.Name = "buttonStergeNota";
            this.buttonStergeNota.Size = new System.Drawing.Size(100, 36);
            this.buttonStergeNota.TabIndex = 10;
            this.buttonStergeNota.Text = "Sterge";
            this.buttonStergeNota.UseVisualStyleBackColor = true;
            this.buttonStergeNota.Click += new System.EventHandler(this.buttonStergeNota_Click);
            // 
            // buttonModificaNota
            // 
            this.buttonModificaNota.Location = new System.Drawing.Point(780, 176);
            this.buttonModificaNota.Name = "buttonModificaNota";
            this.buttonModificaNota.Size = new System.Drawing.Size(100, 36);
            this.buttonModificaNota.TabIndex = 9;
            this.buttonModificaNota.Text = "Modifica";
            this.buttonModificaNota.UseVisualStyleBackColor = true;
            this.buttonModificaNota.Click += new System.EventHandler(this.buttonModificaNota_Click);
            // 
            // buttonAdaugaNota
            // 
            this.buttonAdaugaNota.Location = new System.Drawing.Point(780, 112);
            this.buttonAdaugaNota.Name = "buttonAdaugaNota";
            this.buttonAdaugaNota.Size = new System.Drawing.Size(100, 36);
            this.buttonAdaugaNota.TabIndex = 8;
            this.buttonAdaugaNota.Text = "Adauga";
            this.buttonAdaugaNota.UseVisualStyleBackColor = true;
            this.buttonAdaugaNota.Click += new System.EventHandler(this.buttonAdaugaNota_Click);
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(779, 36);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(101, 20);
            this.label10.TabIndex = 7;
            this.label10.Text = "Adauga nota";
            // 
            // textBoxNota
            // 
            this.textBoxNota.Location = new System.Drawing.Point(780, 66);
            this.textBoxNota.Name = "textBoxNota";
            this.textBoxNota.Size = new System.Drawing.Size(100, 26);
            this.textBoxNota.TabIndex = 6;
            // 
            // dataGridViewNote
            // 
            this.dataGridViewNote.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewNote.Location = new System.Drawing.Point(8, 342);
            this.dataGridViewNote.Name = "dataGridViewNote";
            this.dataGridViewNote.RowHeadersWidth = 62;
            this.dataGridViewNote.RowTemplate.Height = 28;
            this.dataGridViewNote.Size = new System.Drawing.Size(889, 180);
            this.dataGridViewNote.TabIndex = 5;
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(431, 308);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(43, 20);
            this.label9.TabIndex = 4;
            this.label9.Text = "Note";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(557, 7);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(76, 20);
            this.label8.TabIndex = 3;
            this.label8.Text = "Discipline";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(146, 7);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(69, 20);
            this.label7.TabIndex = 2;
            this.label7.Text = "Studenti";
            // 
            // dataGridViewDisciplineNote
            // 
            this.dataGridViewDisciplineNote.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewDisciplineNote.Location = new System.Drawing.Point(400, 36);
            this.dataGridViewDisciplineNote.Name = "dataGridViewDisciplineNote";
            this.dataGridViewDisciplineNote.RowHeadersWidth = 62;
            this.dataGridViewDisciplineNote.RowTemplate.Height = 28;
            this.dataGridViewDisciplineNote.Size = new System.Drawing.Size(355, 238);
            this.dataGridViewDisciplineNote.TabIndex = 1;
            // 
            // dataGridViewStudentiNote
            // 
            this.dataGridViewStudentiNote.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewStudentiNote.Location = new System.Drawing.Point(8, 36);
            this.dataGridViewStudentiNote.Name = "dataGridViewStudentiNote";
            this.dataGridViewStudentiNote.RowHeadersWidth = 62;
            this.dataGridViewStudentiNote.RowTemplate.Height = 28;
            this.dataGridViewStudentiNote.Size = new System.Drawing.Size(355, 238);
            this.dataGridViewStudentiNote.TabIndex = 0;
            this.dataGridViewStudentiNote.SelectionChanged += new System.EventHandler(this.dataGridViewStudentiNote_SelectionChanged);
            // 
            // tabStudenti
            // 
            this.tabStudenti.Controls.Add(this.buttonDelete);
            this.tabStudenti.Controls.Add(this.buttonUpdate);
            this.tabStudenti.Controls.Add(this.buttonSave);
            this.tabStudenti.Controls.Add(this.textBoxPrenume);
            this.tabStudenti.Controls.Add(this.textBoxNume);
            this.tabStudenti.Controls.Add(this.textBoxNumar);
            this.tabStudenti.Controls.Add(this.label3);
            this.tabStudenti.Controls.Add(this.label2);
            this.tabStudenti.Controls.Add(this.label1);
            this.tabStudenti.Controls.Add(this.dataGridViewStudents);
            this.tabStudenti.Location = new System.Drawing.Point(4, 29);
            this.tabStudenti.Name = "tabStudenti";
            this.tabStudenti.Padding = new System.Windows.Forms.Padding(3);
            this.tabStudenti.Size = new System.Drawing.Size(904, 533);
            this.tabStudenti.TabIndex = 1;
            this.tabStudenti.Text = "Studenti";
            this.tabStudenti.UseVisualStyleBackColor = true;
            this.tabStudenti.Click += new System.EventHandler(this.tabStudenti_Click);
            // 
            // buttonDelete
            // 
            this.buttonDelete.Location = new System.Drawing.Point(702, 427);
            this.buttonDelete.Name = "buttonDelete";
            this.buttonDelete.Size = new System.Drawing.Size(106, 50);
            this.buttonDelete.TabIndex = 9;
            this.buttonDelete.Text = "Sterge";
            this.buttonDelete.UseVisualStyleBackColor = true;
            this.buttonDelete.Click += new System.EventHandler(this.buttonDelete_Click);
            // 
            // buttonUpdate
            // 
            this.buttonUpdate.Location = new System.Drawing.Point(702, 346);
            this.buttonUpdate.Name = "buttonUpdate";
            this.buttonUpdate.Size = new System.Drawing.Size(106, 50);
            this.buttonUpdate.TabIndex = 8;
            this.buttonUpdate.Text = "Modifica";
            this.buttonUpdate.UseVisualStyleBackColor = true;
            this.buttonUpdate.Click += new System.EventHandler(this.buttonUpdate_Click);
            // 
            // buttonSave
            // 
            this.buttonSave.Location = new System.Drawing.Point(702, 271);
            this.buttonSave.Name = "buttonSave";
            this.buttonSave.Size = new System.Drawing.Size(106, 50);
            this.buttonSave.TabIndex = 7;
            this.buttonSave.Text = "Salveaza";
            this.buttonSave.UseVisualStyleBackColor = true;
            this.buttonSave.Click += new System.EventHandler(this.buttonSave_Click);
            // 
            // textBoxPrenume
            // 
            this.textBoxPrenume.Location = new System.Drawing.Point(142, 427);
            this.textBoxPrenume.Name = "textBoxPrenume";
            this.textBoxPrenume.Size = new System.Drawing.Size(142, 26);
            this.textBoxPrenume.TabIndex = 6;
            // 
            // textBoxNume
            // 
            this.textBoxNume.Location = new System.Drawing.Point(142, 358);
            this.textBoxNume.Name = "textBoxNume";
            this.textBoxNume.Size = new System.Drawing.Size(142, 26);
            this.textBoxNume.TabIndex = 5;
            // 
            // textBoxNumar
            // 
            this.textBoxNumar.Location = new System.Drawing.Point(142, 283);
            this.textBoxNumar.Name = "textBoxNumar";
            this.textBoxNumar.Size = new System.Drawing.Size(142, 26);
            this.textBoxNumar.TabIndex = 4;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(50, 427);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(73, 20);
            this.label3.TabIndex = 3;
            this.label3.Text = "Prenume";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(72, 358);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(51, 20);
            this.label2.TabIndex = 2;
            this.label2.Text = "Nume";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(8, 286);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(115, 20);
            this.label1.TabIndex = 1;
            this.label1.Text = "Numar matricol";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // dataGridViewStudents
            // 
            this.dataGridViewStudents.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewStudents.Location = new System.Drawing.Point(0, 6);
            this.dataGridViewStudents.Name = "dataGridViewStudents";
            this.dataGridViewStudents.RowHeadersWidth = 62;
            this.dataGridViewStudents.RowTemplate.Height = 28;
            this.dataGridViewStudents.Size = new System.Drawing.Size(901, 229);
            this.dataGridViewStudents.TabIndex = 0;
            this.dataGridViewStudents.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView1_CellContentClick);
            this.dataGridViewStudents.SelectionChanged += new System.EventHandler(this.dataGridViewStudents_SelectionChanged);
            // 
            // tabDiscipline
            // 
            this.tabDiscipline.Controls.Add(this.dataGridViewDiscipline);
            this.tabDiscipline.Controls.Add(this.buttonDeleteDisciplina);
            this.tabDiscipline.Controls.Add(this.buttonUpdateDiscilina);
            this.tabDiscipline.Controls.Add(this.buttonSaveDisciplina);
            this.tabDiscipline.Controls.Add(this.textBoxNumarCrediteDisciplina);
            this.tabDiscipline.Controls.Add(this.textBoxDenumireDisciplina);
            this.tabDiscipline.Controls.Add(this.textBoxCodDisciplina);
            this.tabDiscipline.Controls.Add(this.label4);
            this.tabDiscipline.Controls.Add(this.label5);
            this.tabDiscipline.Controls.Add(this.label6);
            this.tabDiscipline.Location = new System.Drawing.Point(4, 29);
            this.tabDiscipline.Name = "tabDiscipline";
            this.tabDiscipline.Size = new System.Drawing.Size(904, 533);
            this.tabDiscipline.TabIndex = 2;
            this.tabDiscipline.Text = "Discipline";
            this.tabDiscipline.UseVisualStyleBackColor = true;
            // 
            // dataGridViewDiscipline
            // 
            this.dataGridViewDiscipline.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewDiscipline.Location = new System.Drawing.Point(8, 3);
            this.dataGridViewDiscipline.Name = "dataGridViewDiscipline";
            this.dataGridViewDiscipline.RowHeadersWidth = 62;
            this.dataGridViewDiscipline.RowTemplate.Height = 28;
            this.dataGridViewDiscipline.Size = new System.Drawing.Size(889, 244);
            this.dataGridViewDiscipline.TabIndex = 19;
            this.dataGridViewDiscipline.SelectionChanged += new System.EventHandler(this.dataGridViewDiscipline_SelectionChanged);
            // 
            // buttonDeleteDisciplina
            // 
            this.buttonDeleteDisciplina.Location = new System.Drawing.Point(723, 423);
            this.buttonDeleteDisciplina.Name = "buttonDeleteDisciplina";
            this.buttonDeleteDisciplina.Size = new System.Drawing.Size(106, 50);
            this.buttonDeleteDisciplina.TabIndex = 18;
            this.buttonDeleteDisciplina.Text = "Sterge";
            this.buttonDeleteDisciplina.UseVisualStyleBackColor = true;
            this.buttonDeleteDisciplina.Click += new System.EventHandler(this.buttonDeleteDisciplina_Click);
            // 
            // buttonUpdateDiscilina
            // 
            this.buttonUpdateDiscilina.Location = new System.Drawing.Point(723, 342);
            this.buttonUpdateDiscilina.Name = "buttonUpdateDiscilina";
            this.buttonUpdateDiscilina.Size = new System.Drawing.Size(106, 50);
            this.buttonUpdateDiscilina.TabIndex = 17;
            this.buttonUpdateDiscilina.Text = "Modifica";
            this.buttonUpdateDiscilina.UseVisualStyleBackColor = true;
            this.buttonUpdateDiscilina.Click += new System.EventHandler(this.buttonUpdateDiscilina_Click);
            // 
            // buttonSaveDisciplina
            // 
            this.buttonSaveDisciplina.Location = new System.Drawing.Point(723, 267);
            this.buttonSaveDisciplina.Name = "buttonSaveDisciplina";
            this.buttonSaveDisciplina.Size = new System.Drawing.Size(106, 50);
            this.buttonSaveDisciplina.TabIndex = 16;
            this.buttonSaveDisciplina.Text = "Salveaza";
            this.buttonSaveDisciplina.UseVisualStyleBackColor = true;
            this.buttonSaveDisciplina.Click += new System.EventHandler(this.buttonSaveDisciplina_Click);
            // 
            // textBoxNumarCrediteDisciplina
            // 
            this.textBoxNumarCrediteDisciplina.Location = new System.Drawing.Point(163, 423);
            this.textBoxNumarCrediteDisciplina.Name = "textBoxNumarCrediteDisciplina";
            this.textBoxNumarCrediteDisciplina.Size = new System.Drawing.Size(142, 26);
            this.textBoxNumarCrediteDisciplina.TabIndex = 15;
            // 
            // textBoxDenumireDisciplina
            // 
            this.textBoxDenumireDisciplina.Location = new System.Drawing.Point(163, 354);
            this.textBoxDenumireDisciplina.Name = "textBoxDenumireDisciplina";
            this.textBoxDenumireDisciplina.Size = new System.Drawing.Size(142, 26);
            this.textBoxDenumireDisciplina.TabIndex = 14;
            // 
            // textBoxCodDisciplina
            // 
            this.textBoxCodDisciplina.Location = new System.Drawing.Point(163, 279);
            this.textBoxCodDisciplina.Name = "textBoxCodDisciplina";
            this.textBoxCodDisciplina.Size = new System.Drawing.Size(142, 26);
            this.textBoxCodDisciplina.TabIndex = 13;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(36, 426);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(108, 20);
            this.label4.TabIndex = 12;
            this.label4.Text = "Numar credite";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(66, 360);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(78, 20);
            this.label5.TabIndex = 11;
            this.label5.Text = "Denumire";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(38, 282);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(106, 20);
            this.label6.TabIndex = 10;
            this.label6.Text = "Cod disciplina";
            // 
            // buttonGenereazaRaport
            // 
            this.buttonGenereazaRaport.Location = new System.Drawing.Point(718, 292);
            this.buttonGenereazaRaport.Name = "buttonGenereazaRaport";
            this.buttonGenereazaRaport.Size = new System.Drawing.Size(162, 36);
            this.buttonGenereazaRaport.TabIndex = 11;
            this.buttonGenereazaRaport.Text = "Genereaza rapot";
            this.buttonGenereazaRaport.UseVisualStyleBackColor = true;
            this.buttonGenereazaRaport.Click += new System.EventHandler(this.buttonGenereazaRaport_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(913, 562);
            this.Controls.Add(this.tab1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.tab1.ResumeLayout(false);
            this.tabNote.ResumeLayout(false);
            this.tabNote.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewNote)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewDisciplineNote)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewStudentiNote)).EndInit();
            this.tabStudenti.ResumeLayout(false);
            this.tabStudenti.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewStudents)).EndInit();
            this.tabDiscipline.ResumeLayout(false);
            this.tabDiscipline.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewDiscipline)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tab1;
        private System.Windows.Forms.TabPage tabNote;
        private System.Windows.Forms.TabPage tabStudenti;
        private System.Windows.Forms.DataGridView dataGridViewStudents;
        private System.Windows.Forms.TabPage tabDiscipline;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button buttonDelete;
        private System.Windows.Forms.Button buttonUpdate;
        private System.Windows.Forms.Button buttonSave;
        private System.Windows.Forms.TextBox textBoxPrenume;
        private System.Windows.Forms.TextBox textBoxNume;
        private System.Windows.Forms.TextBox textBoxNumar;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button buttonDeleteDisciplina;
        private System.Windows.Forms.Button buttonUpdateDiscilina;
        private System.Windows.Forms.Button buttonSaveDisciplina;
        private System.Windows.Forms.TextBox textBoxNumarCrediteDisciplina;
        private System.Windows.Forms.TextBox textBoxDenumireDisciplina;
        private System.Windows.Forms.TextBox textBoxCodDisciplina;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.DataGridView dataGridViewDiscipline;
        private System.Windows.Forms.Button buttonAdaugaNota;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.TextBox textBoxNota;
        private System.Windows.Forms.DataGridView dataGridViewNote;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.DataGridView dataGridViewDisciplineNote;
        private System.Windows.Forms.DataGridView dataGridViewStudentiNote;
        private System.Windows.Forms.Button buttonStergeNota;
        private System.Windows.Forms.Button buttonModificaNota;
        private System.Windows.Forms.Button buttonGenereazaRaport;
    }
}

