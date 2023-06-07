using System;

namespace BasketballCompetition.Domain.Validators
{
    public class ElevValidator : IValidator<Elev>
    {
        public void Validate(Elev entity)
        {
            if (entity.Id < 0)
            {
                throw new Exception("Id is smaller than 0");
            }

            if (entity.Scoala == "")
            {
                throw new Exception("School is empty");
            }

            if (entity.Nume == "")
            {
                throw new Exception("Name is empty");
            }
        }
    }
}