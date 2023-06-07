using System;
using System.Windows.Forms;
using Catalog.service;

namespace Catalog
{
    partial class Form1 : Form
    {
        AllService service;

        public Form1(AllService service)
        {
            this.service = service;
            InitializeComponent();

            ShowStudenti();
            ShowDiscipline();
        }

        private void ShowStudenti()
        {
            dataGridViewStudents.DataSource = service.GetAllStudents();
            dataGridViewStudentiNote.DataSource = service.GetAllStudents();
        }

        private void ShowDiscipline()
        {
            dataGridViewDiscipline.DataSource = service.GetAllDisciplines();
            dataGridViewDisciplineNote.DataSource = service.GetAllDisciplines();
        }

        private void ShowNote(int numarMatricol)
        {
            dataGridViewNote.DataSource = service.GetAllGradesForStudent(numarMatricol);
        }

        private void tabStudenti_Click(object sender, EventArgs e)
        {

        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void buttonSave_Click(object sender, EventArgs e)
        {
            if (textBoxNumar.Text == "" || textBoxNume.Text == "" || textBoxPrenume.Text == "")
            {
                MessageBox.Show("Completati campurile!");
                return;
            }

            try
            {
                service.AddStudent(int.Parse(textBoxNumar.Text), textBoxNume.Text, textBoxPrenume.Text);
                ShowStudenti();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private int SelectionChanged(DataGridView dataGridView, string name)
        {
            var selectedRowIndex = -1;
            var cellsCount = dataGridView.SelectedCells.Count;
            if (cellsCount == 0 || cellsCount > 1)
            {
                var rowsCount = dataGridView.SelectedRows.Count;
                if (rowsCount == 0 || rowsCount > 1)
                {
                    MessageBox.Show(this, @"Wrong selection!");
                    return -1;
                }

                selectedRowIndex = dataGridView.SelectedRows[0].Index;
            }

            if (selectedRowIndex == -1)
            {
                selectedRowIndex = dataGridView.SelectedCells[0].RowIndex;
            }

            var row = dataGridView.Rows[selectedRowIndex];

            var rowId = row.Cells[name].Value;
            if (rowId != null && rowId is int id) 
                return id;

            MessageBox.Show(this, @"Wrong selection!");
            return -1;
        }

        private void dataGridViewStudents_SelectionChanged(object sender, EventArgs e)
        {
            if (!dataGridViewStudents.Focused)
            {
                return;
            }

            var id = SelectionChanged(dataGridViewStudents, "NrMatricol");

            if (id == -1)
            {
                return;
            }

            textBoxNumar.Text = dataGridViewStudents.CurrentRow.Cells[0].Value.ToString();
            textBoxNume.Text = dataGridViewStudents.CurrentRow.Cells[1].Value.ToString();
            textBoxPrenume.Text = dataGridViewStudents.CurrentRow.Cells[2].Value.ToString();
        }

        private void buttonDelete_Click(object sender, EventArgs e)
        {

            var id = SelectionChanged(dataGridViewStudents, "NrMatricol");

            if (id == -1)
            {
                return;
            }

            try
            {
                service.DeleteStudent(int.Parse(dataGridViewStudents.CurrentRow.Cells[0].Value.ToString()));
                ShowStudenti();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void buttonUpdate_Click(object sender, EventArgs e)
        {
            if (textBoxNumar.Text == "" || textBoxNume.Text == "" || textBoxPrenume.Text == "")
            {
                MessageBox.Show("Completati campurile!");
                return;
            }

            try
            {
                service.UpdateStudent(int.Parse(textBoxNumar.Text), textBoxNume.Text, textBoxPrenume.Text);
                ShowStudenti();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void dataGridViewDiscipline_SelectionChanged(object sender, EventArgs e)
        {
            if (!dataGridViewDiscipline.Focused)
            {
                return;
            }

            textBoxCodDisciplina.Text = dataGridViewDiscipline.CurrentRow.Cells[0].Value.ToString();
            textBoxDenumireDisciplina.Text = dataGridViewDiscipline.CurrentRow.Cells[1].Value.ToString();
            textBoxNumarCrediteDisciplina.Text = dataGridViewDiscipline.CurrentRow.Cells[2].Value.ToString();
        }

        private void buttonSaveDisciplina_Click(object sender, EventArgs e)
        {
            if (textBoxCodDisciplina.Text == "" || textBoxDenumireDisciplina.Text == "" ||
                textBoxNumarCrediteDisciplina.Text == "")
            {
                MessageBox.Show("Completati campurile!");
                return;
            }

            try
            {
                service.AddDisciplina(textBoxCodDisciplina.Text, textBoxDenumireDisciplina.Text,
                    int.Parse(textBoxNumarCrediteDisciplina.Text));
                ShowDiscipline();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void buttonDeleteDisciplina_Click(object sender, EventArgs e)
        {
            try
            {
                service.DeleteDisciplina(dataGridViewDiscipline.CurrentRow.Cells[0].Value.ToString());
                ShowDiscipline();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void buttonUpdateDiscilina_Click(object sender, EventArgs e)
        {
            if (textBoxCodDisciplina.Text == "" || textBoxDenumireDisciplina.Text == "" ||
                textBoxNumarCrediteDisciplina.Text == "")
            {
                MessageBox.Show("Completati campurile!");
                return;
            }

            try
            {
                service.UpdateDisciplina(textBoxCodDisciplina.Text, textBoxDenumireDisciplina.Text,
                    int.Parse(textBoxNumarCrediteDisciplina.Text));
                ShowDiscipline();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void dataGridViewStudentiNote_SelectionChanged(object sender, EventArgs e)
        {
            if (!dataGridViewStudentiNote.Focused)
            {
                return;
            }

            var nrMatricol = int.Parse(dataGridViewStudentiNote.CurrentRow.Cells[0].Value.ToString());
            ShowNote(nrMatricol);
        }

        private void buttonAdaugaNota_Click(object sender, EventArgs e)
        {
            var nrMatricol = int.Parse(dataGridViewStudentiNote.CurrentRow.Cells[0].Value.ToString());
            var codDisciplina = dataGridViewDisciplineNote.CurrentRow.Cells[0].Value.ToString();
        
            if (textBoxNota.Text == "")
            {
                MessageBox.Show("Adaugati o nota!");
                return;
            }
            var nota = int.Parse(textBoxNota.Text);

            try
            {
                service.AddNota(nrMatricol, codDisciplina, nota);
                ShowNote(nrMatricol);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void buttonModificaNota_Click(object sender, EventArgs e)
        {
            var nrMatricol = int.Parse(dataGridViewNote.CurrentRow.Cells[0].Value.ToString());
            var codDisciplina = dataGridViewNote.CurrentRow.Cells[1].Value.ToString();

            if (textBoxNota.Text == "")
            {
                MessageBox.Show("Adaugati o nota!");
                return;
            }
            var nota = int.Parse(textBoxNota.Text);

            try
            {
                service.ModificaNota(nrMatricol, codDisciplina, nota);
                ShowNote(nrMatricol);
            } catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void buttonStergeNota_Click(object sender, EventArgs e)
        {
            var nrMatricol = int.Parse(dataGridViewNote.CurrentRow.Cells[0].Value.ToString());
            var codDisciplina = dataGridViewNote.CurrentRow.Cells[1].Value.ToString();

            try
            {
                service.StergeNota(nrMatricol, codDisciplina);
                ShowNote(nrMatricol);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void buttonGenereazaRaport_Click(object sender, EventArgs e)
        {
            service.GenereazaRaport();

            MessageBox.Show("Raport generat");
        }
    }
}
