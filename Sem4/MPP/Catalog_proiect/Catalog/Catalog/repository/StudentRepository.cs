using System;
using System.Collections.Generic;
using Catalog.model;

namespace Catalog.repository
{
    class StudentRepository
    {
        public void Save(Student student)
        {
            var con = DbUtils.GetConnection();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "INSERT INTO Studenti(numar_matricol, nume, prenume, sters) VALUES " +
                    "(@nr, @nume, @prenume, @sters)";

                var paramNr = comm.CreateParameter();
                paramNr.ParameterName = "@nr";
                paramNr.Value = student.NrMatricol;
                comm.Parameters.Add(paramNr);

                var paramNume = comm.CreateParameter();
                paramNume.ParameterName = "@nume";
                paramNume.Value = student.Nume;
                comm.Parameters.Add(paramNume);

                var paramPrenume = comm.CreateParameter();
                paramPrenume.ParameterName = "@prenume";
                paramPrenume.Value = student.Prenume;
                comm.Parameters.Add(paramPrenume);

                var paramSters = comm.CreateParameter();
                paramSters.ParameterName = "@sters";
                paramSters.Value = 0;
                comm.Parameters.Add(paramSters);


                var result = comm.ExecuteNonQuery();
                if (result == 0)
                {
                    throw new Exception("No Student added!");
                }
            }
        }

        public void Delete(int id)
        {
            var con = DbUtils.GetConnection();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "UPDATE Studenti SET sters = @s WHERE numar_matricol = @id";

                var paramNr = comm.CreateParameter();
                paramNr.ParameterName = "@id";
                paramNr.Value = id;
                comm.Parameters.Add(paramNr);

                var paramSters = comm.CreateParameter();
                paramSters.ParameterName = "@s";
                paramSters.Value = 1;
                comm.Parameters.Add(paramSters);

                var result = comm.ExecuteNonQuery();
                if (result == 0)
                {
                    throw new Exception("No Student deleted!");
                }
            }
        }

        public void Update(int id, string nume, string prenume)
        {
            var con = DbUtils.GetConnection();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "UPDATE Studenti SET nume = @n, prenume = @p WHERE numar_matricol = @id";

                var paramNr = comm.CreateParameter();
                paramNr.ParameterName = "@id";
                paramNr.Value = id;
                comm.Parameters.Add(paramNr);

                var paramNume = comm.CreateParameter();
                paramNume.ParameterName = "@n";
                paramNume.Value = nume;
                comm.Parameters.Add(paramNume);

                var paramPrenume = comm.CreateParameter();
                paramPrenume.ParameterName = "@p";
                paramPrenume.Value = prenume;
                comm.Parameters.Add(paramPrenume);


                var result = comm.ExecuteNonQuery();
                if (result == 0)
                {
                    throw new Exception("No Student updated!");
                }
            }
        }

        public List<Student> GetAll()
        {
            var con = DbUtils.GetConnection();
            var students = new List<Student>();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "SELECT * FROM Studenti WHERE sters = 0";

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        var nr = dataR.GetInt32(0);
                        var nume = dataR.GetString(1);
                        var prenume = dataR.GetString(2);

                        var student = new Student(nr, nume, prenume);

                        students.Add(student);
                    }
                }
            }
            return students;
        }

    }
}
