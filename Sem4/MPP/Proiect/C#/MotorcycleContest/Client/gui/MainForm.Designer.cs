using System.ComponentModel;

namespace Client.gui
{
    partial class MainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private IContainer components = null;

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
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.buttonAdd = new System.Windows.Forms.Button();
            this.comboBoxSelectedRace = new System.Windows.Forms.ComboBox();
            this.comboBoxSelectedEngineCapacity = new System.Windows.Forms.ComboBox();
            this.comboBoxSelectedTeam = new System.Windows.Forms.ComboBox();
            this.textBoxName = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.dataGridViewRace = new System.Windows.Forms.DataGridView();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.buttonSearchMembers = new System.Windows.Forms.Button();
            this.comboBoxTeams = new System.Windows.Forms.ComboBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.dataGridViewMembers = new System.Windows.Forms.DataGridView();
            this.buttonLogout = new System.Windows.Forms.Button();
            this.tabControl1.SuspendLayout();
            this.tabPage1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize) (this.dataGridViewRace)).BeginInit();
            this.tabPage2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize) (this.dataGridViewMembers)).BeginInit();
            this.SuspendLayout();
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Controls.Add(this.tabPage2);
            this.tabControl1.HotTrack = true;
            this.tabControl1.Location = new System.Drawing.Point(12, 12);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(888, 470);
            this.tabControl1.TabIndex = 0;
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.buttonAdd);
            this.tabPage1.Controls.Add(this.comboBoxSelectedRace);
            this.tabPage1.Controls.Add(this.comboBoxSelectedEngineCapacity);
            this.tabPage1.Controls.Add(this.comboBoxSelectedTeam);
            this.tabPage1.Controls.Add(this.textBoxName);
            this.tabPage1.Controls.Add(this.label6);
            this.tabPage1.Controls.Add(this.label5);
            this.tabPage1.Controls.Add(this.label4);
            this.tabPage1.Controls.Add(this.label3);
            this.tabPage1.Controls.Add(this.dataGridViewRace);
            this.tabPage1.Location = new System.Drawing.Point(4, 29);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(880, 437);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "All Races";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // buttonAdd
            // 
            this.buttonAdd.Location = new System.Drawing.Point(770, 313);
            this.buttonAdd.Name = "buttonAdd";
            this.buttonAdd.Size = new System.Drawing.Size(92, 55);
            this.buttonAdd.TabIndex = 2;
            this.buttonAdd.Text = "Add Participant";
            this.buttonAdd.UseVisualStyleBackColor = true;
            this.buttonAdd.Click += new System.EventHandler(this.buttonAdd_Click);
            // 
            // comboBoxSelectedRace
            // 
            this.comboBoxSelectedRace.FormattingEnabled = true;
            this.comboBoxSelectedRace.Location = new System.Drawing.Point(546, 362);
            this.comboBoxSelectedRace.Name = "comboBoxSelectedRace";
            this.comboBoxSelectedRace.Size = new System.Drawing.Size(185, 28);
            this.comboBoxSelectedRace.TabIndex = 8;
            // 
            // comboBoxSelectedEngineCapacity
            // 
            this.comboBoxSelectedEngineCapacity.FormattingEnabled = true;
            this.comboBoxSelectedEngineCapacity.Location = new System.Drawing.Point(546, 282);
            this.comboBoxSelectedEngineCapacity.Name = "comboBoxSelectedEngineCapacity";
            this.comboBoxSelectedEngineCapacity.Size = new System.Drawing.Size(185, 28);
            this.comboBoxSelectedEngineCapacity.TabIndex = 7;
            this.comboBoxSelectedEngineCapacity.SelectedIndexChanged += new System.EventHandler(this.comboBoxSelectedEngineCapacity_SelectedIndexChanged);
            // 
            // comboBoxSelectedTeam
            // 
            this.comboBoxSelectedTeam.FormattingEnabled = true;
            this.comboBoxSelectedTeam.Location = new System.Drawing.Point(123, 362);
            this.comboBoxSelectedTeam.Name = "comboBoxSelectedTeam";
            this.comboBoxSelectedTeam.Size = new System.Drawing.Size(185, 28);
            this.comboBoxSelectedTeam.TabIndex = 6;
            // 
            // textBoxName
            // 
            this.textBoxName.Location = new System.Drawing.Point(123, 286);
            this.textBoxName.Name = "textBoxName";
            this.textBoxName.Size = new System.Drawing.Size(186, 26);
            this.textBoxName.TabIndex = 5;
            // 
            // label6
            // 
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte) (0)));
            this.label6.Location = new System.Drawing.Point(343, 362);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(197, 32);
            this.label6.TabIndex = 4;
            this.label6.Text = "Available Races:";
            // 
            // label5
            // 
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte) (0)));
            this.label5.Location = new System.Drawing.Point(343, 282);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(197, 32);
            this.label5.TabIndex = 3;
            this.label5.Text = "Engine Capacity:";
            // 
            // label4
            // 
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte) (0)));
            this.label4.Location = new System.Drawing.Point(26, 362);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(91, 32);
            this.label4.TabIndex = 2;
            this.label4.Text = "Team:";
            // 
            // label3
            // 
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte) (0)));
            this.label3.Location = new System.Drawing.Point(26, 282);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(91, 32);
            this.label3.TabIndex = 1;
            this.label3.Text = "Name:";
            // 
            // dataGridViewRace
            // 
            this.dataGridViewRace.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewRace.Location = new System.Drawing.Point(-4, 0);
            this.dataGridViewRace.Name = "dataGridViewRace";
            this.dataGridViewRace.RowTemplate.Height = 28;
            this.dataGridViewRace.Size = new System.Drawing.Size(883, 261);
            this.dataGridViewRace.TabIndex = 0;
            // 
            // tabPage2
            // 
            this.tabPage2.Controls.Add(this.buttonSearchMembers);
            this.tabPage2.Controls.Add(this.comboBoxTeams);
            this.tabPage2.Controls.Add(this.label2);
            this.tabPage2.Controls.Add(this.label1);
            this.tabPage2.Controls.Add(this.dataGridViewMembers);
            this.tabPage2.Location = new System.Drawing.Point(4, 29);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(880, 437);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "Search";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // buttonSearchMembers
            // 
            this.buttonSearchMembers.Location = new System.Drawing.Point(634, 190);
            this.buttonSearchMembers.Name = "buttonSearchMembers";
            this.buttonSearchMembers.Size = new System.Drawing.Size(126, 39);
            this.buttonSearchMembers.TabIndex = 4;
            this.buttonSearchMembers.Text = "Search";
            this.buttonSearchMembers.UseVisualStyleBackColor = true;
            this.buttonSearchMembers.Click += new System.EventHandler(this.buttonSearchMembers_Click);
            // 
            // comboBoxTeams
            // 
            this.comboBoxTeams.FormattingEnabled = true;
            this.comboBoxTeams.Location = new System.Drawing.Point(583, 133);
            this.comboBoxTeams.Name = "comboBoxTeams";
            this.comboBoxTeams.Size = new System.Drawing.Size(215, 28);
            this.comboBoxTeams.TabIndex = 3;
            // 
            // label2
            // 
            this.label2.Location = new System.Drawing.Point(664, 98);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(79, 21);
            this.label2.TabIndex = 2;
            this.label2.Text = "Teams";
            // 
            // label1
            // 
            this.label1.Location = new System.Drawing.Point(182, 14);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(79, 21);
            this.label1.TabIndex = 1;
            this.label1.Text = "Members";
            // 
            // dataGridViewMembers
            // 
            this.dataGridViewMembers.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewMembers.Location = new System.Drawing.Point(2, 38);
            this.dataGridViewMembers.Name = "dataGridViewMembers";
            this.dataGridViewMembers.RowTemplate.Height = 28;
            this.dataGridViewMembers.Size = new System.Drawing.Size(491, 402);
            this.dataGridViewMembers.TabIndex = 0;
            // 
            // buttonLogout
            // 
            this.buttonLogout.Location = new System.Drawing.Point(815, 488);
            this.buttonLogout.Name = "buttonLogout";
            this.buttonLogout.Size = new System.Drawing.Size(81, 29);
            this.buttonLogout.TabIndex = 1;
            this.buttonLogout.Text = "Logout";
            this.buttonLogout.UseVisualStyleBackColor = true;
            this.buttonLogout.Click += new System.EventHandler(this.buttonLogout_Click);
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(912, 524);
            this.Controls.Add(this.buttonLogout);
            this.Controls.Add(this.tabControl1);
            this.Name = "MainForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "MainForm";
            this.tabControl1.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize) (this.dataGridViewRace)).EndInit();
            this.tabPage2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize) (this.dataGridViewMembers)).EndInit();
            this.ResumeLayout(false);
        }

        private System.Windows.Forms.Button buttonAdd;
        private System.Windows.Forms.ComboBox comboBoxSelectedEngineCapacity;
        private System.Windows.Forms.ComboBox comboBoxSelectedRace;
        private System.Windows.Forms.ComboBox comboBoxSelectedTeam;
        private System.Windows.Forms.TextBox textBoxName;

        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;

        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;

        private System.Windows.Forms.DataGridView dataGridViewMembers;

        private System.Windows.Forms.Button buttonSearchMembers;

        private System.Windows.Forms.ComboBox comboBoxTeams;

        private System.Windows.Forms.Label label2;

        private System.Windows.Forms.Label label1;

        private System.Windows.Forms.DataGridView dataGridViewRace;

        private System.Windows.Forms.Button buttonLogout;

        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;

        #endregion
    }
}