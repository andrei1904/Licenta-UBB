using System;

namespace BasketballCompetition.Domain.Validators
{
    public class MeciValidator : IValidator<Meci>

    {
        public void Validate(Meci entity)
        {
            if (entity.Id < 0)
            {
                throw new Exception("Id is smaller than 0");
            }

            if (entity.Echipa1 == null)
            {
                throw new Exception("First team is null");
            }
            
            if (entity.Echipa2 == null)
            {
                throw new Exception("Second team is null");
            }
            
        }
    }
}