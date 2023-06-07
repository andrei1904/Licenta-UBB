using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        private readonly SqlConnection _cs =
            new SqlConnection(ConfigurationManager.ConnectionStrings["conString"].ConnectionString);

        private readonly SqlDataAdapter _da = new SqlDataAdapter();
        private readonly DataSet _ds = new DataSet();

        private readonly string _parentTableName = ConfigurationManager.AppSettings["parentTableName"];
        private readonly string _tableName = ConfigurationManager.AppSettings["tableName"];
        
        public Form1()
        {
            InitializeComponent();

            _ds.Tables.Add(_parentTableName);
            _ds.Tables.Add(_tableName);
            CreateTextBoxes();
            ShowContent();
        }

        private void CreateTextBoxes()
        {
            try
            {
                var columnNames = new List<string>(
                    ConfigurationManager.AppSettings["columnNames"].Split(','));
                const int pointXLabel = 0;
                const int pointXTextBox = 110;
                var pointY = 40;
             
                panel1.Controls.Clear();

                foreach (var columnName in columnNames)
                {
                    var label = new Label
                    {
                        Text = columnName + @":",
                        Location = new Point(pointXLabel, pointY),
                        Visible = true,
                        Parent = panel1,
                        TextAlign = ContentAlignment.MiddleRight
                    };

                    if (columnName.Contains("Date"))
                    {
                        var dateTimePicker = new DateTimePicker
                        {
                            Name = columnName,
                            Location = new Point(pointXTextBox, pointY),
                            Visible = true,
                            Parent = panel1
                        };
                    }
                    else
                    {
                        var textBox = new TextBox
                        {
                            Name = columnName,
                            Location = new Point(pointXTextBox, pointY),
                            Visible = true,
                            Parent = panel1
                        };
                    }

                    panel1.Show();
                    pointY += 30;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(this, ex.Message);
            }
        }
        
        private void ShowContent()
        {
            var select = ConfigurationManager.AppSettings["parentSelect"];
            _da.SelectCommand = new SqlCommand(select, _cs);
            _ds.Tables[_parentTableName].Clear();
            _da.Fill(_ds.Tables[_parentTableName]);
            dataGridView1.DataSource = _ds.Tables[_parentTableName];

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
            if (rowId != null && rowId is int id) return id;
            
            MessageBox.Show(this, @"Wrong selection!");
            return -1;
        }
        
        private DataGridViewRow SelectionChangedRow(DataGridView dataGridView)
        {
            var selectedRowIndex = -1;
            var cellsCount = dataGridView.SelectedCells.Count;
            if (cellsCount == 0 || cellsCount > 1)
            {
                var rowsCount = dataGridView.SelectedRows.Count;
                if (rowsCount == 0 || rowsCount > 1)
                {
                    MessageBox.Show(this, @"Wrong selection!");
                    return null;
                }

                selectedRowIndex = dataGridView.SelectedRows[0].Index;
            }

            if (selectedRowIndex == -1)
            {
                selectedRowIndex = dataGridView.SelectedCells[0].RowIndex;
            }

            var row = dataGridView.Rows[selectedRowIndex];
            return row;
        }
        
        private void dataGridView1_SelectionChanged(object sender, EventArgs e)
        {
            if (!dataGridView1.Focused)
                return;

            var tableId = ConfigurationManager.AppSettings["parentTableId"];
            var id = SelectionChanged(dataGridView1, tableId);
            
            if (id == -1)
            {
                return;
            }

            _da.SelectCommand = new SqlCommand(
                "SELECT * FROM" + " " + _tableName + " WHERE " + 
                tableId +
                " = " + id, _cs);
            _ds.Tables[_tableName].Clear();
            _da.Fill(_ds.Tables[_tableName]);
            dataGridView2.DataSource = _ds.Tables[_tableName];
        }

        private void buttonInsert_Click(object sender, EventArgs e)
        {
            try
            {
                var columnNamesInsertParameters = ConfigurationManager.AppSettings["columnNamesInsertParameters"];
                var columnNamesList = new List<string>(
                    ConfigurationManager.AppSettings["columnNames"].Split(','));

                var ids = _ds.Tables[_parentTableName].AsEnumerable().Select(x => x[
                    ConfigurationManager.AppSettings["parentTableId"]].ToString()).ToList();
                
                var cmd = new SqlCommand("INSERT INTO" + " " + _tableName +
                                         " (" + 
                                         ConfigurationManager.AppSettings["columnNames"] +
                                         ") VALUES (" + columnNamesInsertParameters +
                                         ")", _cs);
                foreach (var column in columnNamesList)
                {
                    if (column.Contains("Id"))
                    {
                        var textBox = (TextBox) panel1.Controls[column];

                        if (textBox.Text == "")
                        {
                            throw new Exception("Empty fields!");
                        }
                        
                        if (!ids.Contains(textBox.Text))
                        {
                            throw new Exception("Id is invalid");
                        }
                        
                        cmd.Parameters.AddWithValue("@" + column, textBox.Text);
                    }
                    else if (column.Contains("Date"))
                    {
                        var dateTimePicker = (DateTimePicker) panel1.Controls[column];
                        cmd.Parameters.AddWithValue("@" + column, dateTimePicker.Value);
                    }
                    else
                    {
                        var textBox = (TextBox) panel1.Controls[column];

                        if (textBox.Text == "")
                        {
                            throw new Exception("Empty fields!");
                        }
                        
                        cmd.Parameters.AddWithValue("@" + column, textBox.Text);
                    }
                }

                _cs.Open();
                cmd.ExecuteNonQuery();
                _ds.Tables[_tableName].Clear();
                _da.Fill(_ds.Tables[_tableName]);
                MessageBox.Show(this, @"Succesfuly added!");
                _cs.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(this, ex.Message);
            }
        }

        private void buttonDelete_Click(object sender, EventArgs e)
        {
            var tableId = ConfigurationManager.AppSettings["tableId"];
            var id = SelectionChanged(dataGridView2, tableId);
            
            if (id == -1)
            {
                return;
            }
            
            try
            {
                _da.InsertCommand = new SqlCommand(
                    "DELETE FROM" + " " + _tableName + " WHERE " +
                    tableId + " = " + id, _cs);
                
                _cs.Open();
                _da.InsertCommand.ExecuteNonQuery();
                _cs.Close();
                
                _ds.Tables[_tableName].Clear();
                _da.Fill(_ds.Tables[_tableName]);
                dataGridView2.DataSource = _ds.Tables[_tableName];
            }
            catch (Exception ex)
            {
                MessageBox.Show(this, ex.Message);
            }
        }

        private void buttonUpdate_Click(object sender, EventArgs e)
        {
            var tableId = ConfigurationManager.AppSettings["tableId"];
            var id = SelectionChanged(dataGridView2, tableId);
            
            var ids = _ds.Tables[_parentTableName].AsEnumerable().Select(x => x[
                ConfigurationManager.AppSettings["parentTableId"]].ToString()).ToList();


            if (id == -1)
            {
                return;
            }
            
            try
            {
                var columnNamesList = new List<string>(
                    ConfigurationManager.AppSettings["columnNames"].Split(','));
                var updateQuery = ConfigurationManager.AppSettings["update"];
                
                var cmd = new SqlCommand(updateQuery, _cs);
                
                foreach (var column in columnNamesList)
                {
                    if (column.Contains("Id"))
                    {
                        var textBox = (TextBox) panel1.Controls[column];

                        if (textBox.Text == "")
                        {
                            throw new Exception("Empty fields!");
                        }
                        
                        if (!ids.Contains(textBox.Text))
                        {
                            throw new Exception("Id is invalid");
                        }
                        
                        cmd.Parameters.AddWithValue("@" + column, textBox.Text);
                    }
                    else if (column.Contains("Date"))
                    {
                        var dateTimePicker = (DateTimePicker) panel1.Controls[column];
                        cmd.Parameters.AddWithValue("@" + column, dateTimePicker.Value);
                    }
                    else
                    {
                        var textBox = (TextBox) panel1.Controls[column];
                        cmd.Parameters.AddWithValue("@" + column, textBox.Text);
                    }
                }
                cmd.Parameters.AddWithValue("@id", id);
                
                _cs.Open();
                cmd.ExecuteNonQuery();
                _ds.Tables[_tableName].Clear();
                _da.Fill(_ds.Tables[_tableName]);
                MessageBox.Show(this, @"Succesfuly updated!");
                _cs.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(this, ex.Message);
            }
        }

        private void dataGridView2_SelectionChanged(object sender, EventArgs e)
        {
            if (!dataGridView2.Focused) 
                return;

            var row = SelectionChangedRow(dataGridView2);
            if (row == null) 
                return;

            try
            {
                var columnNamesList = new List<string>(
                    ConfigurationManager.AppSettings["columnNames"].Split(','));

                foreach (var column in columnNamesList)
                {
                    if (column.Contains("Date"))
                    {
                        var dateTimePicker = (DateTimePicker) panel1.Controls[column];
                        dateTimePicker.Value = DateTime.Parse(row.Cells[column].Value.ToString());
                    }
                    else
                    {
                        var textBox = (TextBox) panel1.Controls[column];
                        textBox.Text = row.Cells[column].Value.ToString();
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(this, ex.Message);
            }
        }
    }
}