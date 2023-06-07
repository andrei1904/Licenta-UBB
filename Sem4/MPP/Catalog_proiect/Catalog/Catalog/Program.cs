using System;
using System.Windows.Forms;
using Catalog.repository;
using Catalog.service;

namespace Catalog
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            StudentRepository studentRepository = new StudentRepository();

            DisciplinaRepository disciplinaRepository = new DisciplinaRepository();

            CatalogRepository catalogRepository = new CatalogRepository();

            AllService service = new AllService(studentRepository, disciplinaRepository, catalogRepository);

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Form1 form = new Form1(service);
            Application.Run(form);
        }
    }
}
