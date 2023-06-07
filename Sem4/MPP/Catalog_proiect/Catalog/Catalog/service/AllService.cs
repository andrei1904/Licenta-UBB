using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using Catalog.model;
using Catalog.repository;

namespace Catalog.service
{
    class AllService
    {
        private StudentRepository studentRepository;
        private DisciplinaRepository disciplinaRepository;
        private CatalogRepository catalogRepository;

        public AllService(StudentRepository studentRepository, DisciplinaRepository disciplinaRepository,
            CatalogRepository catalogRepository)
        {
            this.studentRepository = studentRepository;
            this.disciplinaRepository = disciplinaRepository;
            this.catalogRepository = catalogRepository;
        }

        public void AddStudent(int nrMatricol, string nume, string prenume)
        {
            if (studentRepository.GetAll().Where(student => student.NrMatricol == nrMatricol).Count() > 0)
            {
                throw new Exception("Acest numar matricol exista deja!");
            }

            studentRepository.Save(new Student(nrMatricol, nume, prenume));
        }

        public List<Disciplina> GetAllDisciplines()
        {
            return disciplinaRepository.GetAll();
        }

        public List<Student> GetAllStudents()
        {
            return studentRepository.GetAll();
        }

        public void DeleteStudent(int id)
        {
            if (studentRepository.GetAll().Where(student => student.NrMatricol == id).Count() != 1)
            {
                throw new Exception("Nu exista un student cu acest numar matricol!");
            }

            studentRepository.Delete(id);
        }

        public void UpdateStudent(int id, string nume, string prenume)
        {
            if (studentRepository.GetAll().Where(student => student.NrMatricol == id).Count() != 1)
            {
                throw new Exception("Nu exista un student cu acest numar matricol!");
            }

            studentRepository.Update(id, nume, prenume);
        }

        public void AddDisciplina(string cod, string nume, int credite)
        {
            if (disciplinaRepository.GetAll().Where(disciplina => disciplina.CodDisciplina == cod).Count() > 0)
            {
                throw new Exception("Exista o disciplina cu acest cod!");
            }

            disciplinaRepository.Save(new Disciplina(cod, nume, credite));
        }

        public void DeleteDisciplina(string cod)
        {
            if (disciplinaRepository.GetAll().Where(disciplina => disciplina.CodDisciplina == cod).Count() != 1)
            {
                throw new Exception("Nu exista o disciplina cu acest cod!");
            }

            disciplinaRepository.Delete(cod);
        }

        public void UpdateDisciplina(string cod, string nume, int credite)
        {
            if (disciplinaRepository.GetAll().Where(disciplina => disciplina.CodDisciplina == cod).Count() != 1)
            {
                throw new Exception("Nu exista o disciplina cu acest cod!");
            }

            disciplinaRepository.Update(cod, nume, credite);
        }

        public List<Nota> GetAllGradesForStudent(int numarMatricol)
        {
            if (studentRepository.GetAll().Where(student => student.NrMatricol == numarMatricol).Count() != 1)
            {
                throw new Exception("Nu exista un student cu acest numar matricol!");
            }

            return catalogRepository.GetAllForStudent(numarMatricol);
        }

        public void AddNota(int nrMatricol, string codDisciplina, int nota)
        {
            if (catalogRepository.GetAllForStudent(nrMatricol).Where(x => x.cod_disciplina == codDisciplina).Count() != 0)
            {
                throw new Exception("Exista deja o nota pentru acest student la aceasta materie!");
            }

            catalogRepository.Save(nrMatricol, codDisciplina, nota);
        }

        public void ModificaNota(int nrMatricol, string codDisciplina, int nota)
        {
            catalogRepository.Update(nrMatricol, codDisciplina, nota);
        }

        public void StergeNota(int nrMatricol, string codDisciplina)
        {
            catalogRepository.Delete(nrMatricol, codDisciplina);
        }

        public void GenereazaRaport()
        {
            TextWriter tw = new StreamWriter("C:\\Fac\\proiect\\Catalog\\raport.csv");

            var studenti = studentRepository.GetAll();

            foreach (var student in studenti)
            {
                string linie = student.NrMatricol.ToString() + ",";
                double medie = 0;
                var catalog = catalogRepository.GetAllForStudent(student.NrMatricol);
                string rezultat = "integralist";
                int nrCredite = 0;

                foreach (var x in catalog)
                {
                    linie += x.cod_disciplina + "," + x.nota.ToString() + ",";
                    medie += x.nota;

                    if (x.nota < 5)
                    {
                        rezultat = "neintegralist";
                    } else
                    {
                        nrCredite += disciplinaRepository.GetNrCrediteDisciplina(x.cod_disciplina);
                    }
                }

                medie /= catalog.Count();

                linie += medie + "," + nrCredite + "," + rezultat;

                tw.WriteLine(linie);

            }

            tw.Close();
        }

    }
}
