using System;
using Seminar11.Domain;
using Seminar11.Repository;

namespace Seminar11
{
    class Program
    {
        private static InMemoryRepository<string, Angajat> GetRepo()
        {
            var repo = new InMemoryRepository<String, Angajat>();
            
            var angajat = new Angajat()
            {
                Id = "1",
                Nume = "Denisa",
                Nivel = KnowledgeLevel.Junior,
                VenitPeOra = 10
            };

            var angajat1 = new Angajat()
            {
                Id = "2",
                Nume = "Denis",
                Nivel = KnowledgeLevel.Senior,
                VenitPeOra = 100
            };

            var angajat2 = new Angajat()
            {
                Id = "3",
                Nume = "Oscar",
                Nivel = KnowledgeLevel.Medium,
                VenitPeOra = 50
            };

            repo.Save(angajat);
            repo.Save(angajat1);
            repo.Save(angajat2);

            return repo;
        }
        
        private static void Main(string[] args)
        {
            //Angajat angajat = new Angajat();
            //angajat.Nume = "Horatiu";
            //angajat.AngajatId = "1";

            //Angajat angajat2 = new Angajat()
            //{
            //    AngajatId = "2",
            //    Nume = "Denisa",
            //    Nivel = KnowledgeLevel.Senior,
            //    VenitPeOra = 10
            //};

            //Console.WriteLine(angajat2);

            var repo = GetRepo();
            foreach (var item in repo.FindAll())
            {
                Console.WriteLine(item);
            }

        }
    }
}
