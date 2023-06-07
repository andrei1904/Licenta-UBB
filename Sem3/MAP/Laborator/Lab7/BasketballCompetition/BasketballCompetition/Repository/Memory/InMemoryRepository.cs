using System;
using System.Collections.Generic;
using BasketballCompetition.Domain;
using BasketballCompetition.Domain.Validators;

namespace BasketballCompetition.Repository.Memory
{
    public class InMemoryRepository<TId, TE> : IRepository<TId, TE> where TE : Entity<TId>
    {
        private readonly IValidator<TE> _validator;
        private readonly IDictionary<TId, TE> _entities;

        protected InMemoryRepository(IValidator<TE> validator)
        {
            _validator = validator;
            _entities = new Dictionary<TId, TE>();
        }

        public TE FindOne(TId id)
        {
            _entities.TryGetValue(id, out var rez);
            return rez;
        }

        public IEnumerable<TE> FindAll()
        {
            return _entities.Values;
        }

        public virtual TE Save(TE entity)
        {
            if (entity == null)
            {
                throw new Exception("Entity is null");
            }

            _validator.Validate(entity);
            
            if (_entities.ContainsKey(entity.Id))
            {
                throw new Exception("Entity is already in memory");
            }

            _entities[entity.Id] = entity;
            return default;
        }

        public TE Delete(TId id)
        {
            var rez = _entities[id];
            
            return _entities.Remove(id) ? rez : null;
        }

        public TE Update(TE entity)
        {
            if (entity == null)
            {
                throw new Exception("Entity is null");
            }
            
            _validator.Validate(entity);
            
            if (!_entities.ContainsKey(entity.Id)) return null;

            _entities[entity.Id] = entity;
            return entity;
        }
        
    }
}