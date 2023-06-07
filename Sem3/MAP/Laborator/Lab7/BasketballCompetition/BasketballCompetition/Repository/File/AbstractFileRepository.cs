using System.Collections.Generic;
using System.IO;
using System.Linq;
using BasketballCompetition.Domain;
using BasketballCompetition.Domain.Validators;
using BasketballCompetition.Repository.Memory;

namespace BasketballCompetition.Repository.File
{
    public abstract class AbstractFileRepository<TId, TE> : InMemoryRepository<TId, TE> where TE : Entity<TId>
    {
        private readonly string _fileName;

        protected AbstractFileRepository(string fileName, IValidator<TE> validator) : base(validator)
        {
            _fileName = fileName;
            LoadData();
        }

        private void LoadData()
        {
            var path = Path.GetFullPath(_fileName);
            
            try
            {
                var lines = System.IO.File.ReadAllLines(path).ToList();
                foreach (var entity in lines.Select(line => 
                        ExctractEntity(line.Split(';').ToList())))
                {
                    base.Save(entity);
                }
            }
            catch (IOException e)
            {
                e.GetBaseException();
            }
        }

        protected abstract TE ExctractEntity(List<string> attributes);
        
    }
}