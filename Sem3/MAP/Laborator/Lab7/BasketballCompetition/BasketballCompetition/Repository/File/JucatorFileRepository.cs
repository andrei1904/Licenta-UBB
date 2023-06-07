using System.Collections.Generic;
using BasketballCompetition.Domain;
using BasketballCompetition.Domain.Validators;

namespace BasketballCompetition.Repository.File
{
    public class JucatorFileRepository : AbstractFileRepository<int, Jucator>
    {
        public JucatorFileRepository(string fileName, IValidator<Jucator> validator) : base(fileName, validator)
        {
        }

        protected override Jucator ExctractEntity(List<string> attributes)
        {
            var jucator = new Jucator(attributes[1], attributes[2], 
                new Echipa(attributes[4])
                {
                    Id = int.Parse(attributes[3])
                })
            {
                Id = int.Parse(attributes[0])
            }; 

            return jucator;
        }
    }
}