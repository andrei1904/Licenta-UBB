namespace BasketballCompetition.Domain
{
    public class Echipa : Entity<int>
    {
        public string Nume { get; private set; }

        public Echipa(string nume)
        {
            Nume = nume;
        }

        public override string ToString()
        {
            return Id + " " + Nume;
        }
        
        public override bool Equals(object obj)
        {
            if (obj == null)
            {
                return false;
            }

            var echipa = (Echipa) obj;
            return (Id == echipa.Id) && (Nume.Equals(echipa.Nume));
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
    }
}