using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Catalog.model;

namespace Catalog.repository
{
    class DisciplinaRepository
    {
        public List<Disciplina> GetAll()
        {
            var con = DbUtils.GetConnection();
            var discipline = new List<Disciplina>();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "SELECT * FROM Discipline WHERE sters = 0";

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        var cod = dataR.GetString(0);
                        var nume = dataR.GetString(1);
                        var credite = dataR.GetInt32(2);

                        var disciplina = new Disciplina(cod, nume, credite);

                        discipline.Add(disciplina);
                    }
                }
            }
            return discipline;
        }


        public int GetNrCrediteDisciplina(string codDisciplina)
        {
            var con = DbUtils.GetConnection();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "SELECT numar_credite FROM Discipline WHERE sters = 0 and cod_disciplina = @cod";
                
                var paramCod = comm.CreateParameter();
                paramCod.ParameterName = "@cod";
                paramCod.Value = codDisciplina;
                comm.Parameters.Add(paramCod);

                using (var dataR = comm.ExecuteReader())
                {

                    while (dataR.Read())
                    {
                        var credite = dataR.GetInt32(0);

                        return credite;
                    }
                }
            }
            return 0;
        }

        public void Save(Disciplina disciplina)
        {
            var con = DbUtils.GetConnection();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "INSERT INTO Discipline(cod_disciplina, denumire, numar_credite, sters) VALUES " +
                    "(@cod, @nume, @credite, @sters)";

                var paramCod = comm.CreateParameter();
                paramCod.ParameterName = "@cod";
                paramCod.Value = disciplina.CodDisciplina;
                comm.Parameters.Add(paramCod);

                var paramNume = comm.CreateParameter();
                paramNume.ParameterName = "@nume";
                paramNume.Value = disciplina.Denumire;
                comm.Parameters.Add(paramNume);

                var paramCredite = comm.CreateParameter();
                paramCredite.ParameterName = "@credite";
                paramCredite.Value = disciplina.NrCredite;
                comm.Parameters.Add(paramCredite);

                var paramSters = comm.CreateParameter();
                paramSters.ParameterName = "@sters";
                paramSters.Value = 0;
                comm.Parameters.Add(paramSters);


                var result = comm.ExecuteNonQuery();
                if (result == 0)
                {
                    throw new Exception("No Discipline added!");
                }
            }
        }

        public void Delete(string cod)
        {
            var con = DbUtils.GetConnection();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "UPDATE Discipline SET sters = @s WHERE cod_disciplina = @id";

                var paramNr = comm.CreateParameter();
                paramNr.ParameterName = "@id";
                paramNr.Value = cod;
                comm.Parameters.Add(paramNr);

                var paramSters = comm.CreateParameter();
                paramSters.ParameterName = "@s";
                paramSters.Value = 1;
                comm.Parameters.Add(paramSters);

                var result = comm.ExecuteNonQuery();
                if (result == 0)
                {
                    throw new Exception("No Discipline deleted!");
                }
            }
        }

        public void Update(string cod, string nume, int credite)
        {
            var con = DbUtils.GetConnection();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "UPDATE Discipline SET denumire = @n, numar_credite = @c WHERE cod_disciplina = @cod";

                var paramNr = comm.CreateParameter();
                paramNr.ParameterName = "@cod";
                paramNr.Value = cod;
                comm.Parameters.Add(paramNr);

                var paramNume = comm.CreateParameter();
                paramNume.ParameterName = "@n";
                paramNume.Value = nume;
                comm.Parameters.Add(paramNume);

                var paramPrenume = comm.CreateParameter();
                paramPrenume.ParameterName = "@c";
                paramPrenume.Value = credite;
                comm.Parameters.Add(paramPrenume);


                var result = comm.ExecuteNonQuery();
                if (result == 0)
                {
                    throw new Exception("No Discipline updated!");
                }
            }
        }
    }
}
