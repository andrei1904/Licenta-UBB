using System;
using System.Collections.Generic;
using BasketballCompetition.Domain;
using BasketballCompetition.Domain.Validators;

namespace BasketballCompetition.Repository.File
{
    public class MeciFileRepository : AbstractFileRepository<int, Meci>
    {
        public MeciFileRepository(string fileName, IValidator<Meci> validator) : base(fileName, validator)
        {
            
        }

        protected override Meci ExctractEntity(List<string> attributes)
        {
            var meci = new Meci(new Echipa(attributes[2])
                {
                    Id = int.Parse(attributes[1])
                }, 
                new Echipa(attributes[4])
                {
                    Id = int.Parse(attributes[3])
                }, DateTime.Parse(attributes[5]))
            {
                Id = int.Parse(attributes[0])
            };

            return meci;
        }
    }
}