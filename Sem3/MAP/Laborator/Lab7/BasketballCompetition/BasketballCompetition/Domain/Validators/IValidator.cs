using System;

namespace BasketballCompetition.Domain.Validators
{
    public interface IValidator<in TE>
    {
        void Validate(TE entity);
    }
}