using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;


namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        SqlConnection cs = new SqlConnection("Data Source=DESKTOP-99N7EQ6;Initial Catalog" +
            "=BloodBank;Integrated Security=True");
        SqlDataAdapter da = new SqlDataAdapter();
        DataSet ds = new DataSet();


        public Form1()
        {
            ds.Tables.Add("BloodBanks");
            ds.Tables.Add("Addresses");
            ds.Tables.Add("Donors");

            InitializeComponent();
            ShowContent();
            LoadComboBoxes();
        }

        private void ShowContent()
        {
            da.SelectCommand = new SqlCommand("SELECT * FROM BloodBanks", cs);
            ds.Tables["BloodBanks"].Clear();
            da.Fill(ds.Tables["BloodBanks"]);
            dataGridViewBloodBanks.DataSource = ds.Tables["BloodBanks"];

            // pupulez combobox
            var val = ds.Tables["BloodBanks"].AsEnumerable().Select(x => x["BloodBankId"]).ToList();
            foreach (var x in val)
            {
                comboBoxBank.Items.Add(x);
            }
        }

        private void LoadComboBoxes()
        {
            da.SelectCommand = new SqlCommand("SELECT * FROM Addresses", cs);
            ds.Tables["Addresses"].Clear();
            da.Fill(ds.Tables["Addresses"]);
            var val = ds.Tables["Addresses"].AsEnumerable().Select(x => x["AddressId"]).ToList();
            foreach (var x in val)
            {
                comboBoxAddress.Items.Add(x);
            }
        }

        private void dataGridViewBloodBanks_SelectionChanged(object sender, EventArgs e)
        {
            if (!dataGridViewBloodBanks.Focused)
                return;


            int selectedRowIndex = -1;
            var cellsCount = dataGridViewBloodBanks.SelectedCells.Count;
            if (cellsCount == 0 || cellsCount > 1)
            {
                var rowsCount = dataGridViewBloodBanks.SelectedRows.Count;
                if (rowsCount == 0 || rowsCount > 1)
                {
                    MessageBox.Show("Wrong selection!");
                    return;
                }

                selectedRowIndex = dataGridViewBloodBanks.SelectedRows[0].Index;
            }

            if (selectedRowIndex == -1)
            {
                selectedRowIndex = dataGridViewBloodBanks.SelectedCells[0].RowIndex;
            }

            var row = dataGridViewBloodBanks.Rows[selectedRowIndex];
            if (row == null)
                return;

            var bankId = row.Cells["BloodBankId"].Value;
            if (bankId == null || bankId.GetType() != typeof(int))
            {
                MessageBox.Show("Wrong selection!");
                return;
            }


            da.SelectCommand = new SqlCommand("SELECT * FROM Donors " +
                                              "WHERE BloodBankId=" + bankId, cs);
            ds.Tables["Donors"].Clear();
            da.Fill(ds.Tables["Donors"]);
            dataGridViewDonors.DataSource = ds.Tables["Donors"];
        }

        private void buttonDelete_Click(object sender, EventArgs e)
        {
          
            int selectedRowIndex = -1;
            var cellsCount = dataGridViewDonors.SelectedCells.Count;
            if (cellsCount == 0 || cellsCount > 1)
            {
                var rowsCount = dataGridViewDonors.SelectedRows.Count;
                if (rowsCount == 0 || rowsCount > 1)
                {
                    MessageBox.Show("Wrong selection!");
                    return;
                }

                selectedRowIndex = dataGridViewDonors.SelectedRows[0].Index;
            }

            if (selectedRowIndex == -1)
            {
                selectedRowIndex = dataGridViewDonors.SelectedCells[0].RowIndex;
            }

            var row = dataGridViewDonors.Rows[selectedRowIndex];
            if (row == null)
                return;

            var donorId = row.Cells["DonorId"].Value;
            if (donorId == null || donorId.GetType() != typeof(int))
            {
                MessageBox.Show("Wrong selection!");
                return;
            }


            try
            {
                da.InsertCommand = new SqlCommand("DELETE FROM Donors WHERE DonorId=" + donorId, cs);
                cs.Open();
                da.InsertCommand.ExecuteNonQuery();
                cs.Close();

                ds.Tables["Donors"].Clear();
                da.Fill(ds.Tables["Donors"]);
                dataGridViewDonors.DataSource = ds.Tables["Donors"];

                comboBoxAddress.SelectedItem = null;
                comboBoxBank.SelectedItem = null;
                comboBoxGender.SelectedItem = null;
                labelMedicalNo.Text = "";
                labelFirstName.Text = "";
                labelLastName.Text = "";
                dateTimePicker1.Value = DateTime.Now;
                labelPhone.Text = "";
            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message);
                cs.Close();
            }
        }

        private void dataGridViewDonors_SelectionChanged(object sender, EventArgs e)
        {
            if (!dataGridViewDonors.Focused)
                return;

            int selectedRowIndex = -1;
            var cellsCount = dataGridViewDonors.SelectedCells.Count;
            if (cellsCount == 0 || cellsCount > 1)
            {
                var rowsCount = dataGridViewDonors.SelectedRows.Count;
                if (rowsCount == 0 || rowsCount > 1)
                {
                    MessageBox.Show("Wrong selection!");
                    return;
                }

                selectedRowIndex = dataGridViewDonors.SelectedRows[0].Index;
            }

            if (selectedRowIndex == -1)
            {
                selectedRowIndex = dataGridViewDonors.SelectedCells[0].RowIndex;
            }

            var row = dataGridViewDonors.Rows[selectedRowIndex];
            if (row == null)
                return;

            var donorId = row.Cells["DonorId"].Value;
            if (donorId == null || donorId.GetType() != typeof(int))
            {
                MessageBox.Show("Wrong selection!");
                return;
            }


            var medicalNo = row.Cells["NationalMediaclNumber"].Value;
            labelMedicalNo.Text = medicalNo.ToString();

            var firstName = row.Cells["FirstName"].Value;
            labelFirstName.Text = firstName.ToString();

            var lastName = row.Cells["LastName"].Value;
            labelLastName.Text = lastName.ToString();

            var phoneNumber = row.Cells["PhoneNumber"].Value;
            labelPhone.Text = phoneNumber.ToString();

            var birthDate = row.Cells["DateOfBirth"].Value;
            dateTimePicker1.Value = DateTime.Parse(birthDate.ToString());

            var gender = row.Cells["Gender"].Value;
            comboBoxGender.SelectedItem = gender.ToString();

            var addressIds = row.Cells["AdressId"].Value;
            comboBoxAddress.SelectedItem = addressIds;

            var bankIds = row.Cells["BloodBankId"].Value;
            comboBoxBank.SelectedItem = bankIds;


        }

        private void updateDataGridViewDonors()
        {
            
            int selectedRowIndex = -1;
            var cellsCount = dataGridViewBloodBanks.SelectedCells.Count;
            if (cellsCount == 0 || cellsCount > 1)
            {
                var rowsCount = dataGridViewBloodBanks.SelectedRows.Count;
                if (rowsCount == 0 || rowsCount > 1)
                {
                    MessageBox.Show("Wrong selection!");
                    return;
                }

                selectedRowIndex = dataGridViewBloodBanks.SelectedRows[0].Index;
            }

            if (selectedRowIndex == -1)
            {
                selectedRowIndex = dataGridViewBloodBanks.SelectedCells[0].RowIndex;
            }

            var row = dataGridViewBloodBanks.Rows[selectedRowIndex];
            if (row == null)
                return;

            var bankId = row.Cells["BloodBankId"].Value;
            if (bankId == null || bankId.GetType() != typeof(int))
            {
                MessageBox.Show("Wrong selection!");
                return;
            }


            da.SelectCommand = new SqlCommand("SELECT * FROM Donors " +
                                              "WHERE BloodBankId=" + bankId, cs);
            ds.Tables["Donors"].Clear();
            da.Fill(ds.Tables["Donors"]);
            dataGridViewDonors.DataSource = ds.Tables["Donors"];
        }

        private void buttonInsert_Click(object sender, EventArgs e)
        {
            var medicalNo = labelMedicalNo.Text;

            var firstName = labelFirstName.Text;

            var lastName = labelLastName.Text;

            var phoneNumber = labelPhone.Text;

            var birthDate = dateTimePicker1.Value;

            if (comboBoxGender.SelectedItem == null)
            {
                return;
            }
            var gender = comboBoxGender.SelectedItem.ToString();

            var addressIds = comboBoxAddress.Text;

            var bankIds = comboBoxBank.Text;
          


            try
            {
                da.InsertCommand = new SqlCommand("INSERT INTO Donors (AdressId, BloodBankId, NationalMediaclNumber, Gender, DateOfBirth, FirstName, LastName, PhoneNumber) " +
                    "VALUES(@adrid, @bankid, @medno, @gender, @date, @first, @last, @phone)", cs);

                da.InsertCommand.Parameters.Add("@adrid", SqlDbType.Int).Value = addressIds;

                da.InsertCommand.Parameters.Add("@bankid", SqlDbType.Int).Value = bankIds;

                da.InsertCommand.Parameters.Add("@medno", SqlDbType.Int).Value = medicalNo;

                da.InsertCommand.Parameters.Add("@gender", SqlDbType.VarChar).Value = gender;

                da.InsertCommand.Parameters.Add("@date", SqlDbType.Date).Value = birthDate;

                da.InsertCommand.Parameters.Add("@first", SqlDbType.VarChar).Value = firstName;

                da.InsertCommand.Parameters.Add("@last", SqlDbType.VarChar).Value = lastName;

                da.InsertCommand.Parameters.Add("@phone", SqlDbType.Int).Value = phoneNumber;


                cs.Open();
                da.InsertCommand.ExecuteNonQuery();
                cs.Close();


                comboBoxAddress.SelectedItem = null;
                comboBoxBank.SelectedItem = null;
                comboBoxGender.SelectedItem = null;
                labelMedicalNo.Text = "";
                labelFirstName.Text = "";
                labelLastName.Text = "";
                dateTimePicker1.Value = DateTime.Now;
                labelPhone.Text = "";

                MessageBox.Show("Successfully inserted!");
                updateDataGridViewDonors();

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
                cs.Close();
            }
        }

        private void buttonUpdate_Click(object sender, EventArgs e)
        {
            
            int selectedRowIndex = -1;
            var cellsCount = dataGridViewDonors.SelectedCells.Count;
            if (cellsCount == 0 || cellsCount > 1)
            {
                var rowsCount = dataGridViewDonors.SelectedRows.Count;
                if (rowsCount == 0 || rowsCount > 1)
                {
                    MessageBox.Show("Wrong selection!");
                    return;
                }

                selectedRowIndex = dataGridViewDonors.SelectedRows[0].Index;
            }

            if (selectedRowIndex == -1)
            {
                selectedRowIndex = dataGridViewDonors.SelectedCells[0].RowIndex;
            }

            var row = dataGridViewDonors.Rows[selectedRowIndex];
            if (row == null)
                return;

            var donorId = row.Cells["DonorId"].Value;
            if (donorId == null || donorId.GetType() != typeof(int))
            {
                MessageBox.Show("Wrong selection!");
                return;
            }

            var medicalNo = labelMedicalNo.Text;

            var firstName = labelFirstName.Text;

            var lastName = labelLastName.Text;

            var phoneNumber = labelPhone.Text;

            var birthDate = dateTimePicker1.Value;

            if (comboBoxGender.SelectedItem == null)
            {
                return;
            }
            var gender = comboBoxGender.SelectedItem.ToString();

            var addressIds = comboBoxAddress.Text;

            var bankIds = comboBoxBank.Text;



            try
            {
                da.UpdateCommand = new SqlCommand("UPDATE Donors SET AdressId=@adrid, " +
                    "BloodBankId=@bankid, NationalMediaclNumber=@medno, Gender=@gender, " +
                    "DateOfBirth=@date, FirstName=@first, LastName=@last, " +
                    "PhoneNumber=@phone WHERE DonorId = @id", cs);

                da.UpdateCommand.Parameters.Add("@adrid", SqlDbType.Int).Value = addressIds;

                da.UpdateCommand.Parameters.Add("@bankid", SqlDbType.Int).Value = bankIds;

                da.UpdateCommand.Parameters.Add("@medno", SqlDbType.Int).Value = medicalNo;

                da.UpdateCommand.Parameters.Add("@gender", SqlDbType.VarChar).Value = gender;

                da.UpdateCommand.Parameters.Add("@date", SqlDbType.Date).Value = birthDate;

                da.UpdateCommand.Parameters.Add("@first", SqlDbType.VarChar).Value = firstName;

                da.UpdateCommand.Parameters.Add("@last", SqlDbType.VarChar).Value = lastName;

                da.UpdateCommand.Parameters.Add("@phone", SqlDbType.Int).Value = phoneNumber;

                da.UpdateCommand.Parameters.Add("@id", SqlDbType.Int).Value = row.Cells["DonorId"].Value;


                cs.Open();
                var x = da.UpdateCommand.ExecuteNonQuery();
                cs.Close();

                if (x > 0)
                {
                    MessageBox.Show("Successfully updated!");
                    updateDataGridViewDonors();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
                cs.Close();
            }
        }
    }
}
