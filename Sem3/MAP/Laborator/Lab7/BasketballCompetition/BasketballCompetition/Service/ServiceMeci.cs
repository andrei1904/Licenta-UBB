using System.Collections.Generic;
using System.Linq;
using BasketballCompetition.Domain;
using BasketballCompetition.Repository.Memory;

namespace BasketballCompetition.Service
{
    public class ServiceMeci
    {
        private readonly IRepository<int, Meci> _repository;

        public ServiceMeci(IRepository<int, Meci> repository)
        {
            _repository = repository;
        }

        public List<Meci> FindAll()
        {
            return _repository.FindAll().ToList();
        }

        public Meci FindOne(int gameId)
        {
            return _repository.FindOne(gameId);
        }
        
    }
}