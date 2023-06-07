namespace BasketballCompetition.Domain
{
    public enum TipJucator
    {
        Rezerva,
        Participant
    }
    
    public class JucatorActiv : Entity<int>
    {
        public int IdJucator { get; set; }
        public int IdMeci { get; set; }
        public int NrPuncteInscrise { get; set; }
        public TipJucator Tip { get; set; }

        public JucatorActiv(int idJucator, int idMeci, int nrPuncteInscrise, TipJucator tipJucator)
        {
            IdJucator = idJucator;
            IdMeci = idMeci;
            NrPuncteInscrise = nrPuncteInscrise;
            Tip = tipJucator;
        }

        public override string ToString()
        {
            return Id + " " + IdJucator + " " + IdMeci + " " + NrPuncteInscrise + " " + Tip;
        }
    }
}