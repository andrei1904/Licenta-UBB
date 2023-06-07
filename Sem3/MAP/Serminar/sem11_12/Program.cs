
using Sem11_12.Model;
using Sem11_12.Model.Validator;
using Sem11_12.Repository;
using Sem11_12.Service;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Reflection.Metadata;
using System.Security.Cryptography;

namespace Sem11_12
{
    class Program
    {

        static void Main(string[] args)
        {
            
            var angajati = GetAngajatService().FindAllAngajati();
            var sarcini = GetSarcinaService().FindAllSarcini();
            var pontaje = GetPontajService().FindAllPontaje();

            var l = new List<int>() { 1,2,3,4,5,6,7,8,9,10 };          
            var res=l.Where(x => x % 2 == 0);
            // var res=l.Where(x => x % 2 == 0).ToList();  
            
            l.Add(44);

            // res.ToList().ForEach(Console.WriteLine); // -
            // res.ForEach(Console.WriteLine); -- nu se modifica res
            
            // 1. afisati doar angajatii care au nivelul junior - extension methods "Where"

            //2  - cerinta 1 din pdf sem11-12  extension methods 

            var res1 = angajati.OrderBy(x => x.Nivel)
                .ThenByDescending(x => x.VenitPeOra);
            
            // res1.ToList().ForEach(Console.WriteLine);
            
            
            //2  - cerinta 1 din pdf sem11-12  sql like 
            
            var res11 =
                from angajat in angajati
                orderby angajat.Nivel ascending, angajat.VenitPeOra descending
                select angajat;
            
            // res11.ToList().ForEach(Console.WriteLine);
            

            //2 cate ore dureaza in medie fiecare tip de sarcina

            var res2 =
                sarcini.GroupBy(x => x.TipDificultate)
                    .Select(g => new
                    {
                        Dificultate = g.Key,
                        MedieSarcina = g.Average(x => x.NrOreEstimate)
                    });

            // res2.ToList().ForEach(Console.WriteLine);

            var res21 =
                from sarcina in sarcini
                group sarcina by sarcina.TipDificultate
                into g
                select new
                {
                    Dificultate = g.Key,
                    MediaSarcina = g.Average(x => x.NrOreEstimate)
                };

            // res21.ToList().ForEach(Console.WriteLine);
            
            //3 primii doi cei mai harnici angajati

            var res3 =
                pontaje.GroupBy(x => x.Angajat)
                    .Select(g => new
                    {
                        Angajat = g.Key,
                        VenitAngajat = g.Sum(x
                            => x.Sarcina.NrOreEstimate * x.Angajat.VenitPeOra)
                    })
                    .OrderByDescending(x => x.VenitAngajat)
                    .Take(2);

            // res3.ToList().ForEach(Console.WriteLine);

            var res31 =
                (from pontaj in pontaje
                    group pontaj by pontaj.Angajat
                    into g
                    select new
                    {
                        Angajat = g.Key,
                        VenitAngajat = g.Sum(x
                            => x.Sarcina.NrOreEstimate * x.Angajat.VenitPeOra)
                    }
                    into x
                    orderby x.VenitAngajat descending
                    select x).Take(2);
            
            // res31.ToList().ForEach(Console.WriteLine);

            var res4 = GetPontajService().Salar(3);
            res4.ForEach(Console.WriteLine) ;
        }

        private static void Task2()
        {
            

        }


        private static AngajatService GetAngajatService()
        {
            //string fileName2 = ConfigurationManager.AppSettings["angajatiFileName"];
            string fileName = "..\\..\\..\\data\\angajati.txt";
            IValidator<Angajat> vali = new AngajatValidator();

            IRepository<string, Angajat> repo1 = new AngajatInFileRepository(vali, fileName);
            AngajatService service = new AngajatService(repo1);
            return service;
        }

        private static SarcinaService GetSarcinaService()
        {
            //string fileName2 = ConfigurationManager.AppSettings["sarciniFileName"];
            string fileName2 = "..\\..\\..\\data\\sarcini.txt";
            IValidator<Sarcina> vali = new SarcinaValidator();

            IRepository<string, Sarcina> repo1 = new SarcinaInFileRepository(vali, fileName2);
            SarcinaService service = new SarcinaService(repo1);
            return service;
        }

        private static PontajService GetPontajService()
        {
            //string fileName2 = ConfigurationManager.AppSettings["pontajeFileName"];
            string fileName2 = "..\\..\\..\\data\\pontaje.txt";
            IValidator<Pontaj> vali = new PontajValidator();

            IRepository<string, Pontaj> repo1 = new PontajInFileRepository(vali, fileName2);
            PontajService service = new PontajService(repo1);
            return service;
        }

    }
}
