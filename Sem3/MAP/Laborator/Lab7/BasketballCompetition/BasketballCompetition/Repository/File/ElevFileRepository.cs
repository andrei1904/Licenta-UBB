using System.Collections.Generic;
using BasketballCompetition.Domain;
using BasketballCompetition.Domain.Validators;

namespace BasketballCompetition.Repository.File
{
    public class ElevFileRepository : AbstractFileRepository<int, Elev>
    {
        public ElevFileRepository(string fileName, IValidator<Elev> validator) : base(fileName, validator)
        {
        }

        protected override Elev ExctractEntity(List<string> attributes)
        {
            var elev = new Elev(attributes[1], attributes[2])
            {
                Id = int.Parse(attributes[0])
            };

            return elev;
        }
    }
}