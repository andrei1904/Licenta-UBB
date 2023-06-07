using System;
using System.Collections.Generic;
using System.Linq;
using BasketballCompetition.Domain;
using BasketballCompetition.Repository.Memory;

namespace BasketballCompetition.Service
{
    public class ServiceEchipa
    {
        private readonly IRepository<int, Echipa> _repository;

        public ServiceEchipa(IRepository<int, Echipa> repository)
        {
            _repository = repository;
        }

        public List<Echipa> FindAll()
        {
            return _repository.FindAll().ToList();
        }

        public Echipa FindOneByName(string teamName)
        {
            var teams = _repository.FindAll();

            var res =
                teams.Where(x => x.Nume == teamName)
                    .Take(1)
                    .ToList();

            if (res.Count == 0)
            {
                throw new Exception("This team does not exist!");
            }

            return res[0];
        }
    }
}