using System.Collections.Generic;
using BasketballCompetition.Domain;
using BasketballCompetition.Domain.Validators;

namespace BasketballCompetition.Repository.File
{
    public class EchipaFileRepository : AbstractFileRepository<int, Echipa>
    {
        public EchipaFileRepository(string fileName, IValidator<Echipa> validator) : base(fileName, validator)
        {
        }

        protected override Echipa ExctractEntity(List<string> attributes)
        {
            var echipa = new Echipa(attributes[1]) {Id = int.Parse(attributes[0])};

            return echipa;
        }
    }
}