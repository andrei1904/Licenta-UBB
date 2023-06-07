using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Configuration;

namespace Laborator2
{
    public partial class Form1 : Form
    {
        private SqlConnection _cs =
            new SqlConnection(ConfigurationManager.ConnectionStrings["conStr"].ConnectionString);

        private SqlDataAdapter _da = new SqlDataAdapter();
        private DataSet _ds = new DataSet();

        private string _parentTableName = ConfigurationSettings.AppSettings["ParentTableName"];
        
        public Form1()
        {
            InitializeComponent();

            _ds.Tables.Add(_parentTableName);
            ShowContent();
        }

        private void ShowContent()
        {
            var select = ConfigurationSettings.AppSettings["parentSelect"];
            _da.SelectCommand = new SqlCommand(select, _cs);
            _ds.Tables[_parentTableName].Clear();
            _da.Fill(_ds.Tables[_parentTableName]);
            dataGridView1.DataSource = _ds.Tables[_parentTableName];

        }
    }
}