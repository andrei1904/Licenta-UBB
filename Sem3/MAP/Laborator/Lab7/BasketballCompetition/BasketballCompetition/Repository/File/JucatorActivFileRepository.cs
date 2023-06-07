using System;
using System.Collections.Generic;
using BasketballCompetition.Domain;
using BasketballCompetition.Domain.Validators;

namespace BasketballCompetition.Repository.File
{
    public class JucatorActivFileRepository : AbstractFileRepository<int, JucatorActiv>
    {
        public JucatorActivFileRepository(string fileName, IValidator<JucatorActiv> validator) : base(fileName, validator)
        {
        }

        protected override JucatorActiv ExctractEntity(List<string> attributes)
        {
            TipJucator tip;
            if (attributes[4] == "rezerva")
            {
                tip = TipJucator.Rezerva;
            } else
            {
                if (attributes[4] == "participant")
                {
                    tip = TipJucator.Participant;
                }
                else
                {
                    throw new Exception("Invalid player type");
                }
            }
            
            var jucatorActiv = new JucatorActiv(int.Parse(attributes[1]),
                int.Parse(attributes[2]), int.Parse(attributes[3]),
                tip)
            {
                Id = int.Parse(attributes[0])
            };

            return jucatorActiv;
        }
    }
}