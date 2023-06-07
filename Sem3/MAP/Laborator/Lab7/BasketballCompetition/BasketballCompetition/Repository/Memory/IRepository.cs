using System.Collections.Generic;
using BasketballCompetition.Domain;

namespace BasketballCompetition.Repository.Memory
{
    public interface IRepository<in TId, TE> where TE : Entity<TId>
    {
        TE FindOne(TId id);
        
        IEnumerable<TE> FindAll();
        
        TE Save(TE entity);
        
        TE Delete(TId id);
        
        TE Update(TE entity);
    }
}