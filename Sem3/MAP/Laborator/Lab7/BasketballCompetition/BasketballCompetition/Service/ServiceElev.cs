using BasketballCompetition.Domain;
using BasketballCompetition.Repository.Memory;

namespace BasketballCompetition.Service
{
    public class ServiceElev
    {
        private IRepository<int, Elev> _repository;

        public ServiceElev(IRepository<int, Elev> repository)
        {
            _repository = repository;
        }
    }
}