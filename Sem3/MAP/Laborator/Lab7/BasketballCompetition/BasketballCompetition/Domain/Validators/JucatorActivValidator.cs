using System;

namespace BasketballCompetition.Domain.Validators
{
    public delegate bool JucatorActivPredicate(JucatorActiv jucatorActiv);

    public class JucatorActivValidator : IValidator<JucatorActiv>
    {
        private static bool IdValidator(JucatorActiv jucatorActiv)
        {
            return jucatorActiv.Id < 0;
        }

        private static bool IdJucatorValidator(JucatorActiv jucatorActiv)
        {
            return jucatorActiv.IdJucator < 0;
        }
        
        private static bool IdMeciValidator(JucatorActiv jucatorActiv)
        {
            return jucatorActiv.IdMeci < 0;
        }
        
        private static bool PuncteValidator(JucatorActiv jucatorActiv)
        {
            return jucatorActiv.NrPuncteInscrise < 0;
        }
        
        private static bool TipValidator(JucatorActiv jucatorActiv)
        {
            return jucatorActiv.Tip != TipJucator.Participant &&
                jucatorActiv.Tip != TipJucator.Rezerva;
        }
        
        
        public void Validate(JucatorActiv entity)
        {
            var testJucatorActiv = new JucatorActivPredicate(IdValidator);
            
            if (testJucatorActiv(entity))
            {
                throw new Exception("Id is smaller than 0");
            }

            testJucatorActiv = IdJucatorValidator;

            if (testJucatorActiv(entity))
            {
                throw new Exception("PlayerId is smaller than 0");
            }

            testJucatorActiv = IdMeciValidator;

            if (testJucatorActiv(entity))
            {
                throw new Exception("GameId is smaller than 0");
            }

            testJucatorActiv = PuncteValidator;
            
            if (testJucatorActiv(entity))
            {
                throw new Exception("Scored points are smaller than 0");
            }

            testJucatorActiv = TipValidator;

            if (testJucatorActiv(entity))
            {
                throw new Exception("Not a valid type");
            }
        }
    }
}