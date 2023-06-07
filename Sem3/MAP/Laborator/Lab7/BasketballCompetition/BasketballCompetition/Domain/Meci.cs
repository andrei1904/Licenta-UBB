using System;

namespace BasketballCompetition.Domain
{
    public class Meci : Entity<int>
    {
        public Echipa Echipa1 { get; set; }
        public Echipa Echipa2 { get; set; }
        public DateTime Date { get; set; }

        public Meci(Echipa echipa1, Echipa echipa2, DateTime date)
        {
            Echipa1 = echipa1;
            Echipa2 = echipa2;
            Date = date;
        }

        public override string ToString()
        {
            return Id + " " + Echipa1 + " " + Echipa2 + " " + Date;
        }
    }
}