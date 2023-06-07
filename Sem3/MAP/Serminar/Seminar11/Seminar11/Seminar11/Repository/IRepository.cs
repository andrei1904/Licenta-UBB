using System;
using System.Collections.Generic;
using System.Text;
using Seminar11.Domain;

namespace Seminar11.Repository
{
    interface IRepository<ID, E> where E : Entity<ID>
    {
        E FindOne(ID id);
        IEnumerable<E> FindAll();
        E Save(E entity);
        E Delete(ID id);
        E Update(E entity);
    }
}
