using System.Collections.Generic;
using System.Linq;
using BasketballCompetition.Domain;
using BasketballCompetition.Repository.Memory;

namespace BasketballCompetition.Service
{
    public class ServiceJucatorActiv
    {
        private readonly IRepository<int, JucatorActiv> _repository;

        public ServiceJucatorActiv(IRepository<int, JucatorActiv> repository)
        {
            _repository = repository;
        }

        public List<JucatorActiv> FindAllActiveByGame(Meci game)
        { 
            var players = _repository.FindAll();

            var res = players
                .Where(x => x.IdMeci == game.Id 
                            // && x.Tip == TipJucator.Participant
                            )
                .ToList();

            return res;
        }
    }
}