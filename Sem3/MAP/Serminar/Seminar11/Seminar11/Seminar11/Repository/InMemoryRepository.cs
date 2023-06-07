using System;
using System.Collections.Generic;
using System.Text;
using Seminar11.Domain;
using Seminar11.Repository;


namespace Seminar11.Repository
{
    class InMemoryRepository<ID, E> : IRepository<ID, E> where E : Entity<ID>
    {
        private IDictionary<ID, E> entities = new Dictionary<ID, E>();

        public E Delete(ID id)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<E> FindAll()
        {
            return entities.Values;
        }
        
        public E FindOne(ID id)
        {
            throw new NotImplementedException();
        }
        
        public E Save(E entity)
        {
            if (entity == null)
            {
                throw new ArgumentNullException();
            }
            
            if (entities.ContainsKey(entity.Id))
            {
                return entity;
            } 
            else
            {
                entities[entity.Id] = entity;
                return default(E);
            }
        }

        public E Update(E entity)
        {
            throw new NotImplementedException();
        }
    }
}
