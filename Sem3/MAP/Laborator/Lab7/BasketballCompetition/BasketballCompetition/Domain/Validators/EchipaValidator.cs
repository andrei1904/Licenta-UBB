using System;

namespace BasketballCompetition.Domain.Validators
{
    public delegate bool EchipaPredicate(Echipa echipa);

    public class EchipaValidator : IValidator<Echipa>
    {
        private static bool IdValidator(Echipa echipa)
        {
            return echipa.Id < 0;
        }

        private static bool NameValidator(Echipa echipa)
        {
            return echipa.Nume == "";
        }
        
        public void Validate(Echipa entity)
        {
            var testEchipa = new EchipaPredicate(IdValidator);

            if (testEchipa(entity))
            {
                throw new Exception("Id smaller than 0");
            }
            
            testEchipa = NameValidator;

            if (testEchipa(entity))
            {
                throw new Exception("Name is empty");
            }
        }
    }
}