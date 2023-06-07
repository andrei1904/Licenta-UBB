using System;

namespace BasketballCompetition.Domain.Validators
{
    public class JucatorValidator : IValidator<Jucator>
    {
        public void Validate(Jucator entity)
        {
            if (entity.Id < 0)
            {
                throw new Exception("Id is smaller than 0");
            }

            if (entity.Echipa == null)
            {
                throw new Exception("Team is null");
            }

            if (entity.Nume == "")
            {
                throw new Exception("Name is empty");
            }

            if (entity.Scoala == "")
            {
                throw new Exception("School is empty");
            }
        }
    }
}