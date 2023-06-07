using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Catalog.model;

namespace Catalog.repository
{
    class CatalogRepository
    {
        public List<Nota> GetAllForStudent(int nrMatricol)
        {
            var con = DbUtils.GetConnection();
            var note = new List<Nota>();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "SELECT * FROM CatalogNote WHERE sters = 0 AND nr_matricol = @nr";

                var paramNr = comm.CreateParameter();
                paramNr.ParameterName = "@nr";
                paramNr.Value = nrMatricol;
                comm.Parameters.Add(paramNr);

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        var codDisciplina = dataR.GetString(1);
                        var val = dataR.GetInt32(3);

                        var nota = new Nota(nrMatricol, codDisciplina, val);

                        note.Add(nota);
                    }
                }
            }
            return note;
        }

        public void Save(int nrMatricol, string codDisciplina, int nota)
        {
            var con = DbUtils.GetConnection();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "INSERT INTO CatalogNote(cod_disciplina, nr_matricol, note, sters) VALUES " +
                    "(@cod, @nr, @nota, 0)";

                var paramCod = comm.CreateParameter();
                paramCod.ParameterName = "@cod";
                paramCod.Value = codDisciplina;
                comm.Parameters.Add(paramCod);

                var paramNume = comm.CreateParameter();
                paramNume.ParameterName = "@nr";
                paramNume.Value = nrMatricol;
                comm.Parameters.Add(paramNume);

                var paramCredite = comm.CreateParameter();
                paramCredite.ParameterName = "@nota";
                paramCredite.Value = nota;
                comm.Parameters.Add(paramCredite);


                var result = comm.ExecuteNonQuery();
                if (result == 0)
                {
                    throw new Exception("No Mark added!");
                }
            }
        }


        public void Update(int nrMatricol, string codDisciplina, int nota)
        {
            var con = DbUtils.GetConnection();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "UPDATE CatalogNote SET note = @n WHERE cod_disciplina = @cod and nr_matricol = @nr";

                var paramNr = comm.CreateParameter();
                paramNr.ParameterName = "@n";
                paramNr.Value = nota;
                comm.Parameters.Add(paramNr);

                var paramNume = comm.CreateParameter();
                paramNume.ParameterName = "@nr";
                paramNume.Value = nrMatricol;
                comm.Parameters.Add(paramNume);

                var paramPrenume = comm.CreateParameter();
                paramPrenume.ParameterName = "@cod";
                paramPrenume.Value = codDisciplina;
                comm.Parameters.Add(paramPrenume);


                var result = comm.ExecuteNonQuery();
                if (result == 0)
                {
                    throw new Exception("No Mark updated!");
                }
            }

           
        }

        public void Delete(int nrMatricol, string codDisciplina)
        {
            var con = DbUtils.GetConnection();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "UPDATE CatalogNote SET sters = 1 WHERE cod_disciplina = @cod and nr_matricol = @nr";

                var paramNr = comm.CreateParameter();
                paramNr.ParameterName = "@cod";
                paramNr.Value = codDisciplina;
                comm.Parameters.Add(paramNr);

                var paramSters = comm.CreateParameter();
                paramSters.ParameterName = "@nr";
                paramSters.Value = nrMatricol;
                comm.Parameters.Add(paramSters);

                var result = comm.ExecuteNonQuery();
                if (result == 0)
                {
                    throw new Exception("No Mark deleted!");
                }
            }
        }

    }
}
