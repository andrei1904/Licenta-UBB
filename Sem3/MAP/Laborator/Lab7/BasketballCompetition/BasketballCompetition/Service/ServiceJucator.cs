using System.Collections.Generic;
using System.Linq;
using BasketballCompetition.Domain;
using BasketballCompetition.Repository.Memory;

namespace BasketballCompetition.Service
{
    public class ServiceJucator
    {
        private readonly IRepository<int, Jucator> _repository;

        public ServiceJucator(IRepository<int, Jucator> repository)
        {
            _repository = repository;
        }

        public List<Jucator> FindOneByTeam(Echipa team)
        {
            var players = _repository.FindAll();

            return players.Where(x => x.Echipa.Equals(team))
                .ToList();
        }
    }
}