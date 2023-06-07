using Sem11_12.Repository;
using Sem11_12.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sem11_12.Service
{
    class PontajService
    {
        private IRepository<string, Pontaj> repo;

        public PontajService(IRepository<string, Pontaj> repo)
        {
            this.repo = repo;
        }

        public List<Pontaj> FindAllPontaje()
        {
            return repo.FindAll().ToList();
        }



        public List<PontajDTO> Salar(int luna)  //4
        {
            var res =
                from pontaj in repo.FindAll().ToList()
                where pontaj.Date.Month.Equals(luna)
                group pontaj by pontaj.Angajat
                into g
                select new PontajDTO
                {
                    NumeAngajat = g.Key.Nume,
                    Nivel = g.Key.Nivel,
                    Salar = g.Sum(x
                        => x.Sarcina.NrOreEstimate * x.Angajat.VenitPeOra)
                }
                into pontajDto
                orderby pontajDto.Nivel, pontajDto.Salar
                select pontajDto;
            
            return res.ToList();
        }


    }
}
